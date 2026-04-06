# 💰 Digital Wallet & Payment System

## 🚀 Overview

A full stack **Digital Wallet & Payment System** built using **Spring Boot (Backend)** and **React.js (Frontend)**.
This application enables users to securely manage their wallet, perform transactions, and view transaction history.

---

## 🛠️ Tech Stack

* **Backend:** Java, Spring Boot, Spring Security
* **Frontend:** React.js
* **Database:** MySQL
* **Authentication:** JWT (JSON Web Token)
* **Architecture:** REST APIs, Layered Architecture

---

## ⚙️ Features

* User authentication & authorization (JWT-based)
* Add money to wallet
* Secure fund transfer between users
* Transaction history tracking
* Input validation & exception handling
* Atomic transactions using `@Transactional`

---

## 🧠 Key Highlights

* Developed a secure backend using best coding practices and modular design
* Designed and implemented RESTful APIs for wallet operations
* Created normalized MySQL schema ensuring data integrity
* Ensured transactional consistency for money transfers
* Followed clean architecture using OOP principles

---

## 📂 Project Structure

```id="ptv8dn"
wallet/
 ├── frontend/        # React Application
 ├── src/             # Backend (Spring Boot)
 ├── pom.xml
```

---

## ▶️ How to Run

### 🔹 Backend

```id="ghq3q2"
mvn spring-boot:run
```

### 🔹 Frontend

```id="qfsl96"
cd frontend
npm install
npm start
```

---

## 📌 API Endpoints (Sample)

* POST /api/auth/register
* POST /api/auth/login
* POST /api/wallet/add
* POST /api/wallet/transfer
* GET /api/transactions

---

## 👩‍💻 Author

Anuksha Chikte
