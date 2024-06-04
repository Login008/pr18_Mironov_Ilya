package com.example.filmcollection

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import com.google.gson.stream.JsonReader

class Authorization : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
        lateinit var login : EditText
        lateinit var password : EditText
    private val MY_SETTINGS = "my_settings"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        login = findViewById<EditText>(R.id.editTextLogin)
        password = findViewById<EditText>(R.id.editTextTextPassword)
    }

    fun SignIn(view: View) {
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
        var pass = pref.getString("${login.text}", """{"login": "", "pass": ""}""")

        data class User(var login: String, var pass: String)
        val user : User = Gson().fromJson(pass, User::class.java)

        if (login.text.toString().isNotEmpty() and password.text.toString().isNotEmpty()) {
            if (password.text.toString() == user.pass) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Неверный логин или пароль")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
        }
        else
        {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("У вас есть незаполненные поля")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }
    }

    fun gotoregister(view: View) {
        val intent = Intent(this, registration::class.java)
        startActivity(intent)
    }
}