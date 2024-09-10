import java.util.*;

public class ExpenseTracker {
    private static List<User> users = new ArrayList<>();
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadUsers();
        loadExpenses();

        System.out.println("Welcome to the Expense Tracker");
        User currentUser = authenticateUser(scanner);

        if (currentUser != null) {
            boolean running = true;
            while (running) {
                System.out.println("Choose an option:");
                System.out.println("1. Add Expense");
                System.out.println("2. View Expenses");
                System.out.println("3. View Category-wise Summation");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  

                switch (choice) {
                    case 1:
                        addExpense(scanner);
                        break;
                    case 2:
                        viewExpenses();
                        break;
                    case 3:
                        viewCategorySummation();
                        break;
                    case 4:
                        saveExpenses();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }

        saveUsers();
        scanner.close();
    }

    private static User authenticateUser(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Login failed.");
        return null;
    }

    private static void addExpense(Scanner scanner) {
        System.out.println("Enter expense date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        System.out.println("Enter expense category:");
        String category = scanner.nextLine();
        System.out.println("Enter expense amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        expenses.add(new Expense(date, category, amount));
        System.out.println("Expense added successfully.");
    }

    private static void viewExpenses() {
        System.out.println("List of expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void viewCategorySummation() {
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense expense : expenses) {
            categoryTotals.put(expense.getCategory(), categoryTotals.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }

        System.out.println("Category-wise expense totals:");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void loadUsers() {
       
        users.add(new User("admin", "password"));
    }

    private static void saveUsers() {
        
    }

    private static void loadExpenses() {
       
    }

    private static void saveExpenses() {
        
    }
}

