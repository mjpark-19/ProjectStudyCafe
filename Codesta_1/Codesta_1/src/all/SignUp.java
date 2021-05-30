package all;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUp extends DB{

	// 변수 선언
	JFrame frame ;
	
	JPanel signUpPanel;
	JPanel mainPanel;
	JPanel topOrangePanel;
	
	JLabel lblMainTime;

	HashMap<String, UserInfo> mapInfo;
	UserInfo userInfo;
	KeyPadSignUp CkeyPad;
	
	
	// 생성자
	public SignUp(JFrame frame, JPanel mainPanel, UserInfo userInfo, HashMap<String, UserInfo> mapInfo, JLabel lblMainTime) {
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.userInfo = userInfo;
		this.mapInfo = mapInfo;
		this.lblMainTime = lblMainTime;
		initialize();
	}
	
	// 메소드 시작
	public void initialize() {
		signUpPanel = new JPanel();
		signUpPanel.setBounds(0, 0, 700, 800);                      // 중요
		frame.getContentPane().add(signUpPanel);
		signUpPanel.setBackground(Color.DARK_GRAY);
		signUpPanel.setLayout(null);
		signUpPanel.setFocusable(true); // false에서는 id칸에 focus가 가서 키패드 오픈 상태
		
		// 현재 시간 레이블 
		signUpPanel.add(lblMainTime);
		
		// 꾸미기 전
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setBounds(181, 65, 48, 14);
		signUpPanel.add(lblSignUp);
		
		// 꾸미기 전
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(192, 86, 309, 47);
		signUpPanel.add(lblNewLabel);

		
		JButton btnCertSend = new JButton("인증번호 발송");
		btnCertSend.setBounds(470, 258, 151, 47);
		btnCertSend.setBackground(Color.LIGHT_GRAY);
		signUpPanel.add(btnCertSend);
		
		
		JLabel lblCheckID = new JLabel("ID는 핸드폰 번호를 입력해주세요.");
		lblCheckID.setBounds(100, 150, 309, 47);
		signUpPanel.add(lblCheckID);
		lblCheckID.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		// 키패드 오픈
		CkeyPad = new KeyPadSignUp(frame, signUpPanel);
		CkeyPad.textOnPW.setVisible(true);
		
		
		JButton btn_checkID = new JButton("중복 확인");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkID(lblCheckID);
			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);

		signUpPanel.add(btn_checkID);

		
		// 상단 가로 긴 패널1
		JPanel topTitlePanel = new JPanel();
		topTitlePanel.setBounds(0, 0, 686, 45);
		signUpPanel.add(topTitlePanel);
		topTitlePanel.setLayout(null);

		// 상단 가로 긴 패널1
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 45, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		signUpPanel.add(topOrangePanel);
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
			
			if (!(list_ID.contains(CkeyPad.textID.getText()) )){
				

				if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {
					
					// 정적 변수로 ID 저장
					Main.id = CkeyPad.textID.getText();
					
					// 고객 정보 객체 생성
					userInfo = new UserInfo.Builder(Main.id, CkeyPad.textPW.getText()).build();
					
					// 맵에 할당
					mapInfo.put(Main.id, userInfo);
			         
			        // Db 클래스의 메소드로 text파일에 ID와 PW 생성
			        createDbInfo(CkeyPad.textID.getText(), CkeyPad.textPW.getText());
			         
			        // 로그인 후 메뉴로 이동
					new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !
					
					signUpPanel.setVisible(false);
					mainPanel.setVisible(false);
					
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
			JButton btnAfterLogin = new JButton("처음으로");
			btnAfterLogin.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e) {
					signUpPanel.setVisible(false);
					mainPanel.setVisible(true);
					mainPanel.add(lblMainTime);
					
				}
			});
			btnAfterLogin.setBounds(0, 0, 149, 48);
			btnAfterLogin.setBackground(Color.ORANGE);
			topOrangePanel.add(btnAfterLogin);
			
			
			JButton btnBefore = new JButton("이전");
			btnBefore.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e) {
					signUpPanel.setVisible(false);
					mainPanel.setVisible(true);
					mainPanel.add(lblMainTime);

				}
			});
			btnBefore.setBackground(Color.ORANGE);
			btnBefore.setBounds(0, 702, 343, 61);
			signUpPanel.add(btnBefore);

			
			
			JButton btnNext = new JButton("가입 완료");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					
					finalCheck();
					CkeyPad.textID.setText("010"); // createUserInfo()보다 아래에 배치할 것. Main.id 에러.
					CkeyPad.textPW.setText("");
					CkeyPad.textPW2.setText("");
					CkeyPad.textOnPW.setVisible(true);
					CkeyPad.textPW.setVisible(false);
					CkeyPad.textCert.setText("");

				}
			});
			btnNext.setBackground(Color.ORANGE);
			btnNext.setBounds(343, 702, 343, 61);
			signUpPanel.add(btnNext);
			
		}
}
