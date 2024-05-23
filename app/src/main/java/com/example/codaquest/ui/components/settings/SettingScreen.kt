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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.codaquest.domain.models.SettingsState
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.viewmodels.LoginViewModel
import com.example.codaquest.ui.components.viewmodels.SettingsViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

// Nathasja
@Composable
fun SettingsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    loginViewModel: LoginViewModel,
) {
    val settingsViewModel: SettingsViewModel = viewModel()

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
                    .size(50.dp)
                    .clickable {
                        navController.navigate("profile")
                    },
            )
            Spacer(modifier = Modifier.width(90.dp))
            Text(
                text = "Settings",
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 7.dp),
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.DarkGray,
        )

        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (settingsViewModel.settingsState) {
                SettingsState.ChangePassword -> PasswordChangeComposable(
                    settingsViewModel = settingsViewModel,
                    navController = navController,
                )
                SettingsState.Overview -> {
                    Text(
                        text = "Account Settings",
                        fontSize = 30.sp,
                        color = Color(0xFF6BB38A),
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    ClickableTextWithDivider(
                        text1 = "Password",
                        text2 = "Change password",
                        onClick = { settingsViewModel.settingsState = SettingsState.ChangePassword },
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ClickableTextWithDivider(
                        text1 = "Personal Details",
                        text2 = "Username, email",
                        onClick = { settingsViewModel.settingsState = SettingsState.AccountOverview },
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    ClickableTextWithDivider(
                        text1 = "Delete Account",
                        text2 = "Permanently delete your account",
                        onClick = { settingsViewModel.showReLoginPopUp = true },
                        textColor = Color(0xFFB11C10),
                    )
                }
                SettingsState.AccountOverview -> {
                    AccountOverviewComposable(settingsViewModel = settingsViewModel, navController = navController, sharedViewModel = sharedViewModel)
                }
            }
        }
    }
    if (settingsViewModel.showReLoginPopUp || settingsViewModel.showPopUpDialog) {
        AlertDialog(
            onDismissRequest = {
                settingsViewModel.showReLoginPopUp = false
                settingsViewModel.showPopUpDialog = false
            },
            title = {
                if (settingsViewModel.showReLoginPopUp) {
                    Text(text = "Login")
                } else {
                    Text("Delete Account")
                }
            },
            text = {
                if (settingsViewModel.showReLoginPopUp) {
                    ReAuthLoginComposable(
                        explanationText = "Please login again to delete your account",
                        loginInfo = settingsViewModel.loginInfo,
                        onEmailChange = { settingsViewModel.loginInfo = settingsViewModel.loginInfo.copy(email = it) },
                        onPasswordChange = { settingsViewModel.loginInfo = settingsViewModel.loginInfo.copy(password = it) },
                        onReAuthSuccess = {
                            settingsViewModel.showReLoginPopUp = false
                            settingsViewModel.showPopUpDialog = true
                        },
                    )
                }
                else if (settingsViewModel.showPopUpDialog) {
                    Text(text = settingsViewModel.error)
                }
            },
            confirmButton = {
                if (settingsViewModel.showPopUpDialog && !settingsViewModel.showReLoginPopUp) {
                    Button(
                        onClick = {
                            settingsViewModel.deleteAccount(onCompleted = {
                                settingsViewModel.showPopUpDialog = false
                                navController.navigate("home")
                            })
                        },
                    ) {
                        Text(text = "Delete Account")
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        settingsViewModel.showReLoginPopUp = false
                        settingsViewModel.showPopUpDialog = false
                    },
                ) {
                    Text(text = "Cancel")
                }
            },
        )
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        NavBar(currentScreen = "profile", navController = navController, sharedViewModel = sharedViewModel)
    }
}

@Composable
fun ClickableTextWithDivider(
    text1: String,
    text2: String,
    onClick: () -> Unit,
    textColor: Color = MaterialTheme.colorScheme.primary,
) {
    Column {
        Text(
            text = text1,
            style = TextStyle(
                fontSize = 20.sp,
                color = textColor,
            ),
            modifier = Modifier
                .clickable { onClick() },
        )
        Text(
            text = text2,
            style = TextStyle(
                fontSize = 15.sp,
                color = Color(0xFF9FB4BF),
            ),
            modifier = Modifier
                .padding(top = 8.dp),
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .width(1.dp),
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}
