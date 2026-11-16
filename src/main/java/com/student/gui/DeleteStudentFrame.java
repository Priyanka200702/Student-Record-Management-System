
package com.student.gui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteStudentFrame extends JFrame {

    private JTextField rollField;
    private JLabel detailsLabel;
    private JButton deleteButton;

    private Student selectedStudent;

    public DeleteStudentFrame() {

        setTitle("Delete Student");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel(
                "DELETE STUDENT RECORD",
                SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 21));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(heading, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Enter Roll Number:"), gbc);

        rollField = new JTextField(18);

        gbc.gridx = 1;
        panel.add(rollField, gbc);

        JButton searchButton = new JButton("Search Student");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(searchButton, gbc);

        detailsLabel = new JLabel("No student selected.");

        gbc.gridy = 3;
        panel.add(detailsLabel, gbc);

        deleteButton = new JButton("Delete Student");
        JButton closeButton = new JButton("Close");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);

        gbc.gridy = 4;
        panel.add(buttonPanel, gbc);

        deleteButton.setEnabled(false);

        searchButton.addActionListener(e -> searchStudent());
        deleteButton.addActionListener(e -> deleteStudent());

        closeButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    private void searchStudent() {

        String roll = rollField.getText().trim();

        if (roll.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Please enter a roll number!"
            );
            return;
        }

        try {

            StudentDAO dao = new StudentDAO();
            selectedStudent = null;

            for (Student student : dao.getAllStudents()) {

                if (student.getRollNo().equalsIgnoreCase(roll)) {
                    selectedStudent = student;
                    break;
                }
            }

            if (selectedStudent == null) {

                detailsLabel.setText("Student not found.");
                deleteButton.setEnabled(false);

                JOptionPane.showMessageDialog(
                        this, "No student found with this roll number!"
                );

                return;
            }

            detailsLabel.setText(
                    "<html>Found: "
                    + selectedStudent.getName()
                    + "<br>Course: "
                    + selectedStudent.getCourse()
                    + "<br>Year: "
                    + selectedStudent.getYear()
                    + "</html>"
            );

            deleteButton.setEnabled(true);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + e.getMessage()
            );
        }
    }

    private void deleteStudent() {

        if (selectedStudent == null) {
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete "
                        + selectedStudent.getName()
                        + " ("
                        + selectedStudent.getRollNo()
                        + ")?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            StudentDAO dao = new StudentDAO();

            boolean deleted = dao.deleteStudent(
                    selectedStudent.getRollNo()
            );

            if (deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student record deleted successfully!"
                );

                selectedStudent = null;
                rollField.setText("");
                detailsLabel.setText("No student selected.");
                deleteButton.setEnabled(false);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student record could not be deleted."
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + e.getMessage()
            );
        }
    }
}
