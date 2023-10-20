package controller;

import view.ExpenseTrackerView;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.ExpenseTrackerModel;
import model.Transaction;
import model.TransactionFilter;
import java.awt.Color;

public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
  // Undo function
  public void undoTransaction(int[] selectedRows) {
    // Get transaction list
    List<Transaction> transactions = model.getTransactions();
    // Make sure selected legal row(s) 
    if (selectedRows[0] >= 0 && selectedRows.length < transactions.size()) {

      // remove all the rows
      for(int selectedRow : selectedRows){
        Transaction selectedTransaction = transactions.get(selectedRow);
        model.removeTransaction(selectedTransaction);
      }
      
      // Refresh after all the removal
      view.refreshTable(model.getTransactions());
    }
  }
  
  // Yujin Wrote
  // Other controller methods
  public boolean applyFilter(TransactionFilter transactionFilter) {
    List<Transaction> list = model.getTransactions();
    List<Transaction> newList = transactionFilter.filter(list);
    refresh();
    JTable transactionsTable = view.getTransactionsTable();
    int count = 0;
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < newList.size(); j++) {
        if (list.get(i).getAmount() == newList.get(j).getAmount()
            && list.get(i).getCategory() == newList.get(j).getCategory()
            && list.get(i).getTimestamp() == newList.get(j).getTimestamp()) {
          transactionsTable.addRowSelectionInterval(i, i);
          count += 1;
        }
      }
    }
    transactionsTable.setSelectionForeground(Color.BLACK);
    transactionsTable.setBackground(Color.WHITE);
    transactionsTable.setSelectionBackground(new Color(173, 255, 168));
    transactionsTable.updateUI();
    

    if (count == newList.size()){
      return true;
    }
    else{
      return false;
    }
  }
}