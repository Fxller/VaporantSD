# 🌬️ Vaporant

<div align="center">

**Modern E-Commerce Platform for Electronic Cigarettes & Vaping Accessories**

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

</div>

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#️-configuration)
- [Running the Application](#-running-the-application)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [API Endpoints](#-api-endpoints)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 Overview

**Vaporant** is a full-featured e-commerce web application built with Spring Boot, designed specifically for the electronic cigarette and vaping accessories market. The platform provides a secure, intuitive, and responsive shopping experience with modern features including user authentication, dynamic product catalogs, shopping cart management, and PDF invoice generation.

### Why Vaporant?

- 🔒 **Secure Authentication** - User account management with session handling
- 📦 **Dynamic Product Management** - Browse and search through an extensive catalog
- 🛒 **Smart Shopping Cart** - Add, remove, and manage products effortlessly
- 💳 **Multiple Payment Methods** - Support for various payment options
- 📄 **PDF Invoice Generation** - Automated professional invoices using Apache PDFBox
- 📱 **Responsive Design** - Seamless experience across all devices
- 🚀 **Modern Architecture** - Built with Spring Boot 3.2 and Java 17

---

## ✨ Features

### Customer Features
- **User Registration & Login** - Secure account creation and authentication
- **Product Browsing** - Explore products with detailed descriptions and images
- **Advanced Search** - Find products quickly with our search functionality
- **Shopping Cart** - Add/remove items and view cart totals in real-time
- **Checkout Process** - Streamlined checkout with address management
- **Order History** - Track your order history and view past purchases
- **Invoice Download** - Download professional PDF invoices for your orders
- **User Profile Management** - Update personal information and addresses

### Admin Features
- **Product Management** - Add, edit, and remove products
- **Order Management** - View and process customer orders
- **Inventory Control** - Track product stock levels

### Technical Features
- ✅ Spring Boot 3.2.0 with embedded Tomcat
- ✅ MySQL database integration with JDBC
- ✅ JSP-based templating with JSTL
- ✅ RESTful controller architecture
- ✅ PDF generation using Apache PDFBox
- ✅ Custom error handling with user-friendly pages
- ✅ Session management for cart and user data
- ✅ Maven build automation

---

## 🛠️ Tech Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring JDBC** - Database connectivity
- **Apache Tomcat 10.1.16** - Embedded web server

### Frontend
- **JSP (Jakarta Server Pages)** - Dynamic web pages
- **JSTL** - Tag library for JSP
- **HTML5/CSS3** - Modern web standards
- **JavaScript** - Client-side interactivity

### Database
- **MySQL 8.0+** - Relational database
- **HikariCP** - High-performance connection pooling

### Build & Dependencies
- **Maven 3.9+** - Dependency management and build tool
- **Apache PDFBox 2.0.30** - PDF generation

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed on your system:

| Software | Version | Download Link |
|----------|---------|---------------|
| Java JDK | 17+ | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java17) |
| Apache Maven | 3.9+ | [Maven Download](https://maven.apache.org/download.cgi) |
| MySQL Server | 8.0+ | [MySQL Installer](https://dev.mysql.com/downloads/installer/) |
| Git (optional) | Latest | [Git Download](https://git-scm.com/downloads) |

### Verify Installation

```bash
# Check Java version
java -version
# Expected output: java version "17.x.x"

# Check Maven version
mvn -version
# Expected output: Apache Maven 3.9.x

# Check MySQL
mysql --version
# Expected output: mysql  Ver 8.0.x
```

---

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/vaporant.git
cd vaporant
```

Or download the ZIP file and extract it.

### 2. Database Setup

#### Create the Database

```sql
CREATE DATABASE storage CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE storage;
```

#### Import Database Schema

Locate the SQL schema file in the `database/` directory and execute it:

```bash
mysql -u root -p storage < database/schema.sql
```

Or import it via MySQL Workbench:
1. Open MySQL Workbench
2. Connect to your MySQL server
3. File → Open SQL Script → Select `database/schema.sql`
4. Execute the script

#### Sample Data (Optional)

If you want to populate the database with sample products and users:

```bash
mysql -u root -p storage < database/sample_data.sql
```

---

## ⚙️ Configuration

### Database Configuration

Edit `src/main/resources/application.properties` with your MySQL credentials:

```properties
# Application Name
spring.application.name=vaporant

# Server Port
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/storage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JSP Configuration
spring.mvc.view.prefix=/
spring.mvc.view.suffix=.jsp
```

**Important**: Replace `YOUR_MYSQL_PASSWORD` with your actual MySQL root password.

### Port Configuration (Optional)

If port 8080 is already in use, you can change it:

```properties
server.port=8081
```

---

## 🏃 Running the Application

### Development Mode

```bash
# Navigate to project directory
cd vaporant

# Clean and build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Production Build

```bash
# Build the JAR file
mvn clean package -DskipTests

# Run the JAR
java -jar target/vaporant-0.0.1-SNAPSHOT.jar
```

### Verify Application is Running

Open your browser and navigate to:
- **Homepage**: http://localhost:8080
- **Login Page**: http://localhost:8080/loginForm.jsp
- **Products**: http://localhost:8080/ProductView.jsp

You should see the Vaporant homepage with product listings.

---

## 💡 Usage

### For Customers

1. **Register an Account**
   - Navigate to the registration page
   - Fill in your personal details
   - Submit the form to create your account

2. **Browse Products**
   - View all available products on the homepage
   - Use the search bar to find specific items
   - Click on products to view detailed information

3. **Add to Cart**
   - Click "Add to Cart" on any product
   - Adjust quantities as needed
   - View your cart to review items

4. **Checkout**
   - Proceed to checkout from your cart
   - Select or add a shipping address
   - Choose your payment method
   - Confirm your order

5. **Download Invoice**
   - After order completion, click "Download Invoice"
   - A professional PDF invoice will be generated and downloaded

### For Administrators

1. **Login with Admin Credentials**
   - Use admin credentials from the database

2. **Manage Products**
   - Add new products with images and descriptions
   - Edit existing product details
   - Update stock quantities

3. **Process Orders**
   - View all customer orders
   - Update order statuses
   - Manage customer information

---

## 📁 Project Structure

```
vaporant/
├── src/
│   ├── main/
│   │   ├── java/com/vaporant/
│   │   │   ├── controller/          # Spring MVC Controllers
│   │   │   │   ├── LoginControl.java
│   │   │   │   ├── ProductControl.java
│   │   │   │   ├── OrderControl.java
│   │   │   │   ├── FatturaControl.java    # Invoice & PDF generation
│   │   │   │   └── ...
│   │   │   ├── model/               # Data Models (Beans)
│   │   │   │   ├── UserBean.java
│   │   │   │   ├── ProductBean.java
│   │   │   │   ├── OrderBean.java
│   │   │   │   └── ...
│   │   │   ├── repository/          # DAO Layer
│   │   │   │   ├── UserDaoImpl.java
│   │   │   │   ├── ProductModelDM.java
│   │   │   │   ├── OrderDaoImpl.java
│   │   │   │   └── ...
│   │   │   ├── util/                # Utility Classes
│   │   │   │   └── DataSourceUtil.java
│   │   │   └── VaporantApplication.java  # Spring Boot Main Class
│   │   ├── resources/
│   │   │   └── application.properties    # Configuration
│   │   └── webapp/
│   │       ├── css/                 # Stylesheets
│   │       ├── img/                 # Images
│   │       ├── *.jsp                # JSP Pages
│   │       ├── Header.jsp
│   │       ├── Footer.jsp
│   │       ├── ProductView.jsp
│   │       ├── loginForm.jsp
│   │       ├── checkout.jsp
│   │       ├── fattura.jsp          # Invoice page
│   │       └── error.jsp            # Error handling
│   └── test/                        # Test files
├── database/                        # SQL scripts
│   ├── schema.sql
│   └── sample_data.sql
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── GUIDA_INSTALLAZIONE.md          # Italian installation guide
```

---

## 🔌 API Endpoints

### Public Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Homepage with product listings |
| GET | `/loginForm.jsp` | User login page |
| GET | `/ProductView.jsp` | Product catalog |
| GET | `/details` | Product detail page |

### Authenticated Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/login` | User authentication |
| POST | `/sign` | User registration |
| GET | `/logout` | User logout |
| GET | `/Utente.jsp` | User profile page |
| POST | `/modify` | Update user information |
| GET | `/cart` | View shopping cart |
| POST | `/order` | Place an order |
| GET/POST | `/fattura` | View invoice |
| GET | `/fattura/download` | Download PDF invoice |

### Admin Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin` | Admin dashboard |
| POST | `/product/add` | Add new product |
| POST | `/product/edit` | Edit product |
| POST | `/product/delete` | Delete product |

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit your changes** (`git commit -m 'Add some AmazingFeature'`)
4. **Push to the branch** (`git push origin feature/AmazingFeature`)
5. **Open a Pull Request**

### Development Guidelines

- Follow Java coding conventions
- Write meaningful commit messages
- Update documentation for new features
- Test your changes thoroughly before submitting

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🐛 Troubleshooting

### Common Issues

**Issue**: Port 8080 already in use
```
Solution: Change the port in application.properties:
server.port=8081
```

**Issue**: MySQL connection refused
```
Solution: 
1. Ensure MySQL server is running
2. Check credentials in application.properties
3. Verify database 'storage' exists
```

**Issue**: JSP pages not rendering
```
Solution:
1. Clean Maven cache: mvn clean
2. Rebuild: mvn package
3. Restart the application
```

**Issue**: PDF download not working
```
Solution:
1. Ensure order session exists
2. Check browser download settings
3. Verify PDFBox dependency in pom.xml
```

---

## 🙏 Acknowledgments

- Spring Boot Team for the excellent framework
- Apache PDFBox for PDF generation capabilities
- The open-source community for continuous support

---

<div align="center">

**Made with ❤️ by the Vaporant Team**

⭐ **If you find this project useful, please consider giving it a star!** ⭐

</div>
