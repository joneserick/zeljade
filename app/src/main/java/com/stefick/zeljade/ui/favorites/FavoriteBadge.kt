package com.stefick.zeljade.ui.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stefick.zeljade.R

@Composable
fun FavoriteBadge(
    modifier: Modifier = Modifier,
    props: FavoriteBadgeProps
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(16.dp))
            .shadow(elevation = 2.dp)
            .background(color = colorResource(id = R.color.radFootprint))
            .padding(vertical = 4.dp, horizontal = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
                .padding(end = 4.dp),
            imageVector = Icons.Rounded.Favorite,
            tint = Color.Red,
            contentDescription = ""
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = props.quantity.toString()
        )

        if (props.isHighQuantity)
            Icon(
                modifier = Modifier.size(6.dp),
                imageVector = Icons.Sharp.Add,
                contentDescription = ""
            )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteBadgePreview() {
    FavoriteBadge(props = FavoriteBadgeProps(99))
}

@Preview(showBackground = true)
@Composable
fun FavoriteBadgeHighQuantityPreview() {
    FavoriteBadge(props = FavoriteBadgeProps(99, true))
}
