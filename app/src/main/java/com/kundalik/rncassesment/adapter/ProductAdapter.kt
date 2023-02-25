package com.kundalik.rncassesment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kundalik.rncassesment.R
import com.kundalik.rncassesment.model.Product
import com.kundalik.rncassesment.viewModel.MainViewModel
import kotlinx.android.synthetic.main.product_row_layout.view.*

class ProductAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var mProductList = emptyList<Product>()


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_row_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val mProduct = mProductList[position]
        holder.itemView.apply {
            Glide.with(this).load(mProductList[position].image_link).into(iv_product_image)
            tv_product_name.text = mProductList[position].name
            tv_product_brand.text = mProductList[position].brand
            tv_product_price.text = mProductList[position].price
            val product_link = mProductList[position].product_link

            setOnClickListener {
                viewModel.selectItem(mProduct)
            }
        }
    }

    fun setData(newProductList: List<Product>) {
        mProductList = newProductList
        notifyDataSetChanged()
    }
}