package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.viewmodels.SettingsViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun AccountOverviewComposable(
    settingsViewModel: SettingsViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavController,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = "Account Overview",
            fontSize = 30.sp,
            color = Color(0xFF6BB38A),
        )
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = MaterialTheme.colorScheme.secondary)
                .padding(15.dp, 10.dp),
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            sharedViewModel.user?.username?.let { username ->
                Text(
                    text = "Username: $username ",
                    fontSize = 20.sp,
                    color = Color.Black,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "",
                fontSize = 20.sp,
                color = Color.Black,
            )
        }
    }
}
