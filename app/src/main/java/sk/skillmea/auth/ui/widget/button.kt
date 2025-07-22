package sk.skillmea.auth.ui.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sk.skillmea.auth.BuildConfig
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorGrey300
import sk.skillmea.auth.ui.colorGrey900
import sk.skillmea.auth.ui.colorViolet300
import sk.skillmea.auth.ui.colorViolet600
import sk.skillmea.auth.ui.colorWhite
import sk.skillmea.auth.ui.textStyleBodyRegular

@Composable
fun SkillmeaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    progress: Boolean = false,
    backgroundColor: Color? = null,
    textColor: Color? = null
) {
    Box(modifier) {
        Row(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(enabled) { onClick() }
                .background(backgroundColor ?: if (enabled) colorViolet600 else colorViolet300)
                .padding(vertical = 20.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (progress) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    strokeCap = StrokeCap.Round,
                    color = colorWhite,
                    trackColor = Color(0x00000000),
                    modifier = Modifier.size(20.dp).padding(2.dp)
                )
                Spacer(Modifier.width(10.dp))
            }
            Text(text, style = textStyleBodyRegular, fontWeight = FontWeight.SemiBold, color = textColor ?: colorWhite)
        }
    }
}

@Composable
private fun SkillmeaSocialButton(
    icon: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Row(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .border(1.dp, colorGrey300, RoundedCornerShape(12.dp))
                .padding(vertical = 20.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(icon, null)
            Spacer(Modifier.width(8.dp))
            Text(text, style = textStyleBodyRegular, fontWeight = FontWeight.Medium, color = colorGrey900)
        }
    }
}

@Composable
fun SkillmeaFacebookButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SkillmeaSocialButton(
        painterResource(R.drawable.ic_facebook),
        "Continue with Facebook",
        onClick,
        modifier
    )
}

@Composable
fun SkillmeaGoogleButton(
    onTokenReceived: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val credentialManager = CredentialManager.create(context)

    SkillmeaSocialButton(
        painterResource(R.drawable.ic_google),
        "Continue with Google",
        {
            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(BuildConfig.GOOGLE_SERVER_CLIENT_ID)
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            coroutineScope.launch(Dispatchers.IO) {
                try {
                    val result = credentialManager.getCredential(
                        request = request,
                        context = context,
                    )
                    if (result.credential.type == "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL") {
                        result.credential.data.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN")?.let {
                            onTokenReceived(it)
                        } ?: run {

                        }
                    } else {

                    }
                } catch (e: GetCredentialException) {
                    // Handle failure
                }
            }
            // google sign in
        },
        modifier
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SkillmeaButtonPreview() {
    Column(Modifier.systemBarsPadding(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SkillmeaButton("Pokus", {})
        SkillmeaButton("Pokus progress", {}, progress = true)
        SkillmeaButton("Pokus 2", {}, enabled = false)
        SkillmeaButton("Pokus 2 progress", {}, enabled = false, progress = true)
        SkillmeaFacebookButton({})
        SkillmeaGoogleButton({})
    }
}