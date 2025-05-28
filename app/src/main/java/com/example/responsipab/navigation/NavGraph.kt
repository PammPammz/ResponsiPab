package com.example.responsipab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.responsipab.ui.camera_detail.CameraDetailScreen
import com.example.responsipab.ui.home.HomeScreen

@Composable
fun RentalCameraNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(onCameraClick = { camera ->
                navController.navigate("camera_detail/${camera.id}")
            })
        }

        composable(
            route = "camera_detail/{cameraId}",
            arguments = listOf(navArgument("cameraId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cameraId = backStackEntry.arguments?.getInt("cameraId")
            CameraDetailScreen(
                cameraId = cameraId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
