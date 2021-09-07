package com.example.cache;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static com.example.cache.CacheConfig.CACHE_KEY;
import static com.example.cache.CacheConfig.CACHE_LIST_KEY;

/**
 * Created Date : 2021/09/07
 *
 * @author zzk
 */
@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        userRepository.save(new User().setAge(18).setName("张三"));
        userRepository.save(new User().setAge(20).setName("李四"));

    }

    @SneakyThrows
    @GetMapping
    @Cacheable(value = CACHE_LIST_KEY)
    public Collection<User> users() {

        Thread.sleep(3_000);
        log.info("查找用户");
        return userRepository.findAll();
    }

    @SneakyThrows
    @Cacheable(CACHE_KEY)
    @GetMapping("/{id}")
    public User findByIndex(@PathVariable String id) {
        Thread.sleep(3_000);
        return userRepository
                .findById(id)
                .orElse(null);
    }


    @SneakyThrows
    @PostMapping
    @CacheEvict(value = CACHE_LIST_KEY, allEntries = true)
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    @Caching(
            evict = {
                    @CacheEvict(value = CACHE_LIST_KEY, allEntries = true),
                    @CacheEvict(value = CACHE_KEY, key = "#id")
            }
    )
    public void updateUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
        log.info("更新用户");
    }

    @SneakyThrows
    @Caching(
            evict = {
                    @CacheEvict(value = CACHE_LIST_KEY, allEntries = true),
                    @CacheEvict(value = CACHE_KEY, key = "#id")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        log.info("删除用户");
    }
}
