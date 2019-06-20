//package com.hazz.kotlinmvp.net.exception
package com.sixcat.exception
/**
 * Created by xuhao on 2017/12/5.
 * desc:
 */
class ApiException : RuntimeException {

    private var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}