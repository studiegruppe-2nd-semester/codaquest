package com.example.codaquest.ui.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.R

@Composable
fun SettingsScreen (

) {
    Column (modifier = Modifier
        .fillMaxSize()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = "back arrow icon",
                modifier = Modifier.size(100.dp))
        }


        Box {
            Text(text = "Account setting",
                fontSize = 25.sp
            )
        }

    }
}

@Preview (showBackground = true)
@Composable
fun SettingsScreenPreview (){
    SettingsScreen()
}