package com.osisupermoses.myshopapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.osisupermoses.myshopapp.ui.adapters.DashboardItemsListAdapter
import com.osisupermoses.myshopapp.R
import com.osisupermoses.myshopapp.databinding.FragmentDashboardBinding
import com.osisupermoses.myshopapp.firestore.FirestoreClass
import com.osisupermoses.myshopapp.models.Product
import com.osisupermoses.myshopapp.ui.activities.CartListActivity
import com.osisupermoses.myshopapp.ui.activities.SettingsActivity

class DashboardFragment : BaseFragment() {

//    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Override the onCreate function and add the piece of code to inflate the option menu in fragment.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // If we want to use the option menu in fragment we need to add it.
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()

        getDashboardItemsList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    // Override the onCreateOptionMenu function and inflate the Dashboard menu file init.
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Override the onOptionItemSelected function and handle the action items init.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {

            R.id.action_settings -> {

                // Launch the SettingActivity on click of action item.
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }

            R.id.action_cart -> {
                startActivity(Intent(activity, CartListActivity::class.java))
                return true

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun successDashboardItemsList(dashboardItemsList: ArrayList<Product>) {
        hideProgressDialog()

        if (dashboardItemsList.size > 0) {
            binding.rvDashboardItems.visibility = View.VISIBLE
            binding.tvNoDashboardItemsFound.visibility = View.GONE

            binding.rvDashboardItems.layoutManager = GridLayoutManager(activity, 2)
            binding.rvDashboardItems.setHasFixedSize(true)

            val dashboardAdapter = DashboardItemsListAdapter(requireActivity(), dashboardItemsList)
            binding.rvDashboardItems.adapter = dashboardAdapter

//            dashboardAdapter.setOnClickListener(object: DashboardItemsListAdapter.OnClickListener{
//                override fun onClick(position: Int, product: Product) {
//                    val intent = Intent(context, ProductDetailsActivity::class.java)
//                    intent.putExtra(Constants.EXTRA_PRODUCT_ID, product.product_id)
//                    intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, product.user_id)
//                    startActivity(intent)
//                }
//            })
        } else {
            binding.rvDashboardItems.visibility = View.GONE
            binding.tvNoDashboardItemsFound.visibility = View.VISIBLE
        }
    }

    private fun getDashboardItemsList() {
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getDashboardItemsList(this@DashboardFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}