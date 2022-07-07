package com.example.lab08_danp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //notification()
        datos()

    }
    private fun datos(){
        val titulo:String? = intent.getStringExtra("titulo")
        val mensaje:String? = intent.getStringExtra("mensaje")
        val imagen:String? = intent.getStringExtra("imagen")

        val textTitle:TextView = findViewById(R.id.title)
        val textMessage:TextView = findViewById(R.id.message)
        val vimagen:ImageView = findViewById(R.id.imageView)

        textTitle.text=titulo
        textMessage.text = mensaje
        //vimagen.setImageResource(imagen)

    }
    private fun notification(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg =  token
            Log.e(TAG,token)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }
}