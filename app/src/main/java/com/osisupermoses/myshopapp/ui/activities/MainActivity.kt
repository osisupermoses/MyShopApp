package com.osisupermoses.myshopapp.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osisupermoses.myshopapp.databinding.ActivityMainBinding
import com.osisupermoses.myshopapp.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the stored username from the Android SharedPreferences.
        // START
        // Create an instance of Android SharedPreferences
        val sharedPreferences =
            getSharedPreferences(Constants.MYSHOPAPP_PREFERENCES, Context.MODE_PRIVATE)

        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME, "")!!
        // Set the result to the tv_main.
        binding.tvMain.text= "Hello $username."
        // END
    }
}