package com.osisupermoses.myshopapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.databinding.ItemListLayoutBinding
import com.osisupermoses.myshopapp.models.Product
import com.osisupermoses.myshopapp.ui.activities.ProductDetailsActivity
import com.osisupermoses.myshopapp.ui.fragments.ProductsFragment
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

open class MyProductsListAdapter (
    private val context: Context,
    private var list: ArrayList<Product>,
    private val fragment: ProductsFragment
        ) : RecyclerView.Adapter<MyProductsListAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(private val itemBinding: ItemListLayoutBinding)
        :RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItem(product: Product) {
            GlideLoader(context).loadProductPicture(product.image, itemBinding.ivItemImage)
            itemBinding.tvItemName.text = product.title
            itemBinding.tvItemPrice.text = "$${product.price}"

            // Assigning the click event to the delete button.
            itemBinding.ibDeleteProduct.setOnClickListener {

                // Now let's call the delete function of the ProductsFragment.
                fragment.deleteProduct(product.product_id)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_ID, product.product_id)
                intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, product.user_id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ItemListLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = list[position]
        holder.bindItem(product)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}