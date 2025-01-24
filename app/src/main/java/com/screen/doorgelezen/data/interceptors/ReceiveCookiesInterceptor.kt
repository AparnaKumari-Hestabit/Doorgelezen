package doorgelezen.lib.network.interceptors

import com.screen.doorgelezen.data.CookieJar
import com.screen.doorgelezen.utils.printDebug
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ReceiveCookiesInterceptor @Inject constructor(private val cookieJar: CookieJar) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        cookieJar.addCookies(response.headers.values("Set-Cookie").toSet())
        printDebug(response.headers.values("Set-Cookie").toString())
        return response
    }
}