package com.dnd.weather;

import com.dnd.weather.service.CalculateWeatherFacade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherAppApplication implements CommandLineRunner {

	private final CalculateWeatherFacade calculateWeatherFacade;

	public WeatherAppApplication(CalculateWeatherFacade calculateWeatherFacade) {
		this.calculateWeatherFacade = calculateWeatherFacade;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		calculateWeatherFacade.calculateWeather(41, 1);
	}

}
