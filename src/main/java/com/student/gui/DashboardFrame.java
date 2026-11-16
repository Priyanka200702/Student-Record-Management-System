
package com.student.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Student Record Management System");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Heading
        JLabel heading = new JLabel(
                "STUDENT RECORD MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 22));
        panel.add(heading, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 15, 15));

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Temporary button actions
        addButton.addActionListener(e -> new AddStudentFrame());    

        viewButton.addActionListener(e -> new ViewStudentsFrame());

        updateButton.addActionListener(e -> new UpdateStudentFrame());

        deleteButton.addActionListener(e -> new DeleteStudentFrame());

        searchButton.addActionListener(e -> new SearchStudentFrame());

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        add(panel);
        setVisible(true);
    }
}
