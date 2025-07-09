package sk.skillmea.auth.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.ui.colorGrey100
import sk.skillmea.auth.ui.colorViolet300

@Composable
fun SkillmeaProgressIndicator(
    actualStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier
) {
    if (actualStep >= totalSteps) { throw IllegalArgumentException() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        repeat(totalSteps) {
            Box(
                Modifier
                    .size(20.dp, 4.dp)
                    .background(color = if (it <= actualStep) colorViolet300 else colorGrey100, shape = RoundedCornerShape(2.dp))
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SkillmeaProgressIndicatorPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().systemBarsPadding()) {
        SkillmeaProgressIndicator(0, 3)
        SkillmeaProgressIndicator(1, 3)
        SkillmeaProgressIndicator(2, 3)
    }
}