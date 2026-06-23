package data_structures;

/**
 * EXERCISE 7: Financial Forecasting
 * 
 * 1. Recursion:
 *    - A programming technique where a method calls itself to solve smaller instances 
 *      of the same problem. Simplicity and elegance are key advantages.
 * 
 * 4. Complexity & Optimization:
 *    - Time Complexity: O(N) where N is the number of periods/years.
 *    - Space Complexity: O(N) due to call stack overhead.
 *    - Optimization: Can be optimized using iteration (loops) to achieve O(1) space complexity,
 *      or by utilizing memoization (caching results) to avoid duplicate computation in more complex recursions.
 */
public class FinancialForecasting {

    // Recursive calculation: FV = PV * (1 + R)^N
    public static double predictFutureValue(double presentValue, double growthRate, int years) {
        // Base Case
        if (years == 0) {
            return presentValue;
        }
        // Recursive Step
        return predictFutureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public static void main(String[] args) {
        double capital = 1000.0;
        double rate = 0.05; // 5% growth
        int period = 5; // 5 years

        double futureValue = predictFutureValue(capital, rate, period);

        System.out.println("--- Financial Forecasting ---");
        System.out.printf("Initial Capital: $%.2f%n", capital);
        System.out.printf("Growth Rate: %.1f%%%n", rate * 100);
        System.out.printf("Value after %d years: $%.2f%n", period, futureValue);
    }
}
