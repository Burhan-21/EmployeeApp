import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";       // Change to your DB username
    private static final String PASSWORD = "XYZ@1234"; // Change to your DB password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            conn.setAutoCommit(false); // Enable transaction control
            System.out.println("✅ Connected to Database Successfully!\n");

            while (true) {
                showMenu();
                int choice = readInt(sc, "Enter choice: ");

                try {
                    switch (choice) {
                        case 1 -> addEmployee(conn, sc);
                        case 2 -> viewEmployees(conn);
                        case 3 -> updateSalary(conn, sc);
                        case 4 -> deleteEmployee(conn, sc);
                        case 5 -> {
                            System.out.println("👋 Goodbye!");
                            return;
                        }
                        default -> System.out.println("❌ Invalid choice! Try again.");
                    }
                    conn.commit(); // Commit successful operations
                } catch (SQLException e) {
                    conn.rollback(); // Roll back on error
                    System.err.println("❌ Transaction rolled back: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Connection Error: " + e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Employee Database Menu =====");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Update Employee Salary");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
    }

    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("❌ Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    private static double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.print("❌ Please enter a valid number: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    private static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        sc.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        double salary = readDouble(sc, "Enter salary: ");

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("✅ Employee added successfully!");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "Name", "Department", "Salary");
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-10.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }
        }
    }

    private static void updateSalary(Connection conn, Scanner sc) throws SQLException {
        int id = readInt(sc, "Enter Employee ID to update: ");
        double salary = readDouble(sc, "Enter new salary: ");

        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, salary);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Salary updated!" : "⚠ Employee not found!");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        int id = readInt(sc, "Enter Employee ID to delete: ");

        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Employee deleted!" : "⚠ Employee not found!");
        }
    }
}
