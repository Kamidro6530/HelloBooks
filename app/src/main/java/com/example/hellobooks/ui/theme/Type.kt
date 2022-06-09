package com.example.hellobooks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.hellobooks.R

val roboto_fonts = FontFamily(
    Font(R.font.roboto_bold, weight = FontWeight.Bold), // Pogrubiona
    Font(R.font.roboto_regular, weight = FontWeight.Normal) , //Klasyczna czcionka
    Font(R.font.roboto_medium, weight = FontWeight.Medium) , // Lekko pogrubiona
    Font(R.font.roboto_black, weight = FontWeight.Black) , //Podobny jak bold pogrubiona czcionka
    Font(R.font.roboto_italic), //Pochylona
    Font(R.font.roboto_light, weight = FontWeight.Light) , //Chudsza czcionka między Thin a regular
    Font(R.font.roboto_thin, weight = FontWeight.Thin) , //Bardzo cienki do podpisów/wyjaśnień

)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = roboto_fonts,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)