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

package com.hodak.ppasic.core.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hodak.ppasic.core.designsystem.icon.HoIcons
import com.hodak.ppasic.core.designsystem.theme.HoTheme

@Composable
fun RowScope.HoNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = HoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = HoNavigationDefaults.navigationContentColor(),
            selectedTextColor = HoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = HoNavigationDefaults.navigationContentColor(),
            indicatorColor = HoNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun HoNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = HoNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun HoNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = HoNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = HoNavigationDefaults.navigationContentColor(),
            selectedTextColor = HoNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = HoNavigationDefaults.navigationContentColor(),
            indicatorColor = HoNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun HoNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = HoNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@ThemePreviews
@Composable
fun HoNavigationBarPreview() {
    val items = listOf("요리", "냉장고", "레시피", "장바구니")
    val icons = listOf(
        HoIcons.Home,
        HoIcons.FridgeOutline,
        HoIcons.RecipeOutline,
        HoIcons.CartOutline,
    )
    val selectedIcons = listOf(
        HoIcons.Home,
        HoIcons.Fridge,
        HoIcons.Recipe,
        HoIcons.Cart,
    )

    HoTheme {
        HoNavigationBar {
            items.forEachIndexed { index, item ->
                HoNavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index].resourceId),
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = selectedIcons[index].resourceId),
                            contentDescription = item,
                        )
                    },
                    label = { Text(text = item) },
                    selected = index == 0,
                    onClick = {},
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun HoNavigationRailPreview() {
    val items = listOf("요리", "냉장고", "레시피", "장바구니")
    val icons = listOf(
        HoIcons.Home,
        HoIcons.FridgeOutline,
        HoIcons.RecipeOutline,
        HoIcons.CartOutline,
    )
    val selectedIcons = listOf(
        HoIcons.Home,
        HoIcons.Fridge,
        HoIcons.Recipe,
        HoIcons.Cart,
    )

    HoTheme {
        HoNavigationRail {
            items.forEachIndexed { index, item ->
                HoNavigationRailItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index].resourceId),
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = selectedIcons[index].resourceId),
                            contentDescription = item,
                        )
                    },
                    label = { Text(text = item) },
                    selected = index == 0,
                    onClick = {},
                )
            }
        }
    }
}

object HoNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}