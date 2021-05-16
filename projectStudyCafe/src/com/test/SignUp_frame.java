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
import javax.swing.JPanel;

public class SignUp_frame extends Db {

	private JFrame frame;
	private JTextField textField_id;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp_frame window = new SignUp_frame();
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
	public SignUp_frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 700, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(127, 97, 436, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_id = new JLabel("");
		lblNewLabel_id.setBounds(301, 177, 226, 15);
		panel.add(lblNewLabel_id);
		lblNewLabel_id.setFont(new Font("a���17", Font.PLAIN, 9));
		lblNewLabel_id.setForeground(Color.RED);

		JLabel lblNewLabel_pw = new JLabel("");
		lblNewLabel_pw.setBounds(305, 271, 226, 15);
		panel.add(lblNewLabel_pw);
		lblNewLabel_pw.setForeground(Color.RED);
		lblNewLabel_pw.setFont(new Font("a���17", Font.PLAIN, 9));

		JButton btn_checkid = new JButton("\uC911\uBCF5\uD655\uC778");
		btn_checkid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkId(lblNewLabel_id);

			}
		});
		btn_checkid.setBounds(423, 145, 94, 26);
		panel.add(btn_checkid);

		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(305, 148, 106, 21);
		panel.add(textField_id);

		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(266, 137, 52, 41);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(207, 188, 86, 46);
		panel.add(lblNewLabel_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(305, 202, 106, 21);
		panel.add(passwordField);

		JLabel lblNewLabel_1_1_1 = new JLabel("password check");
		lblNewLabel_1_1_1.setFont(new Font("����", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(161, 233, 155, 46);
		panel.add(lblNewLabel_1_1_1);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(305, 247, 106, 21);
		panel.add(passwordField_1);

		JButton btn_submit = new JButton("submit");
		btn_submit.setBounds(423, 296, 81, 26);
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signUp(lblNewLabel_id, lblNewLabel_pw);
			}
		});
		panel.add(btn_submit);

	

	}

	// ȸ�������� ���� �Լ� (��й�ȣ ��ġ���� Ȯ��, id ���� Ȯ�� ��)
	public void signUp(JLabel lblNewLabel_id, JLabel lblNewLabel_pw) {
		String ID = textField_id.getText();
		String PW = passwordField.getText();
		String PW2 = passwordField_1.getText();
		String lblText_id = lblNewLabel_id.getText();

		if (lblText_id != "id������ �ùٸ��ϴ�") {
			JOptionPane.showMessageDialog(frame, "id �ߺ�Ȯ�� ���ּ���", "signup", JOptionPane.DEFAULT_OPTION);
		} else {
			// ��й�ȣ�� �������� üũ
			if (PW.equals(PW2)) {
				lblNewLabel_pw.setText("");
				if ((lblText_id.equals("id������ �ùٸ��ϴ�")) && lblNewLabel_pw.getText().equals("")) {
					createDbInfo(ID, PW); // text���Ͽ� ���� �Է�
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�", "signup", JOptionPane.DEFAULT_OPTION);
					frame.dispose();
				}

			} else {
				lblNewLabel_pw.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			}

		}
	}

	// id �ߺ�üũ�� ���� �Լ�
	public void checkId(JLabel lblNewLabel_id) {
		String ID = textField_id.getText();
		String pattern = "^\\d{11}$"; // ����ǥ���� ���
		Boolean regex = Pattern.matches(pattern, ID);

		// �ߺ�Ȯ�ι�ư Ŭ��

		if (!regex) {
			lblNewLabel_id.setText("id�� �ڵ�����ȣ�� �������ּ���");
			textField_id.setText("");
		} else {
			try {
				if (mapLoad.containsKey(ID)) { // map�� key������ �Է¹��� ID�� �����ϴ� ���
					JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ� �ִ� id�Դϴ�", "signup", JOptionPane.DEFAULT_OPTION);
					textField_id.setText("");
					lblNewLabel_id.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "��밡���� id�Դϴ�", "signup", JOptionPane.DEFAULT_OPTION);
					lblNewLabel_id.setText("id������ �ùٸ��ϴ�");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}
}
