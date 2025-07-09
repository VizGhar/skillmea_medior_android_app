package sk.skillmea.auth.util

import android.util.Patterns

val String.isValidEmail get() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()