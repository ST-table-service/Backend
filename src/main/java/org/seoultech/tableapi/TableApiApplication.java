package org.seoultech.tableapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TableApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableApiApplication.class, args);
    }

}
