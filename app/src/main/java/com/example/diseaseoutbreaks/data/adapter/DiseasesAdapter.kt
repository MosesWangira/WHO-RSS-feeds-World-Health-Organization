package com.example.diseaseoutbreaks.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import kotlinx.android.synthetic.main.list_item.view.*


class DiseasesAdapter(val items: DataClass) :
    RecyclerView.Adapter<DiseasesAdapter.DiseaseViewHolder>() {

    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiseaseViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {

        val disease = items.items[position]

        holder.view.title.text = disease.title
        holder.view.publication_date.text = "Publication Date : ${disease.pubDate}"
        holder.view.link.text = "Link : ${disease.link}"
        holder.view.description.text = disease.description

        setAnimation(holder.itemView, position)

    }


    class DiseaseViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private fun setAnimation(
        viewToAnimate: View,
        position: Int
    ) {
            val anim = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            //anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            anim.duration = 600 //to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
    }
}