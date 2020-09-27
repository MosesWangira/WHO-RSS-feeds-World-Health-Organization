package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.diseases.DataClass
import com.example.diseaseoutbreaks.data.Model.diseases.Item
import kotlinx.android.synthetic.main.list_item_diseases.view.*

class DiseasesAdapter() :
    RecyclerView.Adapter<DiseasesAdapter.DiseaseViewHolder>() {

    private var items =
        DataClass(ArrayList())

    fun setDataList(items: List<Item>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiseaseViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_diseases,
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
    }

    class DiseaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)


}