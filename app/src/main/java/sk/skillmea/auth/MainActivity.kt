package sk.skillmea.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.facebook.FacebookSdk
import sk.skillmea.auth.ui.screen.CreateEmailAccountScreen
import sk.skillmea.auth.ui.screen.CreateEmailAccountSuccessfulScreen
import sk.skillmea.auth.ui.screen.LandingScreen
import sk.skillmea.auth.ui.screen.LogInScreen
import sk.skillmea.auth.ui.screen.LoginWithEmailScreen
import sk.skillmea.auth.ui.screen.ResetPasswordScreen
import sk.skillmea.auth.ui.screen.ResetPasswordSuccessfulScreen
import sk.skillmea.auth.ui.screen.SignInScreen

sealed interface Screen {
    data object Landing : Screen
    data object SignIn : Screen
    data object LogIn : Screen
    data object CreateEmailAccount : Screen
    data object CreateEmailAccountSuccess : Screen
    data object LoginWithEmail : Screen
    data object ResetPassword : Screen
    data object ResetPasswordSuccess : Screen
    data object Home : Screen
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.setAutoInitEnabled(true)
        enableEdgeToEdge()
        setContent {
            var backStack by remember { mutableStateOf(listOf<Screen>(Screen.Landing)) }
            val goBack = { backStack = backStack.dropLast(1) }
            BackHandler(backStack.size > 1, goBack)
            AnimatedContent(backStack.last()) {
                when(it) {
                    Screen.Landing -> LandingScreen(onSignIn = { backStack += Screen.SignIn }, onLogIn = { backStack += Screen.LogIn })

                    Screen.SignIn -> SignInScreen(onBack = goBack, onEmailSignIn = { backStack += Screen.CreateEmailAccount })
                    Screen.CreateEmailAccount -> CreateEmailAccountScreen(goBack) { backStack += Screen.CreateEmailAccountSuccess }
                    Screen.CreateEmailAccountSuccess -> CreateEmailAccountSuccessfulScreen { backStack = backStack.dropLastWhile { it !is Screen.Landing } + Screen.LogIn }

                    Screen.LogIn -> LogInScreen(onBack = goBack, onEmailLogIn = { backStack += Screen.LoginWithEmail})
                    Screen.LoginWithEmail -> LoginWithEmailScreen(onBack = goBack, onForgotPassword = { backStack += Screen.ResetPassword })
                    Screen.ResetPassword -> ResetPasswordScreen(onBack = goBack)
                    Screen.ResetPasswordSuccess -> ResetPasswordSuccessfulScreen(email = "???", onBack = goBack)

                    Screen.Home -> HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Box(contentAlignment = Alignment.Center) {
        Text("HOME")
    }
}