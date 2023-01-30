package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityFormLoginBinding


class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#000000")
        binding.editEmail.requestFocus()

        binding.btSingIn.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val pass = binding.editPassword.text.toString()

            when{
                email.isEmpty() -> {
                    binding.containerEmail.helperText = "Please enter yor e-mail address"
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF9800")

                }
                pass.isEmpty() -> {
                    binding.containerPassword.helperText = "Please enter your password"
                    binding.containerPassword.boxStrokeColor = Color.parseColor("#FF9800")
                }
            }
        }

        binding.txtSingUp.setOnClickListener{
            val intent = Intent(this, FormSingUp::class.java)
            startActivity(intent)
        }
    }
}