package com.sriyank.kovid19.UI

import android.app.DatePickerDialog
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
import java.util.*

class ContinueRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_continue_register)

        val calendar= Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        BOD.setOnClickListener {

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->
                BOD.setText("" + dayOfMonth + " - " + (monthOfYear+1) + " - " + year)
            }, year, month, day)
            //currentTimeMillis
            datePickerDialog.show()
        }


        registerBtn.setOnClickListener { RegisterData() }

        backBtn.setOnClickListener {onBackPressed()}

    }

    private fun RegisterData(){

        // data we send in the request: Email and password
        val params = JSONObject()

        params.put("patient_first_name", intent.getStringExtra("FirstName"))
        params.put("patient_last_name", intent.getStringExtra("LastName"))
        params.put("patient_age", age.text.toString())
        params.put("patinet_address", intent.getStringExtra("address"))
        params.put("patient_phone", phoneNum.text.toString())
        params.put("patient_email", intent.getStringExtra("email"))
        params.put("patient_date_of_birth", BOD.text.toString())
        params.put("patient_SSN", intent.getStringExtra("snn"))
        params.put("patient_password", password.text.toString())


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

                    // goto Login activity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

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