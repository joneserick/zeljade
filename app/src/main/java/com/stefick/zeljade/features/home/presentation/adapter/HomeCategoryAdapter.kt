package com.stefick.zeljade.features.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.custom.shared.extensions.capitalizeWords
import com.stefick.zeljade.databinding.LayoutCategoryItemBinding
import com.stefick.zeljade.features.home.models.CategoryCardItem

class HomeCategoryAdapter(
    private val categories: CategoryDTO,
    private val onItemClick: (item: CategoryCardItem?) -> Unit
) :
    RecyclerView.Adapter<CategoryItemViewHolder>() {

    private var items: ArrayList<CategoryCardItem>? = null

    init {
        items = getEachCategory()
    }

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
        items?.size ?: 0

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val item = items?.get(position)
        holder.binding.root.setOnClickListener { onItemClick.invoke(item) }
        holder.bind(item)
    }

    private fun getEachCategory(): ArrayList<CategoryCardItem> {
        return arrayListOf<CategoryCardItem>()
    }
}

class CategoryItemViewHolder(val binding: LayoutCategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CategoryCardItem?) {
        Glide.with(binding.root)
            .load(item?.image)
            .centerCrop()
            .into(binding.categoryImage)

        binding.categoryName.text = item?.name?.capitalizeWords()

    }

}
