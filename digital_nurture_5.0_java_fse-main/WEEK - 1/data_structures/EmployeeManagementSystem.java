package data_structures;

/**
 * EXERCISE 4: Employee Management System
 * 
 * 1. Array Representation in Memory:
 *    - Arrays are stored in contiguous blocks of memory. This allows O(1) direct access 
 *      using the base address and index offset.
 * 
 * 4. Complexity & Limitations:
 *    - Add: O(1) (appending if space exists) or O(N) (shifting/reallocating).
 *    - Search: O(N) linear scan.
 *    - Traverse: O(N) to visit each element.
 *    - Delete: O(N) due to shifting subsequent elements to maintain contiguous ordering.
 *    - Limitations: Fixed size capacity. Expensive insertions and deletions.
 */
class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee [ID=" + employeeId + ", Name=" + name + ", Position=" + position + ", Salary=$" + salary + "]";
    }
}

public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add Employee
    public void addEmployee(Employee employee) {
        if (size >= employees.length) {
            System.out.println("Array capacity full. Cannot add employee.");
            return;
        }
        employees[size++] = employee;
        System.out.println("Added Employee: " + employee.getName());
    }

    // Search Employee
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse Employees
    public void traverseEmployees() {
        System.out.println("Employee Directory:");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete Employee
    public void deleteEmployee(String employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Employee not found.");
            return;
        }

        // Shift elements left to fill the gap
        for (int i = index; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }
        employees[--size] = null;
        System.out.println("Deleted Employee ID: " + employeeId);
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        ems.addEmployee(new Employee("E01", "Alice", "Developer", 80000));
        ems.addEmployee(new Employee("E02", "Bob", "Analyst", 60000));

        System.out.println();
        ems.traverseEmployees();

        System.out.println("\n--- Search ---");
        System.out.println(ems.searchEmployee("E01"));

        System.out.println("\n--- Delete ---");
        ems.deleteEmployee("E01");

        System.out.println();
        ems.traverseEmployees();
    }
}
