package com.weather.api.repository;

import com.weather.api.dto.CurrentWeatherDto;
import com.weather.api.dto.DailyForecastDto;
import com.weather.api.dto.HourlyDataDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WeatherRepository {

    private final JdbcTemplate jdbc;

    public WeatherRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Map<String, Object> findCityByName(String cityName) {
        String sql = "SELECT id, name, country_code, country_name " +
                     "FROM cities " +
                     "WHERE LOWER(name) = LOWER(?) " +
                     "LIMIT 1";

        List<Map<String, Object>> rows = jdbc.queryForList(sql, cityName);

        if (rows.isEmpty()) {
            return null; // şəhər tapılmadı
        }

        return rows.get(0);
    }

    public CurrentWeatherDto findCurrentWeather(int cityId) {
        String sql = "SELECT temperature, feels_like, wind_speed, wind_direction, " +
                     "       pressure, humidity, uv_index, visibility_km, " +
                     "       visibility_condition, dew_point, " +
                     "       DATE_FORMAT(sunrise_time, '%H:%i') AS sunrise_time, " +
                     "       DATE_FORMAT(sunset_time,  '%H:%i') AS sunset_time, " +
                     "       weather_description, weather_icon, " +
                     "       DATE_FORMAT(recorded_at, '%Y-%m-%d %H:%i:%s') AS recorded_at " +
                     "FROM current_weather " +
                     "WHERE city_id = ? " +
                     "ORDER BY recorded_at DESC " +
                     "LIMIT 1";

        List<Map<String, Object>> rows = jdbc.queryForList(sql, cityId);

        if (rows.isEmpty()) {
            return null;
        }

        Map<String, Object> row = rows.get(0);

        CurrentWeatherDto dto = new CurrentWeatherDto();
        dto.temperature          = toDouble(row.get("temperature"));
        dto.feels_like           = toDouble(row.get("feels_like"));
        dto.wind_speed           = toDouble(row.get("wind_speed"));
        dto.wind_direction       = String.valueOf(row.get("wind_direction"));
        dto.pressure             = toInt(row.get("pressure"));
        dto.humidity             = toInt(row.get("humidity"));
        dto.uv_index             = toDouble(row.get("uv_index"));
        dto.visibility_km        = toDouble(row.get("visibility_km"));
        dto.visibility_condition = String.valueOf(row.get("visibility_condition"));
        dto.dew_point            = toDouble(row.get("dew_point"));
        dto.sunrise_time         = String.valueOf(row.get("sunrise_time"));
        dto.sunset_time          = String.valueOf(row.get("sunset_time"));
        dto.weather_description  = String.valueOf(row.get("weather_description"));
        dto.weather_icon         = String.valueOf(row.get("weather_icon"));
        dto.recorded_at          = String.valueOf(row.get("recorded_at"));

        return dto;
    }

    public List<DailyForecastDto> findDailyForecast(int cityId) {
        String sql = "SELECT day_name, temperature, weather_description, weather_icon, " +
                     "       DATE_FORMAT(forecast_date, '%Y-%m-%d') AS forecast_date " +
                     "FROM daily_forecast " +
                     "WHERE city_id = ? " +
                     "ORDER BY forecast_date ASC";

        List<Map<String, Object>> rows = jdbc.queryForList(sql, cityId);

        List<DailyForecastDto> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            DailyForecastDto dto = new DailyForecastDto();
            dto.day_name            = String.valueOf(row.get("day_name"));
            dto.temperature         = toDouble(row.get("temperature"));
            dto.weather_description = String.valueOf(row.get("weather_description"));
            dto.weather_icon        = String.valueOf(row.get("weather_icon"));
            dto.forecast_date       = String.valueOf(row.get("forecast_date"));
            result.add(dto);
        }

        return result;
    }

    public List<HourlyDataDto> findHourlyData(int cityId) {
        String sql = "SELECT hour_label, wind_speed, rain_probability, rain_condition " +
                     "FROM hourly_data " +
                     "WHERE city_id = ? " +
                     "ORDER BY recorded_at ASC";

        List<Map<String, Object>> rows = jdbc.queryForList(sql, cityId);

        List<HourlyDataDto> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            HourlyDataDto dto = new HourlyDataDto();
            dto.hour_label       = String.valueOf(row.get("hour_label"));
            dto.wind_speed       = toDouble(row.get("wind_speed"));
            dto.rain_probability = toInt(row.get("rain_probability"));
            dto.rain_condition   = String.valueOf(row.get("rain_condition"));
            result.add(dto);
        }

        return result;
    }

    private double toDouble(Object value) {
        if (value == null) return 0.0;
        return Double.parseDouble(value.toString());
    }

    private int toInt(Object value) {
        if (value == null) return 0;
        return Integer.parseInt(value.toString());
    }
}
