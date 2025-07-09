package sk.skillmea.auth.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorBlack
import sk.skillmea.auth.ui.colorDarkGrey900
import sk.skillmea.auth.ui.textCaptionRegular
import sk.skillmea.auth.ui.textStyleBodyRegular
import sk.skillmea.auth.ui.textStyleBodySemiBold
import sk.skillmea.auth.ui.widget.SkillmeaFullPasswordInput
import sk.skillmea.auth.ui.widget.SkillmeaButton
import sk.skillmea.auth.ui.widget.SkillmeaProgressIndicator
import sk.skillmea.auth.ui.widget.SkillmeaTermsAndPrivacy
import sk.skillmea.auth.ui.widget.SkillmeaTextField
import sk.skillmea.auth.ui.widget.SkillmeaToolbar
import sk.skillmea.auth.ui.widget.SkillmeaToolbarType
import sk.skillmea.auth.util.isValidEmail

@Composable
fun CreateEmailAccountScreen(
    onBack: () -> Unit
) {
    var step by remember { mutableIntStateOf(0) }
    var email by remember { mutableStateOf("") }
    BackHandler(step > 0) { step-- }

    CreateAccountScreenContent(
        when(step) {
            0 -> "Add your email 1/3"
            1 -> "Verify your email 2/3"
            else -> "Create your password 3/3"
        },
        {
            when (step) {
                0 -> CreateAccountScreenStep1()
                1 -> CreateAccountScreenStep2(email)
                else -> CreateAccountScreenStep3()
            }
        },
        onBack = onBack,
        step = step
    )
}

@Composable
fun CreateEmailAccountSuccessfulScreen(
    goToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Image(painter = painterResource(R.drawable.account_created), contentDescription = null)
        Spacer(Modifier.height(32.dp))
        Text("Your account\nwas successfully created!", color = colorBlack, fontWeight = FontWeight.Black, fontSize = 28.sp, lineHeight = 40.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Text("Only one click to explore online education.", style = textStyleBodyRegular, textAlign = TextAlign.Center)
        Spacer(Modifier.height(24.dp))
        SkillmeaButton("Log in", goToLogin)
        Spacer(Modifier.weight(1f))
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
private fun CreateAccountScreenContent(
    toolbarText: String,
    content: @Composable ColumnScope.() -> Unit,
    onBack: () -> Unit,
    step: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SkillmeaToolbar(toolbarText, onBack, type = SkillmeaToolbarType.BACK)
        SkillmeaProgressIndicator(step, 3)
        Spacer(Modifier.height(24.dp))
        content()
        SkillmeaTermsAndPrivacy()
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
private fun ColumnScope.CreateAccountScreenStep1() {
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.weight(1f).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SkillmeaTextField(
            email,
            { email = it },
            placeholderText = "example@example.com",
            hintText = "Email"
        )
        SkillmeaButton("Create an account", onClick = {}, enabled = email.isValidEmail)
    }
}

@Composable
private fun ColumnScope.CreateAccountScreenStep2(email: String) {
    var code by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(code) {
        if (code.length == 5) {
            focusManager.clearFocus()
        }
    }

    Column(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("We just sent 5-digit code to $email, enter it bellow:", style = textStyleBodyRegular, textAlign = TextAlign.Center)
        Spacer(Modifier.height(15.dp))
        BasicTextField(code, { code = it.take(5) }, modifier = Modifier.drawWithContent { }.height(1.dp).focusRequester(focusRequester))
        Text("Code", style = textCaptionRegular, color = colorDarkGrey900, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            for (i in 0..4) { SkillmeaCodeBox(code, i, focusRequester) }
        }
        Spacer(Modifier.height(16.dp))
        SkillmeaButton("Verify email", {}, enabled = code.length == 5)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Wrong email?", style = textStyleBodyRegular)
            Text("Send to different email", style = textStyleBodySemiBold, modifier = Modifier.clickable{}.padding(8.dp))
        }
    }
}

@Composable
private fun ColumnScope.CreateAccountScreenStep3() {
    var password by remember { mutableStateOf("") }
    var passwordStatus by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .weight(1f)
        .fillMaxWidth()) {
        SkillmeaFullPasswordInput(password, { password = it }, { passwordStatus = it })
        Spacer(Modifier.height(16.dp))
        SkillmeaButton("Continue", { }, enabled = passwordStatus)
    }
}

@Composable
private fun RowScope.SkillmeaCodeBox(
    code: String,
    position: Int,
    focusRequester: FocusRequester
) {
    SkillmeaTextField(
        value = code.getOrNull(position)?.toString() ?: "",
        onValueChange = {},
        modifier = Modifier.weight(1f).onFocusChanged { focusRequester.requestFocus() },
        center = true,
        glow = code.length == position
    )
}

@Preview(showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview1() {
    CreateAccountScreenContent("Add your email 1/3", { CreateAccountScreenStep1() }, onBack = {}, step = 0)
}

@Preview(showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview2() {
    CreateAccountScreenContent("Verify your email 2/3", { CreateAccountScreenStep2("jon@snow.com") }, onBack = {}, step = 1)
}

@Preview(showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview3() {
    CreateAccountScreenContent("Create your password 3/3", { CreateAccountScreenStep3() }, onBack = {}, step = 2)
}

@Preview(showSystemUi = true)
@Composable
private fun CreateEmailAccountSuccessfulScreenPreview() {
    CreateEmailAccountSuccessfulScreen({ })
}