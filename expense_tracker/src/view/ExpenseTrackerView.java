package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  // Added
  private JButton addFilterAmountBtn;
  private JButton addFilterCategoryBtn;
  private JComboBox<String> CategoryBox;
  private JFormattedTextField lowerAmountField;
  private JFormattedTextField upperAmountField;

  // Add undo button
  private JButton undoButton;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = { "serial", "Amount", "Category", "Date" };
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);

    //
    //
    //Yuqi accomplish undo button
    //
    //
    undoButton = new JButton("Undo");
    JPanel buttonPanel = new JPanel();

    buttonPanel.add(undoButton);
    

    //
    //
    //
    // Yujin wrote: Create Filter Stuff
    //
    //
    //
    addFilterAmountBtn = new JButton("Filter by amount");
    addFilterCategoryBtn = new JButton("Filter by category");
    String[] items = { "select category", "food", "travel", "bills", "entertainment", "other" };
    CategoryBox = new JComboBox<>(items);

    JLabel lower = new JLabel("From:");
    NumberFormat lowerFormat = NumberFormat.getNumberInstance();
    lowerAmountField = new JFormattedTextField(lowerFormat);
    lowerAmountField.setColumns(10);

    JLabel upper = new JLabel("To:");
    NumberFormat upperFormat = NumberFormat.getNumberInstance();
    upperAmountField = new JFormattedTextField(upperFormat);
    upperAmountField.setColumns(10);
    //
    //
    //
    //

    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel);
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    buttonPanel.add(addTransactionBtn);

    // Yujin wrote: Add Filter panel right under input panel.
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
    filterPanel.add(lower);
    filterPanel.add(lowerAmountField);
    filterPanel.add(upper);
    filterPanel.add(upperAmountField);
    filterPanel.add(addFilterAmountBtn);
    filterPanel.add(CategoryBox);
    filterPanel.add(addFilterCategoryBtn);

    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    // Yujin wrote: add filter panel to frame
    add(filterPanel, BorderLayout.EAST);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Set frame properties
    setSize(800, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  public void refreshTable(List<Transaction> transactions) {
    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = model.getRowCount();
    double totalCost = 0;
    // Calculate total cost
    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }
    // Add rows from transactions list
    for (Transaction t : transactions) {
      model.addRow(new Object[] { rowNum += 1, t.getAmount(), t.getCategory(), t.getTimestamp() });
    }
    // Add total row
    Object[] totalRow = { "Total", null, null, totalCost };
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();

  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }

  // Other view methods
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }

  // Yujin wrote below:
  // Add filter button.
  public JButton getAddFilterAmountBtn() {
    return addFilterAmountBtn;
  }

  public JButton getAddFilterCategoryBtn() {
    return addFilterCategoryBtn;
  }

  // Add dropdown box.
  public JComboBox<String> getCateBox() {
    return CategoryBox;
  }

  public double getLowerAmountField() {
    if (lowerAmountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(lowerAmountField.getText());
      return amount;
    }
  }

  public void setLowerAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public double getUpperAmountField() {
    if (upperAmountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(upperAmountField.getText());
      return amount;
    }
  }

  public void setUpperAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  // Add button.
  public JButton getUndoButton() {
    return undoButton;
  }
}
