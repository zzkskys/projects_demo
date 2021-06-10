package cn.zzk.rabbitmqproducter.direct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DirectRoutingKey {

    SMS("sms"),
    EMAIL("email");

    private final String key;


}
