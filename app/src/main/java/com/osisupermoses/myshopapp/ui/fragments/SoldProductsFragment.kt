package com.osisupermoses.myshopapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.osisupermoses.myshopapp.R
import com.osisupermoses.myshopapp.databinding.FragmentDashboardBinding
import com.osisupermoses.myshopapp.databinding.FragmentSoldProductsBinding
import com.osisupermoses.myshopapp.firestore.FirestoreClass
import com.osisupermoses.myshopapp.models.SoldProduct
import com.osisupermoses.myshopapp.ui.adapters.SoldProductsListAdapter

class SoldProductsFragment : BaseFragment() {
    private lateinit var binding: FragmentSoldProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSoldProductsBinding.inflate(inflater, container, false)

        return binding.root
    }

    fun successSoldProductsList(soldProductsList: ArrayList<SoldProduct>) {
        hideProgressDialog()

        if (soldProductsList.size > 0) {
            binding.rvSoldProductItems.visibility = View.VISIBLE
            binding.tvNoSoldProductsFound.visibility = View.GONE

            binding.rvSoldProductItems.layoutManager = LinearLayoutManager(activity)
            binding.rvSoldProductItems.setHasFixedSize(true)

            val soldProductsAdapter = SoldProductsListAdapter(requireActivity(), soldProductsList)
            binding.rvSoldProductItems.adapter = soldProductsAdapter

        } else {
            binding.rvSoldProductItems.visibility = View.GONE
            binding.tvNoSoldProductsFound.visibility = View.VISIBLE
        }
    }

    private fun getSoldProductsList() {
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getSoldProductsList(this@SoldProductsFragment)
    }

    override fun onResume() {
        super.onResume()

        getSoldProductsList()
    }

}