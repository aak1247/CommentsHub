package com.aak1247.router

import com.aak1247.Application
import com.aak1247.model.HttpMethod
import com.aak1247.utils.RouteTo
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.get


typealias RequestHandler = (RoutingContext) -> Unit

class Routers {
    companion object {

        private lateinit var router: Router

        @JvmStatic
        fun router(vertx: Vertx): Router {

            router = Router.router(vertx)

            //获取所有包含@RouterTo注解的fun 然后加入router

            val methods = Routers.javaClass.declaredMethods

            for (method in methods) {
                val annotation = method.getAnnotation(RouteTo::class.java)
                if (annotation != null) {
                    val path = annotation.path
                    val httpMethod = annotation.method
                    val handler = Handler<RoutingContext> { context ->
                        context.run(method.invoke(this) as RequestHandler)
                    }
                    when (httpMethod) {
                        HttpMethod.GET -> router.get(path).handler(handler)
                        HttpMethod.POST -> router.post(path).handler(handler)
                        else -> {
                            router.route(path).handler(handler)
                        }
                    }
                }
            }

            return router
        }

        @RouteTo(path = "/post", method = HttpMethod.POST)
        @JvmStatic
        fun testRouter(): (RoutingContext) -> Unit = { context: RoutingContext ->
            //business logic
            context.response().
                    setStatusCode(200)
                    .end(
                            Json.encode(
                                    Application.Entity("hello", "world")
                            )
                    )
        }

        @RouteTo(path = "/user", method = HttpMethod.POST)
        @JvmStatic
        fun signInRouter() = { context: RoutingContext ->
            val requestBody = context.bodyAsJson
            val username: String = requestBody["username"]
            val password: String = requestBody["password"]
            //读数据库

        }

        @RouteTo(path = "/user", method = HttpMethod.GET)
        @JvmStatic
        fun getUserRouter() = { context: RoutingContext ->
            val requestBody = context.bodyAsJson

        }

        @RouteTo(path = "/comment", method = HttpMethod.GET)
        @JvmStatic
        fun getComment() = { context: RoutingContext ->
            val requestBody = context.bodyAsJson

        }

        @RouteTo(path = "/comment", method = HttpMethod.PUT)
        @JvmStatic
        fun updateComment() = { context: RoutingContext ->
            val requestBody = context.bodyAsJson

        }

        @RouteTo(path = "/comment", method = HttpMethod.POST)
        @JvmStatic
        fun createComment() = { context: RoutingContext ->
            val requestBody = context.bodyAsJson

        }

    }
}
