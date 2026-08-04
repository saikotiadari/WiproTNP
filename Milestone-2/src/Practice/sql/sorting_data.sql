--1
SELECT last_name, salary
FROM employees
WHERE salary > 12000;

--2
SELECT last_name, department_id
FROM employees
WHERE employee_id = 100;

--3
SELECT last_name, salary
FROM employees
WHERE salary NOT BETWEEN 5000 AND 12000;

--4
SELECT last_name, job_id, hire_date
FROM employees
WHERE last_name IN ('King', 'reddy')
ORDER BY hire_date ASC;


--5
SELECT last_name, department_id
FROM employees
WHERE department_id IN (20, 50)
ORDER BY last_name ASC;

--6
SELECT last_name AS "Employee", 
       salary AS "Monthly Salary"
FROM employees
WHERE salary BETWEEN 5000 AND 12000
  AND department_id IN (20, 50);
  
  --7
  SELECT last_name, hire_date
FROM employees
WHERE hire_date LIKE '%94' 
   OR TO_CHAR(hire_date, 'YYYY') = '1994';
   
--8
SELECT last_name, job_id
FROM employees
WHERE job_id IS NULL;

--9
SELECT last_name, salary, commission_pct
FROM employees
WHERE phone_number IS NOT NULL
ORDER BY 2 DESC, 3 DESC;

--10
SELECT last_name, salary
FROM employees
WHERE salary > &Salary_value;

--11
SELECT employee_id, last_name, salary, department_id
FROM employees
WHERE manager_id = &manager_id
ORDER BY &sorted_by;

--12
SELECT last_name
FROM employees
WHERE last_name LIKE '__a%';

--13
SELECT last_name
FROM employees
WHERE LOWER(last_name) LIKE '%a%' 
  AND LOWER(last_name) LIKE '%e%';
  
--14
SELECT last_name, job_id, salary
FROM employees
WHERE job_id IN ('SA_REP', 'ST_CLERK')
  AND salary NOT IN (2500, 3500, 7000);