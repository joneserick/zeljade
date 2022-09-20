package com.stefick.zeljade.features.home.presentation.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.custom.shared.extensions.capitalizeWords
import com.stefick.zeljade.databinding.FragmentCompendiumItemDetailsBinding
import com.stefick.zeljade.features.base.BaseFragment
import com.stefick.zeljade.features.home.presentation.item_details.adapter.SpecsListAdapter
import kotlin.math.roundToInt

class CompendiumItemDetailsFragment : BaseFragment<FragmentCompendiumItemDetailsBinding>(),
    EntryViewContract {

    private var presenter: EntryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter =
            EntryPresenter(this, CompendiumRepository(CompendiumRemoteService()), lifecycleScope)

        arguments?.apply {
            presenter?.getEntry(getInt(ENTRY_EXTRA_KEY))
        }

    }

    override fun onResume() {
        super.onResume()
        if (binding?.shimmerViewContainer?.isShimmerVisible == false)
            binding?.shimmerViewContainer?.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding?.shimmerViewContainer?.stopShimmer()
    }

    override fun onBackPressed(): Boolean {
        finish()
        return true
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentCompendiumItemDetailsBinding {
        return FragmentCompendiumItemDetailsBinding.inflate(inflater, container, false)
    }

    override fun showLoader() {
        binding?.shimmerViewContainer?.startShimmer()
    }

    override fun hideLoader() {
        binding?.shimmerViewContainer?.stopShimmer()
    }

    override fun displayEntry(entry: EntryResponse) {
        setupView(entry)
    }

    override fun displayError(error: ErrorResponse?) {
        error?.let {
            val safeActivity = activity ?: return
            Toast.makeText(
                safeActivity,
                it.message ?: getString(R.string.default_error),
                Toast.LENGTH_LONG
            )
                .show()
        }

    }

    private fun setupView(entry: EntryResponse) {
        binding?.run {
            Glide.with(root)
                .load(entry.data?.image)
                .centerCrop()
                .placeholder(
                    ContextCompat.getDrawable(
                        root.context,
                        R.drawable.loading_image
                    )
                )
                .into(itemImage)

            val title = entry.data?.name ?: getString(R.string.str_not_available)
            itemName.text = title.capitalizeWords()
            itemDescription.text =
                entry.data?.description ?: getString(R.string.str_not_available)

            entry.data?.commonLocations?.let {
                itemCommonLocations.apply {
                    layoutManager = getSharedLayoutManager()
                    adapter = SpecsListAdapter(it)
                    visibility = View.VISIBLE
                }
                itemCommonLocationsLabel.visibility = View.VISIBLE
            }
            setupSpecsList(entry)

            shimmerViewContainer.let {
                it.stopShimmer()
                it.visibility = View.GONE
            }
            container.visibility = View.VISIBLE
        }

    }

    private fun setupSpecsList(entry: EntryResponse) {
        binding?.run {
            entry.data?.drops?.let {
                if (it.isNotEmpty()) {
                    itemDropsList.apply {
                        layoutManager = getSharedLayoutManager()
                        adapter = SpecsListAdapter(it)
                        visibility = View.VISIBLE
                    }
                    itemDrops.visibility = View.VISIBLE
                }
            }

            entry.data?.apply {
                val specs = arrayListOf<String>()
                heartsRecovered?.let {
                    specs.add(getString(R.string.str_hearts_recovered, "%.1f".format(it)))
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