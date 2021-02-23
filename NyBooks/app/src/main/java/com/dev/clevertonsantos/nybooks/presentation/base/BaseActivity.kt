package com.dev.clevertonsantos.nybooks.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {
    protected fun setUpToolbar(toolbar: Toolbar, titleIdRes: Int, showbackButton: Boolean = false) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        if (showbackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}