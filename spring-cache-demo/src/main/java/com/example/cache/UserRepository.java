package com.example.cache;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created Date : 2021/09/07
 *
 * @author zzk
 */
public interface UserRepository extends JpaRepository<User, String> {

}