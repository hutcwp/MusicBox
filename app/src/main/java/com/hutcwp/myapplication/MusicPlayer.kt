package com.hutcwp.myapplication

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.hutcwp.myapplication.music.Beat

/**
 *  author : kevin
 *  date : 2022/9/18 12:47 AM
 *  description :
 */
class MusicPlayer(private val context: Context) {


    private val mediaPlayer = MediaPlayer()
    private val handler = Handler(Looper.getMainLooper())


    init {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
    }

    fun startPlay(beat: Beat) {
        turnPlay(beat)
    }

    /**
     * 轮流播放每个音
     */
    private fun turnPlay(beat: Beat) {
        if (beat.isFinish()) {
            return
        }

        val sound = beat.getCur()
        playAudio(sound.audioFile)
        beat.moveToNext()
        handler.postDelayed({
            turnPlay(beat)
        }, sound.duration)
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
    }
}