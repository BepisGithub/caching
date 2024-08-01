package com.example.caching;

import com.example.caching.models.Item;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.MissingResourceException;

@RestController
@RequestMapping("/cache")
@CacheConfig(cacheNames = "CachingController")
public class CachingController {

    @PostMapping(path = "/create")
    @CachePut(key = "#key")
    public Item addKeyValuePairToCache(@RequestParam String key, @RequestParam String name, @RequestParam int age) throws InterruptedException {
        Thread.sleep(1000L);
        return new Item(key,name,age);
    }

    @PutMapping(path = "/update")
    @CachePut(key = "#key")
    public Item updateKeyValuePairInCache(@RequestParam String key, @RequestParam String name, @RequestParam int age) throws InterruptedException {
        Thread.sleep(1000L);
        return new Item(key,name,age);
    }

    @GetMapping(path = "/{key}")
    @Cacheable(key = "#key")
    public Item getValueFromCache(@PathVariable String key) throws MissingResourceException {
        throw new MissingResourceException("resource not found", "Item", key);
    }


//    public void getAllValuesFromCache() {
//
//    }


    @DeleteMapping(path = "/delete")
    @CacheEvict(key = "#key", allEntries = false, beforeInvocation = true)
    public String deleteKeyInCache(@RequestParam String key) {
        return "success";
    }
}
