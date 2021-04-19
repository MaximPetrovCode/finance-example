CREATE DATABASE finance_db;
CREATE USER finance_user WITH ENCRYPTED PASSWORD 'finance_user';
GRANT ALL PRIVILEGES ON DATABASE finance_db TO finance_user;
