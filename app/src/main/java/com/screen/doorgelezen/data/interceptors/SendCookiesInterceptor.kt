package doorgelezen.lib.network.interceptors

import com.screen.doorgelezen.data.CookieJar
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class SendCookiesInterceptor @Inject constructor(private val cookieJar: CookieJar) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        cookieJar.cookies.forEach { cookie ->
            builder.addHeader("Cookie", cookie)
        }
        return chain.proceed(builder.build())
    }
}