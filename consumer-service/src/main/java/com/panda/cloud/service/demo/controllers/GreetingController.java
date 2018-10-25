package com.panda.cloud.service.demo.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.panda.cloud.service.demo.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  @Autowired private GreetingService greetingService;

  @RequestMapping("/test")
  @HystrixCommand(
    fallbackMethod = "greetingFallbackMethod"
  ) // 断路器配置,当请求其他服务发生错误，则调用 greetingFallbackMethod 方法
  public String test(String name) {
    return "调用服务的结果为: " + greetingService.greeting(name);
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
