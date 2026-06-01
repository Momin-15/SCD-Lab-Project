# Smart Expense Calculator
A Java Swing desktop application built for the SCD Lab Project. The project demonstrates clean code refactoring by completely separating backend business logic from the user interface.
## Features
- **Separation of Concerns:** `ExpenseProcessor.java` handles all financial calculations and data validation independently.
- **Robust Exception Handling:** Gracefully intercepts empty fields, negative inputs, and alphabetical characters in numerical fields using custom runtime logic without application crashes.
- **Dynamic UI:** Built using Java Swing (`JFrame`, `JTable`, `GridLayout`, and `BorderLayout`).