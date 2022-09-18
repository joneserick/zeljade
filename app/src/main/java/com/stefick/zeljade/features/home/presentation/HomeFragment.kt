package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefick.zeljade.R
import com.stefick.zeljade.databinding.FragmentHomeBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.presentation.adapter.HomeCategoryAdapter
import com.stefick.zeljade.features.home.presentation.category_details.CategoryDetailsFragment

internal class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val model: HomeViewModel by activityViewModels()

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            val safeContext = context ?: return@run

            setActionBarTitle(getString(R.string.app_name))

            categories.let {
                it.layoutManager =
                    LinearLayoutManager(safeContext, LinearLayoutManager.HORIZONTAL, false)
            }

            model.categories.observe(viewLifecycleOwner) {
                val categoriesList = model.getEachCategory(it.data)
                categories.adapter = HomeCategoryAdapter(categoriesList) { selectedCategory ->
                    (activity as HomeActivity).changeFragment(
                        CategoryDetailsFragment.newInstance(selectedCategory.name), true
                    )
                }
            }

            model.error.observe(viewLifecycleOwner) {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}