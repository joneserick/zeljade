package com.stefick.zeljade.features.home.presentation.category_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION_EXTRAS)
        }
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCategoryItemBinding {
        return FragmentCategoryItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.categories.value?.data?.creatures?.let { displayCreatures(it) }

        model.categories.observe(viewLifecycleOwner) { response ->
            response.data.creatures?.let { displayCreatures(it) }
        }

    }

    private fun displayCreatures(creaturesResponse: CreaturesResponse) {
        val items = when (position) {
            FIRST_POSITION -> creaturesResponse.food
            else -> creaturesResponse.nonFood
        }
        binding?.run {
            val safeContext = context ?: return@run

            categoryItemList.layoutManager =
                LinearLayoutManager(safeContext, LinearLayoutManager.VERTICAL, false)

            categoryItemList.adapter = CategoryItemDetailsAdapter(items) { id ->
                (activity as HomeActivity).changeFragment(
                    CompendiumItemDetailsFragment.newInstance(
                        id
                    )
                )
            }
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