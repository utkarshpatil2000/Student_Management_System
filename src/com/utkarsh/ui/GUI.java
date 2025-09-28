package com.utkarsh.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.utkarsh.dao.StudentDAO;
import com.utkarsh.model.Student;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nameField, emailField, phoneField;
	private JTextArea outputArea;
	private StudentDAO dao = new StudentDAO();

	public GUI() {
		setTitle("Student Management");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		nameField = new JTextField(20);
		emailField = new JTextField(20);
		phoneField = new JTextField(20);
		JButton addBtn = new JButton("Add Student");
		JButton showBtn = new JButton("Show Students");
		outputArea = new JTextArea(10, 30);

		add(new JLabel("Name:"));
		add(nameField);
		add(new JLabel("Email:"));
		add(emailField);
		add(new JLabel("Phone:"));
		add(phoneField);
		add(addBtn);
		add(showBtn);
		add(new JScrollPane(outputArea));

		addBtn.addActionListener(this::addStudent);
		showBtn.addActionListener(e -> showStudents());
	}

	private void addStudent(ActionEvent e) {
		String name = nameField.getText().trim();
		String email = emailField.getText().trim();
		String phone = phoneField.getText().trim();

		if (name.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Name and Email required!");
			return;
		}
		if (!email.contains("@")) {
			JOptionPane.showMessageDialog(this, "Invalid email!");
			return;
		}

		dao.insert(new Student(0, name, email, phone));
		JOptionPane.showMessageDialog(this, "Student Added!");
	}

	private void showStudents() {
		outputArea.setText("");
		for (Student s : dao.getAll()) {
			outputArea.append(s.toString() + "\n");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
	}
}
