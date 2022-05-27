package com.sriyank.kovid19.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        con.setOnClickListener {

            if(validate()) {

            val intent = Intent(this, ContinueRegister::class.java)
            intent.putExtra("FirstName", fname.text.toString())
            intent.putExtra("LastName", lname.text.toString())
            intent.putExtra("ssn", assn.text.toString())
            intent.putExtra("email", emailInput.text.toString())
            intent.putExtra("address", addressInputEditText.text.toString())

            startActivity(intent)
            }

        }

        backBtn.setOnClickListener { onBackPressed() }

        noAccountTv.setOnClickListener { onBackPressed() }

    }

    private fun validate(): Boolean {

        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        if (fname.text.toString().isNotEmpty() && lname.text.toString().isNotEmpty()
            && (assn.text.toString().isNotEmpty() && assn.text.toString().length == 14)
            && (emailInput.text.toString().trim().matches(emailPattern.toRegex()) &&
                    emailInput.text.toString().trim().isNotEmpty()) &&
            addressInputEditText.text.toString().isNotEmpty()
        ) {
            return true
        } else {

            if (!emailInput.text.toString().trim().matches(emailPattern.toRegex()))
                Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_LONG).show()
            else if (emailInput.text.toString().trim().isEmpty())
                Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show()

            if (fname.text.toString().isEmpty())
                Toast.makeText(this, "Enter your First Name", Toast.LENGTH_LONG).show()

            if (lname.text.toString().isEmpty())
                Toast.makeText(this, "Enter your Last Name", Toast.LENGTH_LONG).show()

            if (assn.text.toString().isEmpty())
                Toast.makeText(this, "Enter your SSN", Toast.LENGTH_LONG).show()
            else if (assn.text.toString().length != 14)
                Toast.makeText(this, "Enter your Valid SSN (14 Number)", Toast.LENGTH_LONG).show()

            if (addressInputEditText.text.toString().isEmpty())
                Toast.makeText(this, "Enter your Address", Toast.LENGTH_LONG).show()

            return false
        }
    }
}