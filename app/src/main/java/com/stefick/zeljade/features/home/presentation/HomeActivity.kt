package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
            return

        setContent {


            MaterialTheme {

                val model = hiltViewModel<HomeViewModel>()

                LaunchedEffect(key1 = Unit) {
                    model.requestAllData()
                }

                model.compendium.observe(LocalLifecycleOwner.current) {
                    it?.let { item ->
                        for (value in item.entries) {
                            println(value)
                        }
                    }
                }
            }
        }

    }

}