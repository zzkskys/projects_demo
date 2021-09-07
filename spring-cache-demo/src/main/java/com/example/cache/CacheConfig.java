package com.example.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created Date : 2021/09/07
 *
 * @author zzk
 */
@Configuration
public class CacheConfig {

    public static final String CACHE_KEY = "user";

    public static final String CACHE_LIST_KEY = "user:list";

    @Bean
    public CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }
}
