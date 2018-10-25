package com.aak1247.router

import com.aak1247.Application
import com.aak1247.utils.HttpMethod
import com.aak1247.utils.RouterTo
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import kotlin.jvm.internal.ReflectionFactory


typealias RequestHandler = (RoutingContext) -> Unit


class Routers {
    companion object {

        lateinit var router: Router

        @JvmStatic fun router(vertx: Vertx): Router {

            router = Router.router(vertx)

            val reflectionFactory = ReflectionFactory()

            //获取所有包含@RouterTo注解的fun 然后加入router

            val methods = Routers.javaClass.declaredMethods

            for (method in methods) {
                val annotation = method.getAnnotation(RouterTo::class.java)
                if (annotation != null) {
                    val path = annotation.path
                    val httpMethod = annotation.method
                    val handler = Handler<RoutingContext> { context ->
                        context.run(method.invoke(this) as RequestHandler)
                    }
                    when(httpMethod) {
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



        @RouterTo(path = "/")
        @JvmStatic fun signInRouter():(RoutingContext) -> Unit = { context: RoutingContext ->
            //business logic
            println("hello")
            context.response().putHeader("Content-Type", "text/plain").end("hello world")
        }

        @RouterTo(path = "/post", method = HttpMethod.POST)
        @JvmStatic fun testRouter():(RoutingContext) -> Unit = { context: RoutingContext ->
            //business logic
            println("test")
            context.response().end(Json.encode(Application.Entity("hello", "world")))
        }


    }
}
