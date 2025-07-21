package sk.skillmea.auth.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import sk.skillmea.auth.data.storeAuthToken
import sk.skillmea.auth.net.LoginRequest
import sk.skillmea.auth.net.LoginResponse
import sk.skillmea.auth.net.service

class LoginEmailViewModel : ViewModel() {

    val loginSharedFlow = MutableSharedFlow<ApiCom<LoginResponse>>()

    fun login(email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            doRequest { service.login(LoginRequest(email, pass)) }.collect {
                if (it is ApiCom.Success) { storeAuthToken(it.data.authToken) }
                loginSharedFlow.emit(it)
            }
        }
    }
}