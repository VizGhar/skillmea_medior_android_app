package sk.skillmea.auth.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.colorWhite
import sk.skillmea.auth.ui.textStyleHeading3

enum class SkillmeaToolbarType(@DrawableRes val iconRes: Int?) {
    NONE(null),
    BACK(R.drawable.ic_arrow_back)
}

@Composable
fun SkillmeaToolbar(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: SkillmeaToolbarType = SkillmeaToolbarType.NONE
) {
    Box(modifier.height(60.dp).fillMaxWidth()) {
        Text(title, style = textStyleHeading3, modifier = Modifier.align(Alignment.Center))
        type.iconRes?.let {
            IconButton(onClick, modifier = Modifier.align(Alignment.CenterStart)) { Icon(painterResource(it), null) }
        }
    }
}

@Preview
@Composable
private fun SkillmeaToolbarPreview(

) {
    Column(Modifier.fillMaxWidth().background(colorWhite)) {
        SkillmeaToolbar("Home page", {}, Modifier, SkillmeaToolbarType.NONE)
        SkillmeaToolbar("Log into account", {}, Modifier, SkillmeaToolbarType.BACK)
    }
}