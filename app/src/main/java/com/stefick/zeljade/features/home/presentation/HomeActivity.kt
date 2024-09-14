package com.stefick.zeljade.features.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stefick.zeljade.R
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
                    model.requestCategoryData("equipment")
                }

                model.compendium.observe(LocalLifecycleOwner.current) {
                    it?.let { item ->
                        for (value in item.entries) {
                            println(value)
                        }
                    }
                }
                val entry = model.entry.collectAsState()
                val categories = model.category.collectAsState().value
                val listState = rememberLazyListState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = dimensionResource(id = R.dimen.raw_16_dp)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.raw_16_dp))
                ) {
                    Text(text = entry.value?.name ?: "NOT AVAILABLE")
                    LazyColumn(state = listState) {
                        val entries = categories?.entries
                        items(10) { entryIndex ->
                            Text(
                                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.raw_8_dp)),
                                text = entries?.get(entryIndex)?.name ?: "NOT AVAILABLE"
                            )
                            Divider(color = Color.Gray, thickness = 2.dp)
                        }

                    }
                }
            }
        }

    }

}