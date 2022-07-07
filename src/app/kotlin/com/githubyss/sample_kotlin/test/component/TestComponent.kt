package com.githubyss.sample_kotlin.test.component

import com.githubyss.sample_kotlin.test.entity.Request
import com.githubyss.sample_kotlin.test.entity.Response
import org.json.JSONObject


fun component() {
    println("解构")
    println()

    val (url, params) = request()

    val (code, message, body) = response()
    println("code:$code, message:$message, body:$body")

    println()
}

private fun request(): Request {
    val url = "http://"
    val params = HashMap<String, Any>()
    params["id"] = 100
    return Request(url, params)
}

private fun response(): Response {
    val code = 200
    val message = "OK"
    val body = JSONObject()
    return Response(code, message, body)
}