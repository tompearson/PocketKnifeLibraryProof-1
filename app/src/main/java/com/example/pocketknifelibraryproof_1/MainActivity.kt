package com.example.pocketknifelibraryproof_1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary1.getNetworkStatus
import com.example.mylibrary2.methodWithPermissions
import com.example.mylibrary3.getMACAddress
import com.example.mylibrary4.isItRooted
import com.example.mylibrary5.getLocationStatus
import com.example.mylibrary6.getBlueToothStatus
import com.example.mylibrary7.MapsActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.Flags
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.utils.async.AppCenterConsumer


class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    lateinit var button: Button
    lateinit var crash_button: Button

    // builder (string) are parameters to the libraries and returned with text
    private val builder = StringBuilder()

    // properties are used for AppCenter analytics
    private val properties: Map<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, getString(R.string.onCreate), Toast.LENGTH_SHORT).show()

        /* Setup buttons */
        button = findViewById(R.id.button_Map_id)
        button.setText(R.string.title_map_button)

        crash_button = findViewById(R.id.button_crash_id)
        crash_button.setText(R.string.button_crash_text)

        textMessage = findViewById(R.id.message)
        textMessage.setTextColor(Color.BLACK)
        /* End Setup buttons */

        /***** Setup Microsoft AppCenter *****/
        AppCenter.start(
            application, "20dd1e0b-3ec2-421d-8d1b-8e9d88643aa8",
            Analytics::class.java, Crashes::class.java, Distribute::class.java
        )
        // Use AppCenter Distribute to:
        //  Enable for debug builds
        //  Check for update
        //  In app updates

        // Enable Distribute
        Distribute.setEnabled(true)

        // Releases are true by default but debug is not
        Distribute.setEnabledForDebuggableBuild(true)
        /* App updates occur when:
           A higher value of versionCode or
           An equal value of versionCode but a different value of versionName */

        //  AppCenter can log previous crashes so we test this with a crash button in the app and handler here
        val future = Crashes.hasCrashedInLastSession()
        future.thenAccept(AppCenterConsumer {
            if (it) {
                Analytics.trackEvent(getString(R.string.crash_detected), properties, Flags.CRITICAL)
                Toast.makeText(this, getText(R.string.crash_oops), Toast.LENGTH_SHORT).show()
            }
        })
        /***** End Setup Microsoft AppCenter *****/

        /* Ready to call the libraries */
        methodWithPermissions(this)

        textMessage.setText(getNetworkStatus(this, builder))
        textMessage.setText(getLocationStatus(this, builder))
        textMessage.setText(getBlueToothStatus(this, builder))
        textMessage.setText(getMACAddress(this, builder))
        textMessage.setText(isItRooted(this, builder))
        /* End Ready to call the libraries */

        /* The button handlers */
        crash_button.setOnClickListener {
            Analytics.trackEvent(
                getString(R.string.button_crashing_clicked),
                properties,
                Flags.CRITICAL
            )
            Crashes.generateTestCrash()
        }
//        Map feature
        button.setOnClickListener {
            Analytics.trackEvent(getString(R.string.button_map_clicked), properties, Flags.NORMAL);
            val intent: Intent = Intent(this, MapsActivity::class.java).apply {}
            startActivity(intent)
        }
        /* End The button handlers */
    }

    @SuppressLint("ShowToast")
    override fun onStop() {
        super.onStop()
        Analytics.trackEvent(getString(R.string.onStop), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onStop), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ShowToast")
    override fun onRestart() {
        super.onRestart()
        Analytics.trackEvent(getString(R.string.onRestart), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onRestart), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ShowToast")
    override fun onResume() {
        super.onResume()
        Analytics.trackEvent(getString(R.string.onResume), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onResume), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ShowToast")
    override fun onStart() {
        super.onStart()
        Analytics.trackEvent(getString(R.string.onStart), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onStart), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("ShowToast")
    override fun onPause() {
        super.onPause()
        Analytics.trackEvent(getString(R.string.onPause), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onPause), Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        Analytics.trackEvent(getString(R.string.onDestroy), properties, Flags.NORMAL)
        Toast.makeText(this, getString(R.string.onDestroy), Toast.LENGTH_SHORT).show()
    }
}