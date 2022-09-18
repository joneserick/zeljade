package com.stefick.zeljade.features.home.presentation.item_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stefick.zeljade.R
import com.stefick.zeljade.databinding.LayoutSpecItemBinding

class SpecsListAdapter(private val specs: List<String>) :
    RecyclerView.Adapter<SpecsListAdapter.SpecListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecListItemViewHolder {
        return SpecListItemViewHolder(
            LayoutSpecItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        specs.size


    override fun onBindViewHolder(holder: SpecListItemViewHolder, position: Int) {
        holder.bind(specs.get(position))
    }

    class SpecListItemViewHolder(val binding: LayoutSpecItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.specItem.text =
                item.ifBlank { binding.root.context.getString(R.string.str_not_available) }
        }

    }

}
