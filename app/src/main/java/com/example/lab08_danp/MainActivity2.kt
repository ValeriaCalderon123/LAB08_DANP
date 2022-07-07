package com.example.lab08_danp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        onNewIntent(intent)


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val bundle: Bundle? = intent!!.extras
        if (bundle != null) {
            val msg = bundle?.getString("KEY 1")
            Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
        }
    }
}