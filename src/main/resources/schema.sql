CREATE TABLE clients (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(75) NOT NULL,
  lastname VARCHAR(75) NOT NULL,
  docnumber VARCHAR(11) NOT NULL
);

CREATE TABLE invoices (
  id INT PRIMARY KEY AUTOINCREMENT,
  client_id INT,
  created_at DATETIME NOT NULL,
  total DOUBLE NOT NULL,
  FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE products (
 id INTEGER PRIMARY KEY,
 description VARCHAR(150) NOT NULL,
 code VARCHAR(50) NOT NULL,
 stock INT NOT NULL,
 price DOUBLE NOT NULL
);

CREATE TABLE invoice_details (
  invoice_detail_id INT PRIMARY KEY AUTOINCREMENT,
  invoice_id INT NOT NULL,
  product_id INT NOT NULL,
  amount INT NOT NULL,
  price DOUBLE NOT NULL,
  FOREIGN KEY (invoice_id) REFERENCES invoices(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);