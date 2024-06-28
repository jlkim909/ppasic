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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hodak.ppasic.core.designsystem.icon.HoIcons
import com.hodak.ppasic.core.designsystem.theme.HoTheme

@Composable
fun HoIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = if (checked) {
                MaterialTheme.colorScheme.onBackground.copy(
                    alpha = HoIconButtonDefaults.DISABLED_ICON_BUTTON_CONTAINER_ALPHA,
                )
            } else {
                Color.Transparent
            },
        ),
    ) {
        if (checked) checkedIcon() else icon()
    }
}

@Composable
fun HoIconButtonWithBadge(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    badgeCount: Int = 0,
    icon: @Composable () -> Unit,
) {
    Box {
        IconButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = colors,
        ) {
            icon()
        }
        if (badgeCount > 0) {
            Badge(
                content = {
                    Text(
                        text = "$badgeCount",
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-4).dp, y = 4.dp),
            )
        }
    }
}

@ThemePreviews
@Composable
fun HoIconButtonTogglePreview() {
    HoTheme {
        HoIconToggleButton(
            checked = true,
            onCheckedChange = {},
            icon = {
                Icon(
                    painter = painterResource(HoIcons.Home.resourceId),
                    contentDescription = null,
                )
            },
            checkedIcon = {
                Icon(
                    painter = painterResource(HoIcons.Home.resourceId),
                    contentDescription = null,
                )
            },
        )
    }
}

@ThemePreviews
@Composable
fun HoIconButtonTogglePreviewUnchecked() {
    HoTheme {
        HoIconToggleButton(
            checked = false,
            onCheckedChange = {},
            icon = {
                Icon(
                    painter = painterResource(HoIcons.Home.resourceId),
                    contentDescription = null,
                )
            },
            checkedIcon = {
                Icon(
                    painter = painterResource(HoIcons.Home.resourceId),
                    contentDescription = null,
                )
            },
        )
    }
}

@ThemePreviews
@Composable
fun HoIconButtonWithBadgePreview() {
    HoTheme {
        HoIconButtonWithBadge(
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(HoIcons.Home.resourceId),
                    contentDescription = null,
                )
            },
            badgeCount = 3,
        )
    }
}

object HoIconButtonDefaults {
    const val DISABLED_ICON_BUTTON_CONTAINER_ALPHA = 0.12f
}
