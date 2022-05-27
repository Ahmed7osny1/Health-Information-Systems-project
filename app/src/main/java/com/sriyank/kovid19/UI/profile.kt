package com.sriyank.kovid19.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.sriyank.kovid19.APIAuth.MyConfig
import com.sriyank.kovid19.APIAuth.MyRequest
import com.sriyank.kovid19.HOME.HomeActivity
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONObject

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backBtnProfile.setOnClickListener { onBackPressed() }

        appCompatButton.setOnClickListener { onBackPressed() }



        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.GET,
            "/profile",
            null,
            { response ->

                Log.d("mytag", "response = $response")
                val profile = response.getJSONObject("patient")

                yourName.text = "${profile.getString("pat_fname")} ${profile.getString("pat_lname")}"
                yourPhone.text = profile.getLong("pat_phone").toString()
                yourAddress.text = profile.getString("pat_address")
                DateBirth.text = profile.getString("pat_DOF")
                yourEmail.text = profile.getString("pat_email")

                // if there is an error (wrong email or password)
                if (response.has("error")) {
                    val errorMesssage = response.getString("error")
                    Toast.makeText(this, errorMesssage, Toast.LENGTH_SHORT).show()

                }
            },
            { error ->
                Log.e(
                    "mytag",
                    "Error: $error - Status Code = ${error.networkResponse?.statusCode}"
                )
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)

    }
}