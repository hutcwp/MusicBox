package com.hutcwp.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hutcwp.myapplication.music.TrackFactory


class MainActivity : AppCompatActivity() {

    private val handle by lazy { Handler(Looper.myLooper()!!) }

    private val musicPlayer by lazy { MusicPlayer(baseContext) }
    private val musicPlayer2 by lazy { MusicPlayer(baseContext) }


    private val trackPlayer by lazy { TrackPlayer(musicPlayer) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
    }


    private fun initView() {
        findViewById<Button>(R.id.btn_play).setOnClickListener {
            setMusicData()
//            playTest()
        }
    }

    private fun playTest() {
        val mediaPlayer = MediaPlayer()
        val descriptor = assets.openFd("do.wav")

//        val descriptor = context.assets.openFd(audioFile.name)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            mediaPlayer.reset()
            mediaPlayer.setDataSource(descriptor)
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    private fun setMusicData() {
        val track = TrackFactory.build()
            .name("track1")
            .beatSpeed(68)
            .addBeatList(beatStrArr2Beats(-1L, MusicTrackConstants.QING_HUA_CI))
            .make()

        Log.i("hutcwp", "setMusicData: track=$track")

        trackPlayer.setBeats(track.beats)
        trackPlayer.prepare()
        trackPlayer.startPlay()

//        val track2 = Track("track2", MusicTrackConstants.ALWAYS_YOU_ACC, beatSpeed = 108)
//        musicPlayer2.startPlay(track2)

    }


    private fun initData() {

    }

}