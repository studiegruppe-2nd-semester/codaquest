package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.R
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Row(modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
            ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back arrow icon",
                modifier = Modifier.size(70.dp),
            )
            Spacer(modifier = Modifier.width(60.dp))
            Text(
                text = "Setting",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

        }

        Box(modifier = Modifier.padding(top = 15.dp)) {
            Text(
                text = "Account setting",
                fontSize = 25.sp,
            )

        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
*/
