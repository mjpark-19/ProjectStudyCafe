package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PWchange_frame extends Db {

	private JFrame frame;
	private JTextField textField_id;
	private JTextField textField_pw1;
	private JTextField textField_pw2;
	private JTextField textField_valcheck;
	int valnum;

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
		frame.setBounds(100, 100, 700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 700, 800);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel_chagePW = new JLabel("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		lblNewLabel_chagePW.setForeground(Color.ORANGE);
		lblNewLabel_chagePW.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_chagePW.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_chagePW.setBounds(225, 56, 221, 60);
		panel.add(lblNewLabel_chagePW);

		textField_id = new JTextField();
		textField_id.setForeground(Color.LIGHT_GRAY);
		textField_id.setText("�ڵ��� ��ȣ�� �Է����ּ���");
		textField_id.setBounds(187, 136, 259, 30);
		panel.add(textField_id);
		textField_id.setColumns(10);

		textField_pw1 = new JTextField();
		textField_pw1.setForeground(Color.LIGHT_GRAY);
		textField_pw1.setText(
				"\uBCC0\uACBD\uD558\uC2E4 \uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textField_pw1.setBounds(187, 283, 259, 30);
		textField_pw1.setColumns(10);
		textField_pw1.setFocusable(false);
		panel.add(textField_pw1);

		textField_pw2 = new JTextField();
		textField_pw2.setText(
				"\uBCC0\uACBD\uD558\uC2E4 \uBE44\uBC00\uBC88\uD638\uB97C \uB2E4\uC2DC\uD55C\uBC88 \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textField_pw2.setForeground(Color.LIGHT_GRAY);
		textField_pw2.setColumns(10);
		textField_pw2.setBounds(187, 363, 259, 30);
		textField_pw2.setFocusable(false);
		panel.add(textField_pw2);

		JButton btn_submit = new JButton("\uBCC0\uACBD\uC644\uB8CC");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = textField_id.getText();
				String PW = textField_pw1.getText();
				
				if (!textField_pw1.getText().equals(textField_pw2.getText())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�", "��й�ȣ����", JOptionPane.DEFAULT_OPTION);
				} else {
					mapLoad.get(ID).setPW(PW); // map�� �ش� ���̵��� userinfo�� ������, pw�� �ʱ�ȭ�Ѵ�.
					System.out.println(mapLoad.get(ID)); // �ٲ� ȸ������ ��� (��й�ȣ �����)
					System.out.println(mapLoad);
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�", "��й�ȣ����", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		btn_submit.setBackground(Color.ORANGE);
		btn_submit.setBounds(305, 428, 141, 53);
		panel.add(btn_submit);

		JButton btn_valid = new JButton("\uC778\uC99D\uBC88\uD638\uBC1C\uC1A1");
		btn_valid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ID = textField_id.getText();

				if (list_ID.contains(ID)) {
					btn_valid.setText("������");
					valnum = createRannum(); // ������ valnum�� �־���
					System.out.println(valnum); // �ڵ����� �����ϴ� ��� ��¸� ��
				} else {
					JOptionPane.showMessageDialog(null, "���Ե��� ���� �ڵ��� ��ȣ�Դϴ�", "��й�ȣ����", JOptionPane.DEFAULT_OPTION);
				}

				
			}
		});
		btn_valid.setForeground(Color.WHITE);
		btn_valid.setBackground(Color.BLUE);
		btn_valid.setBounds(458, 135, 120, 30);
		panel.add(btn_valid);

		textField_valcheck = new JTextField();
		textField_valcheck.setText("\uC778\uC99D\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textField_valcheck.setForeground(Color.LIGHT_GRAY);
		textField_valcheck.setColumns(10);
		textField_valcheck.setBounds(187, 207, 259, 30);
		panel.add(textField_valcheck);

		JButton btn_valcheck = new JButton("\uD655\uC778");
		btn_valcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputnum = textField_valcheck.getText();

				if (inputnum.equals(valnum + "")) {
					JOptionPane.showMessageDialog(null, "Ȯ�εǾ����ϴ�", "��й�ȣ����", JOptionPane.DEFAULT_OPTION);
					textField_pw1.setFocusable(true);
					textField_pw2.setFocusable(true);
					textField_pw1.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "������ȣ�� ��ġ���� �ʽ��ϴ�", "��й�ȣ����", JOptionPane.DEFAULT_OPTION);
				}
			}

		});
		btn_valcheck.setForeground(Color.WHITE);
		btn_valcheck.setBackground(Color.BLUE);
		btn_valcheck.setBounds(458, 207, 120, 30);
		panel.add(btn_valcheck);

	}

	// ���� ���� �Լ� ����
	public int createRannum() {
		int rannum = (int) (Math.random() * 100000);
		return rannum;
	}
}
