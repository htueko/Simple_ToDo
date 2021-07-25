package com.htueko.simpletodo.common.util

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * Show a snackbar with [messageRes]
 */
fun View.showSnackBar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, messageRes, length).show()
}
