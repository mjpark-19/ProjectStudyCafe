package client.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;




import client.keypad.KeyPadCheckOut;
import client.time.TimeCal;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	
	public static String id; // 정적 변수
	private Image img;
//	JPanel mainPanel;
	JPanel mainBtnPanel;
	
	JButton btnLogIn;
	JButton btnSignUp;
	JButton btnFindPW;
	JButton btnSeatMap;

	JLabel lblMainTime;

//	UserInfo userInfo;
	KeyPadCheckOut CkeyPad;
	ArrayList<Object> obj; // 패널 겹칠 때 버튼 감추기용

	int y = 150;
	int x = -140;
	
	
	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	
	public static MainFrame frame;
	
	public MainPanel(MainFrame frame) {
		
		MainPanel.frame = frame;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setFocusable(true); // false에서는 id칸에 focus가 가서 키패드 오픈 상태 - 삭제금지
		MainPanel(new ImageIcon("image\\mainimg.jpg").getImage());
		
		service = UserService.getInstance();
		try {
			
			mapInfo = service.findAll();
			list_ID = service.getIdList();
			list_PW = service.getPwList();
			
		} catch (FindException e) {
			e.printStackTrace();
		} 
		
		System.out.println("Main cart "+ mapInfo.get("01012341234").getCart());
		
		System.out.println("Main list_ID : " + list_ID); // DB에서 list_ID 받아지는지 확인


		labelSet(); // 레이블 관련 모음

		moveToPanel(); // 이동 관련 메소드 모음 - (순서 중요 new KeyPadCheckOut 생성자 위에 둘 것)




	}





	// 이동 관련 메소드
	public void moveToPanel() {
//		mainPanel.setLayout(null);

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
		add(btnLogIn);
		

		mainBtnPanel = new JPanel();
		mainBtnPanel.setBounds(0, 488, 684, 110);
		mainBtnPanel.setBackground(new Color(255, 255, 240));
		add(mainBtnPanel);
		mainBtnPanel.setLayout(null);

		// 회원가입
		btnSignUp = new JButton("회원가입");
		btnSignUp.setBounds(170, 10, 170, 90);
		mainBtnPanel.add(btnSignUp);
		btnSignUp.setBackground(Color.LIGHT_GRAY);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				
				SignUp signUp = new SignUp(lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
				MainPanel.frame.move(signUp);
				
				// 초기화
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("비밀번호를 입력하세요.");
			}
		});
		
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

				setVisible(false);

				if (MainPanel.id == null) { // 좌석 배치도의 용도를 결정한다. (null이면 좌석 선택, 이동 메뉴가 아닌 좌석 파악만 가능함)
					userInfo = null;
				}
				Seat seat = new Seat(userInfo, lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
				MainPanel.frame.move(seat);
				
			}
		});
		btnFindPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				FindPW findPw = new FindPW(lblMainTime); 
				MainPanel.frame.move(findPw);
				
				// 초기화
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("비밀번호를 입력하세요.");
			}
		});


		// KeyPad 클래스
		CkeyPad = new KeyPadCheckOut(this, mainBtnPanel, btnLogIn);

	}

	// 로그인 아이디 체크 및 고객 정보 객체 생성
	public void checkIDPW() {
		System.out.println("Main checkIDPW - ID :"+list_ID);
		System.out.println("Main checkIDPW - PW :"+list_PW);
		try {

			// 아이디 확인 및 PW 확인
			if ((list_ID.contains(CkeyPad.textID.getText()))
					&& (CkeyPad.textPW.getText().equals(list_PW.get(list_ID.indexOf(CkeyPad.textID.getText()))))) {

				if (CkeyPad.textID.getText().length() == 11) {
					MainPanel.id = CkeyPad.textID.getText();
				}

				userInfo = mapInfo.get(MainPanel.id); // 로그인한 아이디에 해당하는 value(=Userinfo 객체) 를 저장

				setVisible(false);

//				new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // 로그인 후 메뉴로 이동
				
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); 
				MainPanel.frame.move(afterLogin);

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
		add(lblMainTime);

		// 현재 시간 호출
		TimeCal Time = new TimeCal(lblMainTime);
		Thread thread1 = new Thread(Time);
		thread1.start();
		
		lblMainTime.setText(Time.currentTime());
	}
	private void MainPanel(Image img) {
		this.img = img;		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
}
