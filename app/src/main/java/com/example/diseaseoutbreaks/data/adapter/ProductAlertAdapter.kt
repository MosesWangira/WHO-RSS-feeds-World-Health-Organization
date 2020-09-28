package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.data.Model.productalert.ProductItem
import kotlinx.android.synthetic.main.list_item_image_left.view.*


class ProductAlertAdapter() :
    RecyclerView.Adapter<ProductAlertAdapter.ProductAlertViewHolder>() {

    private var items =
        ProductAlertDataClass(ArrayList())

    fun setDataList(items: List<ProductItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductAlertViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_image_left,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductAlertViewHolder, position: Int) {

        val product = items.items[position]

        holder.view.title.text = product.title
        holder.view.publication_date.text = "Publication Date : ${product.pubDate}"
        holder.view.description.text = product.description

        Glide
            .with(holder.view.context)
            .load(R.drawable.product_place_holder)
            .centerCrop()
            .placeholder(R.drawable.product_place_holder)
            .into(holder.view.imageLeft)
    }

    class ProductAlertViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}