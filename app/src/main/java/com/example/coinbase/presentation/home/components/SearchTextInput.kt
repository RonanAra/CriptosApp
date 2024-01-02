package com.example.coinbase.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coinbase.R

@Composable
fun SearchTextInput(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        label = {
            Text(text = stringResource(R.string.explore))
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SearchTextInput(
        onValueChange = {}
    )
}