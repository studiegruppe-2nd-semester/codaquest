package com.example.codaquest.ui.components.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.codaquest.ui.theme.CodaQuestTheme

@Composable
fun NavBar(
    navController: NavController
) {
    Box(modifier = Modifier
        .background(Color.Red)
        .fillMaxWidth()
        .height(70.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier
                .clickable { navController.navigate("home") },
                text = "HOME",
                fontSize = 20.sp
            )

            VerticalDivider(
                thickness = 2.dp,
                color = Color.Black
            )

            Text(modifier = Modifier
                .clickable { navController.navigate("profile") },
                text = "PROFILE",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    CodaQuestTheme {
        NavBar(rememberNavController())
    }
}
