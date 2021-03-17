package com.example.belajardoaharian

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_doa_hujan.*
import kotlinx.android.synthetic.main.activity_doa_hujan.seek_bar
import kotlinx.android.synthetic.main.activity_doa_naik_kendaraan.*
import kotlinx.android.synthetic.main.activity_doa_sebelum_belajar.*

class DoaNaikKendaraan : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable: Runnable
    private var handler: Handler = Handler()
    private var pause: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa_naik_kendaraan)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN )

        // Kodingan Tombol Back trus Di iringi mati audio
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.doa_naik_kendaraan)
        btn_back_kendaraan.setOnClickListener {
            onBackPressed()
            if(mediaPlayer.isPlaying || pause.equals(true)) {
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)
            }
        }

        //Mulai media Player
        btn_play_kendaraan.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                Toast.makeText(this,"media playing", Toast.LENGTH_SHORT).show()
            } else{
                mediaPlayer = MediaPlayer.create(applicationContext,R.raw.doa_naik_kendaraan)
                mediaPlayer.start()
                Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
            }
            initializeSeekBar()
            btn_play_kendaraan.isEnabled = false
            btn_pause_kendaraan.isEnabled = true
            btn_stop_kendaraan.isEnabled = true

            mediaPlayer.setOnCompletionListener {
                btn_play_kendaraan.isEnabled = true
                btn_pause_kendaraan.isEnabled = false
                btn_stop_kendaraan.isEnabled = false
                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
            }
        }

        // Pause Media Player
        btn_pause_kendaraan.setOnClickListener{
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                btn_play_kendaraan.isEnabled = true
                btn_pause_kendaraan.isEnabled = false
                btn_stop_kendaraan.isEnabled = true
                Toast.makeText(this, "media pause", Toast.LENGTH_SHORT).show()
            }
        }

        //stop media player
        btn_stop_kendaraan.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)

                btn_play_kendaraan.isEnabled = true
                btn_pause_kendaraan.isEnabled = false
                btn_stop_kendaraan.isEnabled = false
                Toast.makeText(this,"media stop", Toast.LENGTH_SHORT).show()
            }
        }

        // Seek bar change listener
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

    }

    // Method to initialize seek bar and audio stats
    private fun initializeSeekBar() {
        seek_bar.max = mediaPlayer.seconds

        runnable = Runnable {
            seek_bar.progress = mediaPlayer.currentSeconds

            tv_pass_kendaraan.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            tv_due_kendaraan.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    // Creating an extension property to get the media player time duration in seconds
    val MediaPlayer.seconds: Int
        get() {
            return this.duration / 1000
        }
    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

    //Ngebuat layar fullscreen
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}