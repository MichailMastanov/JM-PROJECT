package jm.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("jm")
@ComponentScan("jm")
public class JMProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JMProjectApplication.class, args);
    }

}
