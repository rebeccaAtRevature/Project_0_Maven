CREATE DATABASE bank;
CREATE TABLE customer_details(customer_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 10), 
                                customer_first_name VARCHAR(20), 
                                customer_last_name VARCHAR(20), 
                                customer_phone_number VARCHAR(20), 
                                customer_address VARCHAR(200), 
                                account_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 10), 
                                account_balance NUMERIC(10,2), 
                                customer_password VARCHAR(20), 
                                PRIMARY KEY(customer_id));

CREATE TABLE employee_details(employee_id INT PRIMARY KEY, 
                                employee_first_name VARCHAR(20), 
                                employee_last_name VARCHAR(20), 
                                employee_phone_number VARCHAR(20), 
                                employee_address VARCHAR(200), 
                                password VARCHAR(20))

CREATE TABLE transaction_history(transaction_id INT GENERATED ALWAYS AS IDENTITY, 
                                transaction_date_and_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
                                account_transfer_from INT, 
                                account_transfer_to INT, 
                                transfer_amount NUMERIC(10,2), 
                                PRIMARY KEY(transaction_id));

ALTER TABLE transaction_history ADD CONSTRAINT fk_transaction_from FOREIGN KEY (account_transfer_from) REFERENCES customer_details(customer_id);

ALTER TABLE transaction_history ADD CONSTRAINT fk_transaction_to FOREIGN KEY (account_transfer_to) REFERENCES customer_details(customer_id);
