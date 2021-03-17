package com.example.belajardoaharian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_menu_doa.*

class MenuDoa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_doa)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN )

        btn_ortu.setOnClickListener{
            val go = Intent(this@MenuDoa, DoaOrangTua::class.java)
            startActivity(go)
        }

        btn_sebelum_makan.setOnClickListener{
            val go = Intent(this@MenuDoa, DoaSebelumMakan::class.java)
            startActivity(go)
        }

        btn_sesudah_makan.setOnClickListener {
            val go = Intent(this@MenuDoa, DoaSesudahMakan::class.java)
            startActivity(go)
        }

        btn_belajar.setOnClickListener{
            val go = Intent(this@MenuDoa, DoaSebelumBelajar::class.java)
            startActivity(go)
        }

        btn_hujan.setOnClickListener{
            val go = Intent(this@MenuDoa, DoaHujan::class.java)
            startActivity(go)
        }

        btn_kendaraan.setOnClickListener{
            val go = Intent(this@MenuDoa, DoaNaikKendaraan::class.java)
            startActivity(go)
        }

        btn_back_menu.setOnClickListener {
            onBackPressed()
        }
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