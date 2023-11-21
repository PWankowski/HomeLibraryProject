package booksProject.configuration.cache;

import booksProject.books.dto.BookDto;
import booksProject.configuration.cache.serializers.BookDtoSerializer;
import booksProject.configuration.cache.serializers.CustomerDtoSerializer;
import booksProject.customer.dto.CustomerDto;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.spi.serialization.Serializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import java.time.Duration;


import static org.ehcache.config.builders.ResourcePoolsBuilder.newResourcePoolsBuilder;

@Configuration
@EnableCaching
public class CacheConfig {


    @Bean
    public CacheManager setCacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

        CacheConfiguration<String, BookDto> cacheConfigurationForBookDto = implementsCacheConfigurationWithSerializer(String.class, BookDto.class, 5, 2, new BookDtoSerializer());
        CacheConfiguration<String, CustomerDto> cacheConfigurationForCustomerDto = implementsCacheConfigurationWithSerializer(String.class, CustomerDto.class, 10, 5, new CustomerDtoSerializer());

        cacheManager.createCache("findByUUID", Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfigurationForBookDto));
        cacheManager.createCache("getCustomer", Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfigurationForCustomerDto));
        return cacheManager;
    }

    private CacheConfiguration implementsCacheConfigurationWithSerializer(Class keyClass, Class valueClass, long size, long timeToLive, Serializer serializer) {

        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        keyClass, valueClass, newResourcePoolsBuilder().offheap(size, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(timeToLive)))
                .withValueSerializer(serializer)
                .build();
    }

    private CacheConfiguration implementsCacheConfiguration(Class keyClass, Class valueClass, long size, long timeToLive) {

        return CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        keyClass, valueClass, newResourcePoolsBuilder().offheap(size, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(timeToLive)))
                .build();
    }
}
