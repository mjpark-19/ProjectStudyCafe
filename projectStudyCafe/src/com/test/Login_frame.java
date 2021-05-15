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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;

public class Login_frame {

	private JFrame frame;

	ArrayList<ArrayList<String>> userInfo_list = new ArrayList<ArrayList<String>>();
	private JTextField textField_id;
	private JTextField textField_pw;

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
		initialize(); // frame¿Ã ø≠∏Æ∞‘ «‘

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Db.ReadInfo(userInfo_list);
		frame = new JFrame();
		frame.setBounds(0, 0, 1475, 830);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1475, 830);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setFocusable(true);

		JLabel lblNewLabel_1 = new JLabel("phone number");
		lblNewLabel_1.setFont(new Font("±º∏≤", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(195, 175, 107, 42);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setFont(new Font("±º∏≤", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(195, 254, 107, 42);
		panel.add(lblNewLabel_1_1);

		JButton btn_login = new JButton("login");
		btn_login.setBounds(1017, 190, 175, 100);
		panel.add(btn_login);

		textField_id = new JTextField();
		textField_id.setForeground(Color.GRAY);
		textField_id.setText("\uC544\uC774\uB514\uB97C \uC785\uB825\uD558\uC138\uC694");
		textField_id.setColumns(10);
		textField_id.setBounds(314, 187, 665, 30);
		panel.add(textField_id);

		textField_pw = new JTextField();
		textField_pw.setForeground(Color.GRAY);
		textField_pw.setText("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD558\uC138\uC694");
		textField_pw.setColumns(10);
		textField_pw.setBounds(314, 262, 665, 30);
		panel.add(textField_pw);

		JPanel panel_keypad = new JPanel();
		panel_keypad.setBackground(Color.BLACK);
		panel_keypad.setBounds(0, 390, 1282, 310);
		panel.add(panel_keypad);
		panel_keypad.setLayout(null);
		panel_keypad.setVisible(false);

		/**
		 * create keypad
		 */

		int[] btnPad = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn_num = new JButton(btnPad[i + (j * 3)] + "");
				btn_num.setFont(new Font("±º∏≤", Font.BOLD, 20));
				btn_num.setBackground(Color.BLACK);
				btn_num.setForeground(new Color(255, 200, 0));
				btn_num.setBounds(200 + i * 200, 30 + j * 60, 173, 39);
				btn_num.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (textField_id.isFocusOwner()) {
							textField_id.setText(textField_id.getText() + e.getActionCommand());
						} else {
							textField_pw.setText(textField_pw.getText() + e.getActionCommand());
						}

					}
				});
				panel_keypad.add(btn_num);
			}
		}

		JButton btn_0 = new JButton("0");
		btn_0.setForeground(Color.ORANGE);
		btn_0.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_0.setBackground(Color.BLACK);
		btn_0.setBounds(200 + 1 * 200, 30 + 3 * 60, 173, 39);
		btn_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_id.isFocusOwner()) {
					textField_id.setText(textField_id.getText() + e.getActionCommand());
				} else {
					textField_pw.setText(textField_pw.getText() + e.getActionCommand());
				}

			}
		});
		panel_keypad.add(btn_0);

		JButton btn_010 = new JButton("010");
		btn_010.setForeground(Color.ORANGE);
		btn_010.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_010.setBackground(Color.BLACK);
		btn_010.setBounds(200 + 0 * 200, 30 + 3 * 60, 173, 39);
		btn_010.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_id.isFocusOwner()) {
					textField_id.setText(textField_id.getText() + e.getActionCommand());
				} else {
					textField_pw.setText(textField_pw.getText() + e.getActionCommand());
				}

			}
		});
		panel_keypad.add(btn_010);

		JButton btn_00 = new JButton("00");
		btn_00.setForeground(Color.ORANGE);
		btn_00.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_00.setBackground(Color.BLACK);
		btn_00.setBounds(200 + 2 * 200, 30 + 3 * 60, 173, 39);
		btn_00.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_id.isFocusOwner()) {
					textField_id.setText(textField_id.getText() + e.getActionCommand());
				} else {
					textField_pw.setText(textField_pw.getText() + e.getActionCommand());
				}

			}
		});
		panel_keypad.add(btn_00);

		JButton btn_back = new JButton("BACK");
		btn_back.setForeground(Color.ORANGE);
		btn_back.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_back.setBackground(Color.BLACK);
		btn_back.setBounds(200 + 3 * 200, 30 + 0 * 60, 173, 39);
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_id.isFocusOwner()) {
						textField_id.setText(textField_id.getText().substring(0, textField_id.getText().length() - 1));
					} else {
						textField_pw.setText(textField_pw.getText().substring(0, textField_pw.getText().length() - 1));
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		panel_keypad.add(btn_back);

		JButton btn_reset = new JButton("RESET");
		btn_reset.setForeground(Color.ORANGE);
		btn_reset.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_reset.setBackground(Color.BLACK);
		btn_reset.setBounds(200 + 3 * 200, 30 + 1 * 60, 173, 39);
		btn_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_id.isFocusOwner()) {
					textField_id.setText("");
				} else {
					textField_pw.setText("");
				}

			}
		});
		panel_keypad.add(btn_reset);

		JButton btn_close = new JButton("CLOSE");
		btn_close.setForeground(Color.ORANGE);
		btn_close.setFont(new Font("±º∏≤", Font.BOLD, 20));
		btn_close.setBackground(Color.BLACK);
		btn_close.setBounds(200 + 3 * 200, 30 + 2 * 60, 173, 39);
		btn_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_keypad.setVisible(false);

			}
		});
		panel_keypad.add(btn_close);

		// textfield_id, pw ≈¨∏Ø Ω√ keypad load

		textField_id.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				panel_keypad.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textField_id.isFocusOwner() == true) {
					panel_keypad.setVisible(true);
				}
					
				

			}
		});

		textField_pw.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				panel_keypad.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
				panel_keypad.setVisible(true);

			}
		});

	}
}
