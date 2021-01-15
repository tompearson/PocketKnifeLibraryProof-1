package com.example.mylibrary2


import android.Manifest
import android.content.Context
import android.widget.Toast
//import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions

lateinit var toast: Toast

// QuickPermissions-Kotlin
// The most easiest way to handle Android Runtime Permissions in Kotlin
// Summary
// It's super simple and super easy.
// Just wrap your code with runWithPermissions block and you are good to go by avoiding all the complexity android runtime permissions introduces.
// https://github.com/QuickPermissions/QuickPermissions-Kotlin

fun methodWithPermissions(mythis: Context) =
    mythis.runWithPermissions(Manifest.permission.ACCESS_FINE_LOCATION) {
//        mythis.runWithPermissions(Manifest.permission.WRITE_CALENDAR, Manifest.permission.RECORD_AUDIO)  {

        // Do the stuff with permissions safely
        // TO DO fix the asynchronicity of this so that it blocks until permission is granted
        // Update: runWithPermissions() is async by design. What to do.
        // https://github.com/QuickPermissions/QuickPermissions-Kotlin/blob/master/README.md
        toast = Toast.makeText(
            mythis,
            mythis.getString(R.string.loc_granted),
            Toast.LENGTH_SHORT
        )
        toast.show()
    }