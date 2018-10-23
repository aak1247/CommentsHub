package com.aak1247

import com.aak1247.utils.MapUtil
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.VertxOptions
import io.vertx.kotlin.core.http.HttpServerOptions
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream
import java.net.URL


class Application : AbstractVerticle() {

    companion object {

        @JvmField val config = mutableMapOf<String, Any?>()

        @JvmStatic fun main(args: Array<String>) {
            init()
            var vertx = Vertx.vertx(VertxOptions(workerPoolSize = 40))
            vertx.deployVerticle(Application())
        }

        @JvmStatic fun init() {
            //read config
            var yaml = Yaml()
            var url = Application.javaClass.classLoader.getResource("config.yaml")
            var map = yaml.load<FileInputStream>(FileInputStream(url.file)) as Map<*, *>
            val mapUtil = MapUtil()
            config["comment"] = mapUtil.getObjFromMap(map, "")
//            config[]

        }
    }



    override fun start() {
        var router =routers(vertx)
        println("running at 8888")
        vertx.createHttpServer(HttpServerOptions(port = 8888))
                .requestHandler(router::accept)
                .listen()
    }

    override fun stop() {

    }

    fun routers(vertx: Vertx) : Router {
        var router = Router.router(vertx)
        router.route("/").handler {
            context -> context.response().putHeader("Content-Type", "text/plain").end("hello world")
        }
        router.route("/test").handler {
            context -> context.response().end(Json.encode(Entity("hello", "world")))
        }
        return router
    }

    data class Entity(var name:String,var description:String)
}