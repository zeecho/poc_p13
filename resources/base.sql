CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    date_of_birth VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL
);

CREATE TABLE LocationAgency (
    agency_id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    zipcode VARCHAR(50) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

CREATE TABLE Car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    year YEAR NOT NULL,
    price_per_day DECIMAL(10, 2) NOT NULL
);

CREATE TABLE RentalOffer (
    offer_id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT,
    start_city VARCHAR(100) NOT NULL,
    end_city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (car_id) REFERENCES Car(car_id)
);

CREATE TABLE Reservation (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    offer_id INT,
    user_id INT,
    reservation_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (offer_id) REFERENCES RentalOffer(offer_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Payment (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATETIME NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)
);

CREATE TABLE Message (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    content TEXT NOT NULL,
    sent_at DATETIME NOT NULL,
    is_read BOOLEAN NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

