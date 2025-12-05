import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class ExpenseManager {

    private static final String FILE_NAME = "expenses.txt";
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        loadExpenses(); // Load existing data
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EXPENSE MANAGER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Monthly Total");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addExpense(sc);
                case 2 -> viewExpenses();
                case 3 -> monthlyTotal(sc);
                case 4 -> {
                    saveExpenses();
                    System.out.println("Exiting... Have a nice day!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Load expenses from file
    private static void loadExpenses() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                expenses.add(Expense.fromCSV(line));
            }
        } catch (FileNotFoundException e) {
            // ignore - file will be created when saving
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Save expenses to file
    private static void saveExpenses() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                bw.write(e.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Add a new expense
    private static void addExpense(Scanner sc) {
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter category (Food/Travel/Shopping/Bills/Other): ");
        String category = sc.nextLine();

        System.out.print("Enter description: ");
        String desc = sc.nextLine();

        LocalDate date = LocalDate.now(); // Today’s date

        Expense expense = new Expense(date, category, amount, desc);
        expenses.add(expense);

        System.out.println("Expense added successfully!");
    }

    // Display all expenses
    private static void viewExpenses() {
        System.out.println("\n===== ALL EXPENSES =====");
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
            return;
        }

        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    // Calculate monthly total
    private static void monthlyTotal(Scanner sc) {
        System.out.print("Enter month (1-12): ");
        int month = sc.nextInt();
        System.out.print("Enter year (e.g., 2025): ");
        int year = sc.nextInt();

        YearMonth ym = YearMonth.of(year, month);

        double total = 0;

        System.out.println("\n===== MONTHLY REPORT: " + ym + " =====");

        for (Expense e : expenses) {
            if (YearMonth.from(e.getDate()).equals(ym)) {
                total += e.getAmount();
                System.out.println(e);
            }
        }

        System.out.println("---------------------------");
        System.out.println("Total Expense: Rs. " + total);
    }
}
