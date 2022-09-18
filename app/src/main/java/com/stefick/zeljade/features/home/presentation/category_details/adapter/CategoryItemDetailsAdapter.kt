package com.stefick.zeljade.features.home.presentation.category_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.databinding.LayoutCategoryItemListItemBinding

class CategoryItemDetailsAdapter(private val categoryItem: List<CategoryResponse?>?) :
    RecyclerView.Adapter<CategoryItemDetailsAdapter.CategoryItemDetailsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemDetailsViewHolder {
        return CategoryItemDetailsViewHolder(
            LayoutCategoryItemListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        categoryItem?.size ?: 0

    override fun onBindViewHolder(holder: CategoryItemDetailsViewHolder, position: Int) {
        if (categoryItem != null)
            holder.bind(categoryItem[position])
    }

    class CategoryItemDetailsViewHolder(val binding: LayoutCategoryItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryResponse?) {
            Glide.with(binding.root)
                .load(item?.image)
                .centerCrop()
                .into(binding.itemImage)
            binding.itemName.text = item?.name
        }

    }

}
