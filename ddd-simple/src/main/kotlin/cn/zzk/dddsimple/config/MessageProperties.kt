package cn.zzk.dddsimple.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@ConfigurationProperties("messages")
class MessageProperties {


    var rate: Duration = Duration.ofHours(24)
}