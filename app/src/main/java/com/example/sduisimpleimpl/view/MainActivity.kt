package com.example.sduisimpleimpl.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.viewModel.ScreensViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            NavComponent(viewModel)

        }
    }

    private val viewModel: ScreensViewModel by viewModels()



    @Composable
    fun NavComponent(viewModel: ScreensViewModel) {

        val navController = rememberNavController()
        val model = viewModel.sduiModel.collectAsState().value

        if (model != null)
        {
            NavHost(navController, startDestination = "home")
            {
                model.screens.forEach { (screenKey,screenUIComponents ) ->
                    composable(screenKey) {
                        Log.d("homescreen", "screenKey = $screenKey, and screenUIComponents = $screenUIComponents")
                        GenericScreen(screenKey, screenUIComponents, model, navController)
                    }
                }
            }
        }
    }

    @Composable
    fun GenericScreen(screenKey: String, screen: SDUIModel.Screen, model: SDUIModel, navController: NavController) {
        Column {
            screen.uiComponents.forEach { componentId ->
                Log.d("GenericScreen", "Rendering componentId = $componentId for screenKey = $screenKey")
                DynamicUIComponent(componentId, model, navController)
            }
        }
    }
}



