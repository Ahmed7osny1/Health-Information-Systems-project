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
import kotlinx.android.synthetic.main.activity_continue_register.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.backBtn
import kotlinx.android.synthetic.main.activity_login.noAccountTv
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        goHome.setOnClickListener { loginClicked() }

        backBtn.setOnClickListener {
            onBackPressed()
        }

        noAccountTv.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
        }

        forgetTv.setOnClickListener {
            startActivity(Intent(this,ForgetPassword::class.java))
        }

    }

    private fun loginClicked(){

        if(validateData()) {
            // data we send in the request: Email and password
            val params = JSONObject()
            params.put("email", mEditEmail.text.toString())
            params.put("password", mEditPassword.text.toString())

            Log.d("mytag", "Button clicked")

            // send request
            val queue = Volley.newRequestQueue(this)
            val request = MyRequest(
                this,
                Request.Method.POST,
                "/login",
                params,
                { response ->

                    Log.d("mytag", "response = $response")

                    // if token was sent
                    if (response.has("token")) {
                        // store token in shared prefs
                        val token = response.getString("token")
                        val prefs = getSharedPreferences(
                            MyConfig.SHARED_PREFS_FILENAME,
                            MODE_PRIVATE
                        )
                        val prefsEditor = prefs.edit()
                        prefsEditor.putString("token", token)
                        prefsEditor.apply()
                        // goto profile activity
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

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

    private fun validateData(): Boolean {

        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        if((mEditEmail.text.toString().trim().matches(emailPattern.toRegex()) &&
                    mEditEmail.text.toString().trim().isNotEmpty()) &&
            (mEditPassword.text.toString().isNotEmpty() && mEditPassword.text.toString().length >= 5))
            return true
        else {

            if (!mEditEmail.text.toString().trim().matches(emailPattern.toRegex()))
                Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_LONG).show()
            else if (mEditEmail.text.toString().trim().isEmpty())
                Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show()

            if(mEditPassword.text.toString().isEmpty())
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_LONG).show()
            else if (mEditPassword.text.toString().length < 5)
                Toast.makeText(this, "Enter valid Password > 9", Toast.LENGTH_LONG).show()

            return false

        }
    }
}