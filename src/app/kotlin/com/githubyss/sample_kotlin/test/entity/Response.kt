package com.githubyss.sample_kotlin.test.entity

import org.json.JSONObject


data class Response(var code: Int, var message: String, var body: JSONObject)
