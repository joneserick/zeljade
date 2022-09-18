package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.databinding.FragmentHomeBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.models.CategoryListItem
import com.stefick.zeljade.features.home.presentation.adapter.HomeCategoryAdapter

internal class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val model: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(
            CompendiumRepository(CompendiumRemoteService())
        )
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {

            val safeContext = context ?: return@apply

            categories.let {
                it.layoutManager =
                    LinearLayoutManager(safeContext, LinearLayoutManager.HORIZONTAL, false)
                it.adapter = HomeCategoryAdapter(getCategories()) {
                    model.requestDataByCategory(it.category.toString())
                }
            }

            model.categories.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it.data.first().name, Toast.LENGTH_LONG).show()
            }

            model.error.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun getCategories(): List<CategoryListItem> {
        return listOf(
            CategoryListItem(
                "Monsters",
                "monsters",
                "https://botw-compendium.herokuapp.com/api/v2/entry/stalkoblin/image"
            ),
            CategoryListItem(
                "Equipment",
                "equipment",
                "https://botw-compendium.herokuapp.com/api/v2/entry/golden_claymore/image"
            ),
            CategoryListItem(
                "Treasures",
                "treasure",
                "https://botw-compendium.herokuapp.com/api/v2/entry/treasure_chest/image"
            ),
            CategoryListItem(
                "Creatures",
                "creatures",
                "https://botw-compendium.herokuapp.com/api/v2/entry/rugged_rhino_beetle/image"
            ),
            CategoryListItem(
                "Materials",
                "materials",
                "https://botw-compendium.herokuapp.com/api/v2/entry/wildberry/image"
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}