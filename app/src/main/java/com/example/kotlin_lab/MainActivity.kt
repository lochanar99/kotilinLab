package com.example.kotlin_lab

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID=""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the
            importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as
                    NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }





    }
    fun ShowNotification(view: View){
        val name = findViewById<EditText>(R.id.txtName)
        val age = findViewById<EditText>(R.id.txtAge)
        Log.v("Name", name.text.toString())
        Log.v("Age",age.text.toString())
        var status = ""
        if(age.text.toString().toInt()<20){
            status="Group1"
        }else if(age.text.toString().toInt()<30){
            status="Group2"
        }else{
            status="Group 3"
        }
        Log.v("Message","Hello, "+name.text.toString()+"! Your group is "+status)
        addNotification(name.text.toString(),status)
    }

    private fun addNotification(pname:String,page:String){
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("My notification")
            .setContentText("Welcome to Notification App")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Hello, "+ pname+"! You are selected to the "+page))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationIntent = Intent(this,NotificationView::class.java)
        val contentIntent =
            PendingIntent.getActivity(this,0,notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(contentIntent);
        //add as notification
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as
                    NotificationManager
        notificationManager.notify(0,builder.build())
        Log.v("Notification","Came to the function")
    }
}
