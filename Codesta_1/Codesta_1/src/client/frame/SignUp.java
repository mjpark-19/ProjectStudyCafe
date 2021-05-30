package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;


import client.keypad.KeyPadSignUp;

public class SignUp extends JPanel{

	// 변수 선언
	JFrame frame ;
	
	JPanel signUpPanel;
	JPanel mainPanel;
	JPanel topOrangePanel;
	
	JLabel lblMainTime;

	KeyPadSignUp CkeyPad;
	

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	
	
	// 생성자
	public SignUp(JLabel lblMainTime) {

		this.lblMainTime = lblMainTime;
		
		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
			list_ID = service.getIdList();
			list_PW = service.getPwList();
		} catch (FindException e) {
			e.printStackTrace();
		} 
//		
		initialize();
	}
	
	// 메소드 시작
	public void initialize() {
		setBackground(Color.GRAY);
		setLayout(null);
		
		// 현재 시간 레이블 
		add(lblMainTime);
		
		// 꾸미기 전
		JLabel lblSignUp = new JLabel("회원가입");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setBounds(130, 15, 150, 20);
		add(lblSignUp);


		
		JButton btnCertSend = new JButton("인증번호 발송");
		btnCertSend.setBounds(470, 258, 151, 47);
		btnCertSend.setBackground(Color.LIGHT_GRAY);
		add(btnCertSend);
		
		
		JLabel lblCheckID = new JLabel("ID는 핸드폰 번호를 입력해주세요.");
		lblCheckID.setBounds(100, 150, 309, 47);
		add(lblCheckID);
		lblCheckID.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		// 키패드 오픈
		CkeyPad = new KeyPadSignUp(this);
		CkeyPad.textOnPW.setVisible(true);
		
		
		JButton btn_checkID = new JButton("중복 확인");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkID(lblCheckID);
			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);

		add(btn_checkID);

		// 상단 가로 긴 패널1
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 0, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		add(topOrangePanel);
		topOrangePanel.setLayout(null);
		
		// 이동 관련 메소드
		moveToPanel();
	}
	
	// ID, PW 최종 체크
	public void finalCheck() {
		
			if (CkeyPad.textID.getText().length() != 11) {
				JOptionPane.showMessageDialog(null, "다시 한번 핸드폰 번호를 확인해주세요.", "signup", JOptionPane.DEFAULT_OPTION);
				return;
			}

//			for (String string : service.list_ID) {
//				System.out.println("list_ID : "+ string);
//			}
			
			if (!(list_ID.contains(CkeyPad.textID.getText()) )){
				

				if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {
					
					// 정적 변수로 ID 저장
					MainPanel.id = CkeyPad.textID.getText();
					
					// 고객 정보 객체 생성
					userInfo = new User.Builder(MainPanel.id, CkeyPad.textPW.getText()).build();
					
					// 맵에 할당
					mapInfo.put(MainPanel.id, userInfo);
			         
					
			        // Db 클래스의 메소드로 text파일에 ID와 PW 생성
			        try {
						service.singUp(CkeyPad.textID.getText(), CkeyPad.textPW.getText());
					} catch (FindException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			         
			        // 로그인 후 메뉴로 이동
//					new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !
			        setVisible(false);
					
			        AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); // 좌석 배치도, 좌석 이동, 선택 관련
					MainPanel.frame.move(afterLogin);
			        
			
					
					
					
				} else {
			         JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요", "login", JOptionPane.DEFAULT_OPTION);						}
					
		     } else {
					JOptionPane.showMessageDialog(null, "이미 가입되어 있는 ID입니다", "signup", JOptionPane.DEFAULT_OPTION);
					CkeyPad.textID.setText("010");
		     }
			
			
	}
	
	// ID 중복체크를 위한 함수
	public void checkID(JLabel lblCheckID) {
		String ID = CkeyPad.textID.getText();
		String pattern = "^\\d{11}$"; // 정규표현식 사용
		Boolean regex = Pattern.matches(pattern, ID);

		// 중복확인버튼 클릭

		if (!regex) {
			lblCheckID.setText("다시 한번 핸드폰 번호를 확인해주세요.");
			CkeyPad.textID.setText("010");
		} else {
			try {
				if (list_ID.contains(CkeyPad.textID.getText())) { // map의 key값으로 입력받은 ID가 존재하는 경우
					JOptionPane.showMessageDialog(null, "이미 가입되어 있는 ID입니다", "signup", JOptionPane.DEFAULT_OPTION);
					lblCheckID.setText("");
				} else {
					lblCheckID.setText("사용 가능한 ID입니다");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

	// 이동 관련 메소드
	public void moveToPanel() {

			// [처음으로], [이전] 버튼은 묶어서 클래스 빼기
			JButton btnHome = new JButton("처음으로");
			btnHome.addActionListener( new MyActionListener());
			btnHome.setBounds(0, 0, 149, 48);
			btnHome.setBackground(Color.ORANGE);
			topOrangePanel.add(btnHome);
			
			
			JButton btnBefore = new JButton("이전");
			btnBefore.addActionListener( new MyActionListener());
			btnBefore.setBackground(Color.ORANGE);
			btnBefore.setBounds(0, 702, 343, 61);
			add(btnBefore);

			
			
			JButton btnNext = new JButton("가입 완료");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
				
					
					finalCheck();
					reset();
					
					try {
						service.backUp(userInfo);
					} catch (FindException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnNext.setBackground(Color.ORANGE);
			btnNext.setBounds(343, 702, 343, 61);
			add(btnNext);
			
		}
	
	public void reset() {
		
		CkeyPad.textID.setText("010"); // createUserInfo()보다 아래에 배치할 것. Main.id 에러.
		CkeyPad.textPW.setText("");
		CkeyPad.textPW2.setText("");
		CkeyPad.textOnPW.setVisible(true);
		CkeyPad.textPW.setVisible(false);
		CkeyPad.textCert.setText("");
	}
	
	
	public class MyActionListener implements  ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
			
			MainPanel mainPanel = new MainPanel(MainPanel.frame); 
			MainPanel.frame.move(mainPanel);
						
		}
	}
}
