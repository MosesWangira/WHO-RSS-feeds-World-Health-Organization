package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseItem
import kotlinx.android.synthetic.main.list_item_image_left.view.*

class DiseasesAdapter() :
    RecyclerView.Adapter<DiseasesAdapter.DiseaseViewHolder>() {

    private var items =
        DiseaseDataClass(ArrayList())

    fun setDataList(items: List<DiseaseItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiseaseViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_image_left,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {

        val disease = items.items[position]

        holder.view.title.text = disease.title
        holder.view.publication_date.text = "Publication Date : ${disease.pubDate}"
        holder.view.description.text = disease.description

        Glide
            .with(holder.view.context)
            .load(R.drawable.disease_place_holder)
            .centerCrop()
            .placeholder(R.drawable.disease_place_holder)
            .into(holder.view.imageLeft)

    }

    class DiseaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}