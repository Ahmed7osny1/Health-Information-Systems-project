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
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_continue_register.*
import org.json.JSONObject

class ContinueRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_register)

        registerBtn.setOnClickListener { RegisterData() }

    }

    private fun RegisterData(){

        // data we send in the request: Email and password
        val params = JSONObject()

        params.put("patient_first_name", "Ahmed")
        params.put("patient_last_name", "lotfy")
        params.put("patient_age", 19)
        params.put("patient_address", "Benha")
        params.put("patient_phone", "01000000000")
        params.put("patient_email", "Ahmedhosny1@gmail.com")
        params.put("patient_date_of_birth", "14 7 2001")
        params.put("patient_SSN", "30207151401515")
        params.put("patient_password", "12345")

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.POST,
            "/register",
            params,
            { response ->

                Log.d("mytag", "response = $response")

                // if token was sent
                if(response.has("token")) {
                    // store token in shared prefs
                    val token = response.getString("token")
                    val prefs = getSharedPreferences(MyConfig.SHARED_PREFS_FILENAME, MODE_PRIVATE)
                    val prefsEditor = prefs.edit()
                    prefsEditor.putString("token", token)
                    prefsEditor.apply()
                    // goto profile activity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                // if there is an error (wrong email or password)
                if(response.has("error")){
                    val errorMesssage = response.getString("error")
                    Toast.makeText(this, errorMesssage, Toast.LENGTH_SHORT).show()

                }
            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
    }
}