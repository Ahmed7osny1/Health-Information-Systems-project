package com.sriyank.kovid19.HOME

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.sriyank.kovid19.APIAuth.MyConfig
import com.sriyank.kovid19.APIAuth.MyRequest
import com.sriyank.kovid19.APIAuth.MyRequestArray
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_continue_register.*
import kotlinx.android.synthetic.main.activity_continue_register.BOD
import kotlinx.android.synthetic.main.activity_vaccine_reservation.*
import org.json.JSONObject
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList

class VaccineReservation : AppCompatActivity() {

    private lateinit var TestSelected: String
    private lateinit var LocationSelected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine_reservation)


        backBtnVaccie.setOnClickListener { onBackPressed() }

        //Spinner for TESTS
        var items1 = getData()

        val adapter1 = ArrayAdapter(this, R.layout.drop_down_list_item, items1)
        textViewTest.setOnClickListener {
            //spinner Test
            spinnerVaccine.adapter = adapter1
            spinnerVaccine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TestSelected = items1[p2]
                    textViewTest.text = items1[p2]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        //spinner location
        val items2 = getDataCare()

        val adapter = ArrayAdapter(this, R.layout.drop_down_list_item, items2)
        textViewLocation.setOnClickListener {
            spinnerCare.adapter = adapter
            spinnerCare.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    LocationSelected = items2[p2]
                    textViewLocation.text = items2[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }


        time.setOnClickListener {

            val timePicker: TimePickerDialog = TimePickerDialog(
                this,
                timePicker(),
                12,
                10,
                false
            )

            timePicker.show()

        }

        Date.setOnClickListener { calenderShow() }

        confirm.setOnClickListener {

            if (time.text!!.isNotEmpty() && Date.text!!.isNotEmpty()) {
                ReserveApi(
                    TestSelected,
                    Date.text.toString(),
                    time.text.toString(),
                    LocationSelected
                )
            }
            else {


                if (Date.text!!.isEmpty())
                    Toast.makeText(this, "Please select Date", Toast.LENGTH_LONG).show()

                if (time.text!!.isEmpty())
                    Toast.makeText(this, "Please select Time", Toast.LENGTH_LONG).show()

            }
        }

    }

    private fun ReserveApi(type: String,Date: String,time: String,location: String) {

        val params = JSONObject()

        params.put("test_name", type)
        params.put("test_date", Date)
        params.put("test_time", time)
        params.put("test_patient_health_name", location)


        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.POST,
            "/test-reservation",
            params,
            { response ->

                Log.d("mytag", "response = $response")
                Toast.makeText(this@VaccineReservation,"$response",Toast.LENGTH_LONG).show()
                    // goto Home activity
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                // if there is an error (wrong email or password)
                if (response.has("error")) {
                    val errorMesssage = response.getString("error")
                    Toast.makeText(this@VaccineReservation, errorMesssage, Toast.LENGTH_SHORT)
                        .show()

                }
            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)

    }


    private fun calenderShow(){

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, year, monthOfYear, dayOfMonth ->
                Date.setText("$dayOfMonth  /  ${(monthOfYear + 1)}  /  $year")
            }, year, month, day
        )
        //currentTimeMillis
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis())
        datePickerDialog.show()

    }

    private fun timePicker() : TimePickerDialog.OnTimeSetListener {

        // listener which is triggered when the
        // time is picked from the time picker dialog
        val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =

            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                // logic to properly handle
                // the picked timings by user
                val formattedTime: String = when {
                    hourOfDay == 0 -> {
                        if (minute < 10) {
                            "${hourOfDay + 12}:0${minute} am"
                        } else {
                            "${hourOfDay + 12}:${minute} am"
                        }
                    }
                    hourOfDay > 12 -> {
                        if (minute < 10) {
                            "${hourOfDay - 12}:0${minute} pm"
                        } else {
                            "${hourOfDay - 12}:${minute} pm"
                        }
                    }
                    hourOfDay == 12 -> {
                        if (minute < 10) {
                            "${hourOfDay}:0${minute} pm"
                        } else {
                            "${hourOfDay}:${minute} pm"
                        }
                    }
                    else -> {
                        if (minute < 10) {
                            "${hourOfDay}:${minute} am"
                        } else {
                            "${hourOfDay}:${minute} am"
                        }
                    }
                }
                time.setText(formattedTime)
            }
        return timePickerDialogListener
    }

    private fun getData(): ArrayList<String> {

        var list = arrayListOf<String>()

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequestArray(
            this,
            Request.Method.GET,
            "/available-tests",
            null,
            { response ->

                Log.d("mytag", "$response")

                for (i in 0 until  response.length()){

                    val test: JSONObject = response.getJSONObject(i)

                    // get the current student (json object) data
                    list.add(test.getString("test-name"))

                }
                Log.d("mytag", "$list")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)

        return list
    }

    private fun getDataCare(): ArrayList<String> {

        val arrayList = arrayListOf<String>()

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequestArray(
            this,
            Request.Method.GET,
            "/avaiable-healthcare-centers",
            null,
            { response ->

                Log.d("mytag", "$response")

                for (i in 0 until  response.length()){

                    val student: JSONObject = response.getJSONObject(i)

                    // get the current student (json object) data
                    arrayList.add(student.getString("hc_name"))

                }
                Log.d("mytag", "$arrayList")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)
        return arrayList
    }

}