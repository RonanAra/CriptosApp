package com.example.coinbase.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coinbase.R

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss.invoke() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm.invoke() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    backgroundColor = Color.DarkGray
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 4.dp
                )
            ) {
                Text(text = stringResource(id = R.string.error_dialog_btn_confirm))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.error_dialog_title))
        },
        text = { Text(text = message) },
        icon = {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    ErrorDialog(
        message = "Erro Desconhecido!",
        onConfirm = {},
        onDismiss = {}
    )
}