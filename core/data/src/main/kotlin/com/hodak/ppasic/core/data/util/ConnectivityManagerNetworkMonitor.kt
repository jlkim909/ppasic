/*
 * Copyright 2024 hodak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hodak.ppasic.core.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest.Builder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

// 1. ConnectivityManager 가져오기: context.getSystemService<ConnectivityManager>()를 사용하여
//      ConnectivityManager 객체를 가져온다.
// 2. 콜백 객체 생성: NetworkCallback을 구현한 callback 객체를 생성하여 네트워크 상태 변화를 처리.
// 3. 네트워크 요청 생성: Builder를 사용하여 인터넷 연결 능력을 요구하는 네트워크 요청(NetworkRequest)을 생성
// 4. 콜백 등록: connectivityManager.registerNetworkCallback(request, callback)을 호출하여
//      ConnectivityManager에 네트워크 상태 변화를 감지하도록 콜백을 등록
// 5. 초기 상태 전송: connectivityManager.isCurrentlyConnected()를 호출하여 현재 네트워크 연결 상태를 확인하고,
//      결과를 channel에 전송
internal class ConnectivityManagerNetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context,
) : NetworkMonitor {
    override val isOnline: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService<ConnectivityManager>()
        // 네트워크 모니터링을 위해 ConnectivityManager가 필수적이기 때문에,
        // 이 서비스가 제대로 초기화되지 않은 경우 적절히 처리하지 않으면 앱이 충돌할 수 있다.
        if (connectivityManager == null) {
            // channel은 callbackFlow에서 네트워크 상태를 전달하기 위해 사용되는 SendChannel
            channel.trySend(false)
            channel.close()
            return@callbackFlow
        }

        val callback = object : NetworkCallback() {
            private val networks = mutableSetOf<Network>()

            // 네트워크가 사용 가능해질 때 호출
            override fun onAvailable(network: Network) {
                networks += network
                channel.trySend(true)
            }

            // 네트워크가 손실되었을 때 호출
            override fun onLost(network: Network) {
                networks -= network
                channel.trySend(networks.isNotEmpty())
            }
        }

        // 빌더 객체는 네트워크 요청을 구성하기 위해 사용
        // 네트워크 요청에 특정 기능(네트워크 능력)을 추가
        // Wi-Fi, 모바일 데이터 등 인터넷에 접근할 수 있는 네트워크를 요청
        val request = Builder()
            // NET_CAPABILITY_INTERNET : 인터넷 연결을 요구하는 네트워크 요청을 의미
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        channel.trySend(connectivityManager.isCurrentlyConnected())

        //  awaitClose : 블록 내의 코드는 플로우가 더 이상 사용되지 않을 때 자원을 정리하는 역할
        awaitClose {
            // 네트워크 콜백을 등록 해제
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
        .conflate()

    @Suppress("DEPRECATION")
    private fun ConnectivityManager.isCurrentlyConnected() = when {
        VERSION.SDK_INT >= VERSION_CODES.M ->
            activeNetwork
                ?.let(::getNetworkCapabilities)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        else -> activeNetworkInfo?.isConnected
    } ?: false
}
