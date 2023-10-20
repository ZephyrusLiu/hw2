# hw2
# Hi there! Welcome to Yujin and Yuqi's Expense Tracker App!

## How to use

First, you are able to run "javac ExpenseTrackerApp.java" and "java ExpenseTrackerApp" to launch the application. Then, you will see a panel with input fields and buttons. 

On the top, you can enter the amounts and categories of your transactions, click "Add Transaction" button to add your transaction to current central panel. If you find yourself enter something run and want to revoke the transaction, feel free to click "Undo" button to cancel your addition. You can also delete many transactions together in one time! 

If you want to know what transaction do you have between a certain amount, or what transaction do you have with a certain category, you can use "Filter by amount" button and "Filter by category" button. Transactions that fulfill the conditions will be highlighted in light green.

Hope you have a great time playing with this application!



## New Files

Three new files were created: TransactionFilter.java, AmountFilter.java, and CategoryFilter.java. Other files were modified based on different functionalities we need to implement.



## New Functionalities

# Model

In the model package, to implement filter function, a public interface TransactionFilter was created in the TransactionFilter.java file. Two separate specific filters were implemented in AmountFilter.java and CategoryFilter.java. The intuition behind are pretty much the same. They take in the amount and category as the parameters, checking whether these inputs are valid, both iterating over current transaction list on the table, and finally returning a new list with transactions that fulfill the conditions.

In order to delete transactions, in the ExpenseTrackerModel.java, removeTransaction(Transaction t) was added to delete transactions and it's called in the controller.

# View

User interface changed a lot in the view package. On the bottom of the panel, an "Undo" button was added for deleting selected transactions. On the right side of the panel, we added "Filter by amount" and "Filter by category" buttons. Two input fields following "From:" and "To:" labels allow users to enter lower amount and upper amount. The dropbox above "Filter by category" button allows users to choose which category do they like to filter. By default, the dropbox shows "Selected category" rather than assigning any specific category.

# Controller

In the controller package, applyFilter() function was created for applying the filter to current transaction table. It takes the category filter or amount filter as parameter, looping over both of current transaction list and filtered list, and highlighting them by light green.

Moreover, the undoTransaction() function was created for revoking and deleting selected transactions. It takes an array of int, which is the selected rows, as parameter, looping over all of the current transactions, deleting the selected transactions.

# APP

In the ExpenseTrackerApp.java, we added action listeners to 3 buttons designed in the model package. When users click on the button, new functions would be successfully called.


# Testing

We have added few tests to test the boundary of inputs and progress of new functionalities. It turns out all the functions work pretty well and successfully send error message immediately. 