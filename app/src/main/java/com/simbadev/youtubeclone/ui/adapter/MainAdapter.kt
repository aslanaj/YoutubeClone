package com.simbadev.youtubeclone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.simbadev.youtubeclone.databinding.ItemPlayListBinding

class MainAdapter(
    private val onClick: (item: com.simbadev.youtubeclone.model.Item)-> Unit
) : RecyclerView.Adapter<MainAdapter.PlayListViewHolder>(

) {

    fun setList(liste: List<com.simbadev.youtubeclone.model.Item>){
        this.list = liste as ArrayList<com.simbadev.youtubeclone.model.Item>
        notifyDataSetChanged()
    }

    private var list = arrayListOf<com.simbadev.youtubeclone.model.Item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        return PlayListViewHolder(
            ItemPlayListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class PlayListViewHolder(private val binding: ItemPlayListBinding) :
        ViewHolder(binding.root) {


        fun bind(item: com.simbadev.youtubeclone.model.Item) {

            binding.image.load(item.snippet.thumbnails.default.url)
            binding.apply {
                tvTitle.text = item.snippet.title
                tvVideo.text= "${item.contentDetails.itemCount} video"
            }

            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }

    }

}