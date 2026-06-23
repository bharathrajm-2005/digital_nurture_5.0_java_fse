package tdd;

// Using TDD: This test would ideally be written before the Calculator.add method.
public class CalculatorTest {
    
    public void testAdd() {
        // Arrange
        Calculator calc = new Calculator();
        
        // Act
        int result = calc.add(5, 7);
        
        // Assert
        if (result == 12) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test failed. Expected 12 but got " + result);
        }
    }
}
