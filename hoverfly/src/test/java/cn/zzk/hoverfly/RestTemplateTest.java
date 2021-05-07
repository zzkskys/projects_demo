package cn.zzk.hoverfly;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@HoverflySimulate(
        source = @HoverflySimulate.Source(type = HoverflySimulate.SourceType.DEFAULT_PATH),
        enableAutoCapture = true
)
@ExtendWith(HoverflyExtension.class)
@ExtendWith(SpringExtension.class)
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(Hoverfly hoverfly) {
        int proxyPort = hoverfly.getHoverflyConfig().getProxyPort();
        String host = hoverfly.getHoverflyConfig().getHost();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, proxyPort)));
        restTemplate.setRequestFactory(factory);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper()
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

    }

    @Test
    void hello() {
        String hello = restTemplate.getForObject("http://localhost:8080", String.class);
        assertEquals("hello", hello);
    }

    @Test
    void uuid() {
        String uuid = restTemplate.getForObject("http://localhost:8080/uuid", String.class);
        assertEquals(36, uuid.length());
    }

    @Test
    void suppliers() {
        List<Map> list = restTemplate.getForObject("http://192.168.1.254:8040/police-support-platform/suppliers", List.class);
        List<Supplier> suppliers = list
                .stream()
                .map(map -> objectMapper.convertValue(map, Supplier.class))
                .collect(Collectors.toList());

        assertFalse(suppliers.isEmpty());

    }

}
