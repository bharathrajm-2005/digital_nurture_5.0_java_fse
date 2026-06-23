package data_structures;

/**
 * EXERCISE 3: Sorting Customer Orders
 * 
 * 1. Sorting Algorithms:
 *    - Bubble Sort: Simple swap-based sorting. Compare adjacent items and swap. O(N^2)
 *    - Insertion Sort: Insert each item into its correct relative position. O(N^2)
 *    - Quick Sort: Pivot-based partitioning. Average O(N log N)
 *    - Merge Sort: Divide and merge recursively. Guaranteed O(N log N)
 * 
 * 4. Complexity Comparison:
 *    - Bubble Sort: O(N^2) average and worst-case. Inefficient for large arrays.
 *    - Quick Sort: O(N log N) average-case. Much faster in practice.
 *    - Recommendation: Quick Sort is preferred due to low overhead and in-place sorting.
 */
class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return "Order [ID=" + orderId + ", Customer=" + customerName + ", Total=$" + totalPrice + "]";
    }
}

public class SortingOrders {

    // Bubble Sort: O(N^2)
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort: O(N log N)
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order("O01", "Alice", 250.0),
            new Order("O02", "Bob", 75.5),
            new Order("O03", "Charlie", 400.0)
        };

        System.out.println("--- Original ---");
        for (Order o : orders) System.out.println(o);

        bubbleSort(orders);
        System.out.println("\n--- After Bubble Sort ---");
        for (Order o : orders) System.out.println(o);
    }
}
