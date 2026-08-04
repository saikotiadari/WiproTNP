1.ALTER SESSION SET "_ORACLE_SCRIPT" = true;


2.-- Create the HR user
CREATE USER hr IDENTIFIED BY hr;

-- Grant necessary privileges
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO hr;


3.-- Switch to HR schema
ALTER SESSION SET CURRENT_SCHEMA = hr;

-- Create EMPLOYEES Table
CREATE TABLE hr.employees (
    employee_id   NUMBER(6) PRIMARY KEY,
    first_name    VARCHAR2(20),
    last_name     VARCHAR2(25) NOT NULL,
    email         VARCHAR2(25) NOT NULL,
    phone_number  VARCHAR2(20),
    hire_date     DATE NOT NULL,
    job_id        VARCHAR2(10) NOT NULL,
    salary        NUMBER(8, 2),
    department_id NUMBER(4)
);

-- Insert Sample Data
INSERT INTO hr.employees VALUES (100, 'Steven', 'King', 'SKING', '515.123.4567', SYSDATE, 'AD_PRES', 24000, 90);
INSERT INTO hr.employees VALUES (101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', SYSDATE, 'AD_VP', 17000, 90);
INSERT INTO hr.employees VALUES (102, 'Lex', 'De Haan', 'LDEHAAN', '515.123.4569', SYSDATE, 'AD_VP', 17000, 90);

COMMIT;


