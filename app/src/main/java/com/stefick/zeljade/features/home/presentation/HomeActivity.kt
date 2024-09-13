package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
                    model.requestEntryData("108")
                }

                model.compendium.observe(LocalLifecycleOwner.current) {
                    it?.let { item ->
                        for (value in item.entries) {
                            println(value)
                        }
                    }
                }
                val entry = model.entry.collectAsState()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = entry.value?.name ?: "NOT AVAILABLE")
                }

            }
        }

    }

}