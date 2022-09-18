package com.hutcwp.myapplication.music

/**
 *  author : kevin
 *  date : 2022/9/18 2:42 PM
 *  description : 拍子
 *  一个拍子中可以包含多个音
 */
data class Beat(var duration: Long = -1L, val soundList: List<Sound>) {

    var curIndex = 0


    fun updateDuration() {
        val unit = duration / soundList.size
        soundList.forEach {
            it.duration = unit
        }
    }


    fun getCur(): Sound {
        return soundList[curIndex]
    }

    fun isFinish(): Boolean {
        return curIndex > soundList.lastIndex
    }

    fun hasNext(): Boolean {
        return (curIndex < soundList.lastIndex)
    }

    fun getNext(): Sound {
        return soundList[++curIndex]
    }

    fun moveToNext() {
        ++curIndex
    }

}