package cn.zzk.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "hardware")
public class HardwareEndpoint {

    private final Map<String, Object> map;


    public HardwareEndpoint(HealthEndpoint endpoint) {
        this.map = new ConcurrentHashMap<>();
        map.put("a", "A");
        map.put("b", 2);

    }

    @ReadOperation
    public Map<String, Object> hardware() {
        return map;
    }
}
