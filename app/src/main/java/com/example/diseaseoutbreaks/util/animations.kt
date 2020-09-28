package com.example.diseaseoutbreaks.util

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController

fun animate(context: Context, resId: Int): LayoutAnimationController {
    return AnimationUtils.loadLayoutAnimation(context, resId)
}

fun rotateAndFadeIn(context: Context, resId: Int): Animation {
    return AnimationUtils.loadAnimation(context, resId)
}