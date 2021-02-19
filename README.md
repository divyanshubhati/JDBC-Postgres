# JDBC-Postgres
## Requirement
    1. Postgres 12 running on port:5432
## Note    
    username >> admin with password >> admin
    database >> mydatabase
### You can change the config in Database.java
#### Create Table command >> CREATE TABLE student ( id SERIAL PRIMARY KEY, name VARCHAR (255) NOT NULL, address VARCHAR(255), updated_on TIMESTAMPTZ );