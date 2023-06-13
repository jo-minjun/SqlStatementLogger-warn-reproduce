package com.me.sqlstatementloggerwarnreproduce;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

  private final AEntityRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(ApplicationArguments args) {
    System.out.println("==========================================");
    repository.findWithNativeQuery();
    System.out.println("==========================================");
  }
}
