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
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel.setBounds(127, 97, 436, 30);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_id = new JLabel("");
		lblNewLabel_id.setBounds(301, 177, 226, 15);
		panel.add(lblNewLabel_id);
		lblNewLabel_id.setFont(new Font("a고딕17", Font.PLAIN, 9));
		lblNewLabel_id.setForeground(Color.RED);

		JLabel lblNewLabel_pw = new JLabel("");
		lblNewLabel_pw.setBounds(305, 271, 226, 15);
		panel.add(lblNewLabel_pw);
		lblNewLabel_pw.setForeground(Color.RED);
		lblNewLabel_pw.setFont(new Font("a고딕17", Font.PLAIN, 9));

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
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(266, 137, 52, 41);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(207, 188, 86, 46);
		panel.add(lblNewLabel_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(305, 202, 106, 21);
		panel.add(passwordField);

		JLabel lblNewLabel_1_1_1 = new JLabel("password check");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 17));
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

	// 회원가입을 위한 함수 (비밀번호 일치여부 확인, id 형식 확인 등)
	public void signUp(JLabel lblNewLabel_id, JLabel lblNewLabel_pw) {
		String ID = textField_id.getText();
		String PW = passwordField.getText();
		String PW2 = passwordField_1.getText();
		String lblText_id = lblNewLabel_id.getText();

		if (lblText_id != "id형식이 올바릅니다") {
			JOptionPane.showMessageDialog(frame, "id 중복확인 해주세요", "signup", JOptionPane.DEFAULT_OPTION);
		} else {
			// 비밀번호가 동일한지 체크
			if (PW.equals(PW2)) {
				lblNewLabel_pw.setText("");
				if ((lblText_id.equals("id형식이 올바릅니다")) && lblNewLabel_pw.getText().equals("")) {
					createDbInfo(ID, PW); // text파일에 정보 입력
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다", "signup", JOptionPane.DEFAULT_OPTION);
					frame.dispose();
				}

			} else {
				lblNewLabel_pw.setText("비밀번호가 일치하지 않습니다");
			}

		}
	}

	// id 중복체크를 위한 함수
	public void checkId(JLabel lblNewLabel_id) {
		String ID = textField_id.getText();
		String pattern = "^\\d{11}$"; // 정규표현식 사용
		Boolean regex = Pattern.matches(pattern, ID);

		// 중복확인버튼 클릭

		if (!regex) {
			lblNewLabel_id.setText("id는 핸드폰번호로 설정해주세요");
			textField_id.setText("");
		} else {
			try {
				if (mapLoad.containsKey(ID)) { // map의 key값으로 입력받은 ID가 존재하는 경우
					JOptionPane.showMessageDialog(null, "이미 가입되어 있는 id입니다", "signup", JOptionPane.DEFAULT_OPTION);
					textField_id.setText("");
					lblNewLabel_id.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용가능한 id입니다", "signup", JOptionPane.DEFAULT_OPTION);
					lblNewLabel_id.setText("id형식이 올바릅니다");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}
}
