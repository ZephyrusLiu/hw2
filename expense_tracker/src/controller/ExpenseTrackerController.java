package controller;

import view.ExpenseTrackerView;

import java.util.List;
import javax.swing.table.DefaultTableModel;




import model.ExpenseTrackerModel;
import model.Transaction;
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
  // Get selected transaction.
  // private Transaction getSelectedTransactionFromView() {
  //   // Get selected row in JTable 
  //   int selectedRowIndex = view.getTransactionsTable().getSelectedRow();

  //   // Check if any row is selected
  //   if (selectedRowIndex != -1) {
  //       // Get model
  //       DefaultTableModel tableModel = view.getTableModel();

  //       // Get row data
  //       double amount = (Double) tableModel.getValueAt(selectedRowIndex, 1); 
  //       String category = (String) tableModel.getValueAt(selectedRowIndex, 2); 

  //       // use 
  //       Transaction selectedTransaction = new Transaction(amount, category);

  //       return selectedTransaction;
  //   }

  //   // 如果没有选定行，返回 null
  //   return null;  

  // }
  // // Undo function
  // private void undoTransaction(Transaction transaction) {
  //   model.removeTransaction(transaction);
  //   refresh();
  // }
}