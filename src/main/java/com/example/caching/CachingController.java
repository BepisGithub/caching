package com.example.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CachingController {

    @PostMapping(path = "/create")
    @CachePut(value = "CachingController", key = "#key")
    public String addKeyValuePairToCache(@RequestParam String key, @RequestParam String value) throws InterruptedException {
        Thread.sleep(1000L);
        return value;
    }

    @PutMapping(path = "/update")
    @CachePut(value = "CachingController", key = "#key")
    public String updateKeyValuePairInCache(@RequestParam String key, @RequestParam String value) throws InterruptedException {
        Thread.sleep(1000L);
        return value;
    }

    @GetMapping(path = "/{key}")
    @Cacheable(value = "CachingController", key = "#key")
    public String getValueFromCache(@PathVariable String key) {
        return "empty";
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
