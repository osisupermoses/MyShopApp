package com.osisupermoses.myshopapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.databinding.ItemListLayoutBinding
import com.osisupermoses.myshopapp.models.Order
import com.osisupermoses.myshopapp.ui.activities.MyOrderDetailsActivity
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

class MyOrdersListAdapter(
    private val context: Context,
    private var list: ArrayList<Order>
    ): RecyclerView.Adapter<MyOrdersListAdapter.OrdersListViewHolder>() {

        inner class OrdersListViewHolder(private val itemBinding: ItemListLayoutBinding)
            : RecyclerView.ViewHolder(itemBinding.root) {
                fun bindItem(orderItem: Order) {
                    GlideLoader(context).loadUserPicture(orderItem.image, itemBinding.ivItemImage)
                    itemBinding.tvItemName.text = orderItem.title
                    itemBinding.tvItemPrice.text = "$${orderItem.total_amount}"

                    itemBinding.ibDeleteProduct.visibility = View.GONE

                    itemView.setOnClickListener {
                        val intent = Intent(context, MyOrderDetailsActivity::class.java)
                        intent.putExtra(Constants.EXTRA_MY_ORDER_DETAILS, orderItem)
                        context.startActivity(intent)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersListViewHolder {
        return OrdersListViewHolder(ItemListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrdersListViewHolder, position: Int) {
        val orderItem = list[position]
        holder.bindItem(orderItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}