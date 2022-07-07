package com.githubyss.sample_kotlin.test.delegate

import kotlin.reflect.KProperty


fun delegate() {
    println("委托")
    println()

    ByLazy().presenter
    ByDelegate().token = "abc"
    println("token:${ByDelegate().token}")
}

class ByLazy : BaseView<Presenter> {
    // private val presenter = Presenter("")
    // override fun getPresenter(): Presenter {
    //     return presenter
    // }

    // 使用 by lazy 委托，有两个效果：
    // 1) lazy{} 里的代码，只会在访问到修饰的对象的时候执行
    // 2) lazy{} 里的代码，只会加载一次
    // 实现使用到 presenter 的时候，才会创建实例，并且这个实例只会创建一次
    override val presenter by lazy {
        Presenter("")
    }
}

class ByDelegate {
    // var token: String
    //     set(value) {
    //         CacheUtils.save("token", value)
    //     }
    //     get() {
    //         return CacheUtils.get("token")
    //     }
    //
    // var token2: String
    //     set(value) {
    //         CacheUtils.save("token2", value)
    //     }
    //     get() {
    //         return CacheUtils.get("token2")
    //     }

    // 传入 key 给 Saver 委托，通过委托类进行数据的缓存存储和提取
    var token: String by Saver("token")
    var token2: String by Saver("token2")

    // 通过委托实现数据的处理
    class Saver(var token: String) {
        operator fun setValue(byDelegate: ByDelegate, property: KProperty<*>, value: String) {
            // 存入缓存
            CacheUtils.save(token, value)
        }

        operator fun getValue(byDelegate: ByDelegate, property: KProperty<*>): String {
            // 从缓存中获取
            return CacheUtils.get(token)
        }
    }
}
