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
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(139, 50, 52, 41);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(80, 101, 86, 46);
		frame.getContentPane().add(lblNewLabel_1_1);

		textField_id = new JTextField();
		textField_id.setBounds(178, 61, 106, 21);
		frame.getContentPane().add(textField_id);
		textField_id.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("password check");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(34, 146, 155, 46);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(178, 115, 106, 21);
		frame.getContentPane().add(passwordField);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(178, 160, 106, 21);
		frame.getContentPane().add(passwordField_2);

		JLabel lblNewLabel_id = new JLabel("");
		lblNewLabel_id.setFont(new Font("a고딕17", Font.PLAIN, 9));
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
				String pattern = "^\\d{8}$"; // 정규표현식 사용
				Boolean regex = Pattern.matches(pattern, input_id);

				
				// 중복확인
				// 중복되지 않을 때만 아래의 pw를 입력하는 로직을 구현할 수 있나?
				Db.bringDb_bylist(listPerson_list);

				if (!regex) {
					lblNewLabel_id.setText("id는 핸드폰번호 뒷 8자리로 설정해주세요");
					textField_id.setText("");
				} else {
					if (listPerson_list.get(0).contains(input_id)) {
						JOptionPane.showMessageDialog(null, "id가 이미 존재합니다", "signup", JOptionPane.DEFAULT_OPTION);
						textField_id.setText("");
						lblNewLabel_id.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "사용가능한 id입니다", "signup", JOptionPane.DEFAULT_OPTION);
						lblNewLabel_id.setText("id형식이 올바릅니다");
					}

				}

			}
		});
		btn_checkid.setBounds(296, 58, 94, 26);
		frame.getContentPane().add(btn_checkid);

		JLabel lblNewLabel_pw = new JLabel("");
		lblNewLabel_pw.setForeground(Color.RED);
		lblNewLabel_pw.setFont(new Font("a고딕17", Font.PLAIN, 9));
		lblNewLabel_pw.setBounds(178, 188, 226, 15);
		frame.getContentPane().add(lblNewLabel_pw);
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input_id = textField_id.getText();
				String input_pw = passwordField.getText();
				String input_pw_2 = passwordField_2.getText();

				// id를 빈칸으로 제출했는지 체크 + 중복확인 했나

				// id가 형식에 맞지 않으면 아래 label 에 경고문
//				String pattern = "^\\d{8}$"; // 정규표현식 사용
//				Boolean regex = Pattern.matches(pattern, input_id);
//
//				if (!regex) {
//					lblNewLabel_id.setText("id는 핸드폰번호 뒷 8자리로 설정해주세요");
//				} else {
//					lblNewLabel_id.setText("id형식이 올바릅니다");
//				}

				// 비밀번호가 동일한지 체크
				if (input_pw.equals(input_pw_2)) {
					lblNewLabel_pw.setText("");

				} else {
					lblNewLabel_pw.setText("비밀번호가 일치하지 않습니다");
				}

				// Db에 넣기 (text파일)

				if ((lblNewLabel_id.getText().equals("id형식이 올바릅니다")) && lblNewLabel_pw.getText().equals("")) {
					Db.putinDb(input_id, input_pw_2);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다", "signup", JOptionPane.DEFAULT_OPTION);

				}

			}
		});

	}
}
