package sk.skillmea.auth.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import sk.skillmea.auth.net.CheckCodeRequest
import sk.skillmea.auth.net.CheckEmailRequest
import sk.skillmea.auth.net.CheckEmailResponse
import sk.skillmea.auth.net.CheckPasswordRequest
import sk.skillmea.auth.net.service
import java.lang.Exception

sealed interface ApiCom<T> {
    class Idle<T>: ApiCom<T>
    class Loading<T>: ApiCom<T>
    class Success<T>(val data: T): ApiCom<T>
    class NoInternet<T>(val ex: Exception): ApiCom<T>
    class HttpError<T>(val ex: HttpException): ApiCom<T>
}

fun <T> doRequest(
    request: suspend () -> T
) = flow {
    emit(ApiCom.Loading())
    try {
        emit(ApiCom.Success(request()))
    } catch (ex: HttpException) {
        emit(ApiCom.HttpError(ex))
    } catch (ex: Exception) {
        emit(ApiCom.NoInternet(ex))
    }
}

class CreateEmailAccountViewModel : ViewModel() {

    var registrationToken: String = ""

    val emailSharedFlow = MutableSharedFlow<ApiCom<CheckEmailResponse>>()
    val codeSharedFlow = MutableSharedFlow<ApiCom<Unit>>()
    val passwordSharedFlow = MutableSharedFlow<ApiCom<Unit>>()

    fun sendEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            doRequest { service.checkEmail(CheckEmailRequest(email)) }.collect {
                emailSharedFlow.emit(it)
                if (it is ApiCom.Success) { registrationToken = it.data.registerToken }
            }
        }
    }

    fun sendCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            doRequest { service.checkCode(CheckCodeRequest(code, registrationToken)) }.collect {
                codeSharedFlow.emit(it)
            }
        }
    }

    fun sendPassword(password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            doRequest { service.checkPassword(CheckPasswordRequest(password, registrationToken)) }.collect {
                passwordSharedFlow.emit(it)
            }
        }
    }
}