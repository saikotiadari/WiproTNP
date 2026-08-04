-- Create DEPARTMENTS Table
CREATE TABLE hr.departments (
    department_id   NUMBER(4) PRIMARY KEY,
    department_name VARCHAR2(30) NOT NULL,
    manager_id      NUMBER(6),
    location_id     NUMBER(4)
);

-- Insert Sample Data
INSERT INTO hr.departments VALUES (10, 'Administration', 200, 1700);
INSERT INTO hr.departments VALUES (20, 'Marketing', 201, 1800);
INSERT INTO hr.departments VALUES (30, 'Purchasing', 114, 1700);
INSERT INTO hr.departments VALUES (40, 'Human Resources', 203, 2400);
INSERT INTO hr.departments VALUES (50, 'Shipping', 121, 1500);
INSERT INTO hr.departments VALUES (60, 'IT', 103, 1400);
INSERT INTO hr.departments VALUES (90, 'Executive', 100, 1700);

COMMIT;



1.DESCRIBE departments;

SELECT * FROM departments;

2.SELECT employee_id, last_name, job_id, hire_date AS STARTDATE
FROM employees;

3.SELECT DISTINCT job_id
FROM employees;


4.SELECT employee_id AS "Emp #", 
       last_name AS "Employee", 
       job_id AS "Job", 
       hire_date AS "Hire Date"
FROM employees;