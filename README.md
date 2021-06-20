
# Information for Customer and Branch Rest CRUD Application.

###### This app has been developed as Rest Service with Spring Boot

App will run on 8585 port.

When you are running to started this app on your pc;
schema.sql will create customers and branches
and their many to many tables which are customer_branches and branch_customers automatically.
Then scripts will executed in import.sql file to insert some dummy data to tables. 

Content of `schmea.sql` and `import.sql` as following;

#### schema.sql:

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


#### import.sql:

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

#### Important notes:
* MySQL must be installed on your pc and your must be create mydb schema name.

* MySQL must be running on 3306 port, if not please change port number in application.properties

## ALL CRUD REST SERVICES

##### CHECK HEALTH 
If you want to see service status is running or not,
**Customer Check Health URL:  localhost:8585/customer/health**
**Branch Check Health URL:  localhost:8585/branch/health**

##### LIST ALL CUSTOMER:

**URL: localhost:8585/customer/getcustomerlist**

RESPONSE:

when customer id exists in db response returns:
[
    {
        "id": 1,
        "fullName": "Ibrahim Ates"
    },
    {
        "id": 2,
        "fullName": "Halil Ates"
    },
    {
        "id": 3,
        "fullName": "Ates Ates"
    }
]

when no customer is in database then response returns empty list as:

[]

##### GET CUSTOMER by ID:
**URL: localhost:8585/customer/getcustomer/{id}**

RESPONSE:

when customer id exists in db response returns:
{
    "id": 1,
    "fullName": "Ibrahim Ates"
}

when you search customer id which is not found response returns as:

{
    "timestamp": "2021-06-19T14:36:01.230+00:00",
    "message": "Customer not found for this id :: 4",
    "details": "uri=/customer/getcustomer/4"
}

##### DELETE CUSTOMER by ID:

**URL: localhost:8585/customer/deletecustomer/{id}**

RESPONSE:

true

when you want to delete customer id which is not found then response return as:
{
    "timestamp": "2021-06-19T19:09:47.595+00:00",
    "message": "Transaction silently rolled back because it has been marked as rollback-only",
    "details": "uri=/customer/deletecustomer/8"
}

##### ADD NEW CUSTOMER:
You can request to following url with following sample body
 
**URL: localhost:8585/customer/addcustomer**
 
Body:
{
"fullName":"Ibrahim Ates"
}


##### UPDATE CUSTOMER:
You can updated customer fullname which is in body with id in url header 

**URL: localhost:8585/customer/updatecustomer/{id}**

Body:
{
"fullName":"Ates Ates"
}

Response:
{
"fullName":"Ates Ates"
}

##### GET CUSTOMER BRANCHES:
 
**URL: localhost:8585/customer/getcustomerbranches/{id}**

Response :
[
    {
        "id": 20,
        "name": "Besiktas"
    }
]

When customer not found Response returns as:
{
    "timestamp": "2021-06-19T19:22:59.389+00:00",
    "message": "Customer not found for this id :: 123",
    "details": "uri=/customer/getcustomerbranches/123"
}

When branch not found Response returns as:
{
    "timestamp": "2021-06-19T19:28:17.325+00:00",
    "message": "Branch not found for this id :: 40",
    "details": "uri=/customer/addcustomertobranch/1/40"
}


##### GET BRANCH CUSTOMERS

**URL: localhost:8585/branch/getbranchcustomers/{id}**

Response:
[
    {
        "id": 3,
        "fullName": "Ates Ates"
    }
]

When branch not found Response returns as:
{
    "timestamp": "2021-06-19T19:28:17.325+00:00",
    "message": "Branch not found for this id :: 40",
    "details": "uri=/customer/addcustomertobranch/1/40"
}

When customer not found Response returns as:
{
    "timestamp": "2021-06-19T19:30:11.497+00:00",
    "message": "Customer not found for this id :: 10",
    "details": "uri=/customer/addcustomertobranch/10/20"
}

##### ADD CUSTOMER to BRANCH:

**URL: localhost:8585/branch/addcustomertobranch/{customerid}/{branchid}**

After success response returns added branch to customer as;

{
    "id": 30,
    "name": "Maltepe"
}

If branch which not exists to be added then response returns as;
{
    "timestamp": "2021-06-19T19:39:43.153+00:00",
    "message": "Branch not found for this id :: 300",
    "details": "uri=/customer/addcustomertobranch/3/300"
}

Or If customer id not exists then response returns as;

{
    "timestamp": "2021-06-19T19:41:08.669+00:00",
    "message": "Customer not found for this id :: 30",
    "details": "uri=/customer/addcustomertobranch/30/30"
}

##### ADD BRANCH to CUSTOMER:

**URL: localhost:8585/customer/addbranchtocustomer/{branchid}/{customerid}**   

After success response returns added customer to branch as;

{
    "id": 3,
    "fullName": "Ates Ates"
}

If branch which not exists then response returns as;
{
    "timestamp": "2021-06-19T19:39:43.153+00:00",
    "message": "Branch not found for this id :: 300",
    "details": "uri=/customer/addcustomertobranch/3/300"
}

Or If customer id not exists to be added branch then response returns as;

{
    "timestamp": "2021-06-19T19:41:08.669+00:00",
    "message": "Customer not found for this id :: 30",
    "details": "uri=/customer/addcustomertobranch/30/30"
}

##### DELETE CUSTOMER from BRANCH:

**URL: localhost:8585/branch/deletecustomerfrombranch/{customerid}/{branchid}**


Before request customer's branch list:
 [
     {
         "id": 20,
         "name": "Besiktas"
     },
     {
         "id": 10,
         "name": "Kadikoy"
     }
 ]

After request [localhost:8585/customer/deletebranchfromcustomer/20/1](localhost:8585/customer/deletebranchfromcustomer/20/1),
response returned new customer's branch list after deleted branch as: 
[
    {
        "id": 10,
        "name": "Kadikoy"
    }
]
 
 ##### DELETE BRANCH from CUSTOMER:
 **URL: llocalhost:8585/customer/deletebranchfromcustomer/{branchid}/{customerid}**
 
 Before request branch's customer list:
 
 [
     {
         "id": 1,
         "fullName": "Ibrahim Ates"
     },
     {
         "id": 2,
         "fullName": "Halil Ates"
     }
 ]
 
 After request [localhost:8585/customer/deletebranchfromcustomer/20/1](localhost:8585/branch/deletecustomerfrombranch/2/10),
 response returned new branch's customer list after deleted customer as: 
 [
     {
         "id": 1,
         "fullName": "Ibrahim Ates"
     }
 ]