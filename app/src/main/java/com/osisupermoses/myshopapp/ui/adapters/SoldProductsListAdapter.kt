package com.osisupermoses.myshopapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.databinding.ItemListLayoutBinding
import com.osisupermoses.myshopapp.models.SoldProduct
import com.osisupermoses.myshopapp.ui.activities.SoldProductDetailsActivity
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

class SoldProductsListAdapter(
    private val context: Context,
    private var list: ArrayList<SoldProduct>
): RecyclerView.Adapter<SoldProductsListAdapter.SoldProductsListViewHolder>()  {

    inner class SoldProductsListViewHolder(private val itemBinding: ItemListLayoutBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {
            fun bindItem(soldItem: SoldProduct) {
                GlideLoader(context).loadUserPicture(soldItem.image, itemBinding.ivItemImage)
                itemBinding.tvItemName.text = soldItem.title
                itemBinding.tvItemPrice.text = "$${soldItem.total_amount}"

                itemBinding.ibDeleteProduct.visibility = View.GONE

                itemView.setOnClickListener {
                    val intent = Intent(context, SoldProductDetailsActivity::class.java)
                    intent.putExtra(Constants.EXTRA_SOLD_PRODUCT_DETAILS, soldItem)
                    intent.putExtra(Constants.EXTRA_SOLD_PRODUCT_DETAILS, soldItem)
                    context.startActivity(intent)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoldProductsListViewHolder {
        return SoldProductsListViewHolder(ItemListLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: SoldProductsListViewHolder, position: Int) {
        val soldItem = list[position]
        holder.bindItem(soldItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}