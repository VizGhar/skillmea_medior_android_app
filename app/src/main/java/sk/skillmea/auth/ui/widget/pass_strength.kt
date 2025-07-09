package sk.skillmea.auth.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorGrey800
import sk.skillmea.auth.ui.textStyleBodyRegular

@Composable
fun PasswordStrengthValidator(
    password: String,
    modifier: Modifier = Modifier,
    validatorTexts: List<String> = listOf(
        "8 characters minimum",
        "a number",
        "a symbol",
    ),
    validators: List<(String) -> Boolean> = listOf(
        { it.length >= 8 },
        { it.any { it.isDigit() } },
        { it.any { !it.isLetterOrDigit() }}
    ),
    colors: List<Color> = listOf(
        Color(0xFFD62C01),
        Color(0xFFFAAE16),
        Color(0xFF498200)
    )
) {
    if (validatorTexts.size != validators.size || validatorTexts.size != colors.size) {
        throw IllegalArgumentException()
    }

    val passed = validators.map { it(password) }
    val color = (passed.count { it } - 1).takeIf { it >= 0 }?.let{ colors[it] }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(modifier = Modifier.fillMaxWidth().height(8.dp).background(Color(0xFFEDECEF), RoundedCornerShape(4.dp))) {
            if (color != null) {
                Box(modifier = Modifier.fillMaxWidth(passed.count { it } / validators.size.toFloat()).height(8.dp).background(color, RoundedCornerShape(4.dp)))
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in validators.indices) {
                PasswordValidatorRow(passed[i], validatorTexts[i])
            }
        }
    }
}

@Composable
fun FullPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SkillmeaPasswordTextField(value, onValueChange)
        PasswordStrengthValidator(value)
    }
}

@Composable
private fun PasswordValidatorRow(
    check: Boolean,
    text: String
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        if (check) {
            Image(painterResource(R.drawable.ic_check), null)
        } else {
            Box(Modifier.border(1.dp, Color(0xFF9D9AA4), CircleShape).size(16.dp))
        }
        Text(text, style = textStyleBodyRegular, color = colorGrey800)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PasswordStrengthValidatorPreview() {
    PasswordStrengthValidator("ord1#", modifier = Modifier.systemBarsPadding())
}

@Preview(showSystemUi = true)
@Composable
private fun FullPasswordInputPreview() {
    FullPasswordInput("ord1#", {}, modifier = Modifier.systemBarsPadding())
}