package cn.zzk.jpatest;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;


    @ManyToOne
    private Location location;

}
