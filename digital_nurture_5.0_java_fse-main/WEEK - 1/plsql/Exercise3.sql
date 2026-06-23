-- Exercise 3: Stored Procedures

-- Scenario 1: Process monthly interest of 1% for all savings accounts.
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    -- Apply interest multiplier
    UPDATE Accounts
    SET Balance = Balance * 1.01
    WHERE AccountType = 'Savings';
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest of 1% successfully applied to all Savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying monthly interest: ' || SQLERRM);
END;
/

-- Scenario 2: Apply performance bonuses to all employees in a specific department.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department VARCHAR2,
    p_bonus_percentage NUMBER
) IS
BEGIN
    -- Update salary by adding the bonus rate
    UPDATE Employees
    SET Salary = Salary * (1 + (p_bonus_percentage / 100))
    WHERE Department = p_department;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Applied bonus of ' || p_bonus_percentage || '% to employees in department: ' || p_department);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonuses: ' || SQLERRM);
END;
/

-- Scenario 3: Transfer funds between accounts with balance checking.
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account NUMBER,
    p_to_account   NUMBER,
    p_amount       NUMBER
) IS
    v_balance NUMBER;
BEGIN
    -- Check source balance
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account;
    
    IF v_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Aborted: Insufficient funds in Account ' || p_from_account);
        RETURN;
    END IF;
    
    -- Perform operations
    UPDATE Accounts 
    SET Balance = Balance - p_amount 
    WHERE AccountID = p_from_account;
    
    UPDATE Accounts 
    SET Balance = Balance + p_amount 
    WHERE AccountID = p_to_account;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred $' || p_amount || ' from Account ' || p_from_account || ' to Account ' || p_to_account);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both Account IDs were not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected failure during transfer: ' || SQLERRM);
END;
/
