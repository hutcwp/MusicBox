package com.hutcwp.myapplication.music

import android.util.Log
import com.hutcwp.myapplication.strToBeat

/**
 *  author : kevin
 *  date : 2022/9/18 12:49 AM
 *  description :
 */
data class Track(
    val name: String,
    val speed: Int,
    val beatDuration: Long,
    val beats: List<Beat>
) {

    init {
        Log.i("hutcwp", "track[$name] 一共读到音符${beats.size}个。")

        updateBeatParams()
    }

    /**
     * 根据track设置的参数更新beat的参数
     */
    private fun updateBeatParams() {
        beats.forEach {
            if (it.duration <= 0L) {
                it.duration = beatDuration
                it.updateDuration()
            }
        }
    }


    companion object {
        const val SPLIT = " "
    }


    class Build() {

        var name: String = "track"

        var speed: Int = 100

        var beatDuration = 60 * 1000L / speed //单个拍子的时长


        var beatList = mutableListOf<Beat>()

        fun name(name: String): Build {
            this.name = name
            return this
        }

//        fun duration(duration: Long): Build {
//            this.duration = duration
//            return this
//        }

        fun addBeat(beatStr: String): Build {
            beatList.add(strToBeat(beatDuration, beatStr))
            return this
        }

        fun addBeats(beatStrArr: List<String>): Build {
            beatStrArr.forEach {
                this.addBeat(it)
            }
            return this
        }

        fun addBeatList(beats: List<Beat>): Build {
            beatList.addAll(beats)
            return this
        }

        private fun setBeatDuration(beatSpeed: Int) {
            this.beatDuration = 60 * 1000L / beatSpeed
        }

        fun beatSpeed(speed: Int): Build {
            this.speed = speed
            setBeatDuration(speed)

            return this
        }

        fun make(): Track {
            return Track(name, speed, beatDuration, beatList)
        }

    }


    fun log() {
        Log.i("hutcwp", "track: name=$name, speed=$speed, duration=$beatDuration")
    }
}