package com.weather.api.service;

import com.weather.api.dto.*;
import com.weather.api.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }


    public WeatherResponse getWeatherByCity(String cityName) {

        Map<String, Object> cityRow = weatherRepository.findCityByName(cityName);

        if (cityRow == null) {
            return null;
        }

        int cityId = Integer.parseInt(cityRow.get("id").toString());

        CityDto city = new CityDto();
        city.name         = cityRow.get("name").toString();
        city.country_code = cityRow.get("country_code").toString();
        city.country_name = cityRow.get("country_name").toString();

        CurrentWeatherDto current = weatherRepository.findCurrentWeather(cityId);

        if (current == null) {
            return null;
        }

        List<DailyForecastDto> weeklyForecast = weatherRepository.findDailyForecast(cityId);

        List<HourlyDataDto> hourly = weatherRepository.findHourlyData(cityId);

        WeatherResponse response = new WeatherResponse();
        response.success = true;
        response.message = "OK";

        WeatherResponse.Data data = new WeatherResponse.Data();
        data.city            = city;
        data.current         = current;
        data.weekly_forecast = weeklyForecast;
        data.hourly          = hourly;

        response.data = data;

        return response;
    }
}
