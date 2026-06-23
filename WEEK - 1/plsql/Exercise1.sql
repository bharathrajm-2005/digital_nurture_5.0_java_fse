-- Exercise 1: Control Structures

-- Scenario 1: Apply 1% interest discount to loan interest rates for customers older than 60.
DECLARE
    v_age NUMBER;
BEGIN
    FOR cust IN (SELECT CustomerID, DOB, Name FROM Customers) LOOP
        -- Calculate age in years
        v_age := MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12;
        
        -- If age is greater than 60, apply 1% discount to their interest rate
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for customer: ' || cust.Name);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote customers to VIP status based on their balance (> $10,000).
BEGIN
    FOR cust IN (SELECT CustomerID, Balance, Name FROM Customers) LOOP
        -- Promote to VIP if balance is higher than 10,000
        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Promoted customer to VIP: ' || cust.Name);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days.
DECLARE
    -- Cursor to select loans ending in the next 30 days
    CURSOR c_due_loans IS
        SELECT l.LoanID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Loan Reminder: Customer ' || rec.Name || 
                             ' (Loan ID: ' || rec.LoanID || ') has a payment due on ' || 
                             TO_CHAR(rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
