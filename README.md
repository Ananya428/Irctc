# Railway Management System API

## Project Overview
This project is a railway management system similar to IRCTC, where users can check train availability between two stations,
 view seat availability, and book seats. It includes role-based access with two types of users: Admin and regular users.

## Tech Stack
- **Backend**: Spring Boot
- **Database**: MySQL

## Requirements
1. Register a User
2. Login User
3. Add a New Train (Admin only)
4. Get Seat Availability
5. Book a Seat
6. Get Specific Booking Details

## Setup Instructions

Database Setup
Create a Database:

1- Open your MySQL client (like MySQL Workbench).
Create a new database named railway_db

"CREATE DATABASE railway_db;"

2- Configure Database Credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/SpringDBPractice
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.data.jpa.repositories.enabled=true
spring.jpa.show-sql=true
spring.jpa.database=mysql


Build and Run the Application

1- Build the Project:

In the terminal, ensure you are in the root directory of the project.
Run the following command to clean and build the project
"mvn clean install"

2- Run the Application:

After the build is successful, start the Spring Boot application by running:
"mvn spring-boot:run"

The application should start, and you should see logs indicating that the application is running.


API Endpoints:
Here are the details of the API endpoints available in the application:

1. Register a User

   API - /register

   Request -

curl --location 'localhost:8040/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "Ananya12",
    "password": "Ananya@1234",
    "dob": "1999-01-01",
    "emailId": "a@a.com",
    "role": "USER"
}'

    Response- User registered successfully

2. Login User

   API- /login

   Request- curl --location 'localhost:8040/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "Ananya12",
    "password": "Ananya@1234"
}'

   Response- {
    "user-token": "TWF5YW5rMjpNYXlhbmtAMTIzNA=="
		}
3. Add a New Train

   API - /addTrain

   Request -
curl --location 'localhost:8040/addTrain' \
--header 'admin-token: TWF5YW5rMTpNYXlhbmtAMTIzNA==' \
--header 'Content-Type: application/json' \
--data '{
    "name":"rajdhani",
    "source": "delhi",
    "destination": "bangalore",
    "totalSeats": 500,
    "availableSeats": 500
}'

      Response- Train added successfully

4. Get Seat Availability
	API- /trains

	Request-curl --location 'localhost:8040/trains?source=delhi&destination=mumbai'

	Response- [
    {
        "id": 1,
        "name": "rajdhani",
        "source": "delhi",
        "destination": "mumbai",
        "totalSeats": 500,
        "availableSeats": 500
    },
    {
        "id": 2,
        "name": "rajdhani",
        "source": "delhi",
        "destination": "mumbai",
        "totalSeats": 500,
        "availableSeats": 500
    },
    {
        "id": 52,
        "name": "rajdhani",
        "source": "delhi",
        "destination": "mumbai",
        "totalSeats": 500,
	"availableSeats": 448
     }
]


5. Book a Seat
 	API- /bookSeat

	Request-
curl --location --request POST 'localhost:8040/bookSeat?trainId=52&seats=3' \
--header 'user-token: TWF5YW5rMjpNYXlhbmtAMTIzNA=='

	Response- Seat booked successfully
6. Get Specific Booking Details

	API- /bookingDetails
	Request-curl --location 'localhost:8040/bookingDetails' \
--header 'user-token: TWF5YW5rMjpNYXlhbmtAMTIzNA=='
	Response-[
    {
        "id": 1,
        "user": {
            "id": 3,
            "dob": null,
            "username": "Ananya2",
            "emailId": null,
            "role": "USER"
        },
        "train": {
            "id": 52,
            "name": "rajdhani",
            "source": "delhi",
            "destination": "mumbai",
            "totalSeats": 500,
            "availableSeats": 497
        },
        "seatsBooked": 3
    }
]

testing
