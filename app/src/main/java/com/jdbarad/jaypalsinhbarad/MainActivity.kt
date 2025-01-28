package com.jdbarad.jaypalsinhbarad

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jdbarad.jaypalsinhbarad.ui.home.part1.Part1Route
import com.jdbarad.jaypalsinhbarad.ui.home.part2.Part2Route
import com.jdbarad.jaypalsinhbarad.ui.login.LoginRoute
import com.jdbarad.jaypalsinhbarad.ui.splash.SplashRoute
import com.jdbarad.jaypalsinhbarad.ui.theme.JaypalsinhBaradTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

fun showMessage(message: String) {
    coroutineScope.launch {
        snackBarHostState.showSnackbar(message)
    }
}

private lateinit var coroutineScope: CoroutineScope
private lateinit var snackBarHostState: SnackbarHostState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            coroutineScope = rememberCoroutineScope()
            snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }.value
            val navController = rememberNavController()
            val currentRoute = remember { mutableStateOf<Route>(Route.Splash) }

            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { a, b, c ->
                    when {
                        b.hasRoute(Route.Part1::class) -> currentRoute.value = Route.Part1
                        b.hasRoute(Route.Part2::class) -> currentRoute.value = Route.Part2
                        else -> currentRoute.value = Route.Splash
                    }
                }
            }

            JaypalsinhBaradTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackBarHostState) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .imePadding(),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(it.visuals.message)
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(currentRoute.value) {
                            navController.navigate(it)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Route.Splash
                    ) {
                        composable<Route.Splash> {
                            SplashRoute(
                                onSplashFinished = {
                                    navController.navigate(Route.Login) {
                                        popUpTo(Route.Splash) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }

                        composable<Route.Login> {
                            LoginRoute(onNavigateToPart1 = {
                                navController.navigate(Route.Part1) {
                                    popUpTo(Route.Login) {
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        composable<Route.Part1> {
                            Part1Route()
                        }

                        composable<Route.Part2> {
                            Part2Route()
                        }

                    }
                }
            }
        }
    }
}

@Serializable
sealed class Route {

    @Serializable
    data object Splash : Route()

    @Serializable
    data object Login : Route()

    @Serializable
    data object Part1 : Route()

    @Serializable
    data object Part2 : Route()

}

@SuppressLint("RestrictedApi")
@Composable
private fun BottomBar(currentRoute: Route, onNavigate: (Route) -> Unit) {
    val bottomRoute = listOf(Route.Part1, Route.Part2)
    if (bottomRoute.contains(currentRoute))
        NavigationBar {
            bottomRoute.forEach {
                NavigationBarItem(
                    selected = currentRoute == it,
                    onClick = {
                        onNavigate(it)
                    },
                    label = {
                        Text(it.toString())
                    },
                    icon = {
                        when (it) {
                            Route.Part1 -> Icon(Icons.Default.Home, null)
                            Route.Part2 -> Icon(Icons.Default.Person, null)
                            else -> Unit
                        }
                    }
                )
            }
        }
}