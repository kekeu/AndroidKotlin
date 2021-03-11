package com.dev.clevertonsantos.mybeats.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.dev.clevertonsantos.mybeats.R

//private val navOptions = NavOptions.Builder()
//    .setEnterAnim(R.anim.slide_in_right)
//    .setExitAnim(R.anim.slide_out_left)
//    .setPopEnterAnim(R.anim.slide_in_left)
//    .setPopExitAnim(R.anim.slide_out_right)
//    .build()

fun NavController.navigateWithAnim(idAction: Int) {
//    this.navigate(idAction, null, navOptions)
    this.navigate(idAction)
}