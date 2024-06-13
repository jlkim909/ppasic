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

package com.hodak.ppasic.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import com.hodak.ppasic.core.designsystem.R

object HoIcons {
    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val Cart = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_cart)
    val CartOutline = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_cart_outline)
    val Check = Icons.Rounded.Check
    val Close = Icons.Rounded.Close
    val Fridge = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_fridge)
    val FridgeOutline = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_fridge_outline)
    val Home = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_chef_hat)
    val Recipe = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_recipe)
    val RecipeOutline = Icon.DrawableResourceIcon(R.drawable.core_designsystem_ic_recipe_outline)
    val Search = Icons.Rounded.Search
    val Settings = Icons.Rounded.Settings
    val ViewDay = Icons.Default.MoreVert
}

sealed interface Icon {
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}