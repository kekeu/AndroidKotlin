package com.dev.clevertonsantos.navigationapp.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.clearError() {
    this.error = null
    this.isErrorEnabled = false
}