package cn.zzk.jpatest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(nativeQuery = true, value = "select * from user")
    List<User> nativeFindAll();
}
