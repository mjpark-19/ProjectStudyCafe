package all;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class Main extends DB {

	static String id; // 정적 변수

	JFrame frame;

	JPanel mainPanel;
	JPanel mainBtnPanel;
	
	JButton btnLogIn;
	JButton btnSignUp;
	JButton btnFindPW;
	JButton btnSeatMap;

	JLabel lblMainTime;

	UserInfo userInfo;
	KeyPadCheckOut CkeyPad;
	ArrayList<Object> obj; // 패널 겹칠 때 버튼 감추기용

	int y = 150;
	int x = -140;
	// private HashMap<String, UserInfo> mapInfo = null;
//	HashMap<String, UserInfo> mapInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Main window = new Main();
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
	public Main() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 700, 800); //
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBounds(0, 0, 700, 800);

		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true); // false에서는 id칸에 focus가 가서 키패드 오픈 상태 - 삭제금지

		System.out.println("Main list_ID : " + list_ID); // DB에서 list_ID 받아지는지 확인

		labelSet(); // 레이블 관련 모음

		moveToPanel(); // 이동 관련 메소드 모음 - (순서 중요 new KeyPadCheckOut 생성자 위에 둘 것)

		tempMethod(); // 임시 테스트용 mapInfo 확인용 메소드 - map To print

		// 꾸미기용
		JPanel panel = new JPanel();
		panel.setBounds(0, 727, 684, 36);
		mainPanel.add(panel);
		panel.setLayout(null);

		// 꾸미기용
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(521, 10, 57, 15);
		panel.add(lblNewLabel);


	}

	// 이동 관련 메소드
	public void moveToPanel() {
		mainPanel.setLayout(null);

		// 로그인
		btnLogIn = new JButton("로그인(Login)");
		btnLogIn.setBackground(Color.ORANGE);
		btnLogIn.setBounds(500 + x, 185 + y + 10, 270, 120 - 10);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 11자리 확인
				if (CkeyPad.textID.getText().length() != 11) {
					JOptionPane.showMessageDialog(null, "ID는 핸드폰 번호입니다.", "login", JOptionPane.DEFAULT_OPTION);
					return;
				}

				// 로그인 아이디 체크 및 고객 정보 객체 생성
				checkIDPW();

				// 초기화
				CkeyPad.textID.setText("010");
				CkeyPad.textPW.setVisible(false);
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("비밀번호를 입력하세요.");
				mainBtnPanel.setVisible(true);
			}
		});
		mainPanel.add(btnLogIn);
		

		mainBtnPanel = new JPanel();
		mainBtnPanel.setBounds(0, 488, 684, 110);
		mainBtnPanel.setBackground(Color.DARK_GRAY);
		mainPanel.add(mainBtnPanel);
		mainBtnPanel.setLayout(null);

		// 회원가입
		btnSignUp = new JButton("회원가입");
		btnSignUp.setBounds(170, 10, 170, 90);
		mainBtnPanel.add(btnSignUp);
		btnSignUp.setBackground(Color.LIGHT_GRAY);

		// 비밀번호 찾기
		btnFindPW = new JButton("비밀번호 찾기");
		btnFindPW.setBounds(342, 10, 170, 90);
		mainBtnPanel.add(btnFindPW);
		btnFindPW.setBackground(Color.LIGHT_GRAY);

		btnSeatMap = new JButton("실시간좌석현황");
		btnSeatMap.setBounds(514, 10, 170, 90);
		mainBtnPanel.add(btnSeatMap);
		btnSeatMap.setBackground(Color.LIGHT_GRAY);
		btnSeatMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);

				if (Main.id == null) { // 좌석 배치도의 용도를 결정한다. (null이면 좌석 선택, 이동 메뉴가 아닌 좌석 파악만 가능함)
					userInfo = null;
				}
				new Seat(frame, mainPanel, userInfo); // 좌석 배치도, 좌석 이동, 선택 관련
			}
		});
		btnFindPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);
				new FindPW(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);

				// 초기화
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("비밀번호를 입력하세요.");
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);
				new SignUp(frame, mainPanel, userInfo, mapInfo, lblMainTime);

				// 초기화
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("비밀번호를 입력하세요.");
			}
		});

		// KeyPad 클래스
		CkeyPad = new KeyPadCheckOut(frame, mainPanel, userInfo, mapInfo, mainBtnPanel, btnLogIn);

	}

	// 로그인 아이디 체크 및 고객 정보 객체 생성
	public void checkIDPW() {
		System.out.println(list_ID);
		try {

			// 아이디 확인 및 PW 확인
			if ((list_ID.contains(CkeyPad.textID.getText()))
					&& (CkeyPad.textPW.getText().equals(list_PW.get(list_ID.indexOf(CkeyPad.textID.getText()))))) {

				if (CkeyPad.textID.getText().length() == 11) {
					Main.id = CkeyPad.textID.getText();
				}

				userInfo = mapInfo.get(Main.id); // 로그인한 아이디에 해당하는 value(=Userinfo 객체) 를 저장

				mainPanel.setVisible(false);

				new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // 로그인 후 메뉴로 이동

			} else {
				JOptionPane.showMessageDialog(null, "ID와 PW를 확인해주세요", "login", JOptionPane.DEFAULT_OPTION);
			}

			// 초기화
			CkeyPad.textID.setText("");
			CkeyPad.textPW.setText("");

		} finally {

		}
	}

	// 레이블 관련 모음
	public void labelSet() {

		// 현재 시간 표시
		lblMainTime = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("굴림", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblMainTime.setBounds(536, 0, 150, 34);
		lblMainTime.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblMainTime);

		// 현재 시간 호출
		TimeCal Time = new TimeCal(lblMainTime);
		Thread thread1 = new Thread(Time);
		thread1.start();
//		

	}

	public void tempMethod() {
		JButton btnMapToPrint = new JButton("mapToPrint");
		btnMapToPrint.setBounds(589, 740, 97, 23);
		btnMapToPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mapInfo : " + mapInfo + " Main - mapToPrint");
			}
		});
		mainPanel.add(btnMapToPrint);

	}
}
