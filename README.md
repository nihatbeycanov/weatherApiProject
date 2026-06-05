[README.md](https://github.com/user-attachments/files/28622862/README.md)
# Weather API

A RESTful backend service built with **Java Spring Boot** that returns weather data for a given city in a single API response.

---

## Tech Stack

- Java 21
- Spring Boot 3.3
- MySQL
- Gradle
- Springdoc OpenAPI (Swagger UI)

---

## Getting Started

### Requirements

- Java 21
- MySQL
- IntelliJ IDEA

### 1. Clone the repository

```bash
git clone https://github.com/nihatbeycanov/weather-api.git
cd weather-api
```

### 2. Set up the database

Run the SQL file to create tables and insert test data:

```bash
mysql -u root -p < weather_db.sql
```

### 3. Configure the connection

Open `src/main/resources/application.yaml` and update your credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/weatherdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 5000
```

### 4. Run the project

Open the project in IntelliJ IDEA and run `WeatherApiApplication.java`.

The server starts at: `http://localhost:5000`

---

## API Endpoint

### `GET /api/weather?city={city_name}`

Returns all weather data for the given city.

| Parameter | Type   | Required | Description       |
|-----------|--------|----------|-------------------|
| `city`    | String | Yes      | Name of the city  |

**Example request:**
```
GET http://localhost:5000/api/weather?city=Dhaka
```

**Example response:**
```json
{
  "success": true,
  "message": "OK",
  "data": {
    "city": {
      "name": "Dhaka",
      "country_code": "BD",
      "country_name": "Bangladesh"
    },
    "current": {
      "temperature": 16.0,
      "feels_like": 18.0,
      "wind_speed": 6.7,
      "wind_direction": "N-E",
      "pressure": 1013,
      "humidity": 51,
      "uv_index": 5.5,
      "visibility_km": 4.0,
      "visibility_condition": "Haze is affecting visibility",
      "dew_point": 27.0,
      "sunrise_time": "05:30",
      "sunset_time": "06:45",
      "weather_description": "Scattered Clouds",
      "weather_icon": "03d",
      "recorded_at": "2026-05-24 11:45:00"
    },
    "weekly_forecast": [
      {
        "day_name": "SAT",
        "temperature": 10.0,
        "weather_description": "Rain",
        "weather_icon": "10d",
        "forecast_date": "2026-05-24"
      }
    ],
    "hourly": [
      {
        "hour_label": "10AM",
        "wind_speed": 5.2,
        "rain_probability": 30,
        "rain_condition": "Sunny"
      }
    ]
  }
}
```

**Error responses:**

| Status | Reason                        |
|--------|-------------------------------|
| 400    | `city` parameter is missing   |
| 404    | City not found in database    |

---

## Swagger UI

After starting the server, open in your browser:

```
http://localhost:5000/swagger-ui/index.html
```

---

## Project Structure

```
src/main/java/com/weather/api/
├── config/         # CORS configuration
├── controller/     # HTTP layer — handles requests and responses
├── service/        # Business logic
├── repository/     # SQL queries
└── dto/            # Data transfer objects
```

---
---

# Hava API-si

**Java Spring Boot** ilə qurulmuş RESTful backend xidməti — verilən şəhər üçün bütün hava məlumatlarını tək API cavabında qaytarır.

---

## Texnologiyalar

- Java 21
- Spring Boot 3.3
- MySQL
- Gradle
- Springdoc OpenAPI (Swagger UI)

---

## Başlanğıc

### Tələblər

- Java 21
- MySQL
- IntelliJ IDEA

### 1. Layihəni klonla

```bash
git clone https://github.com/your-username/weather-api.git
cd weather-api
```

### 2. Verilənlər bazasını qur

Cədvəlləri yaratmaq və test datasını daxil etmək üçün SQL faylını icra et:

```bash
mysql -u root -p < weather_db.sql
```

### 3. Bağlantını konfiqurasiya et

`src/main/resources/application.yaml` faylını aç və öz məlumatlarını yaz:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/weatherdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: şifrən
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 5000
```

### 4. Layihəni işə sal

IntelliJ IDEA-da `WeatherApiApplication.java` faylını aç və **Run** et.

Server bu ünvanda başlayır: `http://localhost:5000`

---

## API Endpointi

### `GET /api/weather?city={şəhər_adı}`

Verilmiş şəhər üçün bütün hava məlumatlarını qaytarır.

| Parametr | Tip    | Məcburi | Açıqlama      |
|----------|--------|---------|---------------|
| `city`   | String | Bəli    | Şəhərin adı   |

**Nümunə sorğu:**
```
GET http://localhost:5000/api/weather?city=Dhaka
```

**Nümunə cavab:**
```json
{
  "success": true,
  "message": "OK",
  "data": {
    "city": {
      "name": "Dhaka",
      "country_code": "BD",
      "country_name": "Bangladesh"
    },
    "current": {
      "temperature": 16.0,
      "feels_like": 18.0,
      "wind_speed": 6.7,
      "wind_direction": "N-E",
      "pressure": 1013,
      "humidity": 51,
      "uv_index": 5.5,
      "visibility_km": 4.0,
      "visibility_condition": "Haze is affecting visibility",
      "dew_point": 27.0,
      "sunrise_time": "05:30",
      "sunset_time": "06:45",
      "weather_description": "Scattered Clouds",
      "weather_icon": "03d",
      "recorded_at": "2026-05-24 11:45:00"
    },
    "weekly_forecast": [
      {
        "day_name": "SAT",
        "temperature": 10.0,
        "weather_description": "Rain",
        "weather_icon": "10d",
        "forecast_date": "2026-05-24"
      }
    ],
    "hourly": [
      {
        "hour_label": "10AM",
        "wind_speed": 5.2,
        "rain_probability": 30,
        "rain_condition": "Sunny"
      }
    ]
  }
}
```

**Xəta cavabları:**

| Status | Səbəb                              |
|--------|------------------------------------|
| 400    | `city` parametri göndərilməyib     |
| 404    | Şəhər verilənlər bazasında yoxdur  |

---

## Swagger UI

Serveri işə saldıqdan sonra brauzerdə aç:

```
http://localhost:5000/swagger-ui/index.html
```

---

## Layihə Strukturu

```
src/main/java/com/weather/api/
├── config/         # CORS konfiqurasiyası
├── controller/     # HTTP qatı — sorğuları qəbul edir, cavab qaytarır
├── service/        # Biznes məntiqi
├── repository/     # SQL sorğuları
└── dto/            # Data transfer obyektləri
```
