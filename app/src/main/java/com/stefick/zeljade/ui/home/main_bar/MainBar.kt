package com.stefick.zeljade.ui.home.main_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefick.zeljade.R
import com.stefick.zeljade.ui.favorites.FavoriteBadge
import com.stefick.zeljade.ui.favorites.FavoriteBadgeProps

@Composable
fun MainBar(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Icon(
            modifier = modifier
                .size(32.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.pie_icon),
            contentDescription = ""
        )

        FavoriteBadge(
            modifier = Modifier.align(Alignment.CenterVertically),
            props = FavoriteBadgeProps(23)
        )

    }

}

@Preview(showBackground = true)
@Composable
internal fun MainBarPreview(modifier: Modifier = Modifier) {
    MainBar()
}