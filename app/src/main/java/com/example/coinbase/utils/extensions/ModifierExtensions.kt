package com.example.coinbase.utils.extensions

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView

fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isKeyboardOpen by remember { mutableStateOf(false) }
    val view = LocalView.current
    val focusManager = LocalFocusManager.current
    DisposableEffect(view) {

        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom

            isKeyboardOpen = keypadHeight > screenHeight * 0.15
            if (!isKeyboardOpen) focusManager.clearFocus()
        }
        view.viewTreeObserver?.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        }
    }
    this
}