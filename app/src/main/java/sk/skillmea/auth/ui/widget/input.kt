package sk.skillmea.auth.ui.widget

import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sk.skillmea.auth.R
import sk.skillmea.auth.ui.borderColor
import sk.skillmea.auth.ui.colorGrey700
import sk.skillmea.auth.ui.hintTextStyle
import sk.skillmea.auth.ui.inputTextStyle

@Composable
fun SkillmeaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    hintText: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(Color.Black),
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (hintText.isNotEmpty()) {
            Text(hintText, style = inputTextStyle)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = inputTextStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = cursorBrush,
            decorationBox = {
                Row(
                    modifier = Modifier
                        .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                        .padding(vertical = 11.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        it()
                        if (value.isEmpty()) { Text(placeholderText, style = hintTextStyle, maxLines = 1) }
                    }
                    trailingIcon?.invoke()
                }
            })
    }
}

@Composable
fun SkillmeaPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    hintText: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onAction: (KeyboardActionScope.() -> Unit)? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource? = null,
    cursorBrush: Brush = SolidColor(Color.Black)
) {
    var show by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (hintText.isNotEmpty()) {
            Text(hintText, style = inputTextStyle)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = inputTextStyle,
            keyboardOptions = KeyboardOptions(autoCorrectEnabled = false, capitalization = KeyboardCapitalization.None, imeAction = ImeAction.Go, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onGo = onAction),
            maxLines = 1,
            minLines = 1,
            visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            cursorBrush = cursorBrush,
            decorationBox = {
                Row(
                    modifier = Modifier
                        .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                        .padding(vertical = 11.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        it()
                        if (value.isEmpty()) { Text(placeholderText, style = hintTextStyle, maxLines = 1, minLines = 1) }
                    }
                    Icon(
                        painter = painterResource(if (show) R.drawable.ic_eye_on else R.drawable.ic_eye_off),
                        contentDescription = null,
                        modifier = Modifier.clickable(indication = ripple(false, 24.dp), interactionSource = null) { show = !show }
                    )
                }
            })
    }
}

@Preview(showSystemUi = true)
@Composable
fun SkillmeaTextFieldPreview() {
    var passText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.systemBarsPadding().fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SkillmeaTextField("Sample", {}, placeholderText = "Skillmea", hintText = "Skillmea", modifier = Modifier.fillMaxWidth(), trailingIcon = { Icon(painterResource(R.drawable.ic_eye_off), contentDescription = null, tint = colorGrey700) })
        SkillmeaTextField("", {}, placeholderText = "Skillmea", hintText = "Skillmea", modifier = Modifier.fillMaxWidth(), trailingIcon = { Icon(painterResource(R.drawable.ic_eye_on), contentDescription = null, tint = colorGrey700) })
        SkillmeaPasswordTextField(passText, { passText = it }, placeholderText = "Skillmea", hintText = "Skillmea", modifier = Modifier.fillMaxWidth())
    }
}