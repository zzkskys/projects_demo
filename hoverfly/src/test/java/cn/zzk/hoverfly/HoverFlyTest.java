package cn.zzk.hoverfly;

import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyCapture;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HoverflyCapture(
        config = @HoverflyConfig(captureAllHeaders = true, proxyLocalHost = true),filename = "test.json")
@ExtendWith(HoverflyExtension.class)
public class HoverFlyTest {

    private RestTemplate restTemplate;

    @BeforeEach
    void setup(){
        restTemplate = new RestTemplate();
    }

    @Test
    void hello(){
        String hello = restTemplate.getForObject("http://localhost:8080", String.class);
        assertEquals("hello", hello);
    }

}
