package com.example.caching;

import com.example.caching.models.Item;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.MissingResourceException;

@RestController
@RequestMapping("/cache")
public class CachingController {

    @PostMapping(path = "/create")
    @CachePut(value = "CachingController", key = "#key")
    public Item addKeyValuePairToCache(@RequestParam String key, @RequestParam String name, @RequestParam int age) throws InterruptedException {
        Thread.sleep(1000L);
        return new Item(key,name,age);
    }

    @PutMapping(path = "/update")
    @CachePut(value = "CachingController", key = "#key")
    public Item updateKeyValuePairInCache(@RequestParam String key, @RequestParam String name, @RequestParam int age) throws InterruptedException {
        Thread.sleep(1000L);
        return new Item(key,name,age);
    }

    @GetMapping(path = "/{key}")
    @Cacheable(value = "CachingController", key = "#key")
    public Item getValueFromCache(@PathVariable String key) throws MissingResourceException {
        throw new MissingResourceException("resource not found", "Item", key);
    }


//    public void getAllValuesFromCache() {
//
//    }


    @DeleteMapping(path = "/delete")
    @CacheEvict(value = "CachingController", key = "#key", allEntries = false, beforeInvocation = true)
    public String deleteKeyInCache(@RequestParam String key) {
        return "success";
    }
}
