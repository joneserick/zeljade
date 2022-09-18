package com.stefick.zeljade.features.home.presentation.category_details.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stefick.zeljade.features.home.presentation.category_details.CategoryItemFragment

class CreatureCategoryAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return CategoryItemFragment.newInstance(position)
    }

}