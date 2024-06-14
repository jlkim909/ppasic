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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hodak.ppasic.core.designsystem.icon.HoIcons
import com.hodak.ppasic.core.designsystem.theme.HoTheme

@Composable
fun HoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun HoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    HoButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        HoButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

@Composable
private fun HoButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }

    Box(
        Modifier.padding(
            start = if (leadingIcon != null) {
                ButtonDefaults.IconSpacing
            } else {
                0.dp
            },
        ),
    ) {
        text()
    }
}

@Composable
fun HoOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = HoButtonDefaults.OutlinedButtonBorderWidth,
            color = if (enabled) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = HoButtonDefaults.DISABLED_OUTLINED_BUTTON_BORDER_ALPHA,
                )
            },
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun HoOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    HoOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        HoButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

@Composable
fun HoTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        content = content,
    )
}

@Composable
fun HoTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    HoTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        HoButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

@ThemePreviews
@Composable
fun HoButtonPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoButton(onClick = {}, text = { Text("Test Button") })
        }
    }
}

@ThemePreviews
@Composable
fun HoButtonLeadingIconPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoButton(
                onClick = {},
                text = { Text("Test Button") },
                leadingIcon = { Icon(imageVector = HoIcons.Add, contentDescription = null) },
            )
        }
    }
}

@ThemePreviews
@Composable
fun HoOutlinedButtonPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoOutlinedButton(onClick = {}, text = { Text("Test Button") })
        }
    }
}

@ThemePreviews
@Composable
fun HoOutlinedButtonLeadingIconPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoOutlinedButton(
                onClick = {},
                text = { Text("Test Button") },
                leadingIcon = { Icon(imageVector = HoIcons.Add, contentDescription = null) },
            )
        }
    }
}

@ThemePreviews
@Composable
fun HoTextButtonPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoTextButton(onClick = {}, text = { Text("Test Button") })
        }
    }
}

@ThemePreviews
@Composable
fun HoTextButtonLeadingIconPreview() {
    HoTheme {
        HoBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            HoTextButton(
                onClick = {},
                text = { Text("Test Button") },
                leadingIcon = { Icon(imageVector = HoIcons.Add, contentDescription = null) },
            )
        }
    }
}

object HoButtonDefaults {
    const val DISABLED_OUTLINED_BUTTON_BORDER_ALPHA = 0.12f
    val OutlinedButtonBorderWidth = 1.dp
}
