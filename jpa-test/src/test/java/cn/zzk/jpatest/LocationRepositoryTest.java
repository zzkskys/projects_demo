package cn.zzk.jpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LocationRepositoryTest {


    @Autowired
    private LocationRepository locationRepository;

    @Test
    void save(){
        Location location = locationRepository.save(Location.createShelfLocation("1", "1", "1"));

        Optional<Location> byId = locationRepository.findById(location.getId());
        assertEquals("1",byId.get().getFloor());
    }

}