
package com.student.gui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SearchStudentFrame extends JFrame {

    private JComboBox<String> searchType;
    private JTextField searchField;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public SearchStudentFrame() {

        setTitle("Search Student Records");
        setSize(950, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Heading
        JLabel heading = new JLabel(
                "SEARCH STUDENT RECORDS",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 22));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchType = new JComboBox<>(new String[]{
                "Roll Number", "Student Name", "Course"
        });

        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        JButton closeButton = new JButton("Close");

        searchPanel.add(new JLabel("Search By:"));
        searchPanel.add(searchType);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        // Table
        String[] columns = {
                "Roll No", "Name", "Course",
                "Year", "Email", "Phone", "Gender"
        };

        tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        searchButton.addActionListener(e -> searchStudents());

        searchField.addActionListener(e -> searchStudents());

        clearButton.addActionListener(e -> {
            searchField.setText("");
            tableModel.setRowCount(0);
        });

        closeButton.addActionListener(e -> dispose());

        add(mainPanel);
        setVisible(true);
    }

    private void searchStudents() {

        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter something to search!"
            );

            return;
        }

        try {

            StudentDAO dao = new StudentDAO();

            List<Student> students;

            String selectedType =
                    (String) searchType.getSelectedItem();

            if ("Roll Number".equals(selectedType)) {

                students = dao.searchByRollNo(keyword);

            } else if ("Student Name".equals(selectedType)) {

                students = dao.searchByName(keyword);

            } else {

                students = dao.searchByCourse(keyword);
            }

            tableModel.setRowCount(0);

            for (Student student : students) {

                Object[] row = {
                        student.getRollNo(),
                        student.getName(),
                        student.getCourse(),
                        student.getYear(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getGender()
                };

                tableModel.addRow(row);
            }

            if (students.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No matching student records found!"
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + e.getMessage(),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
