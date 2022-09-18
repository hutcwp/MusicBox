package com.hutcwp.myapplication

import android.util.Log

/**
 *  author : kevin
 *  date : 2022/9/18 12:49 AM
 *  description :
 */
class Track(
    val name: String,
    val content: String,
    val beatSpeed: Int,
    val duration: Long = 60 * 1000L / beatSpeed //一拍的时长
) {

    private lateinit var data: List<AudioFile>

    var curIndex = 0

    init {
        data = content.split(SPLIT).map {
            getAudioResFile(it.toInt())
        }

        Log.i("hutcwp", "track[$name] dur=${duration} 一共读到音符${data.size}个。")
    }

    fun getCur(): AudioFile {
        return data[curIndex]
    }

    fun isFinish(): Boolean {
        return curIndex > data.lastIndex
    }

    fun hasNext(): Boolean {
        return (curIndex < data.lastIndex)
    }

    fun getNext(): AudioFile {
        return data[++curIndex]
    }

    fun moveToNext() {
        ++curIndex
    }

    companion object {
        const val SPLIT = " "
    }

}