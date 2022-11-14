package com.example.hellobooks.screens.book.screen_types

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.darkgreybackground
import com.example.hellobooks.ui.theme.primary
import com.example.hellobooks.ui.theme.tertiary

@Composable
fun ManageBookInformationScreen() {


    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(330.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = darkgreybackground,
            contentColor = primary
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f)
        ) {
              Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                  RatingBar(modifier = Modifier.padding(start = 5.dp),rating = 3.7)

                }
        }
    }


}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = tertiary,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                painterResource(id = R.drawable.star_fill_24),
                contentDescription = null,
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                painterResource(id = R.drawable.star_half_24),
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                painterResource(id = R.drawable.star_24),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}