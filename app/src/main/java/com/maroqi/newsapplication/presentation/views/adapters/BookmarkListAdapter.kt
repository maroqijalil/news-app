package com.maroqi.newsapplication.presentation.views.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.maroqi.newsapplication.databinding.ItemBookmarkListBinding
import com.maroqi.newsapplication.domain.models.NewsModel
import java.text.SimpleDateFormat

class BookmarkListAdapter(private val values: List<NewsModel>) :
    RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookmarkListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val binding: ItemBookmarkListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsModel) {
            binding.tvNewsTitle.text = item.title
            binding.tvNewsDesc.text = item.description
            binding.tvNewsAuthor.text = item.author
            binding.tvNewsDate.text = SimpleDateFormat("dd/MM/yyyy").format(item.date)

            if (!item.image.isNullOrEmpty()) {
                Glide.with(binding.root)
                    .load(item.image)
                    .into(binding.ivNewsItem)
            }
        }
    }
}