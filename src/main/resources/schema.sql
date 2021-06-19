DROP TABLE IF EXISTS branch_customers;
DROP TABLE IF EXISTS customer_branches;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS branches;


CREATE TABLE customers (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE branches (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

