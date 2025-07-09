package sk.skillmea.auth.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorError
import sk.skillmea.auth.ui.textStyleBodyRegular
import sk.skillmea.auth.ui.textStyleBodySemiBold
import sk.skillmea.auth.ui.widget.SkillmeaButton
import sk.skillmea.auth.ui.widget.SkillmeaPasswordTextField
import sk.skillmea.auth.ui.widget.SkillmeaTermsAndPrivacy
import sk.skillmea.auth.ui.widget.SkillmeaTextField
import sk.skillmea.auth.ui.widget.SkillmeaToolbar
import sk.skillmea.auth.ui.widget.SkillmeaToolbarType
import sk.skillmea.auth.util.isValidEmail

@Composable
fun LoginWithEmailScreen(
    onBack: () -> Unit,
    onForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillmeaToolbar("Log into account", onBack, type = SkillmeaToolbarType.BACK)
        Spacer(Modifier.height(24.dp))
        SkillmeaTextField(value = email, onValueChange = { email = it }, hintText = "Email", placeholderText = "example@example.com")
        Spacer(Modifier.height(16.dp))
        SkillmeaPasswordTextField(value = password, onValueChange = { password = it }, hintText = "Password", placeholderText = "Enter password")
        Spacer(Modifier.height(16.dp))
        if (isError) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(painterResource(R.drawable.ic_error), null, tint = colorError)
                Text("Oops! Email or password incorrect try another one.", style = textStyleBodyRegular, color = colorError)
            }
            Spacer(Modifier.height(16.dp))
        }
        SkillmeaButton("Log in", {}, enabled = email.isValidEmail && password.length >= 8)
        Spacer(Modifier.height(16.dp))
        Text("Forgot password?", modifier = Modifier.clickable { onForgotPassword() }.padding(8.dp), style = textStyleBodySemiBold)
        Spacer(Modifier.weight(1f))
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginWithEmailScreenPreview() {
    LoginWithEmailScreen({}, {})
}