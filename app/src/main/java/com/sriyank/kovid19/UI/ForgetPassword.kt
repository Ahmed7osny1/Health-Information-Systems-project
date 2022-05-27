package com.sriyank.kovid19.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_register.*

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        repasslottie.visibility = View.VISIBLE

        send.setOnClickListener {

            var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

            if(!mEditEmail.text.toString().trim().matches(emailPattern.toRegex()) &&
                mEditEmail.text.toString().isEmpty()){

                if(!mEditEmail.text.toString().trim().matches(emailPattern.toRegex()))
                    Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_LONG).show()
                else if(mEditEmail.text.toString().isEmpty())
                    Toast.makeText(this, "Enter your Email", Toast.LENGTH_LONG).show()

            }
            else {
                startActivity(Intent(this, successfulSend::class.java))
            }

        }

    }
}