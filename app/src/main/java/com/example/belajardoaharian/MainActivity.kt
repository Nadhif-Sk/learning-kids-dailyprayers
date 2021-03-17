package com.example.belajardoaharian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowManager.LayoutParams.*
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_about.*
import kotlinx.android.synthetic.main.dialog_view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnKeluar: ImageButton = findViewById(R.id.btn_exit)
        btnKeluar.setOnClickListener(this)

        val btnTentang: ImageButton = findViewById(R.id.btn_about)
        btnTentang.setOnClickListener(this)

        window.setFlags(
                FLAG_FULLSCREEN,
                FLAG_FULLSCREEN )
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        //pindah activity
        btn_start.setOnClickListener {
            val go = Intent (this@MainActivity, MenuDoa:: class.java)
                startActivity(go)
        }

    }
    // Buat tampilan dialog exit
    override fun onClick(v:View?){
        when (v?.id) {
            R.id.btn_exit -> {
                val view = View.inflate(this, R.layout.dialog_view, null)
                val builder = AlertDialog.Builder(this)
                builder.setView(view)

                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.setCancelable(true)

                dialog.btn_yes.setOnClickListener{
                    finish()
                }

                dialog.btn_no.setOnClickListener{
                    dialog.dismiss()
                }
            }

            R.id.btn_about -> {
                val view = View.inflate(this, R.layout.dialog_about,null)
                val builder = AlertDialog.Builder(this)
                builder.setView(view)

                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.setCancelable(true)

                dialog.btn_ok.setOnClickListener{
                    dialog.dismiss()
                }
            }
        }
    }

    //Ngebuat layar fullscreen
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}


