
---

```markdown
# 🚗 Ride Sharing App - LLD + Spring Boot REST API

A backend ride-sharing service modeled using Object-Oriented Design and implemented as a RESTful API using **Java Spring Boot**.

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
| Testing     | Postman / Swagger UI   |

---

## 🗂️ Project Structure


src/main/java/com/jayanth/rideshare/
├── controller/        # REST APIs
├── service/           # Business logic
├── model/             # Domain entities
├── strategy/          # Strategy pattern for fare
├── observer/          # Observer pattern for driver notification
└── RideShareApplication.java

````

---

## 🚀 Getting Started

### 1️⃣ Clone the repo

```bash
git clone https://github.com/yourusername/rideshare-lld.git
cd rideshare-lld
````

### 2️⃣ Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

App will start on:
📍 `http://localhost:8080`

---

## 🔍 API Documentation

### 📖 Swagger UI

> Access all live APIs with docs and test buttons:

🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

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

```

---

✅ Just save this as `README.md` in your project root folder.  
Let me know if you’d like a cool banner/logo or GitHub Actions next!
```
