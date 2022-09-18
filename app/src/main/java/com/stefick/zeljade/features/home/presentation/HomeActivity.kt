package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.stefick.zeljade.R
import com.stefick.zeljade.core.api.CompendiumRemoteService
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.features.base.DefaultFragmentActivity

class HomeActivity : DefaultFragmentActivity() {

    private val model: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(
            CompendiumRepository(CompendiumRemoteService())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
            return

        model.requestAllData()

        model.categories.observe(this) {
            changeFragment(HomeFragment.newInstance(), R.id.fragment_container, false)
        }

    }

}