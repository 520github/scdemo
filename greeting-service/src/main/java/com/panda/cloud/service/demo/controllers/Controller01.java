package com.panda.cloud.service.demo.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller01 {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${spring.application.name}")
  private String appName;

  @RequestMapping("/hello")
  @HystrixCommand(fallbackMethod = "greetingFallbackMethod")
  public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    logger.info("hello {}", name);
    return String.format("Hello %s from %s", name, appName);
  }

  /**
   * 处理失败时候返回的信息
   *
   * @param fallback
   * @return
   */
  public String greetingFallbackMethod(String fallback) {
    return "fallback 参数值为:" + fallback;
  }
}
