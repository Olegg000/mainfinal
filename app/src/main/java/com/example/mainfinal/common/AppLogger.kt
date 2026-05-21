package com.example.mainfinal.common

import android.util.Log

class AppLogger(val tag: String) {
    private val tagg = "AppLogger"

    fun i(act: String, m: String) {Log.i(tagg,"[$tag]: $act - $m")}
    fun d(act: String, m: String) {Log.d(tagg,"[$tag]: $act - $m")}
    fun e(act: String, m: String, e: Throwable? = null) {Log.e(tagg,"[$tag]: $act - $m", e)}
}