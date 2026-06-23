package data_structures;

/**
 * EXERCISE 5: Task Management System
 * 
 * 1. Singly vs. Doubly Linked Lists:
 *    - Singly Linked List: Nodes have values and a reference to the next node. Low memory overhead.
 *    - Doubly Linked List: Nodes have references to both next and previous nodes. Allows bidirectional traversal.
 * 
 * 4. Complexity & Advantages:
 *    - Add: O(1) if adding at head, O(N) at tail (without tail pointer).
 *    - Search: O(N) linear traversal.
 *    - Traverse: O(N) visiting each node.
 *    - Delete: O(N) because we must find the node and its predecessor.
 *    - Advantages: Dynamic size allocation prevents resizing/copying costs.
 */
class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Task [ID=" + taskId + ", Name=" + taskName + ", Status=" + status + "]";
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManagementSystem {
    private TaskNode head;

    // Add Task
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        System.out.println("Task Added: " + task.getTaskName());
    }

    // Search Task
    public Task searchTask(String taskId) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.getTaskId().equals(taskId)) {
                return curr.task;
            }
            curr = curr.next;
        }
        return null;
    }

    // Traverse Tasks
    public void traverseTasks() {
        System.out.println("Task List:");
        TaskNode curr = head;
        while (curr != null) {
            System.out.println(curr.task);
            curr = curr.next;
        }
    }

    // Delete Task
    public void deleteTask(String taskId) {
        if (head == null) return;

        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            System.out.println("Task Deleted: " + taskId);
            return;
        }

        TaskNode curr = head;
        while (curr.next != null && !curr.next.task.getTaskId().equals(taskId)) {
            curr = curr.next;
        }

        if (curr.next != null) {
            curr.next = curr.next.next;
            System.out.println("Task Deleted: " + taskId);
        } else {
            System.out.println("Task not found.");
        }
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.addTask(new Task("T01", "Database Setup", "Done"));
        tms.addTask(new Task("T02", "API Integration", "Pending"));

        System.out.println();
        tms.traverseTasks();

        System.out.println("\n--- Search ---");
        System.out.println(tms.searchTask("T01"));

        System.out.println("\n--- Delete ---");
        tms.deleteTask("T01");

        System.out.println();
        tms.traverseTasks();
    }
}
