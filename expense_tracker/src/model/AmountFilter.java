package model;

import java.util.ArrayList;
import java.util.List;

import controller.InputValidation;

public class AmountFilter implements TransactionFilter {
    private final double lower;
    private final double upper;

    public AmountFilter(double lower, double upper) {
        if (!InputValidation.isValidAmount(lower)) {
            throw new NumberFormatException("Invalid Lower Bound.");
        }
        if (!InputValidation.isValidAmount(upper)) {
            throw new NumberFormatException("Invalid Upper Bound.");
        }
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactionList) {
        List<Transaction> newList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() >= lower && transaction.getAmount() <= upper) {
                newList.add(transaction);
            }
        }
        return newList;
    }
}
