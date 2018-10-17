package com.mobiapp4u.pc.customadapter_kotlin

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var settingsCanWrite = Settings.System.canWrite(applicationContext)


        val status = ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(status == PackageManager.PERMISSION_GRANTED )
        {
            readFiles()
        }else
        {
            ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                111)

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            readFiles()
        }else
        {
            Toast.makeText(this@MainActivity,"Please Provide Permissions",Toast.LENGTH_LONG).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

     fun readFiles() {
//        var path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
         var path = "/storage/sdcard0/"

         var file = File(path)
        if(!file.exists())
        {
//            path = "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
            path = "/storage/emulated/0/"

            file = File(path)
        }
        val files = file.listFiles()
//         val files = file.list() ->for Array adapter Array<File>


         _listview.adapter = CustomAdapter(this@MainActivity,files)

    }
}
