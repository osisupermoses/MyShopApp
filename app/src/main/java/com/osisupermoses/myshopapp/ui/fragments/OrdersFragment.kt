package com.osisupermoses.myshopapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.osisupermoses.myshopapp.R
import com.osisupermoses.myshopapp.databinding.FragmentOrdersBinding
import com.osisupermoses.myshopapp.firestore.FirestoreClass
import com.osisupermoses.myshopapp.models.Order
import com.osisupermoses.myshopapp.ui.adapters.MyOrdersListAdapter

class OrdersFragment : BaseFragment() {

//    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentOrdersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun populateOrdersListIntoUI(ordersList: ArrayList<Order>) {
        hideProgressDialog()

        if (ordersList.size > 0) {
            binding.tvNoOrdersFound.visibility = View.GONE
            binding.rvMyOrderItems.visibility = View.VISIBLE

            binding.rvMyOrderItems.layoutManager = LinearLayoutManager(activity)
            binding.rvMyOrderItems.setHasFixedSize(true)

            val myOrdersAdapter = MyOrdersListAdapter(requireActivity(), ordersList)
            binding.rvMyOrderItems.adapter = myOrdersAdapter
        } else {
            binding.tvNoOrdersFound.visibility = View.VISIBLE
            binding.rvMyOrderItems.visibility = View.GONE
        }
    }

    /**
     * A function to get the list of my orders.
     */
    private fun getMyOrderList() {
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getMyOrdersList(this@OrdersFragment)
    }

    override fun onResume() {
        super.onResume()

        getMyOrderList()
    }
}