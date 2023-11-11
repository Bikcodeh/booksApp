package com.bikcode.booksapp.navigation

sealed class Screens(val route: String) {
    object Splash : Screens(route = "splash_screen")
    object Home : Screens(route = "home_screen")
    object Login : Screens(route = "login_screen")
    object SignUp : Screens(route = "signup_screen")
    object ChangePassword : Screens(route = "change_password_screen")
}
