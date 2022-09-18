package com.hutcwp.myapplication

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.hutcwp.myapplication.music.Beat
import java.util.*

/**
 *  author : kevin
 *  date : 2022/9/18 3:23 PM
 *  description :
 */
class TrackPlayer(val musicPlayer: MusicPlayer) {

    val handler = Handler(Looper.getMainLooper())

    var _beats: List<Beat> = mutableListOf()

    var playBeats = LinkedList<Beat>()

    fun setBeats(beats: List<Beat>) {
        this._beats = beats
    }

    fun prepare() {
        if (_beats.isNullOrEmpty()) {
            Log.w("hutcwp", "beats为空。return")
            return
        }

        playBeats.addAll(_beats)
    }

    fun startPlay() {
        if (playBeats.isNullOrEmpty()) {
            return
        }

        val beat = playBeats.poll()!!
        musicPlayer.startPlay(beat)

        handler.postDelayed({
            startPlay()
        }, beat.duration)
    }

}