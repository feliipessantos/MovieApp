package com.example.movieapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityFormSingUpBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.ktx.Firebase

class FormSingUp : AppCompatActivity() {
    private lateinit var binding: ActivityFormSingUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormSingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")
        binding.editEmail.requestFocus()

        Firebase

        binding.btTryit.setOnClickListener {
            val email = binding.editEmail.text.toString()

            if (!email.isEmpty()) {
                binding.containerPassword.visibility = View.VISIBLE
                binding.btTryit.visibility = View.GONE
                binding.txtDescription1.visibility = View.VISIBLE
                binding.txtDescription2.visibility = View.GONE
                binding.btSingUp.visibility = View.VISIBLE
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
                binding.containerEmail.helperText = ""
                binding.containerHeader.visibility = View.VISIBLE
            } else {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "Email is required"
            }
        }
        binding.btSingUp.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val pass = binding.editPassword.text.toString()

            if (!email.isEmpty() && !pass.isEmpty()) {
                singUp(email, pass)
            } else if (pass.isEmpty()) {
                binding.containerPassword.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerPassword.helperText = "Password is requerid"
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
            } else if (email.isEmpty()) {
                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                binding.containerEmail.helperText = "email is requerid"
            }
        }

        binding.btSingInHeader.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
    }

    private fun singUp(emai: String, pass: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emai, pass)
            .addOnCompleteListener { singUp ->
                if (singUp.isSuccessful) {
                    Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
                    binding.containerEmail.helperText = ""
                    binding.containerPassword.helperText = ""
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF018786")
                    binding.containerPassword.boxStrokeColor = Color.parseColor("#FF018786")
                }
            }.addOnFailureListener {
            val error = it

            when {
                error is FirebaseAuthWeakPasswordException -> {
                    binding.containerPassword.helperText =
                        "Password should be more than 6 characters."
                    binding.containerPassword.boxStrokeColor = Color.parseColor("#FF0000")
                }
                error is FirebaseAuthUserCollisionException -> {
                    binding.containerEmail.helperText =
                        "An account already exists with this email address."
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                }
                error is FirebaseNetworkException -> {
                    binding.containerPassword.helperText = "No internet connection."
                    binding.containerPassword.boxStrokeColor = Color.parseColor("#FF0000")
                    binding.containerEmail.boxStrokeColor = Color.parseColor("#FF0000")
                }
                else -> {
                    Toast.makeText(this, "Error processing your registration.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}