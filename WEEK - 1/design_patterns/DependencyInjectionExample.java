package design_patterns;

// Repository Interface defining customer database contract
interface CustomerRepository {
    String findCustomerById(int id);
}

// Concrete Repository implementing data retrieval contract
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // Simulated Database lookup
        if (id == 101) {
            return "Customer Profile: Bob Vance [Status: Active]";
        } else if (id == 102) {
            return "Customer Profile: Phyllis Vance [Status: Active]";
        }
        return "Customer ID not found.";
    }
}

// Service class dependent on CustomerRepository
class CustomerService {
    private final CustomerRepository customerRepository;

    // Dependency Injection: constructor-based injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomer(int id) {
        String info = customerRepository.findCustomerById(id);
        System.out.println("Result: " + info);
    }
}

// Main class to verify Dependency Injection implementation
public class DependencyInjectionExample {
    public static void main(String[] args) {
        // Instantiate the concrete dependency
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject the repository dependency into the service class
        CustomerService service = new CustomerService(repository);

        // Test database lookup operations
        System.out.println("--- Finding Customer 101 ---");
        service.displayCustomer(101);

        System.out.println();

        System.out.println("--- Finding Customer 999 ---");
        service.displayCustomer(999);
    }
}
