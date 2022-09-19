package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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

        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        model.requestAllData()

        model.categories.observe(this) {
            changeFragment(HomeFragment.newInstance(), R.id.fragment_container, false)
        }

    }

}