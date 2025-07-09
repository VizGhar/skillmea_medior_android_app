package sk.skillmea.auth.ui.screen

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.textStyleBodyRegular
import sk.skillmea.auth.ui.widget.SkillmeaButton
import sk.skillmea.auth.ui.widget.SkillmeaTermsAndPrivacy
import sk.skillmea.auth.ui.widget.SkillmeaTextField
import sk.skillmea.auth.ui.widget.SkillmeaToolbar
import sk.skillmea.auth.ui.widget.SkillmeaToolbarType
import sk.skillmea.auth.util.isValidEmail

@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillmeaToolbar("Reset password", onBack, type = SkillmeaToolbarType.BACK)
        Spacer(Modifier.height(24.dp))
        Text("We will email you\na link to reset your password.", style = textStyleBodyRegular, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        SkillmeaTextField(value = email, onValueChange = { email = it }, hintText = "Email", placeholderText = "example@example.com")
        Spacer(Modifier.height(16.dp))
        SkillmeaButton("Send", {}, enabled = email.isValidEmail)
        Spacer(Modifier.weight(1f))
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
fun ResetPasswordSuccessfulScreen(
    email: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillmeaToolbar("Reset password", onBack, type = SkillmeaToolbarType.BACK)
        Spacer(Modifier.height(24.dp))
        Image(painter = painterResource(R.drawable.reset_password_success), contentDescription = null)
        Spacer(Modifier.height(24.dp))
        Text("We have sent an email to $email with instructions to reset your password.", style = textStyleBodyRegular, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        SkillmeaButton("Back to login", onBack)
        Spacer(Modifier.weight(1f))
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ForgotPasswordScreenPreview() {
    ResetPasswordScreen({})
}

@Preview(showSystemUi = true)
@Composable
private fun ResetPasswordSuccessfulScreenPreview() {
    ResetPasswordSuccessfulScreen("jon@snow.com", {})
}