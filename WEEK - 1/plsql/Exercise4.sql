-- Exercise 4: Functions

-- Scenario 1: Calculate age in years based on customer's DOB.
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    -- Compute age based on months elapsed since DOB
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Scenario 2: Compute monthly installment (EMI) amount for a loan.
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_duration_years NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_months NUMBER;
    v_emi NUMBER;
BEGIN
    -- Monthly rate is annual rate divided by 12 months * 100
    v_monthly_rate := (p_interest_rate / 100) / 12;
    v_months := p_duration_years * 12;
    
    -- Formula: EMI = [P x r x (1+r)^n]/[((1+r)^n)-1]
    IF v_monthly_rate = 0 THEN
        v_emi := p_loan_amount / v_months;
    ELSE
        v_emi := (p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) / 
                 (POWER(1 + v_monthly_rate, v_months) - 1);
    END IF;
    
    RETURN ROUND(v_emi, 2);
END;
/

-- Scenario 3: Verify if an account has a balance >= specified amount.
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
