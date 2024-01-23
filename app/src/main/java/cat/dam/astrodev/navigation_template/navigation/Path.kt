package cat.dam.astrodev.navigation_template.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

typealias Routes = List<Path>

sealed class Path {
    data class Simple(val route: String, val item: @Composable (navController: NavController) -> Unit) : Path()
    data class Children(val route: String, var item: Routes, var startDestination: String) : Path() {
        init {
            val mappedRoutes: Routes = this.item.map {
                when (it) {
                    is Simple -> Simple("${this.route}/${it.route}", it.item)
                    is Children -> Children("${this.route}/${it.route}", it.item, startDestination)
                }
            }

            this.startDestination = "${this.route}/${this.startDestination}"
            this.item = mappedRoutes
        }
    }
}
