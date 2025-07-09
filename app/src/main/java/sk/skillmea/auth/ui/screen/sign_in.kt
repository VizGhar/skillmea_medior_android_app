package sk.skillmea.auth.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.ui.textCaptionRegular
import sk.skillmea.auth.ui.textStyleBodyRegular
import sk.skillmea.auth.ui.textStyleBodySemiBold
import sk.skillmea.auth.ui.widget.SkillmeaButton
import sk.skillmea.auth.ui.widget.SkillmeaFacebookButton
import sk.skillmea.auth.ui.widget.SkillmeaGoogleButton
import sk.skillmea.auth.ui.widget.SkillmeaTermsAndPrivacy
import sk.skillmea.auth.ui.widget.SkillmeaToolbar
import sk.skillmea.auth.ui.widget.SkillmeaToolbarType

@Composable
fun SignInScreen(onBack: () -> Unit, onEmailSignIn: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().systemBarsPadding().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillmeaToolbar("Create new account", onBack, type = SkillmeaToolbarType.BACK)
        Spacer(Modifier.height(24.dp))
        Text("Begin with creating new free account. This helps you keep your learning way easier.", style = textStyleBodyRegular, textAlign = TextAlign.Center)
        Spacer(Modifier.height(32.dp))
        SkillmeaButton("Continue with email", onEmailSignIn)
        Spacer(Modifier.height(20.dp))
        Text("or", style = textStyleBodySemiBold)
        Spacer(Modifier.height(20.dp))
        SkillmeaFacebookButton({})
        Spacer(Modifier.height(12.dp))
        SkillmeaGoogleButton({})
        Spacer(Modifier.weight(1f))
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen({}, {})
}