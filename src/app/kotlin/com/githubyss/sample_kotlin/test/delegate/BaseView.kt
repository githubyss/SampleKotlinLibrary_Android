package com.githubyss.sample_kotlin.test.delegate


interface BaseView<P> {
    // fun getPresenter(): P
    val presenter: P
}
