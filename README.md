# Payment Wallet Backend 💳

A scalable and high-performance **Spring Boot** backend for a digital wallet system. Users can **register**, **manage wallet balances**, **add money**, **transfer funds instantly**, and **track transaction history**.  
Built with **Java, Spring Boot, MySQL, Hibernate**, enhanced with **Redis caching for ultra-fast balance lookups** and **Apache Kafka as a message broker to boost API performance**.

---

## 🚀 Features

- **User Management**: Register and manage user profiles securely.  
- **Wallet Management**: Automatically create wallets after registered as user, check balances, add money.  
- **Money Transfer**: Transfer funds between users with transactional integrity.  
- **Transaction History**: Keep track of all transactions .  
- **Performance Optimizations**:  
  - **Redis** for faster balance lookups.  
  - **Kafka** for asynchronous transaction processing to improve API responsiveness.  

---

## 🛠 Tech Stack

| Layer               | Technology |
| ------------------- | ---------- |
| Language            | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="25" height="25"/> **Java 21+** |
| Framework           | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="25" height="25"/> **Spring Boot 3.5.4** |
| ORM / Persistence   | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/hibernate/hibernate-original.svg" width="25" height="25"/> **Spring Data JPA + Hibernate** |
| Database            | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-original.svg" width="25" height="25"/> **MySQL** |
| Caching             | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/redis/redis-original.svg" width="25" height="25"/> **Redis** |
| Async / Event Stream| <img src="https://static.cdnlogo.com/logos/k/35/kafka.svg" width="25" height="25" style="object-fit: contain;"/> **Apache Kafka** *(improves API responsiveness)* |
| Security            | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" width="25" height="25"/> **Spring Security + OAuth2** |
| Email / Notifications | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" width="25" height="25"/> **Java Mail Sender** |
| Build Tool          | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/maven/maven-original.svg" width="25" height="25"/> **Maven** |
| Utilities           | <img src="https://avatars.githubusercontent.com/u/45949248?s=200&v=4" width="25" height="25"/> **Lombok** |
| Testing             | <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" width="25" height="25"/> **Postman** |
| Documentation       | <img src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SW-logo-clr.png" width="40" height="40" style="vertical-align: middle;"/> **Swagger** |

---

## 📦 Project Structure
```
payment-wallet-backend/                      <-- Parent project root
│
│
├── user-service/                           <-- User Service microservice
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/surja/user/
│   │   │   │       ├── config/              <-- Security, service config, etc.
│   │   │   │       ├── controller/          <-- REST controllers
│   │   │   │       ├── service/             <-- Business logic
│   │   │   │       ├── repository/          <-- JPA repositories/interfaces
│   │   │   │       ├── entity/              <-- Entity classes
│   │   │   │       ├── dto/                 <-- Data transfer objects
│   │   │   │       └── exception/           <-- Exception handlers
│   │   │   ├── resources/
│   │   │   │   └── application.yml          <-- User service config
│   │  
│   ├── pom.xml                             <-- User service POM
│
├── wallet-service/                         <-- Wallet Service microservice
│   ├── src/
│   │   ├── main/
│   │   │   └── java/com/surja/wallet/... (same structure as above)
│   │   │   └── resources/application.yml
│   ├── pom.xml
│
├── notification-service/                   <-- Notification Service microservice
│   ├── src/
│   │   ├── main/
│   │   │   └── java/com/surja/notification/...
│   │   │   └── resources/application.yml
│   │   ├── test/java/com/yourdomain/notification/
│   ├── pom.xml
│
├── transaction-service/                    <-- Transaction Service microservice
│   ├── src/
│   │   ├── main/
│   │   │   └── java/com/surja/transaction/...
│   │   │   └── resources/application.yml
│   ├── pom.xml
│
├── pom.xml                               <-- Parent aggregator POM for all modules
└── README.md                            <-- Parent project README
```

## 🚧 Future Update Plan

- Integrate additional payment gateway options (e.g., PayPal, Stripe).  
- Add admin dashboard for managing users and wallets.  
- Implement two-factor authentication (2FA) for enhanced security.  
- Develop mobile SDKs for easy wallet integration in Android/iOS apps.
---

##  Developer Contact

Please feel free to reach out with any questions, feedback, or support needs. Your input is always appreciated.

|             |                                    |
|-------------|------------------------------------|
| **Name**    | Surja Kumar Jana                    |
| **Email**   | [janaofficial0110@gmail.com](mailto:janaofficial0110@gmail.com) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="16" height="16" style="vertical-align:middle"/> GitHub   | [surjakumarjana0110](https://github.com/SurjaKumarJana) |
| <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg" width="16" height="16" style="vertical-align:middle"/> LinkedIn | [Surja Kumar Jana](https://www.linkedin.com/in/surjakumarjana/) |

---

**Happy Coding! 🚀**
