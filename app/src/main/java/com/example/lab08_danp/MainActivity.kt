package com.example.lab08_danp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.internal.NavigationMenu
import com.google.firebase.messaging.FirebaseMessaging
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private companion object {
        private const val CHANNEL_ID = "channel01"
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //notification()
        datos()
        val notificationbutton: Button = findViewById(R.id.button)
        notificationbutton.setOnClickListener {
            sNotification()
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sNotification() {
        createNotificationChannel()
        val date = Date()
        val notificacionID = SimpleDateFormat("ddHHmmss", Locale.US).format(date).toInt()

        //abrir otra actividad al presionar
        val intentM = Intent(this, MainActivity2::class.java)
        intentM.putExtra("KEY_1", "JOMA")
        intentM.putExtra("KEY_2", "Enviando datos")
        intentM.putExtra("KEY_3", "Todo tipo de datos nuevos")
        intentM.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent =
            PendingIntent.getActivity(this, 1, intentM, PendingIntent.FLAG_ONE_SHOT)

        //abrir actividad al hacer click en accion 1
        val intentAccion1 = Intent(this, MainActivity2::class.java)
        intentAccion1.putExtra("KEY_1", "ACCION 1")
        intentAccion1.putExtra("KEY_2", "Enviando datos")
        intentAccion1.putExtra("KEY_3", "Todo tipo de datos nuevos")
        val mainPendingIntentA1 =
            PendingIntent.getActivity(this, 2, intentAccion1, PendingIntent.FLAG_ONE_SHOT)


        // abrir actividad al hacer click en accion 2
        val intentAccion2 = Intent(this, MainActivity2::class.java)
        intentAccion2.putExtra("KEY_1", "ACCION 2")
        intentAccion2.putExtra("KEY_2", "Enviando datos")
        intentAccion2.putExtra("KEY_3", "Todo tipo de datos nuevos")
        val mainPendingIntentA2 =
            PendingIntent.getActivity(this, 3, intentAccion2, PendingIntent.FLAG_ONE_SHOT)


        // codigo de construccion
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.drawable.ic_notif)
        notificationBuilder.setContentTitle("Animales en peligro")
        notificationBuilder.setContentText("Notificacion de aviso para verificar a un nuevo animal en peligro de extincion")
        notificationBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        notificationBuilder.setAutoCancel(true)

        //CODIGO QUE INDICA QUE HACER AL CLICKEAR LA NOTIFICACION
        notificationBuilder.setContentIntent(mainPendingIntent)

        //CODIGO PARA CREACION Y FUNCION DE "ACCION 1"
        notificationBuilder.addAction(R.drawable.ic_notif, "Accion 1", mainPendingIntentA1)

        //CODIGO PARA CREACION Y FUNCION DE "ACCION 2"
        notificationBuilder.addAction(R.drawable.ic_notif, "Accion 2", mainPendingIntentA2)


        val notificationManagerCompact = NotificationManagerCompat.from(this)
        notificationManagerCompact.notify(notificacionID, notificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "MyNotification"
            val description = "Es una notificaicon"

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel(CHANNEL_ID, name, importance)
            notificationChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)


        }
    }

    private fun datos() {
        val titulo: String? = intent.getStringExtra("titulo")
        val mensaje: String? = intent.getStringExtra("mensaje")
        val imagen: String? = intent.getStringExtra("imagen")

        val textTitle: TextView = findViewById(R.id.title)
        val textMessage: TextView = findViewById(R.id.message)
        val vimagen: ImageView = findViewById(R.id.imageView)

        textTitle.text = titulo
        textMessage.text = mensaje
        Glide.with(this).load(imagen).into(vimagen)

    }

    private fun notification() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = token
            Log.e(TAG, token)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }
}