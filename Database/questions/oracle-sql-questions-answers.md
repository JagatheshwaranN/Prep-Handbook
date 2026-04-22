# Oracle SQL - Questions & Answers

---

## Quick Reference — SQL Command Categories

| Category | Full Name | Commands |
|----------|-----------|----------|
| **DDL** | Data Definition Language | `CREATE`, `ALTER`, `DROP`, `TRUNCATE`, `COMMENT`, `RENAME`, `GRANT` |
| **DML** | Data Manipulation Language | `INSERT`, `UPDATE`, `DELETE`, `MERGE` |
| **TCL** | Transaction Control Language | `COMMIT`, `ROLLBACK`, `SAVEPOINT`, `SET TRANSACTION`, `AUTOCOMMIT` |

---

## 1. What is DML, and what are its types in Oracle?

DML stands for **Data Manipulation Language**. It is used to manipulate data in the database. In Oracle, DML includes commands like `INSERT`, `UPDATE`, `DELETE`, and `MERGE`.

---

## 2. Explain the difference between DDL and DML in Oracle.

| Feature | DDL (Data Definition Language) | DML (Data Manipulation Language) |
|---------|-------------------------------|----------------------------------|
| **Purpose** | Defines and manages the **structure** of database objects. | Manipulates **data** stored in the database. |
| **Examples** | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` | `INSERT`, `UPDATE`, `DELETE`, `MERGE` |
| **Rollback** | Cannot be rolled back (auto-committed). | Can be rolled back using `ROLLBACK`. |

---

## 3. What is TCL, and what are its types in Oracle?

TCL stands for **Transaction Control Language**. It deals with transactions within a database. In Oracle, TCL commands include:

| Command | Purpose |
|---------|---------|
| `COMMIT` | Permanently saves changes made in the current transaction. |
| `ROLLBACK` | Reverts changes to the last commit point. |
| `SAVEPOINT` | Defines a point within a transaction for partial rollback. |
| `SET TRANSACTION` | Specifies properties for a transaction (isolation level, access mode). |
| `AUTOCOMMIT` | Controls whether each statement is automatically committed. |

---

## 4. Differentiate between DELETE and TRUNCATE in Oracle.

| Feature | DELETE | TRUNCATE |
|---------|--------|----------|
| **Type** | DML | DDL |
| **Removes** | Specific rows based on a condition. | All rows from the table. |
| **Rollback** | Can be rolled back using `ROLLBACK`. | Cannot be rolled back. |
| **Storage** | Does not release storage space. | Releases storage space. |
| **WHERE clause** | Supported. | Not supported. |

---

## 5. Explain the COMMIT and ROLLBACK statements in Oracle.

**COMMIT** — A TCL command that **permanently saves** all changes made during the current transaction. Once executed, changes are irreversible.

**ROLLBACK** — A TCL command that **reverts all changes** made during the current transaction back to the last `COMMIT` point, or to the beginning of the transaction if no `COMMIT` has been issued.

---

## 6. What is the purpose of the SAVEPOINT statement in Oracle?

`SAVEPOINT` is a TCL command used to **define a point within a transaction** to which you can later roll back. It allows you to **partially roll back** a transaction instead of rolling back the entire transaction.

```sql
SAVEPOINT sp1;
-- Some operations
ROLLBACK TO sp1;  -- Rolls back only to the savepoint
```

---

## 7. Explain the CASCADE CONSTRAINTS option in Oracle when dropping a table.

`CASCADE CONSTRAINTS` is an option used with the `DROP TABLE` statement. When specified, it **automatically drops all referential integrity constraints** (foreign key constraints) that refer to the primary or unique key columns of the table being dropped.

```sql
DROP TABLE employees CASCADE CONSTRAINTS;
```

---

## 8. What is SET TRANSACTION in TCL?

`SET TRANSACTION` is used to **specify properties for a transaction**, such as:
- Isolation level
- Access mode
- Transaction name

It allows you to control how transactions are processed by the database.

---

## 9. What is AUTOCOMMIT in TCL?

`AUTOCOMMIT` is a mode that can be enabled or disabled:

| Mode | Behavior |
|------|----------|
| **Enabled** | Each SQL statement is treated as a separate transaction and automatically committed after execution. |
| **Disabled** | You must explicitly issue `COMMIT` or `ROLLBACK` to end the transaction. |

---

## 10. Explain the difference between UPDATE and MERGE statements.

| Feature | UPDATE | MERGE |
|---------|--------|-------|
| **Operation** | Edits specific rows based on a `WHERE` clause. | Can perform `INSERT`, `UPDATE`, or `DELETE` based on a matched condition. |
| **Use Case** | Simple row modifications. | Complex operations involving multiple actions. |
| **Conciseness** | Verbose for complex multi-action operations. | More concise for complex operations. |

---

## 11. Write a query to create a table named Customers.

Create a table with columns for `customer_id` (number), `customer_name` (varchar2), and `city` (varchar2).

```sql
CREATE TABLE Customers (
  customer_id   NUMBER PRIMARY KEY,
  customer_name VARCHAR2(50) NOT NULL,
  city          VARCHAR2(30)
);
```

---

## 12. Describe the concept of constraints in Oracle and their types.

Constraints **enforce data integrity** within tables. Common types include:

| Constraint | Description |
|------------|-------------|
| `PRIMARY KEY` | Ensures unique, non-null values in a column or set of columns. |
| `FOREIGN KEY` | Creates a link between two tables, referencing a `PRIMARY KEY` in another table. |
| `NOT NULL` | Prevents null values in a column. |
| `UNIQUE` | Ensures all values in a column are distinct. |
| `CHECK` | Validates that values in a column meet a specified condition. |

---

## 13. Explain the concept of ACID properties in transactions.

| Property | Description |
|----------|-------------|
| **Atomicity** | Transactions are indivisible units of work — either all operations succeed or all fail. |
| **Consistency** | Transactions maintain data integrity by following defined rules. |
| **Isolation** | Transactions are isolated from each other, preventing interference. |
| **Durability** | Once committed, changes made by a transaction are permanent. |

---

## 14. What is a subquery?

A subquery is a **query nested within another query**. It is enclosed within parentheses and can be used in various parts of a SQL statement, such as the `WHERE`, `FROM`, `SELECT`, or `HAVING` clause.

---

## 15. What are the types of subqueries in Oracle SQL?

| Type | Description |
|------|-------------|
| **Single-row subquery** | Returns only one row and one column. |
| **Multi-row subquery** | Returns multiple rows but only one column. |

---

## 16. What is the difference between a correlated and a non-correlated subquery?

| Type | Description |
|------|-------------|
| **Non-correlated subquery** | Can be executed independently of the outer query. Does not reference columns from the outer query. |
| **Correlated subquery** | Depends on the outer query and references columns from it. Re-evaluated for each row of the outer query. |

---

## 17. How can you use a subquery in the WHERE clause?

You can use a subquery in the `WHERE` clause to filter rows based on the result of the subquery.

```sql
SELECT column1 FROM table1
WHERE column2 = (SELECT column3 FROM table2 WHERE condition);
```

---

## 18. What is the result of a subquery that returns NULL values?

If a subquery returns `NULL` values, it is treated as if it returned an **empty set**. For example, if a subquery in the `WHERE` clause matches no rows, the outer query will also return no rows.

---

## 19. When would you use a subquery instead of a join?

- Use **subqueries** when the inner result set is dynamic, depends on the database data, or when the logic is more straightforward to express as a subquery.
- Use **joins** when combining tables based on common columns for performance efficiency.

---

## 20. What are the EXISTS and IN operators, and how are they used with subqueries?

**EXISTS** — Tests for the existence of rows returned by a subquery. Returns `TRUE` if at least one row is found.

```sql
SELECT column1 FROM table1
WHERE EXISTS (SELECT * FROM table2 WHERE condition);
```

**IN** — Filters rows based on whether their values are present in the subquery result set.

```sql
SELECT column1 FROM table1
WHERE column1 IN (SELECT column1 FROM table2 WHERE condition);
```

---

## 21. Write a query to find all employees who earn more than the average salary.

```sql
SELECT * FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
```

---

## 22. Find the name of the department with the highest number of employees.

```sql
SELECT department_name FROM departments
WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM departments);
```

---

## 23. Identify customers who haven't placed any orders in the last 6 months.

```sql
SELECT * FROM customers c
WHERE NOT EXISTS (
  SELECT 1 FROM orders o
  WHERE o.customer_id = c.customer_id
  AND order_date > SYSDATE - 180
);
```

---

## 24. What is a join in Oracle SQL?

A join is a SQL operation used to **combine rows from two or more tables** based on a related column between them.

---

## 25. What are the different types of joins in Oracle SQL?

| # | Join Type |
|---|-----------|
| 1 | Inner Join (Simple Join) |
| 2 | Left Outer Join |
| 3 | Right Outer Join |
| 4 | Full Outer Join |
| 5 | Equi Join |
| 6 | Self Join |
| 7 | Cross Join (Cartesian Product) |
| 8 | Anti Join |
| 9 | Semi Join |

---

## 26. What is INNER JOIN?

Inner Join returns **only the rows where the join condition is met** in both tables.

**Syntax:**
```sql
SELECT columns FROM table1 INNER JOIN table2 ON table1.column = table2.column;
```

**Example:**
```sql
SELECT suppliers.supplier_id, suppliers.supplier_name, orders.order_number
FROM suppliers INNER JOIN orders
ON suppliers.supplier_id = orders.supplier_id;
```

**Sample Data:**

*Suppliers Table:*

| Supplier_Id | Supplier_Name | Supplier_Address |
|-------------|---------------|-----------------|
| 1 | Bata Shoes | Agra |
| 2 | Walkaro Slippers | Delhi |
| 3 | Bombay Dying Shocks | Mumbai |

*Orders Table:*

| Order_Num | Supplier_Id | City |
|-----------|-------------|------|
| 101 | 1 | Allahabad |
| 102 | 2 | Kanpur |

**Output:**

| supplier_id | supplier_name | order_number |
|-------------|---------------|--------------|
| 1 | Bata Shoes | 101 |
| 2 | Walkaro Slippers | 102 |

> Supplier ID 3 ("Bombay Dying Shocks") has no matching order, so it does not appear in the result.

---

## 27. What is LEFT OUTER JOIN?

Left Outer Join returns **all rows from the left table** and only matching rows from the right table. Non-matching rows from the right table are filled with `NULL`.

**Syntax:**
```sql
SELECT columns FROM table1 LEFT [OUTER] JOIN table2 ON table1.column = table2.column;
```

**Example:**
```sql
SELECT suppliers.supplier_id, suppliers.supplier_name, orders.order_number
FROM suppliers LEFT OUTER JOIN orders
ON suppliers.supplier_id = orders.supplier_id;
```

**Output:**

| supplier_id | supplier_name | order_number |
|-------------|---------------|--------------|
| 1 | Bata Shoes | 101 |
| 2 | Walkaro Slippers | 102 |
| 3 | Bombay Dying Shocks | NULL |

> Supplier ID 3 has no orders, so `order_number` shows `NULL`.

---

## 28. What is RIGHT OUTER JOIN?

Right Outer Join returns **all rows from the right table** and only matching rows from the left table. Non-matching rows from the left table are filled with `NULL`.

**Syntax:**
```sql
SELECT columns FROM table1 RIGHT [OUTER] JOIN table2 ON table1.column = table2.column;
```

**Example:**
```sql
SELECT orders.order_number, orders.city, suppliers.supplier_name
FROM suppliers RIGHT OUTER JOIN orders
ON orders.supplier_id = suppliers.supplier_id;
```

**Output:**

| order_num | city | supplier_name |
|-----------|------|---------------|
| 101 | Allahabad | Bata Shoes |
| 102 | Kanpur | Walkaro Slippers |

---

## 29. What is FULL OUTER JOIN?

Full Outer Join returns **all rows from both tables**, placing `NULL` where no match exists.

**Syntax:**
```sql
SELECT columns FROM table1 FULL [OUTER] JOIN table2 ON table1.column = table2.column;
```

**Example:**
```sql
SELECT suppliers.supplier_id, suppliers.supplier_name, orders.order_number
FROM suppliers FULL OUTER JOIN orders
ON suppliers.supplier_id = orders.supplier_id;
```

**Output:**

| supplier_id | supplier_name | order_number |
|-------------|---------------|--------------|
| 1 | Bata Shoes | 101 |
| 2 | Walkaro Slippers | 102 |
| 3 | Bombay Dying Shocks | NULL |
| NULL | NULL | NULL |

> The last row with `NULL` values represents an order with no corresponding supplier.

---

## 30. What is EQUI JOIN in Oracle?

An Equi Join returns matching column values from associated tables using a **comparison operator (`=`) in the `WHERE` clause** or via the `JOIN ... ON` syntax.

**Syntax:**
```sql
SELECT column_list FROM table1, table2 WHERE table1.column = table2.column;
-- OR
SELECT * FROM table1 JOIN table2 ON (join_condition);
```

**Example:**
```sql
SELECT agents.agent_city, customer.LastName, customer.FirstName
FROM Agent JOIN Customer ON agents.Agent_Id = customer.Cust_Id;
```

**Output:**

| Agent_City | LastName | FirstName |
|------------|----------|-----------|
| New Jersey | Samson | Erick |

---

## 31. What is Self Join in Oracle?

A Self Join is a join where a **table is joined with itself**. It is useful for comparing rows within the same table or finding hierarchical relationships.

**Example:**
```sql
SELECT e.Employee_Name AS Employee, m.Employee_Name AS Manager
FROM Employees e INNER JOIN Employees m ON e.Manager_ID = m.Employee_ID;
```

**Sample Data (Employees Table):**

| Employee_ID | Employee_Name | Manager_ID |
|-------------|---------------|------------|
| 1 | John | 3 |
| 2 | Alice | 1 |
| 3 | Smith | NULL |
| 4 | Emily | 3 |
| 5 | Bob | 2 |

**Output:**

| Employee | Manager |
|----------|---------|
| John | Smith |
| Alice | John |
| Emily | Smith |
| Bob | Alice |

---

## 32. What is Cross Join in Oracle?

A Cross Join produces the **Cartesian product** of two tables — every row from the first table is combined with every row from the second table. If table1 has `x` rows and table2 has `y` rows, the result has `x * y` rows.

**Syntax:**
```sql
SELECT * FROM table1 CROSS JOIN table2;
-- OR
SELECT * FROM table1, table2;
```

**Example:**
```sql
SELECT * FROM Suppliers CROSS JOIN Customer;
```

With 3 suppliers and 2 customers → **6 rows** in the output.

---

## 33. What is Anti Join in Oracle?

An Anti Join returns rows from the **first table where no matching rows are found** in the second table. It is the opposite of a Semi Join and is written using `NOT EXISTS` or `NOT IN`.

```sql
SELECT department.department_id, department.department_name
FROM department
WHERE NOT EXISTS (
  SELECT 1 FROM employee
  WHERE employee.department_id = department.department_id
)
ORDER BY department.department_id;
```

**Output:**

| Department_Id | Department_Name |
|---------------|-----------------|
| 3 | Management |

> Only "Management" is returned because no employees are associated with it.

---

## 34. What is Semi Join in Oracle?

A Semi Join returns **one copy of each row from the first table** for which at least one match is found in the second table. It is written using the `EXISTS` construct.

```sql
SELECT department.department_id, department.department_name
FROM department
WHERE EXISTS (
  SELECT 1 FROM employee
  WHERE employee.department_id = department.department_id
)
ORDER BY department.department_id;
```

**Output:**

| Department_Id | Department_Name |
|---------------|-----------------|
| 1 | HR |
| 2 | Finance |

---

## 35. What is the difference between INNER JOIN and OUTER JOIN?

| Feature | INNER JOIN | OUTER JOIN |
|---------|-----------|------------|
| **Returns** | Only rows with a match in **both** tables. | All rows from one or both tables, with `NULL` for unmatched rows. |
| **Unmatched rows** | Excluded. | Included with `NULL` values. |

---

## 36. What is the difference between a join condition in the WHERE clause vs. the ON clause?

| Feature | WHERE clause | ON clause |
|---------|-------------|-----------|
| **Join style** | Implicit join (tables listed with commas in `FROM`). | Explicit join (ANSI SQL syntax after `JOIN` keyword). |
| **Readability** | Less readable for complex joins. | Clearer and more maintainable. |

---

## 37. Describe the common aggregate functions in Oracle.

| Function | Description |
|----------|-------------|
| `AVG()` | Calculates the average value of a numeric column. |
| `COUNT()` | Counts the number of rows or non-null values in a column. |
| `MAX()` | Returns the maximum value in a set. |
| `MIN()` | Returns the minimum value in a set. |
| `SUM()` | Calculates the sum of values in a set. |

---

## 38. Describe the arithmetic operators in Oracle.

| Operator | Description | Example | Output |
|----------|-------------|---------|--------|
| `+` | Addition | `SELECT 5 + 3` | `8` |
| `-` | Subtraction | `SELECT 10 - 4` | `6` |
| `*` | Multiplication | `SELECT 5 * 4` | `20` |
| `/` | Division | `SELECT 20 / 5` | `4` |
| `DIV` | Integer Division (no remainder) | `SELECT 20 DIV 3` | `6` |
| `MOD` | Modulus (remainder) | `SELECT 20 MOD 3` | `2` |
| `POWER()` | Exponentiation | `SELECT POWER(2, 3)` | `8` |

---

## 39. Describe the use of the LIKE clause in Oracle.

The `LIKE` clause is used to **search for a specified pattern** in a column. It is commonly used in `WHERE` clauses for wildcard searches.

**Syntax:**
```sql
SELECT column1, column2 FROM table_name WHERE column_name LIKE pattern;
```

**Wildcard examples:**

| Pattern | Description | Example |
|---------|-------------|---------|
| `'Apple%'` | Starts with "Apple" | `LIKE 'Apple%'` |
| `'%Apple%'` | Contains "Apple" anywhere | `LIKE '%Apple%'` |
| `'%Apple'` | Ends with "Apple" | `LIKE '%Apple'` |
| `'555-1_'` | Matches single character with `_` | `LIKE '555-1_'` |
| `'[C-E]%'` | Starts with C, D, or E | `LIKE '[C-E]%'` |
| `'[^A-D]%'` | Does NOT start with A, B, C, or D | `LIKE '[^A-D]%'` |

---

## 40. Describe the SET operators available in Oracle.

| Operator | Description |
|----------|-------------|
| `UNION` | Combines results of two or more `SELECT` statements, **removing duplicates**. |
| `UNION ALL` | Combines results including **all duplicates**. |
| `INTERSECT` | Returns only rows that appear in **all** result sets. |
| `MINUS` | Returns rows that appear in the **first** result set but **not** in the second. |

---

## 41. What is a View in Oracle? How do you create and retrieve data from a View?

A **view** is a virtual table based on the result set of a `SELECT` query. It does not store data itself — it retrieves data dynamically from underlying tables. Views simplify complex queries, provide abstraction, and restrict access to specific columns or rows.

**Create a View:**
```sql
CREATE VIEW employee_view AS
SELECT employee_id, first_name, last_name, hire_date
FROM employees
WHERE department_id = 30;
```

**Retrieve data from the View:**
```sql
SELECT * FROM employee_view;
```

---

## 42. What is the use of the ORDER BY clause in Oracle?

The `ORDER BY` clause sorts the result set based on one or more columns, in **ascending** (default) or **descending** order.

```sql
SELECT FirstName, LastName FROM Employees ORDER BY LastName;
SELECT customer_name, order_total FROM orders ORDER BY order_total DESC;
```

**NULL Handling:**

| Syntax | Behavior |
|--------|----------|
| `ORDER BY column ASC NULLS LAST` | Ascending order, NULLs at the end. |
| `ORDER BY column DESC NULLS FIRST` | Descending order, NULLs at the beginning. |

---

## 43. Explain the difference between SYSDATE and CURRENT_DATE in Oracle SQL.

| Function | Returns |
|----------|---------|
| `SYSDATE` | Current date **and time** from the system clock, including fractional seconds. |
| `CURRENT_DATE` | Current date **without the time** portion (truncated to midnight). |

---

## 44. What is the use of the GROUP BY clause in Oracle?

The `GROUP BY` clause **categorizes and summarizes data** based on specific columns, allowing aggregate operations (`COUNT`, `SUM`, `AVG`, `MIN`, `MAX`) to be applied per group.

```sql
SELECT department_name, COUNT(*) AS employee_count
FROM employees
GROUP BY department_name;
```

---

## 45. What is the difference between WHERE and HAVING clauses?

| Clause | Filters | Applied |
|--------|---------|---------|
| `WHERE` | Individual row values | **Before** grouping |
| `HAVING` | Aggregate/group values | **After** grouping |

---

## 46. What is the use of the HAVING clause in Oracle?

The `HAVING` clause filters **groups of data** created by `GROUP BY`. Only groups that satisfy the `HAVING` condition are included in the final result.

```sql
SELECT department_name, COUNT(*) AS employee_count
FROM employees
GROUP BY department_name
HAVING COUNT(*) > 5;
```

---

## 47. What is a Trigger in Oracle?

A trigger is a special PL/SQL program that **automatically executes ("fires") in response to a specific event** (typically DML operations like `INSERT`, `UPDATE`, or `DELETE`) on a table or view.

**How Triggers Work:**
1. **Definition** — Created using `CREATE TRIGGER`, specifying name, event, timing, and body.
2. **Event Occurs** — A qualifying DML operation or event happens on the associated table.
3. **Execution** — Oracle automatically runs the PL/SQL code in the trigger body.

**Example — Salary validation trigger:**
```sql
CREATE OR REPLACE TRIGGER Salary_Check
BEFORE INSERT OR UPDATE ON Employees
FOR EACH ROW
BEGIN
    IF :New.Salary < 30000 OR :New.Salary > 100000 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Salary must be between 30,000 and 100,000');
    END IF;
END;
/
```

**Example — Audit log trigger:**
```sql
CREATE OR REPLACE TRIGGER Add_Row_TR
BEFORE UPDATE ON ACCOUNTS
FOR EACH ROW
BEGIN
    INSERT INTO TRANSACTIONS (TxnId, ActNo, TxnDate, Amt, Bal)
    VALUES (ac_seq.nextval, :new.ActNo, SYSDATE,
            ABS(:new.balance - :old.balance), :new.balance);
END;
/
```

**Types of Triggers:**

| Type | Description |
|------|-------------|
| **DML Triggers** | Fire on `INSERT`, `UPDATE`, or `DELETE` operations. |
| **DDL Triggers** | Fire on DDL operations like `CREATE`, `ALTER`, or `DROP`. |

---

## 48. What is the use of Sequences in Oracle?

A **sequence** is a database object that generates a **unique, ordered series of numeric values**. It ensures every row inserted into a table gets a distinct identifier, even with concurrent users.

**Syntax:**
```sql
CREATE SEQUENCE sequence_name
START WITH initial_value
INCREMENT BY increment_value
MINVALUE minimum_value
MAXVALUE maximum_value
[CYCLE | NOCYCLE]
[CACHE size];
```

**Parameters:**

| Parameter | Description |
|-----------|-------------|
| `START WITH` | The initial value for the sequence. |
| `INCREMENT BY` | Amount to increment per new value (default: 1). |
| `MINVALUE` | The lowest allowed value. |
| `MAXVALUE` | The highest allowed value. |
| `CYCLE` | Allows the sequence to wrap around after reaching `MAXVALUE`. |
| `NOCYCLE` | Prevents wrapping — an error occurs at `MAXVALUE`. |
| `CACHE` | Pre-allocates a pool of sequence numbers for better performance. |

**Examples:**
```sql
-- Basic sequence
CREATE SEQUENCE my_sequence;

-- Sequence with start and increment
CREATE SEQUENCE order_seq
START WITH 100
INCREMENT BY 5;

-- Sequence with limits, no wrapping
CREATE SEQUENCE product_id_seq
START WITH 1 INCREMENT BY 1
MINVALUE 1 MAXVALUE 9999
NOCYCLE;

-- Sequence with wrapping
CREATE SEQUENCE customer_id_seq
START WITH 1000 INCREMENT BY 1
MAXVALUE 9999
CYCLE;
```

---

## 49. What is the use of Indexes in Oracle?

Indexes are special database structures that **improve query performance** by providing a faster way to locate data — like an index in a book, without scanning the entire table.

**1. Basic Index on a Single Column:**
```sql
CREATE INDEX idx_customer_name ON customers(customer_name);
```

**2. Index on Multiple Columns:**
```sql
CREATE INDEX idx_order_details ON orders(customer_id, order_date);
```

**3. Unique Index (enforces uniqueness):**
```sql
CREATE UNIQUE INDEX ux_email ON users(email);
```

**4. Index on a Substring:**
```sql
CREATE INDEX idx_product_name_start ON products(SUBSTR(product_name, 1, 10));
```

---

## 50. What are Stored Procedures in Oracle?

Stored procedures are **pre-compiled PL/SQL program blocks** that encapsulate a specific set of database operations. They are reusable, enhance modularity, and improve security by centralizing database logic.

**Example 1 — Retrieve employee details:**
```sql
CREATE OR REPLACE PROCEDURE get_employee_details (p_employee_id IN NUMBER)
IS
    v_first_name  Employees.FirstName%TYPE;
    v_last_name   Employees.LastName%TYPE;
    v_salary      Employees.Salary%TYPE;
BEGIN
    SELECT FirstName, LastName, Salary
    INTO v_first_name, v_last_name, v_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    DBMS_OUTPUT.PUT_LINE('Employee ID: ' || p_employee_id);
    DBMS_OUTPUT.PUT_LINE('First Name: ' || v_first_name);
    DBMS_OUTPUT.PUT_LINE('Last Name: ' || v_last_name);
    DBMS_OUTPUT.PUT_LINE('Salary: ' || v_salary);
END;
/

-- Execute the procedure
BEGIN
    get_employee_details(1001);
END;
/
```

**Example 2 — Calculate order total:**
```sql
CREATE OR REPLACE PROCEDURE calculate_order_total (order_id IN NUMBER)
AS
    total_amount NUMBER;
BEGIN
    SELECT SUM(unit_price * quantity) INTO total_amount
    FROM order_items
    WHERE order_id = calculate_order_total.order_id;

    DBMS_OUTPUT.PUT_LINE('Total amount for order ' || order_id || ': ' || total_amount);
END;
/
```

**Example 3 — Procedure without parameters:**
```sql
CREATE PROCEDURE GetEmployeeDetails
AS
BEGIN
    SELECT * FROM Employees;
END;
/

EXEC GetEmployeeDetails;
```

**Example 4 — Procedure with IN parameter:**
```sql
CREATE PROCEDURE GetEmployeeDetail (EmpId IN NUMBER)
AS
BEGIN
    SELECT * FROM Employees WHERE EmpId = GetEmployeeDetail.EmpId;
END;
/
```

**Example 5 — Procedure with IN and OUT parameters:**
```sql
VARIABLE n1 NUMBER;

CREATE OR REPLACE PROCEDURE GetSalary(
    eid IN  Employees.EmpId%TYPE,
    sal OUT Employees.EmpSal%TYPE
) IS
BEGIN
    SELECT EmpSal INTO sal FROM Employees WHERE EmpId = eid;
END;
/

EXECUTE GetSalary(101, :n1);
PRINT :n1;
-- Output: 50000
```

---

*Happy Querying! 🚀*
