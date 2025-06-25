package com.example.responsipab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.getValue
import com.example.responsipab.ui.auth.LoginScreen
import com.example.responsipab.ui.auth.RegisterScreen
import com.example.responsipab.ui.camera_detail.CameraDetailScreen
import com.example.responsipab.ui.home.HomeScreen
import com.example.responsipab.ui.cart.CartScreen
import com.example.responsipab.ui.checkout.CheckoutScreen
import com.example.responsipab.ui.orders.OrderListScreen
import com.example.responsipab.ui.viewmodel.CartViewModel
import com.example.responsipab.ui.orders.OrderViewModel

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
            val cameraId = backStackEntry.arguments?.getString("slug")
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
            val orderViewModel: OrderViewModel = viewModel()
            val orders by orderViewModel.orders.collectAsState()

            OrderListScreen(
                navController = navController,
                orders = orders,
                onOrderClick = { /* navigate to detail */ },
            )
        }

        composable("checkout") {
            val uiState by cartViewModel.uiState.collectAsState()

            CheckoutScreen(
                navController = navController,
            )
        }
    }
}