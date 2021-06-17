package cn.zzk.jpatest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Location {

    @Id
    private String id = UUID.randomUUID().toString();

    private String number;
    private String floor;
    private String section;


    /**
     * 创建车辆位置
     *
     * @param plate 给定车辆的车牌号
     */
    public static Location createCarLocation(String plate) {
        Location location = new Location();
        location.setNumber(plate);
        return location;
    }

    /**
     * 创建警柜位置
     *
     * @param number  警柜编号
     * @param section 警柜节号
     */
    public static Location createCabinetLocation(String number, String section) {
        Location location = new Location();
        location.setNumber(number);
        location.setSection(section);
        return location;
    }

    /**
     * 创建公共货架位置
     *
     * @param number  货架编号
     * @param section 货架节号
     * @param floor   货架层号
     */
    public static Location createShelfLocation(String number, String section, String floor) {
        Location location = new Location();
        location.setNumber(number);
        location.setSection(section);
        location.setFloor(floor);
        return location;
    }

}