package com.panda.cloud.service.demo.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("greeting-service")
public interface GreetingService {
  @RequestMapping("/hello")
  public String greeting(@RequestParam(value = "name", defaultValue = "World") String name);
}
