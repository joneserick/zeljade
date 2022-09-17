package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.databinding.FragmentHomeBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.models.CategoryListItem
import com.stefick.zeljade.features.home.presentation.adapter.HomeCategoryAdapter

internal class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter =
            HomePresenter(this, CompendiumRepository(CompendiumRemoteService()), lifecycleScope)
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
                it.adapter = HomeCategoryAdapter(getCategories())
            }

            requestTest.setOnClickListener {
                presenter.getDataByCategory("monsters")
            }
        }
    }

    private fun getCategories(): List<CategoryListItem> {
        return listOf(
            CategoryListItem(
                "Monsters",
                "https://botw-compendium.herokuapp.com/api/v2/entry/stalkoblin/image"
            ),
            CategoryListItem(
                "Equipment",
                "https://botw-compendium.herokuapp.com/api/v2/entry/golden_claymore/image"
            ),
            CategoryListItem(
                "Treasures",
                "https://botw-compendium.herokuapp.com/api/v2/entry/treasure_chest/image"
            ),
            CategoryListItem(
                "Creatures",
                "https://botw-compendium.herokuapp.com/api/v2/entry/rugged_rhino_beetle/image"
            ),
            CategoryListItem(
                "Materials",
                "https://botw-compendium.herokuapp.com/api/v2/entry/wildberry/image"
            )
        )
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun displayData(response: CategoryItemResponse) {

    }

    override fun displayError(throwable: Throwable) {
        //TODO("missing implementation")
    }

}