package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.databinding.FragmentHomeBinding
import com.stefick.zeljade.features.base.BaseFragment

internal class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this, CompendiumRepository(CompendiumRemoteService()), lifecycleScope)
    }

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {
            requestTest.setOnClickListener {
                presenter.getDataByCategory("monsters")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun displayData(response: CategoryItemResponse) {
        //TODO("missing implementation")
    }

    override fun displayError(throwable: Throwable) {
        //TODO("missing implementation")
    }

}