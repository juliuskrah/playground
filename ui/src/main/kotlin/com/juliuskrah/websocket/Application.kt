package com.juliuskrah.websocket.websocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.result.view.Rendering

@Controller
@SpringBootApplication
class Application {

    @GetMapping("/", "index", "/welcome")
    fun index(): Rendering {
        return Rendering.view("index").build()
    }

    @GetMapping("/page-two")
    fun pageTwo(): Rendering {
        return Rendering.view("page-two").build()
    }
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}