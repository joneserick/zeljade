package com.stefick.zeljade.features.home.presentation.category_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.custom.shared.extensions.capitalizeWords
import com.stefick.zeljade.databinding.LayoutCategoryItemListItemBinding

class CategoryItemDetailsAdapter(
    private val categoryItems: List<CategoryResponse?>?,
    val onItemClick: (id: Int) -> Unit
) :
    RecyclerView.Adapter<CategoryItemDetailsAdapter.CategoryItemDetailsViewHolder>(), Filterable {

    var filteredItems: List<CategoryResponse?>? = categoryItems

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
        filteredItems?.size ?: 0

    override fun onBindViewHolder(holder: CategoryItemDetailsViewHolder, position: Int) {
        filteredItems?.let {
            val item = it[position]
            holder.bind(item)
            holder.binding.root.setOnClickListener { onItemClick.invoke(item?.id ?: 0) }
        }
    }

    override fun getFilter(): Filter =
        object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredResults = if (constraint?.length == 0) {
                    categoryItems
                } else {
                    getFilteredResults(constraint ?: "")
                }
                return FilterResults().apply {
                    values = filteredResults
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = results?.values as ArrayList<CategoryResponse?>?
                notifyDataSetChanged()
            }

        }

    private fun getFilteredResults(constraint: CharSequence?): ArrayList<CategoryResponse?> {
        val filtered = arrayListOf<CategoryResponse?>()
        categoryItems?.forEach { item ->
            if (item?.name?.lowercase()?.contains(constraint ?: "") == true) {
                filtered.add(item)
            }
        }
        return filtered
    }

    class CategoryItemDetailsViewHolder(val binding: LayoutCategoryItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryResponse?) {
            Glide.with(binding.root)
                .load(item?.image)
                .centerCrop()
                .placeholder(
                    ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.bg_oval_solid_distant_rock
                    )
                )
                .into(binding.itemImage)
            binding.itemName.text = item?.name?.capitalizeWords()
        }

    }

}
