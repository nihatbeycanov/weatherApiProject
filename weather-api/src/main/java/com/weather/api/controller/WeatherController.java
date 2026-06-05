package com.weather.api.controller;

import com.weather.api.dto.WeatherResponse;
import com.weather.api.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // GET /api/weather?city=Dhaka
    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam(required = false) String city) {

        // Yoxlama 1: city parametri göndərilib mi?
        if (city == null || city.isBlank()) {
            WeatherResponse errorResponse = new WeatherResponse();
            errorResponse.success = false;
            errorResponse.message = "'city' parametri məcburidir";
            errorResponse.data    = null;

            return ResponseEntity.status(400).body(errorResponse);
        }

        WeatherResponse result = weatherService.getWeatherByCity(city.trim());

        if (result == null) {
            WeatherResponse errorResponse = new WeatherResponse();
            errorResponse.success = false;
            errorResponse.message = "Şəhər tapılmadı: " + city;
            errorResponse.data    = null;

            return ResponseEntity.status(404).body(errorResponse);
        }

        return ResponseEntity.ok(result);
    }
}
