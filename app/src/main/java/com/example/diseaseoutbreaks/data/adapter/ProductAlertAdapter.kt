package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.data.Model.productalert.ProductItem
import kotlinx.android.synthetic.main.list_item_product.view.*


class ProductAlertAdapter() :
    RecyclerView.Adapter<ProductAlertAdapter.ProductAlertViewHolder>() {

    private var items =
        ProductAlertDataClass(ArrayList())

    fun setDataList(items: List<ProductItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductAlertViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_product,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductAlertViewHolder, position: Int) {

        val product = items.items[position]

        holder.view.productTitle.text = product.title
        holder.view.productPublicationDdate.text = "Publication Date : ${product.pubDate}"
        holder.view.productDescription.text = product.description
    }

    class ProductAlertViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}