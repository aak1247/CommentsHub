package com.aak1247.utils

import com.aak1247.model.HttpMethod


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class RouteTo(val path: String, val method: HttpMethod = HttpMethod.GET)