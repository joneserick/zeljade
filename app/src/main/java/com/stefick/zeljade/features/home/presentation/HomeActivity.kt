package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import com.stefick.zeljade.R
import com.stefick.zeljade.features.base.DefaultFragmentActivity

class HomeActivity : DefaultFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
            return

        changeFragment(HomeFragment.newInstance(), R.id.fragment_container, false)

    }

}