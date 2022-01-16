package com.osisupermoses.myshopapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

object Constants {
    // Firebase Constants
    // Collections in cloud firestore
    const val USERS: String = "users"
    const val PRODUCTS: String = "products"
    const val CART_ITEMS: String = "cartItems"
    const val ADDRESSES: String = "addresses"
    const val ORDERS: String = "orders"
    const val SOLD_PRODUCTS: String = "soldProducts"

    //Create a constant variables for Android SharedPreferences and Username Key.
    const val MYSHOPAPP_PREFERENCES: String = "MyShopAppPrefs"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"

    // Intent extra constants.
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val EXTRA_PRODUCT_ID: String = "extra_product_id"
    const val EXTRA_PRODUCT_OWNER_ID: String = "extra_product_owner_id"
    // Declare a constant to pass the value through intent in the address listing screen which will help to select the address to proceed with checkout.
    const val EXTRA_SELECT_ADDRESS: String = "extra_select_address"
    const val EXTRA_ADDRESS_DETAILS: String = "AddressDetails"
    // Declare the constant variable to pass the address details to the checkout screen through intent.
    const val EXTRA_SELECTED_ADDRESS: String = "extra_selected_address"
    const val EXTRA_MY_ORDER_DETAILS: String = "extra_MY_ORDER_DETAILS"
    const val EXTRA_SOLD_PRODUCT_DETAILS: String = "extra_sold_product_details"

    // Add a unique code for asking the Read Storage Permission.
    //A unique code for asking the Read Storage Permission using this we will be check and identify in the method onRequestPermissionsResult in the Base Activity.
    const val READ_STORAGE_PERMISSION_CODE = 2

    // Add a unique code to select the image from the storage. Using this code we will identify the image URI once it is selected.
    // A unique code of image selection from Phone Storage.
    const val PICK_IMAGE_REQUEST_CODE = 2

    // Constant variables for Gender
    const val MALE: String = "Male"
    const val FEMALE: String = "Female"

    // Firebase database field names
    const val FIRST_NAME: String = "firstName"
    const val LAST_NAME: String = "lastName"
    const val MOBILE: String = "mobile"
    const val GENDER: String = "gender"
    const val IMAGE: String = "image"
    const val COMPLETE_PROFILE: String = "profileCompleted"

    // Create a constant variable for User Profile Image.
    const val USER_PROFILE_IMAGE: String = "User_Profile_Image"
    const val PRODUCT_IMAGE: String = "Product_Image"

    const val USER_ID: String = "user_id"
    const val DEFAULT_CART_QUANTITY: String = "1"
    const val PRODUCT_ID: String = "product_id"

    const val CART_QUANTITY: String = "cart_quantity"
    const val STOCK_QUANTITY: String = "stock_quantity"

    // Add the constants for the address types.
    const val HOME: String = "Home"
    const val OFFICE: String = "Office"
    const val OTHER: String = "Other"

    // Declare a global constant variable to notify the add address.
    const val ADD_ADDRESS_REQUEST_CODE: Int = 121


    /**
     * A function to get the image file extension of the selected image.
     *
     * @param activity Activity reference.
     * @param uri Image file uri.
     */
    fun getFileExtension(activity: Activity, uri: Uri?): String? {
        /*
         * MimeTypeMap: Two-way map that maps MIME-types to file extensions and vice versa.
         *
         * getSingleton(): Get the singleton instance of MimeTypeMap.
         *
         * getExtensionFromMimeType: Return the registered extension for the given MIME type.
         *
         * contentResolver.getType: Return the MIME type of the given content URL.
         */
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))
    }

}