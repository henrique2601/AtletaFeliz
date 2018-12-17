package com.hydragmes.paulo.atletafeliz.ui

import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hydragmes.paulo.atletafeliz.Model.Athlete
import com.hydragmes.paulo.atletafeliz.R

class RecyclerAdapter(private val athletes: List<Athlete>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitle: TextView
        var itemSport: TextView
        var itemLocation: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_name)
            itemSport = itemView.findViewById(R.id.item_sport)
            itemLocation = itemView.findViewById(R.id.item_city)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.cardview_activity, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = athletes[i].name
        viewHolder.itemSport.text = athletes[i].sport
        viewHolder.itemLocation.text = athletes[i].city + " / " + athletes[i].state
        viewHolder.itemImage.setImageResource(R.drawable.placeholder)
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:" + athletes[i].email) // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT,"Interesse em patrocinio")
            startActivity(viewHolder.itemImage.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return athletes.size
    }
}