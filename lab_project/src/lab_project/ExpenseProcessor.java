package lab_project;

public class ExpenseProcessor {
    private double totalExpenses = 0.0;

    public double addExpense(String description, String amountStr) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty!");
        }
        if (amountStr == null || amountStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Amount cannot be empty!");
        }

        try {
            double amount = Double.parseDouble(amountStr);
            
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero!");
            }
            
            totalExpenses += amount;
            return totalExpenses;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a valid numerical value!");
        }
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}