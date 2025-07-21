package sk.skillmea.auth.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:3000")
    .client(OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(AuthService::class.java)

data class CheckEmailRequest(val email: String)
data class CheckEmailResponse(val registerToken: String)
data class CheckCodeRequest(val code: String, val registrationToken: String)
data class CheckPasswordRequest(val password: String, val registrationToken: String)
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val authToken: String)

interface AuthService {

    @POST("/auth/signin/check_email")
    suspend fun checkEmail(@Body body: CheckEmailRequest): CheckEmailResponse

    @POST("/auth/signin/check_code")
    suspend fun checkCode(@Body body: CheckCodeRequest)

    @POST("/auth/signin/password")
    suspend fun checkPassword(@Body body: CheckPasswordRequest)

    @POST("/auth/signin/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
}