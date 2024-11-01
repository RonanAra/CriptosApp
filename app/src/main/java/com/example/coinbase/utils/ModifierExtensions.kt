package com.example.coinbase.utils

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusManager

fun Modifier.clearFocusOnKeyboardDismiss(
    activity: Activity,
    focusManager: FocusManager
): Modifier = composed {
    var isKeyboardOpen by remember { mutableStateOf(false) }

    DisposableEffect(activity) {
        val rootView = activity.window?.decorView?.rootView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView?.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView?.height ?: 0
            val keypadHeight = screenHeight - rect.bottom

            isKeyboardOpen = keypadHeight > screenHeight * 0.15
            if (!isKeyboardOpen) focusManager.clearFocus()
        }
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener(listener)

        onDispose {
            rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
        }
    }
    this
}