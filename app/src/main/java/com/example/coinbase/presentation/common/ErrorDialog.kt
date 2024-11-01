package com.example.coinbase.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coinbase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit = {}
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss
    ) {
        Card(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            border = BorderStroke(
                width = 8.dp,
                color = Color.White
            )
        ) {
            DialogContent(
                message = message,
                onConfirm = onConfirm
            )
        }
    }
}

@Composable
private fun DialogContent(
    message: String,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.ErrorOutline,
            contentDescription = null
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.error_dialog_title)
        )
        Spacer(Modifier.height(16.dp))
        Text(text = message)
        Spacer(Modifier.height(16.dp))
        TextButton(
            onClick = { onConfirm() },
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        ) {
            Text(text = stringResource(id = R.string.error_dialog_btn_confirm))
        }
    }
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