package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.Color;

public class Frame_signup {

	private JFrame frame;
	private JTextField textField_id;
	private JPasswordField passwordField;
	private JPasswordField passwordField_2;
	ArrayList<ArrayList<String>> listPerson_list = new ArrayList<ArrayList<String>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_signup window = new Frame_signup();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame_signup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sign up");
		lblNewLabel.setBounds(0, 10, 436, 30);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(139, 50, 52, 41);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(80, 101, 86, 46);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField_id = new JTextField();
		textField_id.setBounds(178, 61, 106, 21);
		frame.getContentPane().add(textField_id);
		textField_id.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("password check");
		lblNewLabel_1_1_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(34, 146, 155, 46);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(178, 115, 106, 21);
		frame.getContentPane().add(passwordField);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(178, 160, 106, 21);
		frame.getContentPane().add(passwordField_2);

		JLabel lblNewLabel_id = new JLabel("");
		lblNewLabel_id.setFont(new Font("a���17", Font.PLAIN, 9));
		lblNewLabel_id.setForeground(Color.RED);
		lblNewLabel_id.setBounds(178, 90, 226, 15);
		frame.getContentPane().add(lblNewLabel_id);

		JButton btn_submit = new JButton("submit");
		btn_submit.setBounds(296, 209, 81, 26);
		frame.getContentPane().add(btn_submit);

		JButton btn_checkid = new JButton("\uC911\uBCF5\uD655\uC778");
		btn_checkid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_id = textField_id.getText();
				String pattern = "^\\d{8}$"; // ����ǥ���� ���
				Boolean regex = Pattern.matches(pattern, input_id);

				
				// �ߺ�Ȯ��
				// �ߺ����� ���� ���� �Ʒ��� pw�� �Է��ϴ� ������ ������ �� �ֳ�?
				Db.bringDb_bylist(listPerson_list);

				if (!regex) {
					lblNewLabel_id.setText("id�� �ڵ�����ȣ �� 8�ڸ��� �������ּ���");
					textField_id.setText("");
				} else {
					if (listPerson_list.get(0).contains(input_id)) {
						JOptionPane.showMessageDialog(null, "id�� �̹� �����մϴ�", "signup", JOptionPane.DEFAULT_OPTION);
						textField_id.setText("");
						lblNewLabel_id.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "��밡���� id�Դϴ�", "signup", JOptionPane.DEFAULT_OPTION);
						lblNewLabel_id.setText("id������ �ùٸ��ϴ�");
					}

				}

			}
		});
		btn_checkid.setBounds(296, 58, 94, 26);
		frame.getContentPane().add(btn_checkid);

		JLabel lblNewLabel_pw = new JLabel("");
		lblNewLabel_pw.setForeground(Color.RED);
		lblNewLabel_pw.setFont(new Font("a���17", Font.PLAIN, 9));
		lblNewLabel_pw.setBounds(178, 188, 226, 15);
		frame.getContentPane().add(lblNewLabel_pw);
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input_id = textField_id.getText();
				String input_pw = passwordField.getText();
				String input_pw_2 = passwordField_2.getText();

				// id�� ��ĭ���� �����ߴ��� üũ + �ߺ�Ȯ�� �߳�

				// id�� ���Ŀ� ���� ������ �Ʒ� label �� ���
//				String pattern = "^\\d{8}$"; // ����ǥ���� ���
//				Boolean regex = Pattern.matches(pattern, input_id);
//
//				if (!regex) {
//					lblNewLabel_id.setText("id�� �ڵ�����ȣ �� 8�ڸ��� �������ּ���");
//				} else {
//					lblNewLabel_id.setText("id������ �ùٸ��ϴ�");
//				}

				// ��й�ȣ�� �������� üũ
				if (input_pw.equals(input_pw_2)) {
					lblNewLabel_pw.setText("");

				} else {
					lblNewLabel_pw.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
				}

				// Db�� �ֱ� (text����)

				if ((lblNewLabel_id.getText().equals("id������ �ùٸ��ϴ�")) && lblNewLabel_pw.getText().equals("")) {
					Db.putinDb(input_id, input_pw_2);
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�", "signup", JOptionPane.DEFAULT_OPTION);

				}

			}
		});

	}
}
