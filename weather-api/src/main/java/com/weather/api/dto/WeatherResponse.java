package com.weather.api.dto;

import java.util.List;

public class WeatherResponse {

    public boolean success;
    public String  message;
    public Data    data;

    public static class Data {
        public CityDto               city;
        public CurrentWeatherDto     current;
        public List<DailyForecastDto> weekly_forecast;
        public List<HourlyDataDto>   hourly;
    }
}
