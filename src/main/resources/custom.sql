CREATE DATABASE IF NOT EXISTS fastersheep DEFAULT CHARACTER SET = 'utf8mb4' DEFAULT COLLATE = 'utf8mb4_unicode_ci';
GRANT ALL PRIVILEGES ON fastersheep.* to 'randomfastersheeper'@'localhost' IDENTIFIED BY 'xFYvmtj2qK7AnG14Nb7Oft0Xd4ca05hHv9p55gUkArf520CeuHk3i24O9dR0uPS';
flush privileges;


USE fastersheep; CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, username TEXT, password TEXT, roles BIGINT, CONSTRAINT uni_tablename_column UNIQUE (username));

USE fastersheep; CREATE TABLE IF NOT EXISTS example_entity (id BIGINT AUTO_INCREMENT PRIMARY KEY, currencies BIGINT, price DECIMAL(25, 10) NOT NULL, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



exit;