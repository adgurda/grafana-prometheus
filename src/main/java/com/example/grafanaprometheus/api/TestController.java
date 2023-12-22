package com.example.grafanaprometheus.api;

import java.util.List;
import java.util.Random;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
  private Counter counter;

  public TestController(MeterRegistry meterRegistry) {
    this.counter = Counter.builder("test_counter_metric")
      .tag("version", "v1")
      .description("first_counter")
      .register(meterRegistry);
  }

  @GetMapping("/test")
  public String generateStatus() {
    int[] statuses = new int[]{200,400,500};
    int randomIndex = new Random().nextInt(statuses.length);
    int statusCode = statuses[randomIndex];
    counter.increment();
    return sendRequest(statusCode);
  }

  private String sendRequest(Integer statusCode){
    return String.format("statusCode:", statusCode);
  }

}
