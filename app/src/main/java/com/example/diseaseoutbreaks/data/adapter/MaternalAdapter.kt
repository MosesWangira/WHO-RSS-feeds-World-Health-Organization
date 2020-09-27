package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalItem
import kotlinx.android.synthetic.main.list_item_maternal.view.*

class MaternalAdapter() :
    RecyclerView.Adapter<MaternalAdapter.MaternalViewHolder>() {

    private var items =
        MaternalDataClass(ArrayList())

    fun setDataList(items: List<MaternalItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MaternalViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_news,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MaternalViewHolder, position: Int) {

        val maternal = items.items[position]

        holder.view.maternalTitle.text = maternal.title
        holder.view.maternalPublicationDate.text = "Publication Date : ${maternal.pubDate}"
        holder.view.maternalDescription.text = maternal.description
    }

    class MaternalViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}