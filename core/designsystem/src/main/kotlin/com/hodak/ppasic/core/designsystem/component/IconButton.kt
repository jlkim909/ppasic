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

import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

@ThemePreviews
@Composable
fun IconButtonPreview() {
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
fun IconButtonPreviewUnchecked() {
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


object HoIconButtonDefaults {
    const val DISABLED_ICON_BUTTON_CONTAINER_ALPHA = 0.12f
}