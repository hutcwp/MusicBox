package com.hutcwp.myapplication

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 *  author : kevin
 *  date : 2022/9/18 12:47 AM
 *  description :
 */
class MusicPlayer(val context: Context) {


    val mediaPlayer = MediaPlayer()

    val durationMill = 200L //每个音直接的间隔

    val handler = Handler(Looper.getMainLooper())


    init {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

    }

    fun startPlay(track: Track) {
        turnPlay(track)
    }

    /**
     * 轮流播放每个音
     */
    private fun turnPlay(track: Track) {
        if (track.isFinish()) {
            return
        }

        playAudio(track.getCur())
        track.moveToNext()
        handler.postDelayed({
            turnPlay(track)
        }, track.duration)
    }


    private fun playAudio(audioFile: AudioFile) {
        Log.i("hutcwp", "playAudio: audioFile=$audioFile")
        if (audioFile.isZero()) {
            return
        }

        val descriptor = context.assets.openFd(audioFile.name)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(descriptor)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
//        mediaPlayer.setDataSource(context, uri)


    }
}