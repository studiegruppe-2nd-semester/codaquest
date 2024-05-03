package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.Models.Project
import com.example.codaquest.ui.components.SharedViewModel
import com.example.codaquest.ui.components.navbar.NavBar
import com.example.codaquest.ui.components.project.ProjectComposable

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
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            item {
                // your code starts here!
                Text(text = "Project generator",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .wrapContentSize(Alignment.Center)
                        .padding(top = 20.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                    color = Color(0xFF6BB38A),
                    lineHeight = 45.sp
                )
                Text(text = "Here you can generate a project by answering some questions...Then we do the rest with help from chat-gpt",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center)

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "KeyWords",
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                )
                Text(text = "Here you insert some KeyWords that you would like your project to include. fx write 'Pizza' if you want the app to be about pizza",
                    modifier = Modifier
                        .padding(horizontal = 16.dp), // Adjust padding as needed
                    textAlign = TextAlign.Center)
                TextField(
                    value = homeScreenViewModel.keyWords,
                    onValueChange = { homeScreenViewModel.updateKeyWords(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )


                Text(text = "Language",
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(text = "Which programming language would you like to code in?",
                    modifier = Modifier
                        .padding(horizontal = 16.dp), // Adjust padding as needed
                    textAlign = TextAlign.Center)
                TextField(
                    value = homeScreenViewModel.language,
                    onValueChange = { homeScreenViewModel.updateLanguage(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "Hours",
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(text = "How many hours do you wanna use on this project? See it at size of project?",
                    modifier = Modifier.padding(horizontal = 16.dp), // Adjust padding as needed
                    textAlign = TextAlign.Center)


                TextField(
                    value = if (homeScreenViewModel.length == 0) "" else homeScreenViewModel.length.toString(),
                    onValueChange = { newValue -> homeScreenViewModel.updateLength(newValue) }
                )


                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Text(text = "Level",
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(text = "Which level would you like your project to be?",
                    modifier = Modifier
                        .padding(horizontal = 16.dp), // Adjust padding as needed
                    textAlign = TextAlign.Center)
                TextField(
                    value = homeScreenViewModel.level,
                    onValueChange = { homeScreenViewModel.updateLevel(it) })

                Spacer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                )

                Button(onClick = { sharedViewModel.promptApi(
                    Project(
                        level = homeScreenViewModel.level,
                        language = homeScreenViewModel.language,
                        keywords = homeScreenViewModel.keyWords,
                        length = homeScreenViewModel.length
                    ),
                    onSuccess = {
                        sharedViewModel.project = it
                    }
                ) }) {
                    Text(text = "Generate Project")
                }

                Spacer(modifier = Modifier
                    .padding(top = 20.dp),)

                /*

                //Text og scrollable
                Row {
                    Text(text = "Title: ",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ))
                    Text(text = if (homeScreenViewModel.project.title != null) homeScreenViewModel.project.title.toString() else "")

                }

                Row {
                    Text(text = "Description:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ))
                    Text(text = if (homeScreenViewModel.project.description != null) homeScreenViewModel.project.description.toString() else "")
                }

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

                */

                if (!sharedViewModel.project.title.isNullOrEmpty()) {

                    ProjectComposable(project = sharedViewModel.project)

                    Button(onClick = { sharedViewModel.user?.userUid?.let { uid ->
                        sharedViewModel.addProject(
                            uid,
                            onSuccess = { project -> sharedViewModel.addProject(project) }
                        )
                        navController.navigate("profile")
                    } }) {
                        Text(text = "Save project")
                    }
                }

            }
        // Your code ends here
        }

        NavBar(navController, sharedViewModel)
    }
}