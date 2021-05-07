package cn.zzk.hoverfly;

import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@HoverflySimulate(
        source = @HoverflySimulate.Source(type = HoverflySimulate.SourceType.DEFAULT_PATH),
        enableAutoCapture = true
)
@ExtendWith(HoverflyExtension.class)
@ExtendWith(SpringExtension.class)
@EnableFeignClients(clients = SupplierClient.class)
@Import({FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
@TestPropertySource(properties = "supplier.url=http://192.168.1.254:8040/police-support-platform")
public class FeignTest {

    @Autowired
    private SupplierClient supplierClient;

    @Test
    void suppliers() {
        List<Supplier> all = supplierClient.findAll();
        assertEquals(1, all.size());
    }

}
