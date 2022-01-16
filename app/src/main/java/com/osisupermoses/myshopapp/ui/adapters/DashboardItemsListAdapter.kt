package com.osisupermoses.myshopapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.databinding.ItemDashboardLayoutBinding
import com.osisupermoses.myshopapp.models.Product
import com.osisupermoses.myshopapp.ui.activities.ProductDetailsActivity
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

/**
 * A adapter class for dashboard items list.
 */
open class DashboardItemsListAdapter(
    private val context: Context,
    private var list: ArrayList<Product>
) : RecyclerView.Adapter<DashboardItemsListAdapter.DashboardViewHolder>() {

//    private var onClickListener: OnClickListener? = null

    inner class DashboardViewHolder(private val itemBinding: ItemDashboardLayoutBinding)
        :RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItem(product: Product) {
            GlideLoader(context).loadProductPicture(product.image, itemBinding.ivDashboardItemImage)
            itemBinding.tvDashboardItemTitle.text = product.title
            itemBinding.tvDashboardItemPrice.text = "$${product.price}"

            itemView.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_ID, product.product_id)
                intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, product.user_id)
                context.startActivity(intent)
            }
        }
    }

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
            return DashboardViewHolder(
                ItemDashboardLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
            )
    }

//    fun setOnClickListener(onClickListener: OnClickListener) {
//        this.onClickListener = onClickListener
//    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val product = list[position]

        holder.bindItem(product)
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return list.size
    }

//    interface OnClickListener {
//        fun onClick(position: Int, product: Product)
//    }
}