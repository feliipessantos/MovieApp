package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.movieapp.databinding.ActivityFormSingUpBinding

class FormSingUp : AppCompatActivity() {
    private lateinit var binding: ActivityFormSingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormSingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")
        binding.editEmail.requestFocus()

        binding.btTryit.setOnClickListener {
            val email = binding.editEmail.text.toString()

            if(!email.isEmpty()){
                binding.containerPassword.visibility = View.VISIBLE
                binding.btTryit.visibility = View.GONE
                binding.txtDescription1.visibility = View.VISIBLE
                binding.txtDescription2.visibility = View.GONE
                binding.btSingUp.visibility = View.VISIBLE
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
                binding.containerEmail.helperText = ""
                binding.containerHeader.visibility = View.VISIBLE
            }else{
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "Email is required"
            }
        }
        binding.btSingUp.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val pass = binding.editPassword.text.toString()

            if(!email.isEmpty() && !pass.isEmpty()){
                Toast.makeText(this, "Sucess", Toast.LENGTH_SHORT).show()
            }else if(pass.isEmpty()) {
                binding.containerPassword.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerPassword.helperText = "Password is requerid"
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
            }else if (email.isEmpty()){
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "email is requerid"
            }
        }

        binding.btSingInHeader.setOnClickListener{
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
    }
}