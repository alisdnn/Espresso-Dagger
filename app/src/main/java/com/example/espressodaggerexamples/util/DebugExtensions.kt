package com.example.espressodaggerexamples.util

import android.util.Log
import com.example.espressodaggerexamples.util.Constants.DEBUG
import com.example.espressodaggerexamples.util.Constants.TAG

fun printLogD(className: String?, message: String ) {
    if (DEBUG) {
        Log.d(TAG, "$className: $message")
    }
}