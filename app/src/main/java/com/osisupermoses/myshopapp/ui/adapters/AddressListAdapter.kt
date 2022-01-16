package com.osisupermoses.myshopapp.ui.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.databinding.ItemAddressLayoutBinding
import com.osisupermoses.myshopapp.models.Address
import com.osisupermoses.myshopapp.ui.activities.AddEditAddressActivity
import com.osisupermoses.myshopapp.ui.activities.CheckoutActivity
import com.osisupermoses.myshopapp.utils.Constants

open class AddressListAdapter(
    private val context: Context,
    private var list: ArrayList<Address>,
    private val selectAddress: Boolean
) : RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder>() {

        inner class AddressListViewHolder(private var itemBinding: ItemAddressLayoutBinding)
            : RecyclerView.ViewHolder(itemBinding.root) {

                @SuppressLint("SetTextI18n")
                fun bindItem (addressInfo: Address) {
                    itemBinding.tvAddressFullName.text = addressInfo.name
                    itemBinding.tvAddressType.text = addressInfo.type
                    itemBinding.tvAddressDetails.text = "${addressInfo.address}, ${addressInfo.zipCode}"
                    itemBinding.tvAddressMobileNumber.text = addressInfo.mobileNumber

                    if (selectAddress) {
                        itemView.setOnClickListener{
                            val intent = Intent(context, CheckoutActivity::class.java)
                            intent.putExtra(Constants.EXTRA_SELECTED_ADDRESS, list[adapterPosition])
                            context.startActivity(intent)
                        }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressListViewHolder {
        return AddressListViewHolder(
            ItemAddressLayoutBinding.inflate(LayoutInflater.from(
                parent.context), parent, false
            )
        )
    }

    fun notifyEditItem(activity: Activity, position: Int) {
        val intent = Intent(context, AddEditAddressActivity::class.java)
        intent.putExtra(Constants.EXTRA_ADDRESS_DETAILS, list[position])
        activity.startActivityForResult(intent, Constants.ADD_ADDRESS_REQUEST_CODE)
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: AddressListViewHolder, position: Int) {
        val addressItem = list[position]
        holder.bindItem(addressItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}