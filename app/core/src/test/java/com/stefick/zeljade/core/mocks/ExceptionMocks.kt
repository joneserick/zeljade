package com.stefick.zeljade.core.mocks

import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.TimeoutException

object ExceptionMocks {

    @JvmStatic
    fun getMockedIOException(message: String): IOException =
        IOException(message)

    @JvmStatic
    fun getMockedHttpException(message: String, code: Int): Throwable =
        HttpException(
            Response.error<String>(
                code,
                message.toResponseBody()
            )
        ) // couldn't see the actual api error json structure, so I'm leaving it as a simple message

    @JvmStatic
    fun getMockedTimeoutException(message: String): Throwable =
        TimeoutException(message)

    @JvmStatic
    fun getMockedUnknownException(message: String): Throwable =
        UnsupportedOperationException(message) // Mocking an unexpected exception to test the repository behavior

}