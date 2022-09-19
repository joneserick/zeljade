package com.stefick.zeljade.features.home.presentation.category_details

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.CategoryEnum
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.custom.shared.extensions.capitalizeWords
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

    private var adapter: CategoryItemDetailsAdapter? = null

    private val model: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            category = it.getString(CATEGORY_PARAM)
        }

        setActionBarTitle(category.toString().capitalizeWords())
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewBasedOnCategory(category ?: "")

        model.categories.observe(viewLifecycleOwner) {
            setupViewBasedOnCategory(category ?: "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        configureSearch()
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCategoryDetailsBinding {
        return FragmentCategoryDetailsBinding.inflate(inflater, container, false)
    }

    private fun configureSearch() {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(search: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(search: String): Boolean {
                adapter?.filter?.filter(search)
                return true
            }
        })
        searchView?.setOnClickListener { }
    }

    private fun setupViewBasedOnCategory(category: String) {
        when (category.lowercase()) {
            CategoryEnum.CREATURES.category -> setupCreatureList()
            else -> setupSimpleCategoryList()
        }
    }

    private fun setupSimpleCategoryList() {
        adapter = CategoryItemDetailsAdapter(model.getSelectedSimpleCategoryItems(category)) { id ->
            (activity as HomeActivity).changeFragment(CompendiumItemDetailsFragment.newInstance(id))
        }
        binding?.categoryItemList?.let {
            it.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
        }
        binding?.run {
            categoryItemList.visibility = View.VISIBLE
            viewpager.visibility = View.GONE
            tabLayout.visibility = View.GONE
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
        binding?.run {
            categoryItemList.visibility = View.GONE
            viewpager.visibility = View.VISIBLE
            tabLayout.visibility = View.VISIBLE
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