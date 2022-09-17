package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import android.widget.Toast
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.features.base.DefaultFragmentActivity

class HomeActivity : DefaultFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
            return

        changeFragment(HomeFragment.newInstance(), R.id.fragment_container, false)

    }

}