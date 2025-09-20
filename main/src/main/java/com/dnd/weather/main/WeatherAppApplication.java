package com.dnd.weather.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.dnd.weather.main",
        "com.dnd.weather.dao",
        "com.dnd.weather.auth",
        "com.dnd.weather.domain",
        "com.dnd.weather.management"
})
@EnableJpaRepositories(basePackages = "com.dnd.weather.dao")
@EntityScan(basePackages = "com.dnd.weather.domain")
public class WeatherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAppApplication.class, args);
    }

}
