-- SupraMart Database Population Script
-- This script populates the database with realistic sample data

-- Clear existing data (if any)
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- POPULATE ADMIN DATABASE (mydb)
-- =============================================

-- Populate roles
INSERT INTO `mydb`.`role` (`role_id`, `role`) VALUES
(1, 'Cashier'),
(2, 'Branch Manager'),
(3, 'Auditor'),
(4, 'Inventory Manager'),
(5, 'Admin');

-- Populate geographic hierarchy
INSERT INTO `mydb`.`province` (`province_id`, `province`) VALUES
(1, 'Western Province'),
(2, 'Central Province'),
(3, 'Northern Province'),
(4, 'Eastern Province');

INSERT INTO `mydb`.`district` (`district_id`, `district_name`, `province_province_id`) VALUES
(1, 'Colombo', 1),
(2, 'Gampaha', 1),
(3, 'Kalutara', 1),
(4, 'Kandy', 2),
(5, 'Matale', 2),
(6, 'Jaffna', 3),
(7, 'Batticaloa', 4);

INSERT INTO `mydb`.`city` (`city_id`, `city`, `district_district_id`) VALUES
(1, 'Colombo', 1),
(2, 'Dehiwala', 1),
(3, 'Moratuwa', 1),
(4, 'Gampaha', 2),
(5, 'Negombo', 2),
(6, 'Kalutara', 3),
(7, 'Kandy', 4),
(8, 'Matale', 5),
(9, 'Jaffna', 6),
(10, 'Batticaloa', 7);

-- Populate branches
INSERT INTO `mydb`.`branches` (`branch_id`, `branch_name`, `City_city_id`) VALUES
(1, 'SupraMart Colombo Central', 1),
(2, 'SupraMart Gampaha', 4),
(3, 'SupraMart Kandy', 7),
(4, 'SupraMart Jaffna', 9),
(5, 'SupraMart Negombo', 5);

-- Populate employee images
INSERT INTO `mydb`.`employee_image` (`image_id`, `image_path`) VALUES
(1, '/profileImages/defaultUser.png'),
(2, '/profileImages/emp001.jpg'),
(3, '/profileImages/emp002.jpg'),
(4, '/profileImages/emp003.jpg'),
(5, '/profileImages/emp004.jpg'),
(6, '/profileImages/emp005.jpg'),
(7, '/profileImages/emp006.jpg'),
(8, '/profileImages/emp007.jpg'),
(9, '/profileImages/emp008.jpg'),
(10, '/profileImages/emp009.jpg');

-- Populate admin users
INSERT INTO `mydb`.`admin` (`admin_id`, `fname`, `lname`, `email`, `password`, `role_role_id`) VALUES
('ADM001', 'John', 'Smith', 'john.smith@supramart.com', 'admin123', 5),
('ADM002', 'Sarah', 'Johnson', 'sarah.johnson@supramart.com', 'admin456', 5);

-- Link admins to branches
INSERT INTO `mydb`.`admin_has_branches` (`admin_admin_id`, `branches_branch_id`) VALUES
('ADM001', 1),
('ADM001', 2),
('ADM001', 3),
('ADM002', 4),
('ADM002', 5);

-- Populate time dimensions
INSERT INTO `mydb`.`year` (`year_id`, `year`) VALUES
(1, '2024'),
(2, '2025');

INSERT INTO `mydb`.`month` (`month_id`, `month`) VALUES
(1, 'January'), (2, 'February'), (3, 'March'), (4, 'April'),
(5, 'May'), (6, 'June'), (7, 'July'), (8, 'August'),
(9, 'September'), (10, 'October'), (11, 'November'), (12, 'December');

-- Link years and months
INSERT INTO `mydb`.`year_has_month` (`year_id`, `month_id`) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6),
(2, 7), (2, 8), (2, 9), (2, 10), (2, 11), (2, 12);

-- Populate income data
INSERT INTO `mydb`.`income` (`income_id`, `year_id`, `month_id`, `branches_branch_id`, `monthly_income`) VALUES
(1, 1, 1, 1, 2500000.00),
(2, 1, 2, 1, 2800000.00),
(3, 1, 3, 1, 3200000.00),
(4, 1, 1, 2, 1800000.00),
(5, 1, 2, 2, 2000000.00),
(6, 1, 3, 2, 2200000.00),
(7, 1, 1, 3, 1500000.00),
(8, 1, 2, 3, 1700000.00),
(9, 1, 3, 3, 1900000.00);

-- Populate bonus types
INSERT INTO `mydb`.`bonus_type` (`bonus_type_id`, `type_name`, `description`) VALUES
(1, 'Performance Bonus', 'Bonus for exceeding sales targets'),
(2, 'Attendance Bonus', 'Bonus for perfect attendance'),
(3, 'Holiday Bonus', 'Bonus for working on holidays'),
(4, 'Long Service Bonus', 'Bonus for years of service');

-- Populate bonuses
INSERT INTO `mydb`.`bonus` (`bonus_id`, `type_id`, `bonus_name`, `amount`, `role_role_id`) VALUES
(1, 1, 'Sales Performance Bonus', 5000.00, 1),
(2, 1, 'Manager Performance Bonus', 10000.00, 2),
(3, 2, 'Perfect Attendance', 2000.00, 1),
(4, 3, 'Holiday Work Bonus', 3000.00, 1),
(5, 4, '5 Year Service Bonus', 15000.00, 2);

-- Populate petty cash
INSERT INTO `mydb`.`petty_cash` (`petty_cash_id`, `petty_cash_amount`, `date`, `year_has_month_year_id`, `year_has_month_month_id`, `branches_branch_id`) VALUES
(1, 50000.00, '2024-01-15', 1, 1, 1),
(2, 45000.00, '2024-02-15', 1, 2, 1),
(3, 55000.00, '2024-03-15', 1, 3, 1),
(4, 35000.00, '2024-01-15', 1, 1, 2),
(5, 40000.00, '2024-02-15', 1, 2, 2);

-- =============================================
-- POPULATE SUPRAmart DATABASE
-- =============================================

-- Populate product categories
INSERT INTO `supramart`.`product_categories` (`category_id`, `category_name`) VALUES
(1, 'Dairy Products'),
(2, 'Beverages'),
(3, 'Snacks'),
(4, 'Fruits & Vegetables'),
(5, 'Meat & Poultry'),
(6, 'Bakery'),
(7, 'Household'),
(8, 'Personal Care'),
(9, 'Frozen Foods'),
(10, 'Canned Goods');

-- Populate products
INSERT INTO `supramart`.`products` (`product_id`, `name`, `category_id`, `price`, `cost`, `stock_quantity`, `reorder_level`, `added_on`) VALUES
(1, 'Fresh Milk 1L', 1, 180.00, 120.00, 50, 20, '2024-01-01 08:00:00'),
(2, 'Yogurt Natural 500g', 1, 120.00, 80.00, 30, 15, '2024-01-01 08:00:00'),
(3, 'Cheese Cheddar 250g', 1, 350.00, 250.00, 25, 10, '2024-01-01 08:00:00'),
(4, 'Coca Cola 2L', 2, 280.00, 180.00, 40, 20, '2024-01-01 08:00:00'),
(5, 'Orange Juice 1L', 2, 220.00, 150.00, 35, 15, '2024-01-01 08:00:00'),
(6, 'Potato Chips 100g', 3, 95.00, 60.00, 60, 25, '2024-01-01 08:00:00'),
(7, 'Chocolate Bar 100g', 3, 85.00, 55.00, 45, 20, '2024-01-01 08:00:00'),
(8, 'Apples 1kg', 4, 250.00, 180.00, 30, 15, '2024-01-01 08:00:00'),
(9, 'Bananas 1kg', 4, 120.00, 80.00, 40, 20, '2024-01-01 08:00:00'),
(10, 'Chicken Breast 1kg', 5, 850.00, 600.00, 20, 10, '2024-01-01 08:00:00'),
(11, 'Bread White 500g', 6, 95.00, 65.00, 25, 12, '2024-01-01 08:00:00'),
(12, 'Toilet Paper 4pk', 7, 450.00, 300.00, 35, 15, '2024-01-01 08:00:00'),
(13, 'Toothpaste 100g', 8, 180.00, 120.00, 30, 15, '2024-01-01 08:00:00'),
(14, 'Frozen Pizza', 9, 650.00, 450.00, 15, 8, '2024-01-01 08:00:00'),
(15, 'Canned Beans 400g', 10, 120.00, 80.00, 40, 20, '2024-01-01 08:00:00');

-- Populate suppliers
INSERT INTO `supramart`.`suppliers` (`supplier_id`, `supplier_name`, `contact_person`, `email`, `phone_number`) VALUES
(1, 'Lanka Dairy Products', 'Kamal Perera', 'kamal@lankadairy.com', '+94-11-2345678'),
(2, 'Ceylon Beverages Ltd', 'Nimal Silva', 'nimal@ceylonbeverages.com', '+94-11-3456789'),
(3, 'Fresh Fruits Co', 'Sunil Fernando', 'sunil@freshfruits.com', '+94-11-4567890'),
(4, 'Quality Meat Suppliers', 'Rajith Kumar', 'rajith@qualitymeat.com', '+94-11-5678901'),
(5, 'Bakery Supplies Ltd', 'Priya Wijesinghe', 'priya@bakerysupplies.com', '+94-11-6789012'),
(6, 'Household Essentials', 'Dinesh Bandara', 'dinesh@household.com', '+94-11-7890123'),
(7, 'Personal Care Products', 'Anusha Perera', 'anusha@personalcare.com', '+94-11-8901234'),
(8, 'Frozen Foods Co', 'Manoj Silva', 'manoj@frozenfoods.com', '+94-11-9012345');

-- Populate customers
INSERT INTO `supramart`.`customers` (`customer_id`, `first_name`, `last_name`, `email`, `phone_number`, `registered_at`, `branches_branch_id`) VALUES
(1, 'Amal', 'Fernando', 'amal.fernando@email.com', '+94-77-1234567', '2024-01-01 10:00:00', 1),
(2, 'Nimali', 'Silva', 'nimali.silva@email.com', '+94-77-2345678', '2024-01-02 11:00:00', 1),
(3, 'Sunil', 'Perera', 'sunil.perera@email.com', '+94-77-3456789', '2024-01-03 12:00:00', 2),
(4, 'Priya', 'Wijesinghe', 'priya.wijesinghe@email.com', '+94-77-4567890', '2024-01-04 13:00:00', 2),
(5, 'Rajith', 'Kumar', 'rajith.kumar@email.com', '+94-77-5678901', '2024-01-05 14:00:00', 3),
(6, 'Anusha', 'Bandara', 'anusha.bandara@email.com', '+94-77-6789012', '2024-01-06 15:00:00', 3),
(7, 'Dinesh', 'Fernando', 'dinesh.fernando@email.com', '+94-77-7890123', '2024-01-07 16:00:00', 4),
(8, 'Manoj', 'Silva', 'manoj.silva@email.com', '+94-77-8901234', '2024-01-08 17:00:00', 4),
(9, 'Kamal', 'Perera', 'kamal.perera@email.com', '+94-77-9012345', '2024-01-09 18:00:00', 5),
(10, 'Nimali', 'Wijesinghe', 'nimali.wijesinghe@email.com', '+94-77-0123456', '2024-01-10 19:00:00', 5);

-- Populate employees
INSERT INTO `supramart`.`employees` (`employee_id`, `first_name`, `last_name`, `email`, `mobile_number_1`, `mobile_number_2`, `hire_date`, `role_id`, `branch_id`, `image_id`, `base_salary`) VALUES
('EMP001', 'John', 'Smith', 'john.smith@supramart.com', '+94-77-1111111', '+94-76-1111111', '2023-01-15 09:00:00', 5, 1, 1, 75000.00),
('EMP002', 'Sarah', 'Johnson', 'sarah.johnson@supramart.com', '+94-77-2222222', '+94-76-2222222', '2023-02-01 09:00:00', 2, 1, 2, 65000.00),
('EMP003', 'Michael', 'Brown', 'michael.brown@supramart.com', '+94-77-3333333', '+94-76-3333333', '2023-02-15 09:00:00', 1, 1, 3, 45000.00),
('EMP004', 'Emily', 'Davis', 'emily.davis@supramart.com', '+94-77-4444444', '+94-76-4444444', '2023-03-01 09:00:00', 4, 1, 4, 55000.00),
('EMP005', 'David', 'Wilson', 'david.wilson@supramart.com', '+94-77-5555555', '+94-76-5555555', '2023-03-15 09:00:00', 3, 1, 5, 60000.00),
('EMP006', 'Lisa', 'Anderson', 'lisa.anderson@supramart.com', '+94-77-6666666', '+94-76-6666666', '2023-04-01 09:00:00', 2, 2, 6, 65000.00),
('EMP007', 'Robert', 'Taylor', 'robert.taylor@supramart.com', '+94-77-7777777', '+94-76-7777777', '2023-04-15 09:00:00', 1, 2, 7, 45000.00),
('EMP008', 'Jennifer', 'Martinez', 'jennifer.martinez@supramart.com', '+94-77-8888888', '+94-76-8888888', '2023-05-01 09:00:00', 4, 2, 8, 55000.00),
('EMP009', 'William', 'Garcia', 'william.garcia@supramart.com', '+94-77-9999999', '+94-76-9999999', '2023-05-15 09:00:00', 2, 3, 9, 65000.00),
('EMP010', 'Maria', 'Rodriguez', 'maria.rodriguez@supramart.com', '+94-77-0000000', '+94-76-0000000', '2023-06-01 09:00:00', 1, 3, 10, 45000.00);

-- Populate employee addresses
INSERT INTO `supramart`.`employees_has_Address` (`employee_id`, `City_city_id`, `address_line_1`, `address_line_2`, `postal_code`) VALUES
('EMP001', 1, '123 Main Street', 'Apartment 4A', '10000'),
('EMP002', 1, '456 Oak Avenue', 'Unit 2B', '10001'),
('EMP003', 1, '789 Pine Road', 'Suite 3C', '10002'),
('EMP004', 1, '321 Elm Street', 'Floor 1', '10003'),
('EMP005', 1, '654 Maple Drive', 'Apartment 5D', '10004'),
('EMP006', 4, '987 Cedar Lane', 'Unit 6E', '11000'),
('EMP007', 4, '147 Birch Way', 'Suite 7F', '11001'),
('EMP008', 4, '258 Spruce Court', 'Floor 2', '11002'),
('EMP009', 7, '369 Willow Place', 'Apartment 8G', '20000'),
('EMP010', 7, '741 Aspen Circle', 'Unit 9H', '20001');

-- Populate supplier addresses
INSERT INTO `supramart`.`suppliers_has_address` (`supplier_id`, `city_id`, `address_line_1`, `address_line_2`, `postal_code`) VALUES
(1, 1, '45 Industrial Zone', 'Building A', '10010'),
(2, 1, '78 Business Park', 'Floor 3', '10011'),
(3, 4, '123 Farm Road', 'Warehouse B', '11010'),
(4, 1, '456 Meat Market', 'Unit 5', '10012'),
(5, 4, '789 Bakery Street', 'Factory C', '11011'),
(6, 1, '321 Supply Chain', 'Building D', '10013'),
(7, 1, '654 Care Products', 'Floor 2', '10014'),
(8, 1, '987 Frozen Zone', 'Cold Storage', '10015');

-- Populate inventory transactions
INSERT INTO `supramart`.`inventory_transactions` (`transaction_id`, `product_id`, `quantity_change`, `reason`, `date`, `branches_branch_id`) VALUES
(1, 1, 100, 'Initial stock', '2024-01-01 08:00:00', 1),
(2, 2, 50, 'Initial stock', '2024-01-01 08:00:00', 1),
(3, 3, 30, 'Initial stock', '2024-01-01 08:00:00', 1),
(4, 4, 80, 'Initial stock', '2024-01-01 08:00:00', 1),
(5, 5, 60, 'Initial stock', '2024-01-01 08:00:00', 1),
(6, 1, -10, 'Sales', '2024-01-15 14:30:00', 1),
(7, 2, -5, 'Sales', '2024-01-15 15:45:00', 1),
(8, 4, -8, 'Sales', '2024-01-15 16:20:00', 1),
(9, 1, 50, 'Restock', '2024-01-20 09:00:00', 1),
(10, 2, 30, 'Restock', '2024-01-20 09:00:00', 1);

-- Populate sales
INSERT INTO `supramart`.`sales` (`sale_id`, `total_amount`, `payment_method`, `sale_date`, `branches_branch_id`, `employee_id`, `customers_customer_id`) VALUES
(1, 850.00, 'Cash', '2024-01-15 14:30:00', 1, 'EMP003', 1),
(2, 1200.00, 'Credit Card', '2024-01-15 15:45:00', 1, 'EMP003', 2),
(3, 650.00, 'Cash', '2024-01-15 16:20:00', 1, 'EMP003', 3),
(4, 1800.00, 'Debit Card', '2024-01-16 10:15:00', 2, 'EMP007', 4),
(5, 950.00, 'Cash', '2024-01-16 11:30:00', 2, 'EMP007', 5),
(6, 1400.00, 'Credit Card', '2024-01-16 12:45:00', 3, 'EMP010', 6),
(7, 750.00, 'Cash', '2024-01-16 13:20:00', 3, 'EMP010', 7),
(8, 1100.00, 'Debit Card', '2024-01-17 09:30:00', 1, 'EMP003', 8),
(9, 1600.00, 'Credit Card', '2024-01-17 10:45:00', 2, 'EMP007', 9),
(10, 900.00, 'Cash', '2024-01-17 11:15:00', 3, 'EMP010', 10);

-- Populate sale items
INSERT INTO `supramart`.`sale_items` (`sale_item_id`, `sale_id`, `product_id`, `quantity`, `price`) VALUES
(1, 1, 1, 2, 180.00),
(2, 1, 4, 1, 280.00),
(3, 1, 6, 2, 95.00),
(4, 2, 2, 3, 120.00),
(5, 2, 5, 2, 220.00),
(6, 2, 8, 1, 250.00),
(7, 2, 11, 1, 95.00),
(8, 3, 3, 1, 350.00),
(9, 3, 7, 2, 85.00),
(10, 3, 9, 1, 120.00),
(11, 4, 10, 1, 850.00),
(12, 4, 12, 1, 450.00),
(13, 4, 13, 1, 180.00),
(14, 4, 15, 2, 120.00),
(15, 5, 1, 1, 180.00),
(16, 5, 4, 2, 280.00),
(17, 5, 6, 1, 95.00),
(18, 5, 11, 1, 95.00),
(19, 6, 2, 2, 120.00),
(20, 6, 5, 1, 220.00),
(21, 6, 8, 2, 250.00),
(22, 6, 9, 1, 120.00),
(23, 6, 14, 1, 650.00),
(24, 7, 3, 1, 350.00),
(25, 7, 7, 2, 85.00),
(26, 7, 10, 1, 850.00),
(27, 8, 1, 2, 180.00),
(28, 8, 4, 1, 280.00),
(29, 8, 6, 2, 95.00),
(30, 8, 12, 1, 450.00),
(31, 9, 2, 3, 120.00),
(32, 9, 5, 2, 220.00),
(33, 9, 8, 1, 250.00),
(34, 9, 11, 1, 95.00),
(35, 9, 13, 1, 180.00),
(36, 9, 15, 2, 120.00),
(37, 10, 1, 1, 180.00),
(38, 10, 4, 1, 280.00),
(39, 10, 6, 1, 95.00),
(40, 10, 9, 1, 120.00),
(41, 10, 11, 1, 95.00),
(42, 10, 13, 1, 180.00);

-- Populate employee salary records
INSERT INTO `supramart`.`employees_has_salary` (`salary_id`, `employee_id`, `year_id`, `month_id`, `leave_taken`, `OT_hours`, `extra_leave`) VALUES
(1, 'EMP001', 1, 1, 2, '08:00:00', 0),
(2, 'EMP002', 1, 1, 1, '12:00:00', 0),
(3, 'EMP003', 1, 1, 0, '16:00:00', 0),
(4, 'EMP004', 1, 1, 1, '10:00:00', 0),
(5, 'EMP005', 1, 1, 0, '14:00:00', 0),
(6, 'EMP006', 1, 1, 2, '08:00:00', 0),
(7, 'EMP007', 1, 1, 1, '12:00:00', 0),
(8, 'EMP008', 1, 1, 0, '16:00:00', 0),
(9, 'EMP009', 1, 1, 1, '10:00:00', 0),
(10, 'EMP010', 1, 1, 0, '14:00:00', 0);

-- Populate salary bonuses
INSERT INTO `supramart`.`salary_has_bonuses` (`employees_has_salary_salary_id`, `bonus_bonus_id`) VALUES
(1, 2), -- Admin gets manager performance bonus
(2, 2), -- Branch manager gets manager performance bonus
(3, 1), -- Cashier gets sales performance bonus
(4, 1), -- Inventory manager gets performance bonus
(5, 2), -- Auditor gets manager performance bonus
(6, 2), -- Branch manager gets manager performance bonus
(7, 1), -- Cashier gets sales performance bonus
(8, 1), -- Inventory manager gets performance bonus
(9, 2), -- Branch manager gets manager performance bonus
(10, 1); -- Cashier gets sales performance bonus

-- Populate branch-product relationships
INSERT INTO `mydb`.`branches_has_products` (`transaction_id`, `branches_branch_id`, `products_product_id`, `suppliers_supplier_id`, `quantity_change`, `reasons`, `added_date`) VALUES
(1, 1, 1, 1, 100, 'Initial stock', 20240101),
(2, 1, 2, 1, 50, 'Initial stock', 20240101),
(3, 1, 3, 1, 30, 'Initial stock', 20240101),
(4, 1, 4, 2, 80, 'Initial stock', 20240101),
(5, 1, 5, 2, 60, 'Initial stock', 20240101),
(6, 2, 1, 1, 80, 'Initial stock', 20240101),
(7, 2, 2, 1, 40, 'Initial stock', 20240101),
(8, 2, 4, 2, 60, 'Initial stock', 20240101),
(9, 2, 5, 2, 50, 'Initial stock', 20240101),
(10, 3, 1, 1, 70, 'Initial stock', 20240101),
(11, 3, 2, 1, 35, 'Initial stock', 20240101),
(12, 3, 4, 2, 50, 'Initial stock', 20240101),
(13, 3, 5, 2, 40, 'Initial stock', 20240101);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Display summary
SELECT 'Database population completed successfully!' AS Status;
SELECT COUNT(*) AS 'Total Products' FROM `supramart`.`products`;
SELECT COUNT(*) AS 'Total Employees' FROM `supramart`.`employees`;
SELECT COUNT(*) AS 'Total Customers' FROM `supramart`.`customers`;
SELECT COUNT(*) AS 'Total Sales' FROM `supramart`.`sales`;
SELECT COUNT(*) AS 'Total Branches' FROM `mydb`.`branches`; 