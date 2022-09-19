package com.stefick.zeljade.features.home.presentation.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.custom.shared.extensions.capitalizeWords
import com.stefick.zeljade.databinding.FragmentCompendiumItemDetailsBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.presentation.HomeViewModel
import com.stefick.zeljade.features.home.presentation.item_details.adapter.SpecsListAdapter

class CompendiumItemDetailsFragment : BaseFragment<FragmentCompendiumItemDetailsBinding>() {

    private val model: HomeViewModel by activityViewModels()

    private var entryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.apply {
            entryId = getInt(ENTRY_EXTRA_KEY)
        }

        model.requestEntry(entryId ?: 0)
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCompendiumItemDetailsBinding {
        return FragmentCompendiumItemDetailsBinding.inflate(inflater, container, false).apply {

            model.entry.observe(viewLifecycleOwner) { entry ->

                Glide.with(root)
                    .load(entry?.data?.image)
                    .centerCrop()
                    .placeholder(
                        ContextCompat.getDrawable(
                            root.context,
                            R.drawable.loading_image
                        )
                    )
                    .into(itemImage)


                val title = entry?.data?.name ?: getString(R.string.str_not_available)
                itemName.text = title.capitalizeWords()
                itemDescription.text = entry?.data?.description ?: getString(R.string.str_not_available)

                entry.data?.commonLocations?.let {
                    itemCommonLocations.apply {
                        layoutManager = getSharedLayoutManager()
                        adapter = SpecsListAdapter(it)
                        visibility = View.VISIBLE
                    }
                    itemCommonLocationsLabel.visibility = View.VISIBLE
                }
                setupSpecsList(entry)
            }
        }
    }

    private fun setupSpecsList(entry: EntryResponse) {
        binding?.run {
            entry.data?.drops?.let {
                itemDropsList.apply {
                    layoutManager = getSharedLayoutManager()
                    adapter = SpecsListAdapter(it)
                    visibility = View.VISIBLE
                }
                itemDrops.visibility = View.VISIBLE
            }

            entry.data?.apply {
                val specs = arrayListOf<String>()
                heartsRecovered?.let {
                    specs.add(getString(R.string.str_hearts_recovered, it))
                }
                cookingEffect?.let {
                    specs.add(getString(R.string.str_cooking_effect, it))
                }
                attack?.let {
                    specs.add(getString(R.string.str_attack, it))
                }
                defense?.let {
                    specs.add(getString(R.string.str_defense, it))
                }
                if (!specs.isEmpty()) {
                    itemSpecsList.apply {
                        layoutManager = getSharedLayoutManager()
                        adapter = SpecsListAdapter(specs)
                        visibility = View.VISIBLE
                    }
                    itemSpecsLabel.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun getSharedLayoutManager(): LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object {

        const val ENTRY_EXTRA_KEY = "entry"

        @JvmStatic
        fun newInstance(entryId: Int) = CompendiumItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ENTRY_EXTRA_KEY, entryId)
            }
        }
    }
}