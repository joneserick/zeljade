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
import com.stefick.zeljade.features.home.presentation.HomeActivity
import com.stefick.zeljade.features.home.presentation.HomeViewModel
import com.stefick.zeljade.features.home.presentation.category_details.adapter.CategoryItemDetailsAdapter
import com.stefick.zeljade.features.home.presentation.category_details.adapter.CreatureCategoryAdapter
import com.stefick.zeljade.features.home.presentation.item_details.CompendiumItemDetailsFragment

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
        setActionBarTitle(category.toString())
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

        model.entry.observe(viewLifecycleOwner) { entry ->

        }

        model.categories.observe(viewLifecycleOwner) {
            setupSimpleCategoryList()
        }

        model.categories.observe(viewLifecycleOwner) {
            binding?.viewpager?.adapter?.notifyDataSetChanged()
        }
    }

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
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
        }
        setupSimpleCategoryList()
    }

    private fun loadAsCreature() {
        binding?.run {
            categoryItemList.visibility = View.GONE
            viewpager.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE
        }
        setupCreatureList()
    }

    private fun setupSimpleCategoryList() {
        binding?.categoryItemList?.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter =
                CategoryItemDetailsAdapter(model.getSelectedSimpleCategoryItems(category)) { id ->
                    (activity as HomeActivity).changeFragment(
                        CompendiumItemDetailsFragment.newInstance(
                            id
                        )
                    )
                }
        }
    }

    private fun setupCreatureList() {
        binding?.run {
            viewpager.adapter = CreatureCategoryAdapter(this@CategoryDetailsFragment)

            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = when (position) {
                    CreatureCategoryFragment.FIRST_POSITION -> getString(R.string.food_label)
                    else -> getString(R.string.non_food_label)
                }
            }.attach()
        }
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