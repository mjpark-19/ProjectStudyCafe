package client.keypad;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class KeyPadSignUp {
	
	
	protected JFrame frame;

	protected JPanel backPanel;
	protected JPanel keypadPanel;
	protected JPanel checkOutPanel;
	protected JPanel checkOutHeadPanel;
	
	public JTextField textCert;
	public JTextField textID;
	public JTextField textOnPW;
	public JPasswordField textPW;
	public JPasswordField textPW2;
	public JTextField textCheckOutID; // 인증번호 4자리

	
	protected JButton btnSignUp;
	protected JButton btnLogIn;
	protected JButton btnFinish;
	protected JButton btnCheckOutClose;

	protected JLabel lblCheckOutHead;

	int keyX = 90;
	
	public KeyPadSignUp(JPanel backPanel) {
		this.backPanel = backPanel;
		
		
		// 키패드 관련
		keyPadPanel();
		
		// ID, PW, 인증번호 입력칸 관련
		textFieldSet();
		
	}
	
	public void textFieldSet() {
		
		
		textCert = new JTextField();
		textCert.setFont(new Font("굴림", Font.BOLD, 15));
//		textOnPW.setBounds(200, 262, 270, 40);
//		textID.setBounds(200, 187, 270, 40);
		textCert.setBounds(91, 258, 379, 47);
		textCert.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {
				keypadPanel.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		textCert.setForeground(Color.GRAY);
		textCert.setColumns(12);

		
		
		textID = new JTextField();
		textID.setFont(new Font("굴림", Font.BOLD, 15));
//		textOnPW.setBounds(200, 262, 270, 40);
//		textID.setBounds(200, 187, 270, 40);
		textID.setBounds(91, 185, 379, 47);
		
		textID.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				keypadPanel.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		textID.setForeground(Color.GRAY);
		textID.setText("010");
		textID.setColumns(12);
//		backPanel.add(textID);
		
		
		textOnPW = new JTextField();
		textOnPW.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				textPW.requestFocus();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {	
				textPW.setText("");
				textOnPW.setVisible(false);
				textPW.setVisible(true);
				keypadPanel.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {			
			}
			@Override
			public void mouseEntered(MouseEvent e) {			
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});
		textOnPW.setText("비밀번호는 4자리입니다.");
//		textOnPW.setBounds(200, 262, 270, 40);
		textOnPW.setBounds(91, 337, 247, 47);
//		backPanel.add(textOnPW);
		
		
		textPW = new JPasswordField();
		textPW.setVisible(false);
		textPW.setFont(new Font("굴림", Font.BOLD, 15));
		textPW.setForeground(Color.GRAY);
//		textPW.setBounds(200, 262, 270, 40);
		textPW.setBounds(91, 337, 247, 47);

		textPW.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {			
			}

			@Override
			public void mousePressed(MouseEvent e) {
				keypadPanel.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {		
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {			
			}

			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});
		textPW.setColumns(6);
//		backPanel.add(textPW);

		
		textPW2 = new JPasswordField();
		textPW2.setColumns(10);
		textPW2.setBounds(367, 337, 253, 47);
		backPanel.add(textPW2);
		
//		textPW2.setVisible(false);
		textPW2.setFont(new Font("굴림", Font.BOLD, 15));
		textPW2.setForeground(Color.GRAY);
//		textPW.setBounds(200, 262, 270, 40);
//		textPW2.setBounds(91, 337, 247, 47);

		textPW2.addMouseListener(new MouseListener() {            // close 후 재클릭 시 필요한 코드 - 삭제 금지

			@Override
			public void mouseReleased(MouseEvent e) {			
			}

			@Override
			public void mousePressed(MouseEvent e) {
				keypadPanel.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {		
				System.out.println("exit");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {			
			}

			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});


		// textID, pw 클릭 시 keypad load
		textID.addFocusListener(new FocusListener() { // id 입력하세요. - 클릭시 -> 010 자동 뜨게

			@Override
			public void focusLost(FocusEvent e) {
				keypadPanel.setVisible(false);

			}

			@Override
			public void focusGained(FocusEvent e) {
//				textID.setFocusable(true);
				if (textID.isFocusOwner() == true) {
					keypadPanel.setVisible(true);

				}
			}
		});

		textPW.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				keypadPanel.setVisible(false);
			}

			@Override
			public void focusGained(FocusEvent e) {

				if (textPW.isFocusOwner() == true) {
					keypadPanel.setVisible(true);
				}
			}
		});
		
		
		textPW2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				keypadPanel.setVisible(false);
			}

			@Override
			public void focusGained(FocusEvent e) {

				if (textPW2.isFocusOwner() == true) {
					keypadPanel.setVisible(true);
				}
			}
		});
		
		// 인증번호
		textCert.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				keypadPanel.setVisible(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
//				keypadPanel.setVisible(true);

				if (textCert.isFocusOwner() == true) {
					keypadPanel.setVisible(true);
				}
			}
		});
		backPanel.add(textID);
		backPanel.add(textOnPW);
		backPanel.add(textPW);
		backPanel.add(textCert);


	}

	// 키패드 관련 메소드
	public void keyPadPanel() {
		keypadPanel = new JPanel();
//		keypadPanel.setBounds(100, 480, 500, 310);
		keypadPanel.setBounds(0, 430, 700, 270);
		keypadPanel.setBackground(Color.BLACK);
		keypadPanel.setLayout(null);
		keypadPanel.setVisible(false);
		backPanel.add(keypadPanel);

		/**
		 * create keypad
		 */

		int[] btnPad = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton btn_num = new JButton(btnPad[i + (j * 3)] + "");
				btn_num.setFont(new Font("굴림", Font.BOLD, 20));
				btn_num.setBackground(Color.BLACK);
				btn_num.setForeground(new Color(255, 200, 0));
				btn_num.setBounds(200 + i * 200, 30 + j * 60, 173, 39);
				btn_num.setBounds(50 + i * 100+keyX , 30 + j * 60, 80, 39);

				btn_num.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (textID.isFocusOwner()) {
							textID.setText(textID.getText() + e.getActionCommand());   // 기존 코드
						} else if (textCert.isFocusOwner()){
							textCert.setText(textCert.getText() + e.getActionCommand());
						} else if (textPW.isFocusOwner()){
							textPW.setText(textPW.getText() + e.getActionCommand());
						} else {					
							textPW2.setText(textPW2.getText() + e.getActionCommand());
						}
					}
				});
				keypadPanel.add(btn_num);
			}
		}

		JButton btn_0 = new JButton("0");
		btn_0.setForeground(Color.ORANGE);
		btn_0.setFont(new Font("굴림", Font.BOLD, 20));
		btn_0.setBackground(Color.BLACK);
		btn_0.setBounds(200 + 1 * 200, 30 + 3 * 60, 173, 39);
		btn_0.setBounds(50 + 1 * 100 +keyX, 30 + 3 * 60, 80, 39);

		btn_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				if (textID.isFocusOwner()) {
					textID.setText(textID.getText() + e.getActionCommand());
				} else if (textCert.isFocusOwner()){
					textCert.setText(textCert.getText() + e.getActionCommand());
				} else if (textPW.isFocusOwner()){
					textPW.setText(textPW.getText() + e.getActionCommand());
				} else {					
					textPW2.setText(textPW2.getText() + e.getActionCommand());
				}

			}
		});
		keypadPanel.add(btn_0);


		JButton btn_back = new JButton("BACK");
		btn_back.setForeground(Color.ORANGE);
		btn_back.setFont(new Font("굴림", Font.BOLD, 20));
		btn_back.setBackground(Color.BLACK);
		btn_back.setBounds(200 + 3 * 200, 30 + 0 * 60, 173, 39);
		btn_back.setBounds(350+keyX, 30 , 120, 39);

		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { /// try문은 왜??
				try {
		
					if (textID.isFocusOwner()) {
						textID.setText(textID.getText().substring(0, textID.getText().length() - 1));
					} else if (textCert.isFocusOwner()){
						textCert.setText(textCert.getText().substring(0, textCert.getText().length() - 1));
					} else if (textPW.isFocusOwner()){
						textPW.setText(textPW.getText().substring(0, textPW.getText().length() - 1));
					} else {					
						textPW2.setText(textPW2.getText().substring(0, textPW2.getText().length() - 1));
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		keypadPanel.add(btn_back);

		JButton btn_reset = new JButton("RESET");
		btn_reset.setForeground(Color.ORANGE);
		btn_reset.setFont(new Font("굴림", Font.BOLD, 20));
		btn_reset.setBackground(Color.BLACK);
		btn_reset.setBounds(200 + 3 * 200, 30 + 1 * 60, 173, 39);
		btn_reset.setBounds(350+keyX , 90, 120, 39);

		btn_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textID.isFocusOwner()) {
					textID.setText("010");
				} else if (textCert.isFocusOwner()){
					textCert.setText("");
				} else if (textPW.isFocusOwner()){
					textPW.setText("");
				} else {					
					textPW2.setText("");
				}
				
			}
		});
		keypadPanel.add(btn_reset);

		JButton btn_close = new JButton("CLOSE");
		btn_close.setForeground(Color.ORANGE);
		btn_close.setFont(new Font("굴림", Font.BOLD, 20));
		btn_close.setBackground(Color.BLACK);
//		btn_close.setBounds(200 + 3 * 200, 30 + 1 * 60, 173, 39);
		btn_close.setBounds(350+keyX, 150, 120, 39);

		btn_close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn_close에서 setVisible(false) 작동");

				keypadPanel.setVisible(false);
			}
		});
		keypadPanel.add(btn_close);


	}


}
