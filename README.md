# BookIt WebApp

BookIt is a versatile web application designed for seamless book management and delivery. Whether you're a library manager or a book lover, BookIt provides an efficient platform for managing your reading resources.

## Tech Stack

- **Frontend**: Angular
- **Backend**: Java Spring Boot, Spring Security
- **Database**: MySQL

## Features

- **Remote Book Delivery**: Easily request and schedule book deliveries to your location, making reading accessible from the comfort of your home.
- **Local Library Management**: Manage your local library's inventory with ease. Track available books, manage user accounts, and oversee lending processes.
- **Automated Email Reminders**: Receive timely email notifications reminding users about upcoming book returns, ensuring that books are returned on time and helping to maintain library inventory.
- **User-Friendly Interface**: Navigate through a clean and intuitive interface, designed for users of all ages.
- **Responsive Design**: Access BookIt from any device, ensuring a smooth experience whether you're on a desktop, tablet, or smartphone.

## Getting Started

To get started with BookIt, follow the instructions below for both the frontend and backend setup.

### Prerequisites

- [Node.js](https://nodejs.org/) (version 14 or higher)
- [Angular CLI](https://angular.io/cli) (install via npm)
- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 11 or higher)
- [Maven](https://maven.apache.org/) (for building the Spring Boot application)
- [MySQL](https://www.mysql.com/) (for database management)

### Frontend Setup (Angular)

1. Navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install the dependencies:

   ```bash
   npm install
   ```

3. Start the Angular application:

   ```bash
   ng serve
   ```

4. Access the application at `http://localhost:4200`.

### Backend Setup (Spring Boot)

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Set up your MySQL database and create a database named `bookit`.

3. Configure the application properties. Open `src/main/resources/application.properties` and add your database connection details:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookit
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. Build and run the Spring Boot application:

   ```bash
   mvn spring-boot:run
   ```

5. The backend will be accessible at `http://localhost:8080`.

## Usage

Once both the frontend and backend are running, you can use the application to manage your book inventory and request deliveries.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes and commit them (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a pull request.

Feel free to modify any section to better fit your project's needs!
