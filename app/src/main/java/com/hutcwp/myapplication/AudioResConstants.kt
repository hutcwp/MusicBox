package com.hutcwp.myapplication

import java.io.File

/**
 *  author : kevin
 *  date : 2022/9/18 12:57 AM
 *  description :
 */

var basePath = "file:///android_asset/"

val nums = mutableListOf(
    0,
    -1, -2, -3, -4, -5, -6, -7,
    1, 2, 3, 4, 5, 6, 7,
    +1, +2, +3, +4, +5, +6, +7
)

fun num2name(num: Int): String {
    return num.toString()
        .replace("1", "do")
        .replace("2", "re")
        .replace("3", "mi")
        .replace("4", "fa")
        .replace("5", "so")
        .replace("6", "la")
        .replace("7", "xi")
}

fun getAudioResFile(num: Int): AudioFile {
    if (num in nums) {
        val fileName = num2name(num) + ".wav"
        val file = File("$basePath$fileName")
        return AudioFile(fileName, file)
    }

    throw Exception("无效的音符[$num]")
}