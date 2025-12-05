import java.time.LocalDate;

public class Expense {
    private LocalDate date;
    private String category;
    private double amount;
    private String description;

    public Expense(LocalDate date, String category, double amount, String description) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    // Convert to CSV format for saving in file
    public String toCSV() {
        return date + "," + category + "," + amount + "," + description;
    }

    // Parse CSV line from file
    public static Expense fromCSV(String line) {
        String[] parts = line.split(",", 4);
        LocalDate date = LocalDate.parse(parts[0]);
        String category = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String description = parts[3];
        return new Expense(date, category, amount, description);
    }

    @Override
    public String toString() {
        return date + " | " + category + " | Rs. " + amount + " | " + description;
    }
}
