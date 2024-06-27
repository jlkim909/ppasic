package com.hodak.ppasic.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hodak.ppasic.ui.HoAppState

@Composable
fun HoNavHost(
    appState: HoAppState,
    modifier: Modifier = Modifier,
    startDestination: String = ""
){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){

    }
}