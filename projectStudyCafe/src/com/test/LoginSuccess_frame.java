package com.test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class LoginSuccess_frame {

	private JFrame frame;
	static HashMap<String, String> UserInfo = new HashMap<String, String>();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSuccess_frame window = new LoginSuccess_frame();
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
	public LoginSuccess_frame() {
		initialize();
	}

	public LoginSuccess_frame(HashMap<String, String> userinfo) {
		this.UserInfo = userinfo;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JPanel currentPanel = null;

	private void initialize() {
		System.out.println(UserInfo);
		frame = new JFrame();
		frame.setBounds(0, 0, 606, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_userinfo = new JPanel();
		panel_userinfo.setBackground(Color.DARK_GRAY);
		panel_userinfo.setBounds(41, 46, 510, 481);
		frame.getContentPane().add(panel_userinfo);
		panel_userinfo.setLayout(null);
		
		JLabel lblNewLabel_personinfo = new JLabel("\uAC1C\uC778\uC815\uBCF4");
		lblNewLabel_personinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_personinfo.setForeground(Color.YELLOW);
		lblNewLabel_personinfo.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_personinfo.setBounds(212, 46, 86, 27);
		panel_userinfo.add(lblNewLabel_personinfo);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 79, 488, 40);
		panel_userinfo.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_id = new JLabel();
		lblNewLabel_id.setBounds(12, 10, 273, 20);
		lblNewLabel_id.setText(String.format("핸드폰번호 : 010-%s-%s", UserInfo.get("ID").substring(0, 4),
				UserInfo.get("ID").substring(4, 8)));
		panel.add(lblNewLabel_id);

		JButton btn_changePw = new JButton("pw변경");
		btn_changePw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btn_changePw.setForeground(Color.WHITE);
		btn_changePw.setBackground(new Color(0, 0, 255));
		btn_changePw.setBounds(394, 10, 82, 21);
		panel.add(btn_changePw);
		

		JLabel lblNewLabel_point = new JLabel("point : " + UserInfo.get("Point") + "P");
		lblNewLabel_point.setForeground(Color.RED);
		lblNewLabel_point.setBounds(252, 13, 125, 15);
		panel.add(lblNewLabel_point);

		JLabel lblNewLabel_option = new JLabel("보유상품");
		lblNewLabel_option.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_option.setForeground(Color.YELLOW);
		lblNewLabel_option.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_option.setBounds(212, 237, 86, 27);
		panel_userinfo.add(lblNewLabel_option);

		// table 추가
		String[] columnNames = { "상품명", "잔여시간", "잔여기간" };
		String[][] data = { {UserInfo.get("Cart"), UserInfo.get("freeADayPassTime"), "" } };

		JTable table = new JTable(data, columnNames);
		table.setBounds(39, 285, 431, 105);
		JScrollPane sp =new JScrollPane(table); 
		sp.setBounds(37, 284, 435, 100);

	    panel_userinfo.add(sp);     
	    
		
		JPanel panel_main = new JPanel();
		panel_main.setBackground(Color.DARK_GRAY);
		panel_main.setBounds(41, 46, 510, 481);
		frame.getContentPane().add(panel_main);
		panel_main.setBackground(Color.DARK_GRAY);
		panel_main.setBounds(41, 46, 510, 481);


		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-20, 256, 550, 19);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));

		lblNewLabel.setText(UserInfo.get("ID").substring(4, 8) + "회원님 환영합니다. 원하시는 메뉴를 선택해주세요");
		panel_main.add(lblNewLabel);

		// panel 설정
		currentPanel = panel_main;
		panel_main.setLayout(null);
		panel_userinfo.setVisible(false);
		
		JButton btn_userinfo = new JButton("내정보");
		btn_userinfo.setBounds(204, 396, 102, 27);
		btn_userinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPanel.setVisible(!currentPanel.isVisible());
				currentPanel = panel_userinfo;
				panel_userinfo.setVisible(!panel_userinfo.isVisible());
			}
		});
		btn_userinfo.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 15));
		panel_main.add(btn_userinfo);
	}
}
