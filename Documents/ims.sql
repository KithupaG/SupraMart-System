-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.38 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for supramart_db
CREATE DATABASE IF NOT EXISTS `supramart_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `supramart_db`;

-- Dumping structure for table supramart_db.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(6) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.admin: ~2 rows (approximately)
INSERT INTO `admin` (`admin_id`, `fname`, `lname`, `email`, `password`) VALUES
	('A00001', 'John', 'Doe', 'john.doe@supramart.com', 'hashedpass'),
	('A00002', 'John', 'Doe', 'johndoe@supramart.com', 'hashedpass');

-- Dumping structure for table supramart_db.admin_has_branches
CREATE TABLE IF NOT EXISTS `admin_has_branches` (
  `admin_admin_id` varchar(6) NOT NULL,
  `branches_branch_id` int NOT NULL,
  PRIMARY KEY (`admin_admin_id`,`branches_branch_id`),
  KEY `fk_admin_has_branches_branches1_idx` (`branches_branch_id`),
  KEY `fk_admin_has_branches_admin1_idx` (`admin_admin_id`),
  CONSTRAINT `fk_admin_has_branches_admin1` FOREIGN KEY (`admin_admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `fk_admin_has_branches_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.admin_has_branches: ~0 rows (approximately)
INSERT INTO `admin_has_branches` (`admin_admin_id`, `branches_branch_id`) VALUES
	('A00001', 1);

-- Dumping structure for table supramart_db.bonus
CREATE TABLE IF NOT EXISTS `bonus` (
  `bonus_id` int NOT NULL AUTO_INCREMENT,
  `bonus_name` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `role_role_id` int NOT NULL,
  `bonus_type_type_id` int NOT NULL,
  `role_role_id1` int NOT NULL,
  PRIMARY KEY (`bonus_id`),
  KEY `fk_bonus_bonus_type1_idx` (`bonus_type_type_id`),
  KEY `fk_bonus_role1_idx` (`role_role_id1`),
  CONSTRAINT `fk_bonus_bonus_type1` FOREIGN KEY (`bonus_type_type_id`) REFERENCES `bonus_type` (`type_id`),
  CONSTRAINT `fk_bonus_role1` FOREIGN KEY (`role_role_id1`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.bonus: ~12 rows (approximately)
INSERT INTO `bonus` (`bonus_id`, `bonus_name`, `amount`, `role_role_id`, `bonus_type_type_id`, `role_role_id1`) VALUES
	(2, 'Monthly Top Seller', 5000, 3, 1, 3),
	(3, 'Monthly Top Seller', 5000, 1, 1, 1),
	(4, 'Monthly Top Seller', 5000, 1, 1, 1),
	(5, 'Monthly Top Seller', 5000, 1, 1, 1),
	(6, 'Monthly Top Seller', 5000, 1, 1, 1),
	(7, 'Monthly Top Seller', 5000, 1, 1, 1),
	(8, 'Monthly Top Seller', 5000, 1, 1, 1),
	(9, 'Monthly Top Seller', 5000, 1, 1, 1),
	(10, 'Monthly Top Seller', 5000, 1, 1, 1),
	(11, 'Monthly Top Seller', 5000, 1, 1, 1),
	(12, 'Monthly Top Seller', 5000, 1, 1, 1),
	(13, 'Monthly Top Seller', 5000, 1, 1, 1);

-- Dumping structure for table supramart_db.bonus_type
CREATE TABLE IF NOT EXISTS `bonus_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.bonus_type: ~28 rows (approximately)
INSERT INTO `bonus_type` (`type_id`, `type_name`, `description`) VALUES
	(1, 'Performance', 'Monthly performance bonus'),
	(2, 'Attendance', 'Perfect attendance bonus'),
	(3, 'Performance', 'Monthly performance bonus'),
	(4, 'Attendance', 'Perfect attendance bonus'),
	(5, 'Performance', 'Monthly performance bonus'),
	(6, 'Attendance', 'Perfect attendance bonus'),
	(7, 'Performance', 'Monthly performance bonus'),
	(8, 'Attendance', 'Perfect attendance bonus'),
	(9, 'Performance', 'Monthly performance bonus'),
	(10, 'Attendance', 'Perfect attendance bonus'),
	(11, 'Performance', 'Monthly performance bonus'),
	(12, 'Attendance', 'Perfect attendance bonus'),
	(13, 'Performance', 'Monthly performance bonus'),
	(14, 'Attendance', 'Perfect attendance bonus'),
	(15, 'Performance', 'Monthly performance bonus'),
	(16, 'Attendance', 'Perfect attendance bonus'),
	(17, 'Performance', 'Monthly performance bonus'),
	(18, 'Attendance', 'Perfect attendance bonus'),
	(19, 'Performance', 'Monthly performance bonus'),
	(20, 'Attendance', 'Perfect attendance bonus'),
	(21, 'Performance', 'Monthly performance bonus'),
	(22, 'Attendance', 'Perfect attendance bonus'),
	(23, 'Performance', 'Monthly performance bonus'),
	(24, 'Attendance', 'Perfect attendance bonus'),
	(25, 'Performance', 'Monthly performance bonus'),
	(26, 'Attendance', 'Perfect attendance bonus'),
	(27, 'Performance', 'Monthly performance bonus'),
	(28, 'Attendance', 'Perfect attendance bonus'),
	(29, 'Performance', 'Monthly performance bonus'),
	(30, 'Attendance', 'Perfect attendance bonus');

-- Dumping structure for table supramart_db.branches
CREATE TABLE IF NOT EXISTS `branches` (
  `branch_id` int NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(45) DEFAULT NULL,
  `branch_address` varchar(100) DEFAULT NULL,
  `City_city_id` int NOT NULL,
  PRIMARY KEY (`branch_id`),
  KEY `fk_branches_City1_idx` (`City_city_id`),
  CONSTRAINT `fk_branches_City1` FOREIGN KEY (`City_city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.branches: ~28 rows (approximately)
INSERT INTO `branches` (`branch_id`, `branch_name`, `branch_address`, `City_city_id`) VALUES
	(1, 'Colombo Main', '123 Street, Other streeet, this city', 1),
	(2, 'Kandy Main', '456 Street, This other street, this different city', 2);

-- Dumping structure for table supramart_db.branches_has_products
CREATE TABLE IF NOT EXISTS `branches_has_products` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `branches_branch_id` int NOT NULL,
  `products_product_id` int NOT NULL,
  `suppliers_supplier_id` int NOT NULL,
  `quantity_change` int NOT NULL,
  `reasons` varchar(100) DEFAULT NULL,
  `added_date` date NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_branches_has_products_products1_idx` (`products_product_id`),
  KEY `fk_branches_has_products_branches1_idx` (`branches_branch_id`),
  KEY `fk_branches_has_products_suppliers1_idx` (`suppliers_supplier_id`),
  CONSTRAINT `fk_branches_has_products_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `fk_branches_has_products_products1` FOREIGN KEY (`products_product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `fk_branches_has_products_suppliers1` FOREIGN KEY (`suppliers_supplier_id`) REFERENCES `suppliers` (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.branches_has_products: ~11 rows (approximately)
INSERT INTO `branches_has_products` (`transaction_id`, `branches_branch_id`, `products_product_id`, `suppliers_supplier_id`, `quantity_change`, `reasons`, `added_date`) VALUES
	(1, 1, 1, 1, 100, 'Initial Stock', '2024-08-10'),
	(2, 1, 1, 1, 100, 'Initial Stock', '2024-08-10'),
	(3, 1, 1, 1, 100, 'Initial Stock', '2024-08-10');

-- Dumping structure for table supramart_db.city
CREATE TABLE IF NOT EXISTS `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `district_district_id` int NOT NULL,
  PRIMARY KEY (`city_id`),
  KEY `fk_city_district1_idx` (`district_district_id`),
  CONSTRAINT `fk_city_district1` FOREIGN KEY (`district_district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.city: ~28 rows (approximately)
INSERT INTO `city` (`city_id`, `city`, `district_district_id`) VALUES
	(1, 'Colombo 01', 1),
	(2, 'Kandy Town', 2),
	(3, 'Colombo 01', 1),
	(4, 'Kandy Town', 2),
	(5, 'Colombo 01', 1),
	(6, 'Kandy Town', 2),
	(7, 'Colombo 01', 1),
	(8, 'Kandy Town', 2),
	(9, 'Colombo 01', 1),
	(10, 'Kandy Town', 2),
	(11, 'Colombo 01', 1),
	(12, 'Kandy Town', 2),
	(13, 'Colombo 01', 1),
	(14, 'Kandy Town', 2),
	(15, 'Colombo 01', 1),
	(16, 'Kandy Town', 2),
	(17, 'Colombo 01', 1),
	(18, 'Kandy Town', 2),
	(19, 'Colombo 01', 1),
	(20, 'Kandy Town', 2),
	(21, 'Colombo 01', 1),
	(22, 'Kandy Town', 2),
	(23, 'Colombo 01', 1),
	(24, 'Kandy Town', 2),
	(25, 'Colombo 01', 1),
	(26, 'Kandy Town', 2),
	(27, 'Colombo 01', 1),
	(28, 'Kandy Town', 2);

-- Dumping structure for table supramart_db.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `registered_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `branches_branch_id` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_customers_branches1_idx` (`branches_branch_id`),
  CONSTRAINT `fk_customers_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.customers: ~0 rows (approximately)
INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `email`, `phone_number`, `registered_at`, `branches_branch_id`) VALUES
	(1, 'Jane', 'Smith', 'jane.smith@email.com', '0712345678', '2025-08-10 00:32:36', 1);

-- Dumping structure for table supramart_db.district
CREATE TABLE IF NOT EXISTS `district` (
  `district_id` int NOT NULL AUTO_INCREMENT,
  `district_name` varchar(45) DEFAULT NULL,
  `province_province_id` int NOT NULL,
  PRIMARY KEY (`district_id`),
  KEY `fk_district_province1_idx` (`province_province_id`),
  CONSTRAINT `fk_district_province1` FOREIGN KEY (`province_province_id`) REFERENCES `province` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.district: ~28 rows (approximately)
INSERT INTO `district` (`district_id`, `district_name`, `province_province_id`) VALUES
	(1, 'Colombo', 1),
	(2, 'Kandy', 2),
	(3, 'Colombo', 1),
	(4, 'Kandy', 2),
	(5, 'Colombo', 1),
	(6, 'Kandy', 2),
	(7, 'Colombo', 1),
	(8, 'Kandy', 2),
	(9, 'Colombo', 1),
	(10, 'Kandy', 2),
	(11, 'Colombo', 1),
	(12, 'Kandy', 2),
	(13, 'Colombo', 1),
	(14, 'Kandy', 2),
	(15, 'Colombo', 1),
	(16, 'Kandy', 2),
	(17, 'Colombo', 1),
	(18, 'Kandy', 2),
	(19, 'Colombo', 1),
	(20, 'Kandy', 2),
	(21, 'Colombo', 1),
	(22, 'Kandy', 2),
	(23, 'Colombo', 1),
	(24, 'Kandy', 2),
	(25, 'Colombo', 1),
	(26, 'Kandy', 2),
	(27, 'Colombo', 1),
	(28, 'Kandy', 2);

-- Dumping structure for table supramart_db.employees
CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `mobile_number_1` varchar(15) NOT NULL,
  `mobile_number_2` varchar(10) DEFAULT NULL,
  `hire_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `branch_id` int NOT NULL,
  `image_id` int NOT NULL,
  `base_salary` double DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_employees_branches1_idx` (`branch_id`),
  KEY `fk_employees_role1_idx` (`role_id`),
  KEY `fk_employees_employee_image1_idx` (`image_id`),
  CONSTRAINT `fk_employees_branches1` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `fk_employees_employee_image1` FOREIGN KEY (`image_id`) REFERENCES `employee_image` (`image_id`),
  CONSTRAINT `fk_employees_role1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.employees: ~1 rows (approximately)
INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `email`, `password`, `mobile_number_1`, `mobile_number_2`, `hire_date`, `branch_id`, `image_id`, `base_salary`, `role_id`) VALUES
	('E00001', 'William', 'Lee', 'william.lee@supramart.com', '123', '0779876543', NULL, '2025-08-10 00:32:36', 1, 1, 60000, 5);

-- Dumping structure for table supramart_db.employees_has_address
CREATE TABLE IF NOT EXISTS `employees_has_address` (
  `employee_id` varchar(10) NOT NULL,
  `City_city_id` int NOT NULL,
  `address_line_1` varchar(150) NOT NULL,
  `address_line_2` varchar(150) DEFAULT NULL,
  `postal_code` varchar(8) NOT NULL,
  PRIMARY KEY (`employee_id`,`City_city_id`),
  KEY `fk_employees_has_City_City1_idx` (`City_city_id`),
  KEY `fk_employees_has_City_employees1_idx` (`employee_id`),
  CONSTRAINT `fk_employees_has_City_City1` FOREIGN KEY (`City_city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `fk_employees_has_City_employees1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.employees_has_address: ~0 rows (approximately)
INSERT INTO `employees_has_address` (`employee_id`, `City_city_id`, `address_line_1`, `address_line_2`, `postal_code`) VALUES
	('E00001', 1, 'No. 123 Main Street', 'Colombo', '10000');

-- Dumping structure for table supramart_db.employees_has_salary
CREATE TABLE IF NOT EXISTS `employees_has_salary` (
  `salary_id` int NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(10) NOT NULL,
  `year_id` int NOT NULL,
  `month_id` int NOT NULL,
  `leave_taken` int NOT NULL,
  `OT_hours` time DEFAULT NULL,
  `extra_leave` int DEFAULT NULL,
  PRIMARY KEY (`salary_id`),
  KEY `fk_employees_has_year_has_month_year_has_month1_idx` (`year_id`,`month_id`),
  KEY `fk_employees_has_year_has_month_employees1_idx` (`employee_id`),
  CONSTRAINT `fk_employees_has_year_has_month_employees1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `fk_employees_has_year_has_month_year_has_month1` FOREIGN KEY (`year_id`, `month_id`) REFERENCES `year_has_month` (`year_id`, `month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.employees_has_salary: ~4 rows (approximately)
INSERT INTO `employees_has_salary` (`salary_id`, `employee_id`, `year_id`, `month_id`, `leave_taken`, `OT_hours`, `extra_leave`) VALUES
	(1, 'E00001', 1, 1, 0, '05:00:00', 0),
	(2, 'E00001', 1, 1, 0, '05:00:00', 0),
	(3, 'E00001', 1, 1, 0, '05:00:00', 0),
	(4, 'E00001', 1, 1, 0, '05:00:00', 0);

-- Dumping structure for table supramart_db.employee_image
CREATE TABLE IF NOT EXISTS `employee_image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_path` varchar(200) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.employee_image: ~26 rows (approximately)
INSERT INTO `employee_image` (`image_id`, `image_path`) VALUES
	(1, '/images/emp1.jpg'),
	(2, '/images/emp2.jpg'),
	(3, '/images/emp1.jpg');

-- Dumping structure for table supramart_db.expenses
CREATE TABLE IF NOT EXISTS `expenses` (
  `expenses_id` int NOT NULL AUTO_INCREMENT,
  `branches_branch_id` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `monthly_expenses` double DEFAULT NULL,
  PRIMARY KEY (`expenses_id`),
  KEY `FK_expenses_branches` (`branches_branch_id`),
  CONSTRAINT `FK_expenses_branches` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.expenses: ~0 rows (approximately)
INSERT INTO `expenses` (`expenses_id`, `branches_branch_id`, `date`, `monthly_expenses`) VALUES
	(1, 1, '2025-08-30 00:45:48', 30000);

-- Dumping structure for table supramart_db.income
CREATE TABLE IF NOT EXISTS `income` (
  `income_id` int NOT NULL,
  `branches_branch_id` int NOT NULL,
  `date` datetime NOT NULL,
  `monthly_income` double NOT NULL,
  PRIMARY KEY (`income_id`),
  KEY `fk_income_branches1_idx` (`branches_branch_id`),
  CONSTRAINT `fk_income_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.income: ~0 rows (approximately)
INSERT INTO `income` (`income_id`, `branches_branch_id`, `date`, `monthly_income`) VALUES
	(1, 1, '2025-08-30 00:45:32', 250000);

-- Dumping structure for table supramart_db.month
CREATE TABLE IF NOT EXISTS `month` (
  `month_id` int NOT NULL AUTO_INCREMENT,
  `month` varchar(20) NOT NULL,
  PRIMARY KEY (`month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.month: ~28 rows (approximately)
INSERT INTO `month` (`month_id`, `month`) VALUES
	(1, 'January'),
	(2, 'February');

-- Dumping structure for table supramart_db.petty_cash
CREATE TABLE IF NOT EXISTS `petty_cash` (
  `petty_cash_id` int NOT NULL AUTO_INCREMENT,
  `petty_cash_amount` double NOT NULL,
  `date` date NOT NULL,
  `year_has_month_year_id` int NOT NULL,
  `year_has_month_month_id` int NOT NULL,
  `branches_branch_id` int NOT NULL,
  PRIMARY KEY (`petty_cash_id`),
  KEY `fk_petty_cash_year_has_month1_idx` (`year_has_month_year_id`,`year_has_month_month_id`),
  KEY `fk_petty_cash_branches1_idx` (`branches_branch_id`),
  CONSTRAINT `fk_petty_cash_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `fk_petty_cash_year_has_month1` FOREIGN KEY (`year_has_month_year_id`, `year_has_month_month_id`) REFERENCES `year_has_month` (`year_id`, `month_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.petty_cash: ~0 rows (approximately)
INSERT INTO `petty_cash` (`petty_cash_id`, `petty_cash_amount`, `date`, `year_has_month_year_id`, `year_has_month_month_id`, `branches_branch_id`) VALUES
	(1, 15000, '2024-01-15', 1, 1, 1);

-- Dumping structure for table supramart_db.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category_id` int DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `cost` decimal(10,2) NOT NULL,
  `stock_quantity` int DEFAULT '0',
  `reorder_level` int DEFAULT '10',
  `added_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `supplier_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  KEY `FK_products_suppliers` (`supplier_id`),
  CONSTRAINT `FK_products_suppliers` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.products: ~11 rows (approximately)
INSERT INTO `products` (`product_id`, `name`, `category_id`, `price`, `cost`, `stock_quantity`, `reorder_level`, `added_on`, `supplier_id`) VALUES
	(1, 'Coca Cola 1L', 1, 250.00, 180.00, 100, 20, '2025-08-10 00:32:36', 1);

-- Dumping structure for table supramart_db.product_categories
CREATE TABLE IF NOT EXISTS `product_categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.product_categories: ~2 rows (approximately)
INSERT INTO `product_categories` (`category_id`, `category_name`) VALUES
	(1, 'Beverages'),
	(2, 'Snacks');

-- Dumping structure for table supramart_db.province
CREATE TABLE IF NOT EXISTS `province` (
  `province_id` int NOT NULL AUTO_INCREMENT,
  `province` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.province: ~28 rows (approximately)
INSERT INTO `province` (`province_id`, `province`) VALUES
	(1, 'Western'),
	(2, 'Central');

-- Dumping structure for table supramart_db.role
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.role: ~4 rows (approximately)
INSERT INTO `role` (`role_id`, `role_name`) VALUES
	(1, 'Manager'),
	(2, 'Cashier'),
	(3, 'Auditor'),
	(4, 'Inventory Manager'),
	(5, 'Branch Manager');

-- Dumping structure for table supramart_db.salary_has_bonus
CREATE TABLE IF NOT EXISTS `salary_has_bonus` (
  `employees_has_salary_salary_id` int NOT NULL,
  `bonus_bonus_id` int NOT NULL,
  PRIMARY KEY (`employees_has_salary_salary_id`,`bonus_bonus_id`),
  KEY `fk_employees_has_salary_has_bonus_bonus1_idx` (`bonus_bonus_id`),
  KEY `fk_employees_has_salary_has_bonus_employees_has_salary1_idx` (`employees_has_salary_salary_id`),
  CONSTRAINT `fk_salary_has_bonus_bonus1` FOREIGN KEY (`bonus_bonus_id`) REFERENCES `bonus` (`bonus_id`),
  CONSTRAINT `fk_salary_has_bonus_employees_has_salary1` FOREIGN KEY (`employees_has_salary_salary_id`) REFERENCES `employees_has_salary` (`salary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.salary_has_bonus: ~0 rows (approximately)

-- Dumping structure for table supramart_db.sales
CREATE TABLE IF NOT EXISTS `sales` (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `total_amount` decimal(10,2) NOT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `sale_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `branches_branch_id` int NOT NULL,
  `employee_id` varchar(10) NOT NULL,
  `customers_customer_id` int NOT NULL,
  PRIMARY KEY (`sale_id`),
  KEY `fk_sales_branches1_idx` (`branches_branch_id`),
  KEY `fk_sales_employees1_idx` (`employee_id`),
  KEY `fk_sales_customers1_idx` (`customers_customer_id`),
  CONSTRAINT `fk_sales_branches1` FOREIGN KEY (`branches_branch_id`) REFERENCES `branches` (`branch_id`),
  CONSTRAINT `fk_sales_customers1` FOREIGN KEY (`customers_customer_id`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `fk_sales_employees1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.sales: ~2 rows (approximately)
INSERT INTO `sales` (`sale_id`, `total_amount`, `payment_method`, `sale_date`, `branches_branch_id`, `employee_id`, `customers_customer_id`) VALUES
	(1, 500.00, 'Cash', '2025-08-10 00:33:54', 1, 'E00001', 1),
	(2, 500.00, 'Cash', '2025-08-10 00:33:56', 1, 'E00001', 1);

-- Dumping structure for table supramart_db.sale_items
CREATE TABLE IF NOT EXISTS `sale_items` (
  `sale_item_id` int NOT NULL AUTO_INCREMENT,
  `sale_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`sale_item_id`),
  KEY `sale_id` (`sale_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `sale_items_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`sale_id`) ON DELETE CASCADE,
  CONSTRAINT `sale_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.sale_items: ~2 rows (approximately)
INSERT INTO `sale_items` (`sale_item_id`, `sale_id`, `product_id`, `quantity`, `price`) VALUES
	(1, 1, 1, 2, 250.00),
	(2, 1, 1, 2, 250.00);

-- Dumping structure for table supramart_db.suppliers
CREATE TABLE IF NOT EXISTS `suppliers` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.suppliers: ~14 rows (approximately)
INSERT INTO `suppliers` (`supplier_id`, `supplier_name`, `email`, `phone_number`) VALUES
	(1, 'Fresh Supplies Ltd', 'contact@freshsupplies.com', '0771234567');

-- Dumping structure for table supramart_db.suppliers_has_address
CREATE TABLE IF NOT EXISTS `suppliers_has_address` (
  `supplier_id` int NOT NULL,
  `city_id` int NOT NULL,
  `address_line_1` varchar(150) NOT NULL,
  `address_line_2` varchar(150) NOT NULL,
  `postal_code` varchar(8) NOT NULL,
  PRIMARY KEY (`supplier_id`,`city_id`),
  KEY `fk_suppliers_has_city_city1_idx` (`city_id`),
  KEY `fk_suppliers_has_city_suppliers1_idx` (`supplier_id`),
  CONSTRAINT `fk_suppliers_has_city_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `fk_suppliers_has_city_suppliers1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.suppliers_has_address: ~0 rows (approximately)
INSERT INTO `suppliers_has_address` (`supplier_id`, `city_id`, `address_line_1`, `address_line_2`, `postal_code`) VALUES
	(1, 1, 'No. 45 Supply Lane', 'Colombo', '10010');

-- Dumping structure for table supramart_db.year
CREATE TABLE IF NOT EXISTS `year` (
  `year_id` int NOT NULL AUTO_INCREMENT,
  `year` varchar(4) NOT NULL,
  PRIMARY KEY (`year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.year: ~28 rows (approximately)
INSERT INTO `year` (`year_id`, `year`) VALUES
	(1, '2024'),
	(2, '2025');

-- Dumping structure for table supramart_db.year_has_month
CREATE TABLE IF NOT EXISTS `year_has_month` (
  `year_id` int NOT NULL,
  `month_id` int NOT NULL,
  PRIMARY KEY (`year_id`,`month_id`),
  KEY `fk_year_has_month_month1_idx` (`month_id`),
  KEY `fk_year_has_month_year1_idx` (`year_id`),
  CONSTRAINT `fk_year_has_month_month1` FOREIGN KEY (`month_id`) REFERENCES `month` (`month_id`),
  CONSTRAINT `fk_year_has_month_year1` FOREIGN KEY (`year_id`) REFERENCES `year` (`year_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supramart_db.year_has_month: ~2 rows (approximately)
INSERT INTO `year_has_month` (`year_id`, `month_id`) VALUES
	(1, 1),
	(1, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
