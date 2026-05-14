# 🚀 Smart Job Aggregator API

## 📌 Overview

Smart Job Aggregator is a backend REST API project built using Java and Spring Boot. The system manages users, companies, job posts, and job applications while also providing job recommendations based on user skills.

The project follows a layered and modular architecture with separate:

- 🧠 Domain Layer
- ⚙️ Infrastructure Layer
- 🛠️ Use Case Layer
- 🌐 Web Layer

The application also integrates with an external jobs API to aggregate job recommendations from multiple sources.

---

## ✨ Features

### 🔥 Core Features
- 🏛️ Clean-Architecture
- 👤 User management
- 🏢 Company management
- 💼 Job post management
- 📄 Job application management
- 🎯 Skill-based job recommendation system
- 🌍 Multi-source job recommendations (Multi treading handle)
- 🔐 Role-based authorization
- 🚦 Request rate limiting
- ⏰ Scheduled background tasks
- ❌ Global exception handling
- 📦 Standardized API responses
- 🗑️ Soft delete support
- 🔗 External API integration

---

## 🧰 Technologies Used


| Technology | Purpose |
|---|---|
|  Java 21 | Main programming language |
| Spring Boot 4 | Backend framework |
|  Spring Data JPA | ORM and database operations |
|  PostgreSQL | Relational database |
|  Spring Validation | Request validation |
|  Spring Security | Security configuration |
|  MapStruct | Object mapping |
|  Lombok | Reduce boilerplate code |
|  Spring Modulith | Modular architecture support |
|  Spring Web MVC | REST API development |
|  Spring WebFlux | Reactive support |
|  Spring RestClient | External API communication |
|  Swagger / OpenAPI | API documentation |
|  Hibernate | ORM provider |
|  Gradle | Build tool |
|  H2 Console | Development/testing support |


---

## 🧱 Project Architecture

### 🏛️ Clean Architecture

The project follows Clean Architecture principles.

```text
               🌐 Web Layer
          (Controllers, DTOs, APIs)
                     ↓
             🛠️ Use Case Layer
          (Business Application Logic)
                     ↓
              🧠 Domain Layer
          (Core Business Rules & Models)
                     ↑
           ⚙️ Infrastructure Layer
     (Database, External APIs, Persistence)
```

---

## 📂 Project Structure

```text

This project is built using **Clean Architecture** and **Domain-Driven Design (DDD)**. Each layer has a strict responsibility to ensure the business logic remains decoupled from external frameworks.

lk.job_finder_app.smart_job_aggregator
├── 📁 domain                                        @Core Business Logic & Enterprise Rules
│   ├── 📁 models                                    @Pure Domain Entities & Aggregates
│   │   ├── 📁 enums                                 @Domain Constants (Status & Roles)
│   │   │   ├── ApplicationStatus.java
│   │   │   ├── JobStatus.java
│   │   │   └── RoleName.java
│   │   ├── Company.java                             # Company Domain Model
│   │   ├── JobApplication.java                      # Job Application Domain Model
│   │   ├── JobApplicationAggregate.java             # Combined Application & Job data
│   │   ├── JobPost.java                             # Job Post Domain Model
│   │   ├── JobPostWithCompanyAggregate.java         # Job Post with Company details
│   │   ├── Role.java                                # User Role Domain Model
│   │   └── User.java                                # User Domain Model (contains matching logic)
│   └── 📁 repositories                              @Domain Repository Interfaces (Outbound Ports)
│       ├── CompanyRepository.java
│       ├── JobApplicationRepository.java
│       ├── JobPostRepository.java
│       └── UserRepository.java
│
├── 📁 usecase                                       @Application Specific Business Rules
│   ├── 📁 company                                   @Inbound Port for Company Operations
│   │   ├── CompanyUseCase.java                      # Feature Interface
│   │   └── CompanyUseCaseImpl.java                  # Coordination of Domain & Repo
│   ├── 📁 jobApplication                            @Application Lifecycle Logic
│   │   ├── JobApplicationUseCase.java
│   │   └── JobApplicationUseCaseImpl.java
│   ├── 📁 jobPost                                   @Job Aggregation Logic (Local + External)
│   │   ├── JobPostUseCase.java
│   │   └── JobPostUseCaseImpl.java
│   └── 📁 user                                      @User & Recommendation Workflows
│       ├── UserUseCase.java
│       └── UserUseCaseImpl.java
│
├── 📁 infrastructure                                @External Frameworks, Tools & Adapters
│   ├── 📁 external_api                              @Integration with Third-Party Services
│   │   └── 📁 museAPI                               @The Muse API Integration
│   │       ├── 📁 client                            # RestClient Implementation
│   │       │   └── TheMuseClient.java
│   │       ├── 📁 DTOs                              # External API Data Contracts
│   │       │   └── ExternalJobResponseDTO.java
│   │       └── 📁 mappers                           # External DTO -> Domain Mapper
│   │           └── ExternalJobMapper.java
│   ├── 📁 [module] (e.g., jobPost, user, company)   @Infrastructure Implementation per Module
│   │   ├── 📁 config                                # Bean Definition (DI Configuration)
│   │   │   ├── [Module]PersistenceBeanConfig.java
│   │   │   └── [Module]UseCaseBeanConfig.java
│   │   └── 📁 persistence                           # Database Layer (PostgreSQL/H2)
│   │       ├── 📁 entity                            # JPA @Entity Definitions
│   │       │   └── [Module]Entity.java
│   │       ├── 📁 jpa                               # Spring Data JPA Interfaces
│   │       │   └── Jpa[Module]Repository.java
│   │       ├── 📁 mapper                            # Domain <-> Entity Mapping (MapStruct)
│   │       │   └── [Module]PersistenceMapper.java
│   │       └── [Module]RepositoryImpl.java          # Adapter connecting Domain to JPA
│   └── 📁 role                                      # Persistence for Security Roles
│
├── 📁 web                                           @Entry Points & Delivery (UI/API)
│   ├── 📁 [module] (e.g., jobPost, company, user)
│   │   ├── 📁 controllers                           # REST API Endpoints (@RestController)
│   │   ├── 📁 DTOs                                  # API Request/Response JSON structures
│   │   │   ├── [Module]RequestDTO.java
│   │   │   └── [Module]ResponseDTO.java
│   │   └── 📁 webMappers                            # Web DTO <-> Domain Mapping
│   │       └── [Module]WebMapper.java
│   ├── 📁 security                                  @Custom Security Interceptors
│   │   ├── Authorize.java                           # Custom Authorization Annotation
│   │   └── SecurityInterceptor.java                 # RBAC Enforcement logic
│   └── 📁 user
│       ├── 📁 Config                                # MVC Interceptor Registration
│       │   └── WebConfig.java
│       └── 📁 interceptor                            # API Protection logic
│           └── RateLimitInterceptor.java            # Role-aware Request Throttling
│
├── 📁 globalExceptionHandler                        @Centralized Exception Management
│   ├── 📁 superClasses                              # Custom Exception Types
│   │   ├── BadRequestException.java
│   │   ├── ResourceNotFoundException.java
│   │   └── ... (Unauthorized, Forbidden, Conflict)
│   ├── ErrorMessage.java                            # Standardized Error Response Body
│   └── GlobalExceptionHandler.java                  # @RestControllerAdvice for the App
│
├── 📁 globalResponseHandler                         @Generic Response Wrapping
│   └── StandardResponse.java                        # Unified Success/Failure Envelope
│
├── 📁 spring_security_config                        @Security & Authentication Setup
│   └── SecurityConfig.java                          # Stateless Auth & Filter Chain
│
└── SmartJobAggregatorApplication.java               @Spring Boot Main Class

```

---

## 🧠 Domain Models

### 👤 User

Represents application users.

### Fields

- userId
- userName
- userEmail
- role
- skillsRequired

---

### 🏢 Company

Represents companies posting jobs.

### Fields

- companyId
- companyName
- companyIndustry
- companyRating

---

### 💼 JobPost

Represents job vacancies.

### Fields

- postId
- postTitle
- postDescription
- postSalary
- createdAt
- jobStatus
- skillsRequired
- companyId

### ⚙️ Business Logic

- Automatically assigns ACTIVE status for new job posts.
- Automatically expires old posts.

---

### 📄 JobApplication

Represents user applications.

### Fields

- jobApplicationId
- resumeUrl
- matchScore
- applicationStatus
- appliedAt
- userId
- jobPostId
- companyId

### ⚙️ Business Logic

- Automatically sets default status to PENDING.
- Calculates skill matching score.

---

## 🏷️ Enums

### 👥 RoleName

```java
ADMIN
USER
COMPANY_RECRUITER
```

### 📌 JobStatus

```java
ACTIVE
EXPIRED
CLOSED
```

### 📄 ApplicationStatus

```java
PENDING
SELECTED
REJECTED
```

---

##  🗄️ Database Features

---

### 🔗 JPA Relationships

 ---

### Many-to-One Relationships

- JobApplication → User
- JobApplication → JobPost
- JobPost → Company
- User → Role

### 📚 Element Collections

- User skills
- Job post required skills

---

## 🔄 Object Mapping

The project uses MapStruct for:

- DTO to Domain mapping
- Domain to Entity mapping
- Entity to Domain mapping
- Aggregate response mapping
- Update entity mapping

### 🧩 Mapper Types

- Persistence Mappers
- Web Mappers
- Aggregate Mappers

---

## 🔐 Security System

### 🛡️ Custom Authorization

The project uses a custom `@Authorize` annotation.

```java
@Authorize(RoleName.ADMIN)
```

### 👥 Supported Roles

- ADMIN
- USER
- COMPANY_RECRUITER

---

### 🚨 Security Interceptor

A custom interceptor validates:

- User availability
- User roles
- Access permissions
- Request headers

### 📨 Required Header

```http
X-User-Id
```

---

## 🚦 Rate Limiting

The project includes a custom request rate limiter.

### ✨ Features

- User-based request tracking
- Role-based request limits
- Automatic request count reset every minute
- ConcurrentHashMap for thread-safe tracking

---

## ⏰ Scheduled Tasks


### 🧹 Automatic Job Expiration

```java
@Scheduled(cron = "0 * * * * *")
```

Automatically changes old ACTIVE job posts into EXPIRED status.

---

## 🌍 External API Integration

### 🎯 The Muse API

The application fetches external jobs from:

```text
https://www.themuse.com/api/public
```

### ✨ Features

- Fetch external job listings
- Convert external jobs into domain models
- Aggregate local and external job recommendations

---

## ⚡ Multi-Threading

The project uses `CompletableFuture` for asynchronous processing.

### 🧵 Used For

- Fetching local job recommendations
- Fetching external API recommendations
- Combining multiple recommendation sources

---

## 🎯 Job Recommendation System

### 🧠 Skill Matching

The recommendation engine:

1. Reads user skills
2. Finds matching job skills
3. Calculates matching score
4. Returns recommended jobs

### 📊 Matching Score Formula

```text
(matchCount / requiredSkills) * 100
```

---

## 🗑️ Soft Delete

The project uses Hibernate Soft Delete.

```java
@SoftDelete(columnName = "is_deleted")
```

Used in:

- UserEntity
- CompanyEntity
- JobPostEntity
- JobApplicationEntity

---

## ✅ Validation

The project uses Jakarta Validation.

### 📋 Common Validations

- @NotBlank
- @NotNull
- @NotEmpty
- @Email

---

## ❌ Global Exception Handling

Custom exception handling is implemented using:

```java
@RestControllerAdvice
```

### 🚨 Custom Exceptions

```
- BadRequestException -> 400
- UnauthorizedException -> 401
- ConflictException -> 409
- ForbiddenException -> 403
- ResourceNotFoundException -> 404
- Generic Exception → 500
```
---

## 📦 Standard API Response

All API responses follow a common structure.

```json
{
  "status": 200,
  "message": "Success",
  "timestamp": "2026-01-01T10:00:00",
  "data": {}
}
```

---

## 🌐 REST API Endpoints

### 🏢 Company Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/company/ | Get all companies |
| POST | /api/v1/company/ | Create company |
| PUT | /api/v1/company/{companyId} | Update company |
| DELETE | /api/v1/company/{companyId} | Delete company |

---

### 👤 User Endpoints

| Method | Endpoint | Description                   |
|---|---|-------------------------------|
| GET | /api/v1/user/ | Get all users                 |
| GET | /api/v1/user/recommendations/{userId} | Recommended jobs              |
| GET | api/v1/user/recommendations/multi-source/{userId} | Multisources Recommended jobs |
| POST | /api/v1/user/ | Create user                   |
| PUT | /api/v1/user/{userId} | Update user                   |
| DELETE | /api/v1/user/{userId} | Delete user                   |


---

### 💼 Job Post Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/job-post/ | Get all job posts |
| POST | /api/v1/job-post/ | Create job post |
| PUT | /api/v1/job-post/{postId} | Update job post |
| DELETE | /api/v1/job-post/{postId} | Delete job post |

---

### 📄 Job Application Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/v1/job-application/ | Get all applications |
| POST | /api/v1/job-application/apply | Apply for a job |
| PUT | /api/v1/job-application/{jobApplicationId} | Update application |

---

## 📘 Swagger Documentation

Swagger UI is enabled using SpringDoc OpenAPI.

### 🔗 Swagger Endpoints

```text
/swagger-ui.html
/v3/api-docs
```

---
 

## 📚 Key Concepts Implemented

### 🛠️ Backend Concepts

- REST API Development
- Layered Architecture
- Clean Architecture
- Repository Pattern
- DTO Pattern
- Dependency Injection
- Custom Interceptors
- Exception Handling
- Scheduling
- Asynchronous Processing
- External API Integration
- Role-Based Access Control
- Rate Limiting
- Object Mapping
- Modular Monolith Architecture


---

# 👨‍💻 Author

Developed as a Clean Architecture practice project focusing on:

- Real-world backend structure
- Scalable system design
- Separation of concerns
---

# ⭐ Project Purpose

This project was built to practice:

- Clean Architecture
- Spring Boot advanced structuring
- DTO & mapping strategies
- Business rule implementation
- API design best practices
- Multithreaded usage in multiple resources usage
- Rich Domain Models
- Stander Error Response
---
