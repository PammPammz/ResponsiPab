package com.example.responsipab.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.responsipab.ui.auth.LoginScreen
import com.example.responsipab.ui.auth.RegisterScreen
import com.example.responsipab.ui.camera_detail.CameraDetailScreen
import com.example.responsipab.ui.home.HomeScreen
import com.example.responsipab.ui.cart.CartScreen
import com.example.responsipab.ui.checkout.CheckoutScreen
import com.example.responsipab.ui.orders.OrderListScreen
import com.example.responsipab.ui.viewmodel.CartViewModel

@Composable
fun RentalCameraNavGraph(
    navController: NavHostController = rememberNavController(),
    cartViewModel: CartViewModel
) {
    NavHost(navController = navController, startDestination = "home") {

        composable("login") {
            LoginScreen (
                navController = navController,
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen (
                navController = navController
            )
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                onCameraClick = { camera ->
                    navController.navigate("camera_detail/${camera.slug}")
                },
                onNavigateToCart = {
                    navController.navigate("cart")
                }
            )
        }

        composable(
            route = "camera_detail/{slug}",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backStackEntry ->
            CameraDetailScreen(
                onBack = { navController.popBackStack() },
                onNavigateToCart = {
                    navController.navigate("cart")
                }
            )
        }

        composable("cart") {
            CartScreen(
                navController = navController,
            )
        }

        composable("orders") {
            OrderListScreen(
                navController = navController,
            )
        }

        composable("checkout") {
            CheckoutScreen(
                navController = navController,
            )
        }
    }
}