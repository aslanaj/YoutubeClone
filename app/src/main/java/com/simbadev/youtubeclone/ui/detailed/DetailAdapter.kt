package com.simbadev.youtubeclone.ui.detailed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.simbadev.youtubeclone.core.utils.loadImage
import com.simbadev.youtubeclone.databinding.ItemDetailBinding
import com.simbadev.youtubeclone.remote.model.Item
import com.simbadev.youtubeclone.remote.model.PlaylistItems

class DetailAdapter() : RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    fun setList(list: List<PlaylistItems.Item>) {
        this.listOfItems = list as ArrayList<PlaylistItems.Item>
    }

    private var listOfItems = arrayListOf<PlaylistItems.Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listOfItems.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(listOfItems[position])
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        ViewHolder(binding.root) {
        fun bind(item: PlaylistItems.Item) {

            binding.apply {
                image.loadImage(item.snippet.thumbnails.default.url)
                tvTitle.text = item.snippet.title
            }
        }

    }
}


