--1
CREATE TABLE dept (
    dept_id   NUMBER(7) PRIMARY KEY,
    dept_name VARCHAR2(20)
);

--2
INSERT INTO dept VALUES (10, 'Accounts');

INSERT INTO dept VALUES (20, 'TT');

INSERT INTO dept VALUES (30, 'Accounts');

COMMIT;

--3
CREATE TABLE emp (
    id         NUMBER(7) PRIMARY KEY,
    last_name  VARCHAR2(25) NOT NULL,
    first_name VARCHAR2(25),
    dept_id    NUMBER(7),
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id) REFERENCES dept(dept_id)
);

INSERT INTO emp VALUES (101, 'Sam', 'Sundar', 10);

INSERT INTO emp VALUES (102, 'Ram', 'Krishna', 20); 

COMMIT;