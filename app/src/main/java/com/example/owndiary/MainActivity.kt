package com.example.owndiary

import android.annotation.SuppressLint
import android.database.CursorWindow
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission.Write
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.owndiary.ui.screen.*
import com.example.owndiary.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.reflect.Field


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        try {
//            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
//            field.setAccessible(true)
//            field.set(null, 100 * 1024 * 1024) //the 100MB is the new size
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

        setContent {
            OwnDiaryTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    OwnDiaryApp()
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OwnDiaryApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("new_diary") {
            WriteDiaryScreen(
                isNew = true,
                navController = navController,
                )
        }
        composable("detail_diary/{index}") {
            WriteDiaryScreen(
                isNew = false,
                navController = navController,
            )
        }
    }
}
