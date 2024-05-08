package com.example.codaquest.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.NavBarOption(
    contentDescription: String,
    painterResourceId: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .padding(5.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
//        Text(
//            text = contentDescription,
//            fontSize = 20.sp,
//            color = Color.White,
//            textAlign = TextAlign.Center
//        )
        Image(
            painter = painterResource(id = painterResourceId),
            contentDescription = contentDescription,
        )
    }
}
