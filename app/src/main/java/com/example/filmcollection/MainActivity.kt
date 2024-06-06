package com.example.filmcollection

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
    }

    fun gotoAut(view: View) {
        var ed = pref.edit()
        ed.putBoolean("IsAuthorized", false)
        ed.commit()

        val intent = Intent(this, Authorization::class.java)
        startActivity(intent)
    }
}