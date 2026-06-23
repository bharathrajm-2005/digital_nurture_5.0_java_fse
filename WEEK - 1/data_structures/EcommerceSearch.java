package data_structures;

import java.util.Arrays;
import java.util.Comparator;

/**
 * EXERCISE 2: E-commerce Platform Search Function
 * 
 * 1. Big O Notation:
 *    - Describes how the execution time or space requirement of an algorithm grows as the size of input data grows.
 *    - Scenarios:
 *      - Best-case: Minimum time required (e.g., finding the item on the first try, O(1)).
 *      - Average-case: Expected average execution time over all possible inputs.
 *      - Worst-case: Maximum time needed (e.g., finding the item at the very end or not at all, O(N)).
 * 
 * 4. Complexity Comparison:
 *    - Linear Search: O(N) worst-case. Works on unsorted arrays.
 *    - Binary Search: O(log N) worst-case. Requires sorted data.
 *    - Recommendation: Binary search is much better for search features since catalogs are sorted once
 *      and searched millions of times.
 */
class SearchProduct {
    private String productId;
    private String productName;
    private String category;

    public SearchProduct(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "SearchProduct [ID=" + productId + ", Name=" + productName + ", Category=" + category + "]";
    }
}

public class EcommerceSearch {

    // Linear Search: O(N)
    public static SearchProduct linearSearch(SearchProduct[] products, String targetId) {
        for (SearchProduct product : products) {
            if (product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search: O(log N) - Assumes products array is sorted by productId
    public static SearchProduct binarySearch(SearchProduct[] products, String targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comp = products[mid].getProductId().compareTo(targetId);

            if (comp == 0) {
                return products[mid];
            } else if (comp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SearchProduct[] products = {
            new SearchProduct("P03", "Tablet", "Electronics"),
            new SearchProduct("P01", "Keyboard", "Accessories"),
            new SearchProduct("P02", "Monitor", "Electronics")
        };

        // Linear Search
        System.out.println("--- Linear Search ---");
        System.out.println("Found: " + linearSearch(products, "P02"));

        // Sort by ID for Binary Search
        Arrays.sort(products, Comparator.comparing(SearchProduct::getProductId));

        // Binary Search
        System.out.println("\n--- Binary Search (Sorted Array) ---");
        System.out.println("Found: " + binarySearch(products, "P02"));
    }
}
