package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.viewmodels.SettingsViewModel

@Composable
fun AccountOverviewComposable(
    settingsViewModel: SettingsViewModel,
    navController: NavController,
) {
    Column {
        Text(
            text = "Account Overview",
            fontSize = 30.sp,
            color = Color(0xFF6BB38A),
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "",
            fontSize = 20.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "",
            fontSize = 20.sp,
            color = Color.Black,
        )
    }
}
