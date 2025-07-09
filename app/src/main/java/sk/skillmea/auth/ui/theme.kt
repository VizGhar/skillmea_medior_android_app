package sk.skillmea.auth.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val colorWhite = Color(0xFFFFFFFF)
val colorBlack = Color(0xFF000000)
val colorGrey900 = Color(0xFF3A383F)
val colorGrey800 = Color(0xFF4C4A53)
val colorGrey700 = Color(0xFF605D67)
val colorGrey300 = Color(0xFFC7C5CC)
val colorGrey100 = Color(0xFFEDECEF)
val colorViolet600 = Color(0xFF6400CD)
val colorViolet200 = Color(0xFFCFB0F0)
val colorViolet300 = Color(0xFFB88AE8)
val colorDarkGrey900 = Color(0xFF121A2C)

val borderColor = Color(0xFF9EA1A8)
val textColor = Color(0xFF121A2C)
val textColorHint = Color(0xFF9EA1A8)

val textStyleHeading3 = TextStyle(color = colorBlack, fontSize = 20.sp, letterSpacing = 0.sp, lineHeight = 28.sp, fontWeight = FontWeight.Bold)
val textStyleBodyRegular = TextStyle(color = textColor, fontSize = 16.sp, letterSpacing = 0.sp, lineHeight = 25.6.sp)
val textStyleBodySemiBold = textStyleBodyRegular.copy(fontWeight = FontWeight.SemiBold)

val textCaptionRegular = TextStyle(color = colorGrey700, fontSize = 14.sp, letterSpacing = 0.sp, lineHeight = 21.sp, fontWeight = FontWeight.Normal)
val hintTextStyle = textCaptionRegular.copy(color = textColorHint)
