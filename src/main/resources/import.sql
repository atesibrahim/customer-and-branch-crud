INSERT INTO customers (id, full_name) VALUES (1, 'Ibrahim Ates');
INSERT INTO customers (id, full_name) VALUES (2, 'Halil Ates');
INSERT INTO customers (id, full_name) VALUES (3, 'Ates Ates');

INSERT INTO branches (id, name) VALUES (10, 'Kadikoy');
INSERT INTO branches (id, name) VALUES (20, 'Besiktas');
INSERT INTO branches (id, name) VALUES (30, 'Maltepe');

INSERT INTO customer_branches (customer_id, branch_id) VALUES (1, 10);
INSERT INTO customer_branches (customer_id, branch_id) VALUES (1, 20);

INSERT INTO branch_customers (branch_id, customer_id) VALUES (10, 1);
INSERT INTO branch_customers (branch_id, customer_id) VALUES (10, 2);