package com.example.grafanaprometheus.config;

import com.example.grafanaprometheus.api.TestController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
  private TestController testController;

  public Scheduler(TestController testController) {
    this.testController = testController;
  }

  @Scheduled(fixedRate = 10000)
  public void sendScheduledRequest(){
    var response = testController.generateStatus();
    System.out.println("request send: " +response);
  }
}
