package com.sriyank.kovid19.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sriyank.kovid19.R
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        con.setOnClickListener {
            val intent = Intent(baseContext, ContinueRegister::class.java)
            intent.putExtra("FirstName", fname.text.toString())
            intent.putExtra("LastName", lname.text.toString())
            intent.putExtra("ssn", assn.text.toString())
            intent.putExtra("email", emailInput.text.toString())
            intent.putExtra("address", addressInputEditText.text.toString())

            startActivity(intent)
        }

        backBtn.setOnClickListener{ onBackPressed() }

        noAccountTv.setOnClickListener { onBackPressed() }




    }

}