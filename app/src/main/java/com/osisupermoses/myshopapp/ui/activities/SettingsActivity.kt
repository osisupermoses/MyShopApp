package com.osisupermoses.myshopapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.osisupermoses.myshopapp.R
import com.osisupermoses.myshopapp.databinding.ActivitySettingsBinding
import com.osisupermoses.myshopapp.firestore.FirestoreClass
import com.osisupermoses.myshopapp.models.User
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

class SettingsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySettingsBinding

    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        binding.tvEdit.setOnClickListener(this)
        binding.btnLogout.setOnClickListener (this)
        binding.llAddress.setOnClickListener(this)
    }

    // Create a function to setup actionbar.
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(binding.toolbarSettingsActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        binding.toolbarSettingsActivity.setNavigationOnClickListener { onBackPressed() }
    }

    // Create a function to get the user details from firestore.
    /**
     * A function to get the user details from firestore.
     */
    private fun getUserDetails() {

        // Show the progress dialog
        showProgressDialog(resources.getString(R.string.please_wait))

        // Call the function of Firestore class to get the user details from firestore which is already created.
        FirestoreClass().getUserDetails(this@SettingsActivity)
    }

    // Create a function to receive the success result.
    /**
     * A function to receive the user details and populate it in the UI.
     */
    @SuppressLint("SetTextI18n")
    fun userDetailsSuccess(user: User) {

        mUserDetails = user

        // Set the user details to UI.
        // Hide the progress dialog
        hideProgressDialog()

        // Load the image using the Glide Loader class.
        GlideLoader(this@SettingsActivity).loadUserPicture(user.image, binding.ivUserPhoto)

        binding.tvName.text = "${user.firstName} ${user.lastName}"
        binding.tvGender.text = user.gender
        binding.tvEmail.text = user.email
        binding.tvMobileNumber.text = "${user.mobile}"
    }

    // Override the onResume function and call the getUserDetails function init.
    override fun onResume() {
        super.onResume()

        getUserDetails()
    }

    override fun onClick(v: View?) {
        if (v != null)
            when (v.id) {
                // Call the User Profile Activity to add the Edit Profile feature to the app. Pass the user details through intent.
                R.id.tv_edit -> {
                    val intent = Intent(this@SettingsActivity, UserProfileActivity::class.java)
                    intent.putExtra(Constants.EXTRA_USER_DETAILS, mUserDetails)
                    startActivity(intent)
                }

                R.id.btn_logout -> {

                    FirebaseAuth.getInstance().signOut()

                    val intent = Intent(this@SettingsActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }

                R.id.ll_address -> {
                    startActivity(Intent(this@SettingsActivity, AddressListActivity::class.java))
                }
            }
    }
}
