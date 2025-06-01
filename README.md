# 🚗 Ride Sharing App - LLD + Spring Boot REST API

A backend ride-sharing service modeled using Object-Oriented Design and implemented as a RESTful API using **Java Spring Boot**. This service enables efficient ride booking, driver matching, and fare calculation.

> Built with real-world interview standards:  
> ✅ SOLID principles · ✅ Design Patterns · ✅ Layered Architecture · ✅ Swagger UI

---

## 📌 Features

- 🔧 Register drivers and riders
- 🚕 Book rides with location-based matching
- ✅ Complete rides and calculate fares
- 📜 View ride history
- 🔔 Real-time notifications using Observer pattern
- 🧠 Strategy pattern for fare calculation
- 🧪 Live testing via Swagger UI

---

## 🧱 Tech Stack

| Layer       | Tool / Tech            |
|------------|------------------------|
| Language    | Java 17                |
| Framework   | Spring Boot 3.x        |
| Docs        | Swagger (SpringDoc)    |
| Build Tool  | Maven                  |
| Testing     | JUnit 5, Mockito       |
| API Testing | Postman / Swagger UI   |

---

## 🗂️ Project Structure

```
src/main/java/com/jayanth/rideshare/
├── controller/        # REST APIs and endpoint handling
│   ├── RideController.java
│   └── dto/           # Data Transfer Objects
├── service/           # Business logic implementation
│   ├── DriverService.java
│   ├── RideService.java
│   └── impl/          # Service implementations
├── model/             # Domain entities
│   ├── Driver.java
│   ├── Rider.java
│   ├── Ride.java
│   └── Location.java
├── strategy/          # Strategy pattern for fare calculation
│   ├── FareStrategy.java
│   └── SimpleFareStrategy.java
├── observer/          # Observer pattern for notifications
│   ├── NotificationService.java
│   └── ConsoleNotificationService.java
├── exception/         # Custom exceptions and handlers
└── RideshareApplication.java  # Main application class
```

---

## 🚀 Getting Started

### 📋 Prerequisites

- Java JDK 17 or higher
- Maven 3.6+
- Git

### 1️⃣ Clone the repo

```bash
git clone https://github.com/jayanth-j-j/rideshare.git
cd rideshare
```

### 2️⃣ Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

App will start on:
📍 `http://localhost:8080`

### 3️⃣ Environment Configuration

The application uses the following default configuration in `application.properties`:

```properties
server.port=8080
spring.application.name=rideshare
```

---

## 🔍 API Documentation

### 📖 Swagger UI

> Access all live APIs with docs and test buttons:

🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📬 Sample API Usage (via Swagger or Postman)

### ➕ Register Driver

**POST** `/api/ride/registerDriver`

```json
{
  "name": "Arjun",
  "currentLocation": {
    "latitude": 12.9611,
    "longitude": 77.6387
  }
}
```

### 🚕 Book Ride

**POST** `/api/ride/book?dropLat=12.925&dropLng=77.5938`

```json
{
  "name": "Ravi",
  "currentLocation": {
    "latitude": 12.9716,
    "longitude": 77.5946
  }
}
```

### ✅ Complete Ride

**POST** `/api/ride/complete?rideId=<ride-id>`

### 📜 Get Ride History

**GET** `/api/ride/history`

---

## ⚙️ Design Principles & Patterns Used

* ✅ **SOLID principles** (Single Responsibility, Interface Segregation, etc.)
* 🧠 **Strategy Pattern** for fare logic
* 📣 **Observer Pattern** for driver notifications
* 🧱 **Layered Architecture**: Controller → Service → Model
* 🧼 **Clean Code** with testable design

---

## 🧪 Testing

The project includes comprehensive unit and integration tests:

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=RideServiceTest
```

Test coverage reports can be generated with:

```bash
mvn jacoco:report
```

---

## 💡 Future Enhancements

* 📍 Location distance-based driver filtering
* 🧾 Integration with payment and rating systems
* 🗃️ JPA + H2/Postgres persistence
* 🛡️ JWT Authentication & Role-based Access
* 📦 Dockerize & Deploy

---

## 🧑‍💻 Author

**Jayanth Jayadevan**  
Software Engineer @ Microsoft  
🔗 [LinkedIn](https://www.linkedin.com/in/jayanthjj)

---

## 📜 License

MIT License – feel free to fork and expand this for your own portfolio or interviews!

---

## 📊 Project Status

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Test Coverage](https://img.shields.io/badge/coverage-85%25-yellowgreen)
![Version](https://img.shields.io/badge/version-1.0.0-blue)
