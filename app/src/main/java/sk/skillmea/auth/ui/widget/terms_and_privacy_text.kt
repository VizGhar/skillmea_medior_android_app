package sk.skillmea.auth.ui.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import sk.skillmea.auth.ui.textCaptionRegular

@Composable
fun SkillmeaTermsAndPrivacy() {
    val bottomText = buildAnnotatedString {
        append("By using Classroom, you agree to the\n")
        withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("Terms") }
        append(" and ")
        withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("Privacy Policy") }
        append(".")
    }
    Text(bottomText, style = textCaptionRegular, textAlign = TextAlign.Center)
}