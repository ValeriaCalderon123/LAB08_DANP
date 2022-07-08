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
        Log.e("ERRORRRRRRR", "Llega al 2 activity")
        super.onNewIntent(intent)
        Log.e("ERRORRRRRRR", "Llega al 2 activity")
        val bundle: Bundle? = intent!!.extras
        Log.e("ERRORRRRRRR", bundle.toString())
        if (bundle != null) {
            Log.e("ERRORRRRRRR", "Llega al 2 activity")
            val msg = bundle.getString("KEY_1")
            Log.e("ERRORRRRRRR", "$msg")
            Toast.makeText(this, "$msg", Toast.LENGTH_LONG).show()
        }
    }
}