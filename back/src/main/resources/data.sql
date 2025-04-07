INSERT INTO product (name, description, price, quantity, category_path, available) VALUES
  ('Laptop', 'A powerful gaming laptop', 1500.00, 10, 'Electronics', 'Yes'),
  ('Smartphone', 'A high-end smartphone', 999.99, 1, 'Electronics', 'Yes'),
  ('Headphones', 'Noise-cancelling headphones', 299.99, 5, 'Electronics', 'Yes'),
  ('Blender', 'High-speed blender for smoothies', 89.90, 0, 'Home Appliance', 'No'),
  ('Refrigerator', 'Double-door fridge with freezer', 1200.00, 2, 'Home Appliance', 'Yes'),
  ('TV 55"', '4K Ultra HD Smart TV', 1799.00, 4, 'Electronics', 'Yes'),
  ('Washing Machine', 'Front load washing machine', 1100.00, 0, 'Home Appliance', 'No'),
  ('Desk Chair', 'Ergonomic chair with lumbar support', 249.50, 6, 'Furniture', 'Yes'),
  ('Microwave', '900W microwave oven', 349.99, 3, 'Home Appliance', 'Yes'),
  ('Tablet', '10" tablet with stylus support', 499.00, 8, 'Electronics', 'Yes'),
  ('Coffee Maker', 'Espresso coffee machine', 199.90, 1, 'Home Appliance', 'Yes'),
  ('Vacuum Cleaner', 'Bagless vacuum cleaner', 399.99, 0, 'Home Appliance', 'No'),
  ('Monitor 27"', '144Hz 27" gaming monitor', 369.90, 7, 'Electronics', 'Yes'),
  ('Keyboard', 'Mechanical RGB keyboard', 129.90, 9, 'Electronics', 'Yes'),
  ('Mouse', 'Wireless ergonomic mouse', 89.99, 12, 'Electronics', 'Yes'),
  ('Air Fryer', '5L air fryer with digital controls', 299.00, 2, 'Home Appliance', 'Yes'),
  ('Bookshelf', '5-tier wooden bookshelf', 159.00, 0, 'Furniture', 'No'),
  ('Smartwatch', 'Fitness tracking smartwatch', 349.90, 5, 'Electronics', 'Yes'),
  ('Gaming Console', 'Latest-gen gaming console', 2499.00, 1, 'Electronics', 'Yes'),
  ('Electric Kettle', 'Stainless steel electric kettle', 79.00, 3, 'Home Appliance', 'Yes');


INSERT INTO users (id, username, password) VALUES (1, 'admin', ''); -- admin

INSERT INTO user_roles (user_id, role) VALUES (1, 'ROLE_ADMIN');
