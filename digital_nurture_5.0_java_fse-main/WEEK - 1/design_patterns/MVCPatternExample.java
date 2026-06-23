package design_patterns;

// Model Class representing Student data
class Student {
    private String name;
    private String id;
    private String grade;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}

// View Class representing the User Interface / presentation layer
class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("=== Student Record Dashboard ===");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Grade: " + studentGrade);
        System.out.println("=================================");
    }
}

// Controller Class handling communication between Model and View
class StudentController {
    private final Student model;
    private final StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // Intermediary methods to modify/retrieve Model state
    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    // Command View to display model state
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// Test class to verify MVC pattern implementation
public class MVCPatternExample {
    public static void main(String[] args) {
        // Instantiate Model
        Student student = new Student();
        student.setId("STU001");
        student.setName("Alex Mercer");
        student.setGrade("B");

        // Instantiate View
        StudentView view = new StudentView();

        // Instantiate Controller connecting Model and View
        StudentController controller = new StudentController(student, view);

        // Display initial state
        System.out.println("Showing initial record:");
        controller.updateView();

        System.out.println();

        // Update data through Controller
        System.out.println("Updating record via controller...");
        controller.setStudentName("Alex Mercer Jnr.");
        controller.setStudentGrade("A+");

        // Display updated state
        System.out.println("Showing updated record:");
        controller.updateView();
    }
}
