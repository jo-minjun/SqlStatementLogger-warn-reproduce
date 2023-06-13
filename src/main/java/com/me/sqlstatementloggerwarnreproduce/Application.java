package com.me.sqlstatementloggerwarnreproduce;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements ApplicationRunner {

  private final AEntityRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("==========================================");
    final List<AEntity> entities = repository.findWithNativeQuery();
    System.out.println(entities);
    System.out.println("Hello world!");
    System.out.println("==========================================");
  }
}
