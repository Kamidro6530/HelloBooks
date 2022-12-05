package com.example.hellobooks.screens.book.screen_types

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.hellobooks.R
import com.example.hellobooks.ui.theme.*

@Composable
fun ManageBookInformationScreen() {

    var ratingSliderPosition by remember { mutableStateOf(0F) }

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
                  horizontalAlignment = Alignment.CenterHorizontally,
                  modifier = Modifier.wrapContentHeight()
                ) {
                  RatingBar(modifier = Modifier,rating = ratingSliderPosition)
                  Spacer(modifier = Modifier.height(3.dp))
                  RatingBarSlider(onValueChange = {ratingSliderPosition = it}, sliderValue = ratingSliderPosition)
                  Spacer(modifier = Modifier.height(3.dp))

                }
        }
    }


}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float = 0.0F,
    stars: Int = 10,
    starsColor: Color = tertiary,
) {

    val filledStars = kotlin.math.floor(rating).toInt()
    val unfilledStars = (stars - kotlin.math.ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0F))
    val starSize = 30.dp
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                painterResource(id = R.drawable.star_fill_24),
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(starSize)
            )
        }

        if (halfStar) {
            Icon(
                painterResource(id = R.drawable.star_half_24),
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(starSize)
            )
        }

        repeat(unfilledStars) {
            Icon(
                painterResource(id = R.drawable.star_24),
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(starSize)
            )
        }
    }
}

@Composable
fun RatingBarSlider(onValueChange: (Float) -> Unit, sliderValue: Float) {
    Slider(onValueChange = onValueChange, value = sliderValue, modifier = Modifier.padding(start = 10.dp, end = 10.dp), valueRange = 0F..10F, steps = 19, colors = SliderDefaults.colors(thumbColor = tertiary, activeTrackColor = secondary, activeTickColor = tertiary))
}