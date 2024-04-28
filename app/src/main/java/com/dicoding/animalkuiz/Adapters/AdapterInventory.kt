package com.dicoding.animalkuiz.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.animalkuiz.R
import com.dicoding.animalkuiz.data.response.DataItemInventory
import com.google.android.material.card.MaterialCardView

class AdapterInventory(private val itemClickListener: ItemClickListener) : ListAdapter<DataItemInventory, AdapterInventory.ViewHolder>(InventoryDiffCallback()) {

    class ViewHolder(view: View, itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.cardHewan)
        val animalImageView: ImageView = view.findViewById(R.id.ivHewan)
        val animalNameTextView: TextView = view.findViewById(R.id.textView3)

//        init {
//            cardView.setOnClickListener {
//                itemClickListener.onItemClick(adapterPosition)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.animalNameTextView.text = data?.name

        val realImageUrl = data?.realImage

        if (data.isOwned == true) {
            // Load the image into the animalImageView using Glide
            Glide.with(holder.itemView.context)
                .load("https://tf4zs3dh-8080.asse.devtunnels.ms/api/v1/files/$realImageUrl")
                .into(holder.animalImageView)
        } else {
            // If there's no real image, you can set a default image if desired
        }
    }


    class InventoryDiffCallback : DiffUtil.ItemCallback<DataItemInventory>() {
        override fun areItemsTheSame(oldItem: DataItemInventory, newItem: DataItemInventory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItemInventory, newItem: DataItemInventory): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemClickListener {
//        fun onItemClick(position: Int)
    }
}
