package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.viewmodels.SettingsViewmodel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

// Nathasja
@Composable
fun SettingsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val settingsViewModel: SettingsViewmodel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back arrow icon",
                modifier = Modifier
                    .size(70.dp)
                    .clickable {
                        navController.navigate("profile")
                    },
            )
            Spacer(modifier = Modifier.width(60.dp))
            Text(
                text = "Setting",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 15.dp),
            )
        }

        Column(
            modifier = Modifier.padding(top = 15.dp),
        ) {
            Text(
                text = "Account Settings",
                fontSize = 30.sp,
                color = Color(0xFF6BB38A)
            )
            Spacer(modifier = Modifier.height(15.dp))
            ClickableTextWithDivider(text = "Password") {
                TODO("Needs what it should change to from the viewmodel")
            }
            Spacer(modifier = Modifier.height(10.dp))
            ClickableTextWithDivider(text = "Personal Details") {
            }
        }
    }
    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar(currentScreen = "settings", navController = navController, sharedViewModel = sharedViewModel)
    }
}

@Composable
fun ClickableTextWithDivider(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .clickable { onClick() },
    )
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 3.dp,
        color = MaterialTheme.colorScheme.tertiary,
    )
}
