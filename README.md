# Expense-Manager

A small, lightweight Java command-line Expense Manager that lets you add, view, and report monthly expenses. Data is persisted to a local text file (expenses.txt) in a simple CSV format.

## Features
- Add expense with date (auto-set to today), category, amount and description
- View all recorded expenses
- View monthly report (list + total for a given month and year)
- Persistent storage in `expenses.txt` (simple CSV lines)

## Requirements
- Java 8 or newer (uses java.time.LocalDate)
- No external libraries required

## Files
- `Expense.java` — model class representing an expense (CSV serialization/deserialization).
- `ExpenseManager.java` — CLI application (loads/saves `expenses.txt` and provides interactive menu).
- `expenses.txt` — created/updated at runtime; one CSV entry per line.

## Data format
Each line in `expenses.txt` uses CSV with 4 columns:
```
YYYY-MM-DD,category,amount,description
```
- Date format: ISO (e.g. `2025-12-05`)
- Category: string (e.g. `Food`, `Travel`, `Bills`, `Other`, etc.)
- Amount: decimal number (double)
- Description: any text — the parser uses `split(",", 4)` so description may contain commas and will be preserved as the fourth field.

## How it works (brief)
- On start the app reads `expenses.txt` (if present) and loads all expenses.
- The menu offers options to add an expense, view all expenses, view a monthly total, or exit.
- When exiting the app, all expenses are written back to `expenses.txt` (overwriting previous content).

## Build & Run
From the directory containing `Expense.java` and `ExpenseManager.java`:

Compile:
- Unix / macOS / Windows (CMD / PowerShell):
  ```
  javac Expense.java ExpenseManager.java
  ```

Run:
```
java ExpenseManager
```

The app will present a simple text menu for interaction.

## Example session
- Choose `1` to add an expense:
  - Enter amount: `150.50`
  - Enter category: `Food`
  - Enter description: `Lunch at cafe`
- Choose `2` to view all expenses — you will see lines like:
  ```
  2025-12-05 | Food | Rs. 150.5 | Lunch at cafe
  ```
- Choose `3` to get a monthly report — you will be prompted for month and year.

## Known limitations
- No input validation: negative amounts, invalid months, or malformed categories are not prevented.
- All expenses use the current date when added; there is no option in the current UI to set a custom date.
- No edit/delete functionality for expenses.
- File concurrency/locking is not handled — avoid running multiple instances that write to the same file simultaneously.
- Storage is a plain text file (no encryption). Do not store sensitive data in descriptions.

## Possible improvements
- Allow entering custom date for an expense.
- Add edit/delete and filtering by category/date range.
- Export/import full CSV with headers or use a real CSV library for robust parsing.
- Add unit tests and input validation.
- Replace text file with a lightweight embedded database (H2/SQLite) for reliability.
- Add GUI or web interface.

## Contributing
Feel free to fork and submit pull requests. For small changes, open an issue or a PR describing the change.

## License
MIT License — see LICENSE file (or copy the following):

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
