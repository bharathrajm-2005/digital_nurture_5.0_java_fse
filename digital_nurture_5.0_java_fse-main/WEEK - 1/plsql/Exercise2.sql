-- Exercise 2: Error Handling

-- Scenario 1: Safe transfer of funds between accounts with exception rollback.
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_source_acc NUMBER,
    p_dest_acc   NUMBER,
    p_amount     NUMBER
) IS
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Fetch the balance of the source account
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source_acc;
    
    -- Ensure source has enough balance
    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;
    
    -- Deduct from source
    UPDATE Accounts 
    SET Balance = Balance - p_amount 
    WHERE AccountID = p_source_acc;
    
    -- Add to destination
    UPDATE Accounts 
    SET Balance = Balance + p_amount 
    WHERE AccountID = p_dest_acc;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_amount || ' from ' || p_source_acc || ' to ' || p_dest_acc);
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ID ' || p_source_acc || '. Transaction rolled back.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Source or Destination Account does not exist. Transaction rolled back.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected transaction error: ' || SQLERRM || '. Transaction rolled back.');
END;
/

-- Scenario 2: Employee salary update procedure with custom error logger.
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id NUMBER,
    p_percentage NUMBER
) IS
    v_salary NUMBER;
BEGIN
    -- Query salary to verify employee existence
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_emp_id;
    
    -- Perform the update
    UPDATE Employees
    SET Salary = Salary * (1 + (p_percentage / 100))
    WHERE EmployeeID = p_emp_id;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully increased salary for employee ID ' || p_emp_id || ' by ' || p_percentage || '%.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_emp_id || ' does not exist in the company records.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected failure during salary update: ' || SQLERRM);
END;
/

-- Scenario 3: Prevent duplicate primary keys when inserting new customer.
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_cust_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
) IS
    v_exists NUMBER;
    customer_already_exists EXCEPTION;
BEGIN
    -- Check if primary key ID exists
    SELECT COUNT(*) INTO v_exists FROM Customers WHERE CustomerID = p_cust_id;
    
    IF v_exists > 0 THEN
        RAISE customer_already_exists;
    END IF;
    
    -- Add the record
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (p_cust_id, p_name, p_dob, p_balance, SYSDATE, 'FALSE');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' registered successfully.');
EXCEPTION
    WHEN customer_already_exists THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_cust_id || ' already exists. Insertion skipped.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected database error: ' || SQLERRM);
END;
/
