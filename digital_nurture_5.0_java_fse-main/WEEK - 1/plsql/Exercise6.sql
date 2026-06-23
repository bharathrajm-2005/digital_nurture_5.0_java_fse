-- Exercise 6: Cursors

-- Scenario 1: Generate monthly statement output for all customers using explicit cursor.
DECLARE
    -- Cursor fetches current month's transactions
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, a.AccountID, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MM') 
          AND t.TransactionDate < ADD_MONTHS(TRUNC(SYSDATE, 'MM'), 1);
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== CUSTOMER TRANSACTION STATEMENTS ===');
    FOR txn IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Customer: ' || txn.Name || 
                             ' | Account: ' || txn.AccountID ||
                             ' | Txn ID: ' || txn.TransactionID ||
                             ' | Date: ' || TO_CHAR(txn.TransactionDate, 'YYYY-MM-DD') ||
                             ' | Amount: $' || txn.Amount ||
                             ' | Type: ' || txn.TransactionType);
    END LOOP;
END;
/

-- Scenario 2: Apply annual maintenance fee to all accounts using explicit cursor.
DECLARE
    -- Fetch accounts to update their balance
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee CONSTANT NUMBER := 50.00;
BEGIN
    FOR acc IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee,
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;
        DBMS_OUTPUT.PUT_LINE('Deducted $' || v_fee || ' annual fee from Account ID: ' || acc.AccountID);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Update interest rate for all loans using explicit cursor.
DECLARE
    -- Fetch loans to update their rate
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
    v_rate_increment CONSTANT NUMBER := 0.50; -- Policy rate adjustment
BEGIN
    FOR loan IN UpdateLoanInterestRates LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + v_rate_increment
        WHERE CURRENT OF UpdateLoanInterestRates;
        DBMS_OUTPUT.PUT_LINE('Increased interest rate by +' || v_rate_increment || '% for Loan ID: ' || loan.LoanID);
    END LOOP;
    COMMIT;
END;
/
