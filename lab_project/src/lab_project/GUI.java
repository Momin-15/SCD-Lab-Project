package lab_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

    private JPanel contentPane;
    private JTextField txtDescription;
    private JTextField txtAmount;
    private JComboBox<String> comboCategory;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTotalDisplay;
    
    private ExpenseProcessor processor = new ExpenseProcessor();

    public static void main(String[] args) {
        try {
            GUI frame = new GUI();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GUI() {
        setTitle("SCD Lab Project - Smart Expense Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JPanel panelForm = new JPanel();
        panelForm.setBorder(new EmptyBorder(20, 10, 20, 10));
        panelForm.setLayout(new GridLayout(4, 2, 10, 20));
        contentPane.add(panelForm, BorderLayout.WEST);

        panelForm.add(new JLabel("Item Description:"));
        txtDescription = new JTextField();
        txtDescription.setColumns(12);
        panelForm.add(txtDescription);

        panelForm.add(new JLabel("Amount ($):"));
        txtAmount = new JTextField();
        panelForm.add(txtAmount);

        panelForm.add(new JLabel("Category:"));
        String[] categories = {"Food", "Utilities", "Entertainment", "Transport", "Other"};
        comboCategory = new JComboBox<>(categories);
        panelForm.add(comboCategory);

        panelForm.add(new JLabel("")); 
        JButton btnAdd = new JButton("Add Record");
        btnAdd.setBackground(new Color(46, 204, 113)); 
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelForm.add(btnAdd);

        JPanel panelDisplay = new JPanel(new BorderLayout(10, 10));
        contentPane.add(panelDisplay, BorderLayout.CENTER);

        String[] columns = {"Description", "Amount", "Category"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        panelDisplay.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelFooter = new JPanel(new GridLayout(1, 2));
        panelFooter.setBackground(new Color(236, 240, 241));
        panelFooter.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDisplay.add(panelFooter, BorderLayout.SOUTH);

        JLabel lblTotalTitle = new JLabel("Total Balance Due:");
        lblTotalTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelFooter.add(lblTotalTitle);

        lblTotalDisplay = new JLabel("$0.00", SwingConstants.RIGHT);
        lblTotalDisplay.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        lblTotalDisplay.setForeground(new Color(231, 76, 60)); 
        panelFooter.add(lblTotalDisplay);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String desc = txtDescription.getText();
                String amountStr = txtAmount.getText();
                String cat = (String) comboCategory.getSelectedItem();

                try {
                    double runningTotal = processor.addExpense(desc, amountStr);
                    
                    Object[] rowData = {desc, amountStr, cat};
                    tableModel.addRow(rowData);
                    lblTotalDisplay.setText(String.format("$%.2f", runningTotal));

                    txtDescription.setText("");
                    txtAmount.setText("");

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(GUI.this, ex.getMessage(), "Input Entry Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}