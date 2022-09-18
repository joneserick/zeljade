package com.stefick.zeljade.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefick.zeljade.databinding.LayoutCategoryItemBinding
import com.stefick.zeljade.features.home.models.CategoryListItem

class HomeCategoryAdapter(
    val items: List<CategoryListItem>,
    val onItemClick: (item: CategoryListItem) -> Unit
) :
    RecyclerView.Adapter<CategoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(
            LayoutCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val item = items[position]
        holder.binding.root.setOnClickListener { onItemClick.invoke(item) }
        holder.bind(item)
    }
}

class CategoryItemViewHolder(val binding: LayoutCategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategoryListItem) {
        Glide.with(binding.root)
            .load(item.image)
            .centerCrop()
            .into(binding.categoryImage)

        binding.categoryName.text = item.name

    }

}
