package com.stefick.zeljade.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefick.zeljade.databinding.LayoutCategoryItemBinding
import com.stefick.zeljade.features.home.models.CategoryListItem

class HomeCategoryAdapter(items: List<CategoryListItem>) :
    RecyclerView.Adapter<CategoryItemViewHolder>() {

    private val mItems: List<CategoryListItem> = items

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
        mItems.size

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.bind(mItems[position])
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
