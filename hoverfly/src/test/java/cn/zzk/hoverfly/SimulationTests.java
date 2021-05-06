package cn.zzk.hoverfly;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyConfig;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HoverflySimulate(config = @HoverflyConfig,
        source = @HoverflySimulate.Source(value = "hoverfly/test.json", type = HoverflySimulate.SourceType.CLASSPATH))
@ExtendWith(HoverflyExtension.class)
public class SimulationTests {

    private RestTemplate restTemplate;

    @BeforeEach
    void setup(Hoverfly hoverfly) {
        int proxyPort = hoverfly.getHoverflyConfig().getProxyPort();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", proxyPort)));
        restTemplate = new RestTemplate(factory);
    }

    @Test
    void hello() {
        String hello = restTemplate.getForObject("http://localhost:8080", String.class);
        assertEquals("hello", hello);
    }
}
