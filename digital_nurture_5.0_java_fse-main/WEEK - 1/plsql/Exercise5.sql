-- Exercise 5: Triggers

-- Scenario 1: Keep LastModified updated whenever a Customer record is updated.
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    -- Update LastModified field to the current date and time
    :new.LastModified := SYSDATE;
END;
/

-- Scenario 2: Save audit log entries whenever a new Transaction occurs.
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    -- Insert record details into the AuditLog table
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (:new.TransactionID, :new.AccountID, :new.Amount, :new.TransactionType, SYSDATE);
END;
/

-- Scenario 3: Enforce business rules before inserting deposits/withdrawals.
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Get current account balance
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :new.AccountID;

    IF :new.TransactionType = 'Withdrawal' THEN
        -- Withdrawals cannot exceed current balance
        IF :new.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient account balance for withdrawal.');
        END IF;
    ELSIF :new.TransactionType = 'Deposit' THEN
        -- Deposits must be positive
        IF :new.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be greater than zero.');
        END IF;
    END IF;
END;
/
