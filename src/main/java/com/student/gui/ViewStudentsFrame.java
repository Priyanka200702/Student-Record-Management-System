
package com.student.gui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewStudentsFrame extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ViewStudentsFrame() {

        setTitle("View Student Records");
        setSize(950, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel(
                "ALL STUDENT RECORDS",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 22));

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
        studentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        JScrollPane scrollPane = new JScrollPane(studentTable);

        JButton refreshButton = new JButton("Refresh");
        JButton closeButton = new JButton("Close");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        refreshButton.addActionListener(e -> loadStudents());
        closeButton.addActionListener(e -> dispose());

        setLayout(new BorderLayout(10, 10));

        add(heading, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadStudents();

        setVisible(true);
    }

    private void loadStudents() {

        tableModel.setRowCount(0);

        try {

            StudentDAO dao = new StudentDAO();
            List<Student> students = dao.getAllStudents();

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
                        "No student records found!"
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load students: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
