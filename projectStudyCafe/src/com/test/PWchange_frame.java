package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PWchange_frame {

	private JFrame frame;
	private JTextField textField_pw1;
	private JTextField textField_pw2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PWchange_frame window = new PWchange_frame();
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
	public PWchange_frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 483);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_chagePW = new JLabel("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		lblNewLabel_chagePW.setForeground(Color.ORANGE);
		lblNewLabel_chagePW.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_chagePW.setFont(new Font("±¼¸²", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_chagePW.setBounds(121, 33, 221, 60);
		frame.getContentPane().add(lblNewLabel_chagePW);
		
		textField_pw1 = new JTextField();
		textField_pw1.setForeground(Color.LIGHT_GRAY);
		textField_pw1.setText("\uBCC0\uACBD\uD558\uC2E4 \uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textField_pw1.setBounds(112, 244, 259, 30);
		frame.getContentPane().add(textField_pw1);
		textField_pw1.setColumns(10);
		
		textField_pw2 = new JTextField();
		textField_pw2.setText("\uBCC0\uACBD\uD558\uC2E4 \uBE44\uBC00\uBC88\uD638\uB97C \uB2E4\uC2DC\uD55C\uBC88 \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textField_pw2.setForeground(Color.LIGHT_GRAY);
		textField_pw2.setColumns(10);
		textField_pw2.setBounds(112, 284, 259, 30);
		frame.getContentPane().add(textField_pw2);
		
		JButton btnNewButton = new JButton("\uBCC0\uACBD\uC644\uB8CC");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(294, 360, 141, 53);
		frame.getContentPane().add(btnNewButton);
	}
}
