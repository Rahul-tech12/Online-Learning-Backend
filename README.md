📘 Online Learning Platform – Backend

This repository contains the backend service for the Online Learning Platform, built using Spring Boot, Spring Security (JWT), JPA/Hibernate, and PostgreSQL.

The backend is responsible for authentication, authorization, course management, enrollments, and secure communication with the frontend.

🚀 Tech Stack

Java 21

Spring Boot

Spring Security (JWT-based authentication)

Spring Data JPA (Hibernate)

PostgreSQL (Local / Neon)

Maven

Swagger (OpenAPI)

🏗️ Architecture Overview

Controller Layer → REST APIs

Service Layer → Business logic

Repository Layer → Database access (JPA)

Security Layer → JWT, role-based authorization

DTO Layer → Clean API responses

Entity Layer → Database models

🔐 Authentication & Authorization

Stateless authentication using JWT

Role-based access control

Supported roles:
        ROLE_ADMIN
        ROLE_STUDENT

📦 Core Features (Implemented)
👤 User Management

User registration

Login with JWT

Profile retrieval & update

Role-based access

📚 Course Management

Admin can create, update, delete courses

Publish/unpublish courses

Fetch published courses for students

🎓 Enrollment System

Students can enroll in courses

Prevent duplicate enrollments

Fetch enrolled courses per user

Enrollment statistics per course (admin)

🔐 Security

JWT filter

Stateless session management

CORS configuration for local & production

Protected endpoints

📄 API Documentation

Swagger UI enabled

🔮 Future Features (Planned)

These are intentional design goals and will be added incrementally.

📦 Cloud Storage

Store course images in Cloudinary / AWS S3

Signed URLs for secure access

No filesystem dependency in production

💳 Payments & Monetization

Course payments (Stripe / Razorpay)

Payment status tracking

Invoice generation

🧠 Progress Tracking

Track lesson completion

Course progress percentage

Resume from last watched lesson

🎥 Video Streaming

Video-based lessons

Secure video access

Streaming via CDN

⭐ Reviews & Ratings

Course reviews by students

Rating aggregation

Admin moderation

🔔 Notifications

Email notifications

Enrollment confirmations

Course updates

📊 Admin Analytics

Enrollment trends

Revenue reports

Active users dashboard

🧩 Microservice Readiness

Modular architecture

Service separation readiness

API versioning

🐳 Docker Support

Dockerized backend

Docker Compose for local setup

One-command startup

🚀 CI/CD

Automated build pipeline

Environment-based deployments

GitHub Actions integration

🧠 Design Philosophy

Clean layered architecture

Environment-driven configuration

Production-first mindset

Security by default

Scalability-ready
ROLE_ADMIN

ROLE_STUDENT
