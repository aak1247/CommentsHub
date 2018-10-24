package com.aak1247.utils


@Retention(AnnotationRetention.RUNTIME)
annotation class RouterTo(val path: String, val method :HttpMethod = HttpMethod.GET)


enum class HttpMethod(val method: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS")
}