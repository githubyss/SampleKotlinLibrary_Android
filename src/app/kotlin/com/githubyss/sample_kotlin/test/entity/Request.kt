package com.githubyss.sample_kotlin.test.entity


class Request(var url: String, var params: HashMap<String, Any>) {
    operator fun component1(): String {
        return url
    }

    operator fun component2(): HashMap<String, Any> {
        return params
    }
}
