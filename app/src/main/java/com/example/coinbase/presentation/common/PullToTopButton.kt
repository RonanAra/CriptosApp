package com.example.coinbase.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun PullToTopButton(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    icon: ImageVector? = null,
    onCLick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onCLick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color.DarkGray,
            containerColor = Color.White
        )
    ) {
        Icon(
            imageVector = icon ?: Icons.Filled.ArrowUpward,
            contentDescription = contentDescription
        )
    }
}