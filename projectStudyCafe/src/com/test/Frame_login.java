package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JToggleButton;

public class Frame_login {

	private JFrame frame;
	private JTextField id_textField;
	private JTextField pw_textField;
	ArrayList<upload> listPerson_instance = new ArrayList<upload>();
	ArrayList<ArrayList<String>> listPerson_list = new ArrayList<ArrayList<String>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_login window = new Frame_login();
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
	public Frame_login() {
		initialize(); // frame이 열리게 함
		Db.bringDb_bylist(listPerson_list); // frame이 열린뒤, listPerson에 txt파일의 회원정보를 저장하게 함.

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("codesta studycafe");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(133, 27, 219, 65);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("phone number");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(99, 123, 107, 42);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(99, 212, 107, 42);
		frame.getContentPane().add(lblNewLabel_1_1);

		id_textField = new JTextField();
		id_textField.setBounds(221, 135, 106, 21);
		frame.getContentPane().add(id_textField);
		id_textField.setColumns(10);

		pw_textField = new JTextField();
		pw_textField.setColumns(10);
		pw_textField.setBounds(221, 224, 106, 21);
		frame.getContentPane().add(pw_textField);

		JButton btn_login = new JButton("login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input_id = id_textField.getText();
				String input_pw = pw_textField.getText();

				// 세 가지 조건 모두를 만족해야 로그인 가능
				if ((listPerson_list.get(0).contains(input_id)) && (listPerson_list.get(1).contains(input_pw))
						&& listPerson_list.get(0).indexOf(input_id) == listPerson_list.get(1).indexOf(input_pw)) {
					JOptionPane.showMessageDialog(null, "로그인 성공", "login", JOptionPane.DEFAULT_OPTION);

				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패", "login", JOptionPane.DEFAULT_OPTION);

					
				}

			}
		});
		btn_login.setBounds(332, 310, 95, 23);
		frame.getContentPane().add(btn_login);
		
		JButton btn_signup = new JButton("sign_up");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame_signup signup = new Frame_signup();
				signup.main(null);
			}
		});
		btn_signup.setBounds(205, 391, 95, 23);
		frame.getContentPane().add(btn_signup);
	}
}
