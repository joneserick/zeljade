package com.stefick.zeljade.features.home.presentation.category_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.CategoryEnum
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.databinding.FragmentCategoryDetailsBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.presentation.HomeViewModel
import com.stefick.zeljade.features.home.presentation.category_details.adapter.CategoryItemDetailsAdapter
import com.stefick.zeljade.features.home.presentation.category_details.adapter.CreatureCategoryAdapter

private const val CATEGORY_PARAM = "category"

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding>() {

    private var category: String? = null

    private val model: HomeViewModel by activityViewModels {
        HomeViewModel.HomeViewModelFactory(
            CompendiumRepository(CompendiumRemoteService())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(CATEGORY_PARAM)
        }
        setActionBarTitle(category.toString().capitalize())
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCategoryDetailsBinding {
        return FragmentCategoryDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewBasedOnCategory(category ?: "")
    }

    private fun setupViewBasedOnCategory(category: String) {
        when (category.lowercase()) {
            CategoryEnum.CREATURES.category -> loadAsCreature()
            else -> loadAsSimpleCategory()
        }
    }

    private fun loadAsSimpleCategory() {
        binding?.run {
            categoryItemList.visibility = View.VISIBLE
            viewpager.visibility = View.GONE
            tabLayout.visibility = View.GONE

            categoryItemList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            model.categories.observe(viewLifecycleOwner) {
                categoryItemList.adapter =
                    CategoryItemDetailsAdapter(model.getSelectedSimpleCategoryItems(category))
            }
        }
    }

    private fun loadAsCreature() {
        binding?.run {

            categoryItemList.visibility = View.GONE
            viewpager.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE

            model.categories.observe(viewLifecycleOwner) {
                viewpager.adapter = CreatureCategoryAdapter(this@CategoryDetailsFragment)
            }

            viewpager.adapter = CreatureCategoryAdapter(this@CategoryDetailsFragment)

            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = when (position) {
                    CategoryItemFragment.FIRST_POSITION -> getString(R.string.food_label)
                    else -> getString(R.string.non_food_label)
                }
            }.attach()
        }
    }

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String?) =
            CategoryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_PARAM, category)
                }
            }
    }
}