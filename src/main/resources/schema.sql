DROP TABLE customers;

DROP TABLE customer_branches;

CREATE TABLE customers (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE branches;

DROP TABLE branch_customers;

CREATE TABLE branches (
    id   INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);