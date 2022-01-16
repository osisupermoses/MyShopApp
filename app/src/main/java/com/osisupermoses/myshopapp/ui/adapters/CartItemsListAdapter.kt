package com.osisupermoses.myshopapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.osisupermoses.myshopapp.R
import com.osisupermoses.myshopapp.databinding.ItemCartLayoutBinding
import com.osisupermoses.myshopapp.firestore.FirestoreClass
import com.osisupermoses.myshopapp.models.CartItem
import com.osisupermoses.myshopapp.ui.activities.CartListActivity
import com.osisupermoses.myshopapp.utils.Constants
import com.osisupermoses.myshopapp.utils.GlideLoader

class CartItemsListAdapter(
    private val context: Context,
    private var list: ArrayList<CartItem>,
    private val updateCartItem: Boolean
    ) : RecyclerView.Adapter<CartItemsListAdapter.CartItemsViewHolder>()  {

        inner class CartItemsViewHolder(private val itemBinding: ItemCartLayoutBinding)
            : RecyclerView.ViewHolder(itemBinding.root) {

                @SuppressLint("SetTextI18n")
                fun bindItem(cartItem: CartItem) {
                    GlideLoader(context).loadProductPicture(cartItem.image, itemBinding.ivCartItemImage)
                    itemBinding.tvCartItemTitle.text = cartItem.title
                    itemBinding.tvCartItemPrice.text = "$${cartItem.price}"
                    itemBinding.tvCartQuantity.text = cartItem.cart_quantity

                    // Show the text Out of Stock when cart quantity is zero.
                    if (cartItem.cart_quantity == "0") {
                        itemBinding.ibRemoveCartItem.visibility = View.GONE
                        itemBinding.ibAddCartItem.visibility = View.GONE

                        if (updateCartItem) {
                            itemBinding.ibDeleteCartItem.visibility = View.VISIBLE
                        } else {
                            itemBinding.ibDeleteCartItem.visibility = View.GONE
                        }

                        itemBinding.tvCartQuantity.text =
                            context.resources.getString(R.string.lbl_out_of_stock)

                        itemBinding.tvCartQuantity.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorSnackBarError
                            )
                        )
                    } else {
                        if (updateCartItem) {
                            itemBinding.ibRemoveCartItem.visibility = View.VISIBLE
                            itemBinding.ibAddCartItem.visibility = View.VISIBLE
                            itemBinding.ibDeleteCartItem.visibility = View.VISIBLE
                        } else {
                            itemBinding.ibRemoveCartItem.visibility = View.GONE
                            itemBinding.ibAddCartItem.visibility = View.GONE
                            itemBinding.ibDeleteCartItem.visibility = View.GONE
                        }

                        itemBinding.tvCartQuantity.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.colorSecondaryText
                            )
                        )
                    }

                    // Assign the onclick event to the ib_delete_cart_item.
                        itemBinding.ibDeleteCartItem.setOnClickListener {

                        // Call the firestore class function to remove the item from cloud firestore.

                        when (context) {
                            is CartListActivity -> {
                                context.showProgressDialog(context.resources.getString(R.string.please_wait))
                            }
                        }

                        FirestoreClass().removeItemFromCart(context, cartItem.id)
                    }

                    itemBinding.ibRemoveCartItem.setOnClickListener {
                        if (cartItem.cart_quantity == "1") {
                            FirestoreClass().removeItemFromCart(context, cartItem.id)
                        } else {

                            val cartQuantity: Int = cartItem.cart_quantity.toInt()

                            val itemHashMap = HashMap<String, Any>()

                            itemHashMap[Constants.CART_QUANTITY] = (cartQuantity - 1).toString()

                            // Show the progress dialog.
                            if (context is CartListActivity) {
                                context.showProgressDialog(context.resources.getString(R.string.please_wait))
                            }

                            FirestoreClass().updateMyCart(context, cartItem.id, itemHashMap)
                        }

                    }

                    itemBinding.ibAddCartItem.setOnClickListener {

                        // Call the update function of firestore class based on the cart quantity.
                        val cartQuantity: Int = cartItem.cart_quantity.toInt()

                        if (cartQuantity < cartItem.stock_quantity.toInt()) {

                            val itemHashMap = HashMap<String, Any>()

                            itemHashMap[Constants.CART_QUANTITY] = (cartQuantity + 1).toString()

                            // Show the progress dialog.
                            if (context is CartListActivity) {
                                context.showProgressDialog(context.resources.getString(R.string.please_wait))
                            }

                            FirestoreClass().updateMyCart(context, cartItem.id, itemHashMap)
                        } else {
                            if (context is CartListActivity) {
                                context.showErrorSnackBar(
                                    context.resources.getString(
                                        R.string.msg_for_available_stock,
                                        cartItem.stock_quantity
                                    ),
                                    true
                                )
                            }
                        }

                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemsViewHolder {
        return CartItemsViewHolder(
            ItemCartLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartItemsViewHolder, position: Int) {
        val cartItem = list[position]
        holder.bindItem(cartItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}