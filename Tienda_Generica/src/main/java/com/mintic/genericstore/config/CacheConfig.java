package com.mintic.genericstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        return new CacheErrorHandler() {

            private final Logger log = LoggerFactory.getLogger(CacheErrorHandler.class);

            @Override
            public void handleCacheGetError(RuntimeException ex, Cache cache, Object key) {
                log.error("CACHE GET ERROR on cache '{}' for key '{}': {}",
                        cache.getName(), key, ex.getMessage());
                // swallow exception, let app fetch from DB
            }

            @Override
            public void handleCachePutError(RuntimeException ex, Cache cache, Object key, Object value) {
                log.error("CACHE PUT ERROR on cache '{}' for key '{}', value '{}': {}",
                        cache.getName(), key, value, ex.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException ex, Cache cache, Object key) {
                log.error("CACHE EVICT ERROR on cache '{}' for key '{}': {}",
                        cache.getName(), key, ex.getMessage());
            }

            @Override
            public void handleCacheClearError(RuntimeException ex, Cache cache) {
                log.error("CACHE CLEAR ERROR on cache '{}': {}",
                        cache.getName(), ex.getMessage());
            }
        };
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory cf) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // key as plain string
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                // JSON for values, readable by any client
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                // default TTL 15 minutes
                .entryTtl(Duration.ofMinutes(15))
                // disable caching nulls
                .disableCachingNullValues();

        return RedisCacheManager.builder(cf)
                .cacheDefaults(config)
                // override TTL per cache name if needed:
                .withCacheConfiguration("usersCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
                .transactionAware()
                .build();
    }
}
