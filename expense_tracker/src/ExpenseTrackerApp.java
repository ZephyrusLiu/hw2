import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.AmountFilter;
import model.CategoryFilter;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Handle undo transaction.
    view.getUndoButton().addActionListener(e -> {
      int[] selectedRows = view.getTransactionsTable().getSelectedRows();
      // Make sure row is legal
      if (selectedRows[0] >= 0) {
        // Apply undo in controller
        controller.undoTransaction(selectedRows);
      }
    });

    // Yujin
    view.getAddFilterAmountBtn().addActionListener(e -> {
      double lower = view.getLowerAmountField();
      double upper = view.getUpperAmountField();
      AmountFilter amf = new AmountFilter(lower, upper);
      boolean filtered = controller.applyFilter(amf);
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "No such transactions.");
        view.toFront();
      }
    });

    // Yujin
    view.getAddFilterCategoryBtn().addActionListener(e -> {
      String category = view.getSelectedCategory();
      CategoryFilter cf = new CategoryFilter(category);
      boolean filtered = controller.applyFilter(cf);
      if (!filtered) {
        JOptionPane.showMessageDialog(view, "No such transactions.");
        view.toFront();
      }
    });
  }

}