package com.example.codaquest.ui.components.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.codaquest.classes.Project
import com.example.codaquest.repositories.ProjectRepository
import com.example.codaquest.services.ApiService
import com.example.codaquest.ui.components.SharedViewModel
import org.json.JSONObject


class HomeScreenViewModel () : ViewModel () {
    private val projectRepository : ProjectRepository = ProjectRepository()


    var keyWords: String by mutableStateOf("")
        private set

    fun updateKeyWords(newKeyWords: String) {
        keyWords = newKeyWords
    }

    var language: String by mutableStateOf("")
        private set

    fun updateLanguage(newLanguage: String) {
        language = newLanguage
    }

    var length: Int by mutableIntStateOf(0)
        private set

    fun updateLength(newLength: String) {
        length = try {
            newLength.toInt()
        } catch (e: NumberFormatException) {
            // Handle the case where the input cannot be converted to an integer
            // For example, you can set a default value or display an error message
            0 // Set a default value of 0
        }
    }

    var level: String by mutableStateOf("")
        private set

    fun updateLevel(newLevel: String) {
        level = newLevel
    }


    var project: Project = Project(
        title = "test",
        keywords = "test",
        language = "test",
        length = 10,
        level = "test",

        description = "test",

        steps = mutableListOf(
            "test 1",
            "test 2",
            "test 4000"
        )

    )



    fun getJsonIntoHashMap (apiRespond: JSONObject) : HashMap<String, Any> {
        val apiRespondHashMap = hashMapOf<String,Any>()

        val jsonObjectKeys = apiRespond.keys()
        while (jsonObjectKeys.hasNext()) {
            val key = jsonObjectKeys.next()
            val value = apiRespond.get(key)
            apiRespondHashMap[key] = value
        }

        /*
        for ((key, value) in apiRespondHashMap) {
            println("Key: $key, Value: $value")
        }
         */

        return apiRespondHashMap
    }

    fun addProject (
        sharedViewModel: SharedViewModel
    ) {
        projectRepository.addProject(project.copy(uid = sharedViewModel.user?.userUid))
    }


}