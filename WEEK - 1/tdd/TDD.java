

class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
}

class CalculatorTest {

    public void testAdd() {

        Calculator calc = new Calculator();

        int result = calc.add(5, 7);

        if (result == 12) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed!");
        }
    }
}

 class TDD {

    public static void main(String[] args) {

        CalculatorTest test = new CalculatorTest();

        test.testAdd();
    }
}