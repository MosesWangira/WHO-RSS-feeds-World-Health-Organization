package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyItem
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalItem
import kotlinx.android.synthetic.main.list_item_emergency.view.*
import kotlinx.android.synthetic.main.list_item_maternal.view.*

class EmergencyAdapter() :
    RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder>() {

    private var items =
        EmergencyDataClass(ArrayList())

    fun setDataList(items: List<EmergencyItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmergencyViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_maternal,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EmergencyViewHolder, position: Int) {

        val emergency = items.items[position]

        holder.view.emergencyTitle.text = emergency.title
        holder.view.emergencyPublicationDate.text = "Publication Date : ${emergency.pubDate}"
        holder.view.emergencyDescription.text = emergency.description
    }

    class EmergencyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}