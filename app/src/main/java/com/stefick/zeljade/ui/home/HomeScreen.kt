package com.stefick.zeljade.ui.home

import android.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefick.zeljade.ui.home.main_bar.MainBar

@Composable
fun HomeScreen(
    modifier: Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize().padding(16.dp),
        topBar = {
            MainBar()
        },
        bottomBar = {

        }
    ) { paddingValues ->
        Row(Modifier.padding(paddingValues)) {

        }
    }
}


@Preview(showBackground = true, backgroundColor = Color.WHITE.toLong())
@Composable
fun HomePreview() {
    HomeScreen(modifier = Modifier)
}
