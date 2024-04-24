package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.services.ApiService
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.navbar.NavBar

@Composable
fun HomeScreen (
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val homeScreenViewModel: HomeScreenViewModel = viewModel()

    // So we can see it the right screen delete the text if necessary

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        LazyColumn(modifier = Modifier
            .padding(5.dp)
            .padding(bottom = 70.dp)
            .fillMaxSize()

        ) {
            item {
                // your code starts here!
                Text(text = "Project generator")
                Text(text = "Here you can generate a project by answering some questions...Then we do the rest with help from chat-gpt")

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "KeyWords")
                Text(text = "Here you insert some KeyWords that you would like your project to include. fx write 'Pizza' if you want the app to be about pizza")
                TextField(
                    value = homeScreenViewModel.keyWords,
                    onValueChange = { homeScreenViewModel.updateKeyWords(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "Language")
                Text(text = "Which programmin language would you like to code in?")
                TextField(
                    value = homeScreenViewModel.language,
                    onValueChange = { homeScreenViewModel.updateLanguage(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "Length")
                Text(text = "How many hours do you wanna use on this project? See it at size of project...")
                TextField(
                    value = homeScreenViewModel.length.toString(),
                    onValueChange = { newValue -> homeScreenViewModel.updateLength(newValue) }
                )


                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "Level")
                Text(text = "Which level would you like your project to be? (It picks the one you have choose for onboarding unless you pick something else")
                TextField(
                    value = homeScreenViewModel.level,
                    onValueChange = { homeScreenViewModel.updateLevel(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Button(onClick = { sharedViewModel.promptApi(homeScreenViewModel) }) {
                    Text(text = "Generate Project")
                }


                //Text og scrollable

                Text(text = "Title:")
                Text(text = "Title: " + if (homeScreenViewModel.project.title != null) homeScreenViewModel.project.title.toString() else "")
                Text(text = "Description:")
                Text(text = if (homeScreenViewModel.project.description != null) homeScreenViewModel.project.description.toString() else "")
                Text(text = "Steps:")
                Text(text = if (homeScreenViewModel.project.steps != null) homeScreenViewModel.project.steps.toString() else "")

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = if (homeScreenViewModel.project.keywords != null) homeScreenViewModel.project.keywords.toString() else "")
                Text(text = if (homeScreenViewModel.project.language != null) homeScreenViewModel.project.language.toString() else "")
                Text(text = if (homeScreenViewModel.project.length != null) homeScreenViewModel.project.length.toString() else "")
                Text(text = if (homeScreenViewModel.project.level != null) homeScreenViewModel.project.level.toString() else "")


                Button(onClick = { homeScreenViewModel.addProject(sharedViewModel = sharedViewModel) }) {
                    Text(text = "Save project")
                }

            }
        // Your code ends here
        }

        NavBar(navController, sharedViewModel)
    }
}