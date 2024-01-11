# Library Management System API Documentation

The Library Management System API provides endpoints to manage books, patrons, and borrowing operations in a library.

## Prerequisites
- Java 8 or higher installed on your machine.
- Maven build tool installed.

## Running the Application
1. Clone the repository `git clone <repository-url>`
2. Navigate to the project directory `cd library-management-system-api`
3. Build the application using Maven `mvn clean install`
4. Run the application `mvn spring-boot:run`
- The application will start running on http://localhost:8080.
## API Endpoints
The following API endpoints are available:
### Books
- GET /api/books: Retrieve a list of all books.

- GET /api/books/{id}: Retrieve a book by its ID.

- POST /api/books: Create a new book.

- PUT /api/books/{id}: Update an existing book.

- PATCH /api/books/{id}: Update partially an existing book.

- DELETE /api/books/{id}: Delete a book.
### Patrons
- GET /api/patrons: Retrieve a list of all patrons.

- GET /api/patrons/{id}: Retrieve a patron by their ID.

- POST /api/patrons: Create a new patron.

- PUT /api/patrons/{id}: Update an existing patron.

- PATCH /api/patrons/{id}: Update partially an existing patron.

- DELETE /api/patrons/{id}: Delete a patron.

### Borrowing Operations
- POST /api/borrow/{id}/patron/{id}: Borrow a book by specifying the book ID and patron ID.
- POST /api/borrow/{id}/patron/{id}/return: Return a borrowed book by specifying the book ID and patron ID.

## Authentication
Authentication is required to access certain API endpoints. To authenticate, include the following header in your requests:
- `Authorization: Bearer <token>` Replace <token> with a valid authentication token.