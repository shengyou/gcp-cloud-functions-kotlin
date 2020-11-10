package io.kraftsman.functions

import com.google.cloud.functions.HttpFunction
import com.google.cloud.functions.HttpRequest
import com.google.cloud.functions.HttpResponse
import io.kraftsman.services.ContactService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.IOException
import java.net.HttpURLConnection
import kotlin.jvm.Throws
import kotlin.time.ExperimentalTime

class ContactFunction: HttpFunction {

    @ExperimentalTime
    @Throws(IOException::class)
    override fun service(request: HttpRequest, response: HttpResponse) {
        val param = request.getFirstQueryParameter("amount").orElse("")
        val amount = param.toIntOrNull() ?: 10

        val contacts = ContactService().generate(amount)
        val jsonString = Json.encodeToString(mapOf("data" to contacts))

        with(response) {
            setStatusCode(HttpURLConnection.HTTP_OK)
            setContentType("application/json")
            writer.write(jsonString)
        }
    }
}