package com.hutcwp.myapplication

import java.io.File

/**
 *  author : kevin
 *  date : 2022/9/18 2:01 AM
 *  description :
 */
data class AudioFile(val name: String, val file: File) {

    fun isZero(): Boolean {
        return "0.wav" == name
    }
}