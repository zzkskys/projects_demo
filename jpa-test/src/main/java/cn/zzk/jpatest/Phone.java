package cn.zzk.jpatest;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Accessors(chain = true)
@Data
@Entity
public class Phone {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private User user;
}
