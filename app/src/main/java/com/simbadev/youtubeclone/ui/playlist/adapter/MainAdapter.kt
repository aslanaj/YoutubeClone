package com.simbadev.youtubeclone.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.simbadev.youtubeclone.R
import com.simbadev.youtubeclone.databinding.ItemPlayListBinding
import com.simbadev.youtubeclone.remote.model.Item

class MainAdapter(
    private val onClick: (item: Item) -> Unit
) : RecyclerView.Adapter<MainAdapter.PlayListViewHolder>(

) {

    fun setList(liste: List<Item>) {
        this.list = liste as ArrayList<Item>
        notifyDataSetChanged()
    }

    private var list = arrayListOf<Item>()


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


        fun bind(item: Item) {

            binding.image.load(item.snippet.thumbnails.default.url)
            binding.apply {
                tvTitle.text = item.snippet.title
                tvVideo.text = String.format(
                    itemView.context.getString(R.string.video_series),
                    item.contentDetails.itemCount
                )
            }

            itemView.setOnClickListener {
                onClick.invoke(item)
            }
        }

    }

}