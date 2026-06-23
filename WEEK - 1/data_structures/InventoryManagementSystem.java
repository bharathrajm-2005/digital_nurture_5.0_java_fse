package data_structures;

import java.util.HashMap;
import java.util.Map;

/**
 * EXERCISE 1: Inventory Management System
 * 
 * 1. Why Data Structures & Algorithms matter for inventories:
 *    - In large warehouses containing thousands or millions of products, inefficient lookup 
 *      (like linear scanning) can bottleneck operations. Algorithms like hashing allow near-instant access.
 *    - Suitable Data Structures: 
 *      - ArrayList: Good for sequential indexing but slow O(N) for searches/deletions.
 *      - HashMap: Highly optimized for key-value lookups, giving O(1) complexity.
 * 
 * 4. Time Complexity Analysis:
 *    - Add: O(1) average time complexity.
 *    - Update: O(1) average time complexity.
 *    - Delete: O(1) average time complexity.
 *    - Optimization: Using HashMap is already highly optimal. To maintain products in sorted order,
 *      a TreeMap could be used at the cost of O(log N) operations.
 */
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName + ", Qty=" + quantity + ", Price=$" + price + "]";
    }
}

public class InventoryManagementSystem {
    private final Map<String, Product> inventory = new HashMap<>();

    // Add a new product
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists.");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product.getProductName());
    }

    // Update product quantity and price
    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            System.out.println("Updated: " + product.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product from inventory
    public void deleteProduct(String productId) {
        Product removed = inventory.remove(productId);
        if (removed != null) {
            System.out.println("Deleted: " + removed.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display all products
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        Product p1 = new Product("P001", "Laptop", 10, 999.99);
        Product p2 = new Product("P002", "Phone", 20, 599.99);

        ims.addProduct(p1);
        ims.addProduct(p2);
        ims.displayInventory();

        System.out.println();
        ims.updateProduct("P002", 15, 549.99);
        ims.deleteProduct("P001");
        
        System.out.println();
        ims.displayInventory();
    }
}
