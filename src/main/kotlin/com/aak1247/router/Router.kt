package com.aak1247.router

import com.aak1247.Application
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext


class Routers() {
    companion object {
        @JvmStatic fun router(vertx: Vertx): Router {
            var router = Router.router(vertx)

            router.route("/").handler(signInRouter())

            router.route("/test").handler(testRouter())

            return router
        }


        @JvmStatic fun signInRouter() = { context: RoutingContext ->
            //business logic
            context.response().putHeader("Content-Type", "text/plain").end("hello world")
        }

        @JvmStatic fun testRouter() = { context: RoutingContext ->
            //business logic
            context.response().end(Json.encode(Application.Entity("hello", "world")))
        }


    }
}