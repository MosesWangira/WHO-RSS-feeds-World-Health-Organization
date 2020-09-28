package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyItem
import kotlinx.android.synthetic.main.list_item_image_left.view.*
import kotlinx.android.synthetic.main.list_item_image_right.view.*

class EmergencyAdapter() :
    RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder>() {

    private var items =
        EmergencyDataClass(ArrayList())

    fun setDataList(items: List<EmergencyItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmergencyViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_image_right,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EmergencyViewHolder, position: Int) {

        val emergency = items.items[position]

        holder.view.titleRight.text = emergency.title
        holder.view.publicationDateRight.text = "Publication Date : ${emergency.pubDate}"
        holder.view.descriptionRight.text = emergency.description

        Glide
            .with(holder.view.context)
            .load(R.drawable.siren)
            .centerCrop()
            .placeholder(R.drawable.siren)
            .into(holder.view.imageRight)
    }

    class EmergencyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}