package cn.zzk.actuator;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping
@RestController
public class HelloController {

    private final HttpTraceRepository httpTraceRepository;

    public HelloController(HttpTraceRepository httpTraceRepository) {
        this.httpTraceRepository = httpTraceRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/count")
    public List<HttpTrace> count() {
        return httpTraceRepository.findAll();
    }
}
