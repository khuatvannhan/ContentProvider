package com.example.android.contentproviderexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun xyLyMoManHinhDocDanhBa(view: View) {
        val intent = Intent(this, DanhBaActivity::class.java)
        startActivity(intent)
    }
}
