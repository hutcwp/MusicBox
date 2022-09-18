package com.hutcwp.myapplication

import com.hutcwp.myapplication.music.Beat
import com.hutcwp.myapplication.music.Sound
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

/**
 * 将string转成一拍
 */
fun strToBeat(time: Long, str: String): Beat {
    val sounds = str.split(" ").map {
        Sound(-1, getAudioResFile(it.toInt()))
    }
    val t = time / sounds.size
    return Beat(t, sounds)
}

/**
 * 将整首track转成beat string集合
 */
fun beatStrArr2Beats(time: Long = -1, beats: List<String>): List<Beat> {
    return beats.map {
        strToBeat(time, it)
    }
}

fun getAudioResFile(num: Int): AudioFile {
    if (num in nums) {
        val fileName = num2name(num) + ".wav"
        val file = File("$basePath$fileName")
        return AudioFile(fileName, file)
    }

    throw Exception("无效的音符[$num]")
}