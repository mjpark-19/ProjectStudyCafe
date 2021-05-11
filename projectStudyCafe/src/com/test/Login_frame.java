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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JToggleButton;
import javax.swing.JPasswordField;

public class Login_frame {

	private JFrame frame;
	private JTextField id_textField;
	private JPasswordField passwordField;

	ArrayList<ArrayList<String>> userInfo_list = new ArrayList<ArrayList<String>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_frame window = new Login_frame();
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
	public Login_frame() {
		initialize(); // frame이 열리게 함
		

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Db.ReadInfo(userInfo_list);
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

		passwordField = new JPasswordField();
		passwordField.setBounds(218, 223, 107, 23);
		frame.getContentPane().add(passwordField);

		JButton btn_login = new JButton("login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = id_textField.getText();
				String PW = passwordField.getText();

				// 세 가지 조건 모두를 만족해야 로그인 가능
				if ((userInfo_list.get(0).contains(ID)) && (userInfo_list.get(1).contains(PW))
						&& (userInfo_list.get(0).indexOf(ID) == userInfo_list.get(1).indexOf(PW))) {
					
					// user 정보를 map 에 담아두기
					int userIndex =  userInfo_list.get(0).indexOf(ID);
					HashMap<String, String> userInfo = new HashMap<String, String>();
					
					userInfo.put("ID", ID);
					userInfo.put("PW", PW);
					userInfo.put("Index", userIndex+"");
					
					List<String> list_path = Arrays.asList("Cart", "paymentHistory", "Point", "freeADayPassTime",
							"freeDaysPassTime", "freeWeekPassLastDay", "groupADayPassTime", "freeWeekPassPeriod");

					for (int i = 0; i < list_path.size(); i++) {
						userInfo.put(list_path.get(i), userInfo_list.get(i+2).get(userIndex));
						
					}
					
					// map 다음 프레임에 넘겨주기
					LoginSuccess_frame loginsuccess = new LoginSuccess_frame(userInfo);
					loginsuccess.main(new String[] {});
					frame.dispose();

				} else {
					JOptionPane.showMessageDialog(null, "ID와 PW를 확인해주세요", "login", JOptionPane.DEFAULT_OPTION);

				}

			}
		});
		btn_login.setBounds(332, 310, 95, 23);
		frame.getContentPane().add(btn_login);

		JButton btn_signup = new JButton("sign_up");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp_frame signup = new SignUp_frame();
				signup.main(new String[] {});
			}
		});
		btn_signup.setBounds(205, 391, 95, 23);
		frame.getContentPane().add(btn_signup);

	}
}
