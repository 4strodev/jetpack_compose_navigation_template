package cat.dam.astrodev.navigation_template.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import cat.dam.astrodev.navigation_template.screens.MainScreen


val routes: Routes = listOf(
    Path.Children(
        route = "screens",
        startDestination = "1",
        item = listOf(
            Path.Simple("1") { MainScreen() }
        )
    ),
)

@Composable
fun AppNavigation(navController: NavHostController, routes: Routes) {
    NavHost(navController = navController, startDestination = "screens") {
        createRoutes(navController, routes)
    }
}

fun NavGraphBuilder.createRoutes(navController: NavHostController, routes: Routes) {
    routes.forEach { path ->
        when (path) {
            is Path.Simple -> composable(path.route) { path.item(navController) }
            is Path.Children -> navigation(
                route = path.route,
                startDestination = path.startDestination
            ) {
                createRoutes(navController, path.item)
            }
        }
    }
}
