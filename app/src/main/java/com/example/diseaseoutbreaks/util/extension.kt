@file:Suppress("DEPRECATION")

package com.example.diseaseoutbreaks.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.diseaseoutbreaks.R
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Toast message
 * */
fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).apply {
        show()
    }
}

/**
 * SnackBar
 * */
fun View.snackbar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setBackgroundTint(resources.getColor(R.color.colorPrimary))
        snackbar.setAction("okay") {
            snackbar.dismiss()
        }
    }.show()
}


/**
 * Alert Dialog
 * */

fun Context.alertdialog(context: Context, msg: String, icon: Drawable, title: String) {
    val alertDialog = AlertDialog.Builder(context, R.style.MyAlertDialogTheme)

    alertDialog.setIcon(icon)
        .setTitle(title)
        .setMessage(msg)

    alertDialog.setPositiveButton(
        "okay"
    ) { dialog, which ->
        dialog.dismiss()
    }

    alertDialog.show()
}

/**
 * Hide keyboard
 * */
fun hideSoftKeyboard(activity: Activity) {
    if (activity.currentFocus == null) {
        return
    }
    val inputMethodManager =
        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
}

/**
 * Check if edit text is empty
 * */

fun isEditEmpty(string: String): Boolean {
    return string == ""
}

/**
 * Get Current Date and Time
 * */
@SuppressLint("SimpleDateFormat")
fun getCurrentDateTime(): String {
    val dateFormat: DateFormat? = SimpleDateFormat("dd MMM,yyyy HH:mm")
    val date = Date()
    return dateFormat!!.format(date)
}

/**
 * Check for internet connectivity
 * */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return (activeNetworkInfo != null && activeNetworkInfo.isConnected)
}

/**
 * Hide loading progress
 * */
fun hideLoadingProgress(view: View){
    view.apply {
        clearAnimation()
        visibility = View.GONE
    }
}

