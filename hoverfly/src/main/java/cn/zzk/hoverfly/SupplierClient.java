package cn.zzk.hoverfly;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "supplierClient", url = "${supplier.url}", path = "suppliers")
public interface SupplierClient {

    @GetMapping
    List<Supplier> findAll();
}
