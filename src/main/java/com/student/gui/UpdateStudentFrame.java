
package com.student.gui;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpdateStudentFrame extends JFrame {

    private JTextField rollField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;

    private JComboBox<String> courseBox;
    private JComboBox<Integer> yearBox;
    private JComboBox<String> genderBox;

    private JButton updateButton;

    public UpdateStudentFrame() {

        setTitle("Update Student Details");
        setSize(550, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("UPDATE STUDENT DETAILS");
        heading.setFont(new Font("Arial", Font.BOLD, 21));

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
                "BCA", "BBA", "B.Tech", "B.Sc",
                "B.Com", "MCA", "MBA"
        });

        yearBox = new JComboBox<>(new Integer[]{1, 2, 3, 4});

        genderBox = new JComboBox<>(new String[]{
                "Male", "Female", "Other", "Prefer not to say"
        });

        JButton searchButton = new JButton("Search Student");
        updateButton = new JButton("Save Changes");
        JButton clearButton = new JButton("Clear");
        JButton closeButton = new JButton("Close");

        addRow(panel, gbc, 1, "Roll Number:", rollField);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(searchButton, gbc);

        gbc.gridwidth = 1;

        addRow(panel, gbc, 3, "Student Name:", nameField);
        addRow(panel, gbc, 4, "Course:", courseBox);
        addRow(panel, gbc, 5, "Year:", yearBox);
        addRow(panel, gbc, 6, "Email:", emailField);
        addRow(panel, gbc, 7, "Phone:", phoneField);
        addRow(panel, gbc, 8, "Gender:", genderBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        // Disable editing until a student is found
        setFormEnabled(false);

        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
        clearButton.addActionListener(e -> clearForm());
        closeButton.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc,
                        int row, String label, JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private void setFormEnabled(boolean enabled) {

        nameField.setEnabled(enabled);
        emailField.setEnabled(enabled);
        phoneField.setEnabled(enabled);
        courseBox.setEnabled(enabled);
        yearBox.setEnabled(enabled);
        genderBox.setEnabled(enabled);
        updateButton.setEnabled(enabled);
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
            Student found = null;

            for (Student student : dao.getAllStudents()) {
                if (student.getRollNo().equalsIgnoreCase(roll)) {
                    found = student;
                    break;
                }
            }

            if (found == null) {

                JOptionPane.showMessageDialog(
                        this, "Student not found!"
                );

                setFormEnabled(false);
                return;
            }

            nameField.setText(found.getName());
            emailField.setText(found.getEmail());
            phoneField.setText(found.getPhone());

            courseBox.setSelectedItem(found.getCourse());
            yearBox.setSelectedItem(found.getYear());
            genderBox.setSelectedItem(found.getGender());

            setFormEnabled(true);

            JOptionPane.showMessageDialog(
                    this, "Student record found!"
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database error: " + e.getMessage()
            );
        }
    }

    private void updateStudent() {

        String roll = rollField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this, "Please fill in all fields!"
            );
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            JOptionPane.showMessageDialog(
                    this, "Please enter a valid email address!"
            );
            return;
        }

        if (!phone.matches("[0-9]{10}")) {

            JOptionPane.showMessageDialog(
                    this, "Phone number must contain exactly 10 digits!"
            );
            return;
        }

        Student student = new Student(
                roll,
                name,
                (String) courseBox.getSelectedItem(),
                (Integer) yearBox.getSelectedItem(),
                email,
                phone,
                (String) genderBox.getSelectedItem()
        );

        try {

            StudentDAO dao = new StudentDAO();

            if (dao.updateStudent(student)) {

                JOptionPane.showMessageDialog(
                        this, "Student updated successfully!"
                );

                clearForm();

            } else {

                JOptionPane.showMessageDialog(
                        this, "No student record was updated."
                );
            }

        } catch (SQLException e) {

            if ("23000".equals(e.getSQLState())) {

                JOptionPane.showMessageDialog(
                        this,
                        "This email address is already used by another student!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Database error: " + e.getMessage()
                );
            }
        }
    }

    private void clearForm() {

        rollField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");

        courseBox.setSelectedIndex(0);
        yearBox.setSelectedIndex(0);
        genderBox.setSelectedIndex(0);

        setFormEnabled(false);
    }
}
