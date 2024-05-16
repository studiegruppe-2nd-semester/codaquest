package com.example.codaquest.ui.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.codaquest.ui.components.onboarding.ProjectSecondQuestion
import com.example.codaquest.ui.components.viewmodels.HomeScreenViewModel
import com.example.codaquest.ui.components.viewmodels.SharedViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    val viewModel: HomeScreenViewModel = viewModel()

    // So we can see it the right screen delete the text if necessary

    Column(
        modifier = Modifier
            .padding(0.5.dp)
            .fillMaxSize(),
    ) {
        // PROGRESS BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(0.1f),
                contentPadding = PaddingValues(0.dp),
                onClick = { navController.navigate("onboarding") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
            ) {
                Text(text = "Cancel")
            }

            LinearProgressIndicator(
                progress = { (viewModel.currentQuestion + 1).toFloat() / viewModel.questions.size.toFloat() },
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(0.8f),
                color = MaterialTheme.colorScheme.primary, // progress color
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    text = "${viewModel.currentQuestion + 1}/${viewModel.questions.size}",
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center,
        ) {
            when (viewModel.questions[viewModel.currentQuestion]) {
                viewModel.questions[0] -> ProjectFirstQuestion(viewModel = viewModel)
                viewModel.questions[1] -> ProjectSecondQuestion(viewModel = viewModel)
                viewModel.questions[2] -> ProjectFirstQuestion(viewModel = viewModel)
                viewModel.questions[3] -> ProjectIntFieldComposable(viewModel = viewModel)
            }
        }



        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { viewModel.previousQuestion() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (viewModel.currentQuestion == 0) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary,
                ),
            ) {
                Text("Previous")
            }

            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {


                    // Her skal der til sidst generes et projekt.
                          viewModel.nextQuestion()



                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Text(viewModel.nextButton)
            }
        }




    }}

/*
sharedViewModel.getProjectSuggestion(
viewModel.generateProjectDetails,
onError = { error -> viewModel.showError(error) },
) */



/* {
    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
            .padding(bottom = 65.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        item {
            // your code starts here!
            Text(
                text = "Project generator",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .wrapContentSize(Alignment.Center)
                    .padding(top = 20.dp, bottom = 20.dp),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color(0xFF6BB38A),
                lineHeight = 45.sp,
            )
            Text(
                text = "Here you can generate a project by answering some questions...Then we do the rest with help from chat-gpt",
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Text(
                text = "KeyWords",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                ),

            )
            Text(
                text = "Here you insert some KeyWords that you would like your project to include. fx write 'Pizza' if you want the app to be about pizza",
                modifier = Modifier
                    .padding(horizontal = 16.dp), // Adjust padding as needed
                textAlign = TextAlign.Center,
            )
            TextField(
                value = homeScreenViewModel.generateProjectDetails.keywords,
                onValueChange = { homeScreenViewModel.generateProjectDetails = homeScreenViewModel.generateProjectDetails.copy(keywords = it) },
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Text(
                text = "Language",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
            Text(
                text = "Which programming language would you like to code in?",
                modifier = Modifier
                    .padding(horizontal = 16.dp), // Adjust padding as needed
                textAlign = TextAlign.Center,
            )
            TextField(
                value = homeScreenViewModel.generateProjectDetails.language,
                onValueChange = { homeScreenViewModel.generateProjectDetails = homeScreenViewModel.generateProjectDetails.copy(language = it) },
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Text(
                text = "Hours",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
            Text(
                text = "How many hours do you wanna use on this project? See it at size of project?",
                modifier = Modifier.padding(horizontal = 16.dp), // Adjust padding as needed
                textAlign = TextAlign.Center,
            )

            TextField(
                value = if (homeScreenViewModel.generateProjectDetails.length == 0) "" else homeScreenViewModel.generateProjectDetails.length.toString(),
                onValueChange = { homeScreenViewModel.generateProjectDetails = homeScreenViewModel.generateProjectDetails.copy(length = it.toIntOrNull() ?: 0) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Text(
                text = "Level",
                modifier = Modifier
                    .padding(bottom = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary,
                ),
            )
            Text(
                text = "Which level would you like your project to be?",
                modifier = Modifier
                    .padding(horizontal = 16.dp), // Adjust padding as needed
                textAlign = TextAlign.Center,
            )

            TextField(
                value = homeScreenViewModel.generateProjectDetails.level.toString(),
                onValueChange = { homeScreenViewModel.generateProjectDetails = homeScreenViewModel.generateProjectDetails.copy(level = stringToLevelType(it)) },
            )

            Spacer(
                modifier = Modifier
                    .padding(top = 30.dp),
            )

            Button(onClick = {
                sharedViewModel.getProjectSuggestion(
                    homeScreenViewModel.generateProjectDetails,
                    onError = { error -> homeScreenViewModel.showError(error) },
                )
            }) {
                Text(text = "Generate Project")
            }

            Spacer(
                modifier = Modifier
                    .padding(top = 20.dp),
            )

            if (!sharedViewModel.generatedProject.title.isNullOrEmpty()) {
                ProjectComposable(
                    project = sharedViewModel.generatedProject,
                    onDelete = { },
                )

                Button(onClick = {
                    sharedViewModel.user?.userUid?.let { uid ->
                        sharedViewModel.saveProject(uid)
                        navController.navigate("saved-projects")
                    }
                }) {
                    Text(text = "Save project")
                }
            }
        }
        // Your code ends here
    }

    NavBar("home", navController, sharedViewModel)
}
}
*/