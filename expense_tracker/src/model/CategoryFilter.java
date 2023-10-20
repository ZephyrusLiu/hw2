package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryFilter implements TransactionFilter {

    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactionList) {
        List<Transaction> newList = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            if (transaction.getCategory() == category) {
                newList.add(transaction);
            }
        }
        return newList;
    }
}
