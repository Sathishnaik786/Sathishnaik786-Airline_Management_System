# Airline Management System

A comprehensive Spring Boot-based airline management system with JWT authentication, role-based access control, and RESTful APIs.

## Features

- **User Authentication**: JWT-based authentication for secure access
- **Role-Based Access Control**:
  - Admin: Full system access
  - Staff: Limited access to operational functions
  - User: Access to flight search and booking
- **Flight Management**: CRUD operations for flight schedules
- **Booking System**: Ticket reservation and management
- **Search Functionality**: Public flight search endpoint

## Technology Stack

- **Backend**: Spring Boot 3.x
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven
- **Testing**: JUnit, Mockito

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/airline-management-system.git
   ```
2. Configure the database:
   - Create a MySQL database
   - Update `application.properties` with your database credentials

3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
## API Endpoints

| Method | Endpoint                | Description                     | Access Level |
|--------|-------------------------|---------------------------------|--------------|
| POST   | /api/auth/register      | User registration               | Public       |
| POST   | /api/auth/login         | User login                      | Public       |
| GET    | /api/flights/search     | Search available flights        | Public       |
| GET    | /api/admin/flights      | Get all flights                 | Admin        |
| POST   | /api/admin/flights      | Create new flight               | Admin        |
| GET    | /api/staff/bookings     | View all bookings               | Staff/Admin  |

## Security Configuration

The system uses JWT authentication with the following security settings:

- CSRF protection disabled for API endpoints
- Stateless session management
- Role-based access control
- BCrypt password encoding

## Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a pull request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Project Maintainer: [Islavath Sathish Naik]  
Email: sathishnaikislavath@gmail.com  
Project Link: https://github.com/yourusername/airline-management-system
