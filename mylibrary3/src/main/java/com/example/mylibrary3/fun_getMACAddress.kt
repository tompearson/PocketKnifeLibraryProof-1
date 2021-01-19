package com.example.mylibrary3

import android.annotation.SuppressLint
import android.content.Context
import java.net.NetworkInterface
import java.util.*
@SuppressLint
internal lateinit var mActivity: Context

fun getMACAddress(mythis: Context, builder: StringBuilder): String {
    val res1 = StringBuilder()
    val all: Collection<NetworkInterface>

    try {
        mActivity = mythis
        // Get MAC address
        // get all the interfaces
        all = Collections.list(NetworkInterface.getNetworkInterfaces())
        //find network interface wlan0
        for (networkInterface in all) {
            if (!networkInterface.name.equals("wlan0", ignoreCase = true)) continue
            //get the hardware address (MAC) of the interface
            val macBytes = networkInterface.hardwareAddress
            if (macBytes == null) {
                //return "";
                return builder.append(mActivity.getString(R.string.no_mac_address))
                    .toString()
            }
            for (b in macBytes) {
                res1.append(String.format("%02X", b) + ":")
            }
            if (res1.isNotEmpty()) {
                res1.deleteCharAt(res1.length - 1)
                return builder.append(
                    mActivity.getString(R.string.mac_address), res1.toString()
                ).toString()
            }
        }
    } catch (ex: Exception) { // Handles the unknown
        return builder.append(
            mActivity.getString(R.string.mac_address),
            "GetMACAddress Exception " + ex.message,
            mActivity
        ).toString()
    }
    // if a MAC was found we've already returned
        return builder.append(
            mActivity.getString(R.string.mac_no_interface), ""
        ).toString()
}
