package sk.skillmea.auth.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorBlack
import sk.skillmea.auth.ui.colorWhite
import sk.skillmea.auth.ui.textStyleBodyRegular
import sk.skillmea.auth.ui.widget.SkillmeaButton
import java.lang.IllegalStateException

@Composable
fun LandingScreen(
    onSignIn: () -> Unit,
    onLogIn: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        val state = rememberPagerState { 3 }
        HorizontalPager(state) { page ->
            val res = when(page) {
                0 -> R.drawable.onboarding_1
                1 -> R.drawable.onboarding_2
                2 -> R.drawable.onboarding_3
                else -> throw IllegalStateException()
            }
            Image(
                painter = painterResource(res),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(Modifier.align(Alignment.BottomCenter).padding(vertical = 32.dp, horizontal = 16.dp).navigationBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(3) {
                        Box(Modifier.size(8.dp).background(Color(0xFF909090), CircleShape))
                    }
                }
                Box(Modifier.size(8.dp).offset(
                    (state.currentPage * 12).dp + (state.currentPageOffsetFraction * 12).dp
                ).background(Color.White, CircleShape))
            }
            Text("Welcome to Classroom", color = colorWhite, fontWeight = FontWeight.Black, fontSize = 28.sp, lineHeight = 40.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(12.dp))
            Text("Join over 10.000 learners over the World and enjoy online education!", style = textStyleBodyRegular, color = colorWhite, textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))
            SkillmeaButton("Create an account", { onSignIn() }, backgroundColor = colorWhite, textColor = colorBlack)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Already have an account?", color = colorWhite, style = textStyleBodyRegular)
                Text("Log in", modifier = Modifier.clickable { onLogIn() }.padding(8.dp), color = colorWhite, style = textStyleBodyRegular, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen({}, {})
}