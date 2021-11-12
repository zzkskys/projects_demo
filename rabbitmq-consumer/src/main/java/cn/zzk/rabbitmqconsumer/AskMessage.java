package cn.zzk.rabbitmqconsumer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AskMessage {
    private String content;

    private int i;
}
