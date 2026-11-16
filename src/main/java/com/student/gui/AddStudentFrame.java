
package com.student.gui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddStudentFrame extends JFrame {

    private JTextField rollField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private JComboBox<String> courseBox;
    private JComboBox<Integer> yearBox;
    private JComboBox<String> genderBox;

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("ADD STUDENT DETAILS");
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(heading, gbc);

        gbc.gridwidth = 1;

        rollField = new JTextField(18);
        nameField = new JTextField(18);
        emailField = new JTextField(18);
        phoneField = new JTextField(18);

        courseBox = new JComboBox<>(new String[]{
                "BCA", "BBA", "B.Tech", "B.Sc", "B.Com", "MCA", "MBA"
        });

        yearBox = new JComboBox<>(new Integer[]{
                1, 2, 3, 4
        });

        genderBox = new JComboBox<>(new String[]{
                "Male", "Female", "Other", "Prefer not to say"
        });

        addRow(panel, gbc, 1, "Roll Number:", rollField);
        addRow(panel, gbc, 2, "Student Name:", nameField);
        addRow(panel, gbc, 3, "Course:", courseBox);
        addRow(panel, gbc, 4, "Year:", yearBox);
        addRow(panel, gbc, 5, "Email:", emailField);
        addRow(panel, gbc, 6, "Phone:", phoneField);
        addRow(panel, gbc, 7, "Gender:", genderBox);

        JButton saveButton = new JButton("Save Student");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        saveButton.addActionListener(e -> saveStudent());
        clearButton.addActionListener(e -> clearFields());

        backButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc,
                        int row, String label, JComponent component) {

        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void saveStudent() {

        String roll = rollField.getText().trim();
        String name = nameField.getText().trim();
        String course = (String) courseBox.getSelectedItem();
        int year = (Integer) yearBox.getSelectedItem();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();

        if (roll.isEmpty() || name.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields!"
            );
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address!"
            );
            return;
        }

        if (!phone.matches("[0-9]{10}")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits!"
            );
            return;
        }

        Student student = new Student(
                roll, name, course, year, email, phone, gender
        );

        try {

            StudentDAO dao = new StudentDAO();

            if (dao.addStudent(student)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!"
                );

                clearFields();

            }

        } catch (SQLException e) {

            if ("23000".equals(e.getSQLState())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Roll number or email already exists!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Database error: " + e.getMessage()
                );
            }
        }
    }

    private void clearFields() {

        rollField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");

        courseBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        genderBox.setSelectedIndex(0);
    }
}
