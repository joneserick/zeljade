package com.stefick.zeljade.features.home.presentation.category_details

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CreaturesResponse
import com.stefick.zeljade.databinding.FragmentCategoryItemBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.presentation.HomeActivity
import com.stefick.zeljade.features.home.presentation.HomeViewModel
import com.stefick.zeljade.features.home.presentation.category_details.adapter.CategoryItemDetailsAdapter
import com.stefick.zeljade.features.home.presentation.item_details.CompendiumItemDetailsFragment


class CreatureCategoryFragment : BaseFragment<FragmentCategoryItemBinding>() {

    private val model: HomeViewModel by activityViewModels()

    private var position: Int? = null

    private var adapter: CategoryItemDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION_EXTRAS)
        }
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        model.categories.value?.data?.creatures?.let { displayCreatures(it) }
//
//        model.categories.observe(viewLifecycleOwner) { response ->
//            response.data.creatures?.let { displayCreatures(it) }
//        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        configureSearch()
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
        searchView?.apply {
            setOnClickListener { v -> v.requestFocus() }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> false
        }
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCategoryItemBinding {
        return FragmentCategoryItemBinding.inflate(inflater, container, false)
    }

    private fun displayCreatures(creaturesResponse: CreaturesResponse) {
        val items = when (position) {
            FIRST_POSITION -> creaturesResponse.food
            else -> creaturesResponse.nonFood
        }
        binding?.run {
            val safeContext = context ?: return@run

//            categoryItemList.layoutManager =
//                LinearLayoutManager(safeContext, LinearLayoutManager.VERTICAL, false)
//
//            adapter = CategoryItemDetailsAdapter(items) { id ->
//                (activity as HomeActivity).changeFragment(
//                    CompendiumItemDetailsFragment.newInstance(id)
//                )
//            }
//            categoryItemList.adapter = adapter
        }
    }

    companion object {

        const val POSITION_EXTRAS: String = "position"
        const val FIRST_POSITION: Int = 0

        @JvmStatic
        fun newInstance(position: Int) =
            CreatureCategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION_EXTRAS, position)
                }
            }
    }
}