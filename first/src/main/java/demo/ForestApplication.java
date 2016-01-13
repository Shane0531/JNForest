package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForestApplication {
	final public static String HOST = "http://127.0.0.1:8080";

    public static void main(String[] args) {
        SpringApplication.run(ForestApplication.class, args);
    }
}
