package cn.zzk.remotedebug

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RemoteDebugApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(RemoteDebugApplication::class.java, *args)
        }
    }
}

@RestController
class HelloController {

    @GetMapping
    fun hello(): String {
        return "Hello World!"
    }
}