package all;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FindPW {

	// 변수 선언
	JFrame frame;

	JPanel findPWPanel;
	JPanel inputPanel;
	JPanel mainPanel;
	JPanel topOrangePanel;

	JButton btn_checkID;
	JButton btnCert;

	JLabel lblCheckID;
	JLabel lblInputPW;
	JLabel lblMainTime;

	HashMap<String, UserInfo> mapInfo;
	UserInfo userInfo;
	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	KeyPadSignUp CkeyPad;

	// 생성자
	public FindPW(JFrame frame, JPanel mainPanel, HashMap<String, UserInfo> mapInfo, UserInfo userInfo,
			ArrayList<String> list_ID, ArrayList<String> list_PW, JLabel lblMainTime) {
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.mapInfo = mapInfo;
		this.userInfo = userInfo;
		this.list_ID = list_ID;
		this.list_PW = list_PW;
		this.lblMainTime = lblMainTime;

		initialize();
	}

	// 메소드 시작
	public void initialize() {
		findPWPanel = new JPanel();
		findPWPanel.setBounds(0, 0, 700, 800); // 중요
		frame.getContentPane().add(findPWPanel);
		findPWPanel.setBackground(Color.DARK_GRAY);

		findPWPanel.setLayout(null);
		findPWPanel.setFocusable(true); // false에서는 id칸에 focus가 가서 키패드 오픈 상태

		inputPanel = new JPanel();
		inputPanel.setBounds(0, 130, 700, 500); // 중요
		frame.getContentPane().add(findPWPanel);
		inputPanel.setBackground(Color.GRAY);
		inputPanel.setLayout(null);
		inputPanel.setVisible(false);
		findPWPanel.add(inputPanel);

		findPWPanel.add(lblMainTime);

		// 제목 - 꾸미지 않은 상태
		JLabel lbl = new JLabel("Sign Up");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(181, 65, 48, 14);
		findPWPanel.add(lbl);

		// 꾸미지 않은 상태
		btnCert = new JButton("인증번호 발송");
		btnCert.setBackground(Color.LIGHT_GRAY);
		btnCert.setBounds(470, 258, 151, 47);
		findPWPanel.add(btnCert);
//		inputPanel.add(btnCert);

		lblCheckID = new JLabel("ID는 핸드폰 번호를 입력해주세요.");
		lblCheckID.setBounds(100, 150, 309, 47);
		findPWPanel.add(lblCheckID);
//		inputPanel.add(lblCheckID);
		lblCheckID.setFont(new Font("굴림", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		lblInputPW = new JLabel("새로운 비밀번호 4자리를 입력해주세요.");
		lblInputPW.setBounds(100, 300, 309, 47);
		findPWPanel.add(lblInputPW);
//		inputPanel.add(lblInputPW);

		lblInputPW.setFont(new Font("굴림", Font.PLAIN, 15));
		lblInputPW.setForeground(Color.WHITE);

		btn_checkID = new JButton("가입 확인");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ID를 체크하는 메소드
				checkID(lblCheckID);

			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);
		findPWPanel.add(btn_checkID);
//		inputPanel.add(btn_checkID);

		// 키패드 클래스 가져오기
		CkeyPad = new KeyPadSignUp(frame, findPWPanel);
		CkeyPad.textOnPW.setVisible(true);

		// 상단 가로 긴 패널1(흰색)
		JPanel topTitlePanel = new JPanel();
		topTitlePanel.setBounds(0, 0, 686, 45);
		findPWPanel.add(topTitlePanel);
		topTitlePanel.setLayout(null);

		// 상단 가로 긴 패널1(노랑)
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 45, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		findPWPanel.add(topOrangePanel);
		topOrangePanel.setLayout(null);

		// 페이지 이동 관련 메소드
		moveToPanel();
	}

	// ID 중복체크를 위한 함수
	public void checkID(JLabel lblCheckID) {
		String ID = CkeyPad.textID.getText();
		String pattern = "^\\d{11}$"; // 정규표현식 사용
		Boolean regex = Pattern.matches(pattern, ID);

		// 중복확인 버튼 클릭
		System.out.println("FindPW list_ID : " + list_ID);
		if (!regex) {
			lblCheckID.setText("다시 한번 핸드폰 번호를 확인해주세요.");
			CkeyPad.textID.setText("010");
		} else {
			try {
				if (list_ID.contains(CkeyPad.textID.getText())) { // map의 key값으로 입력받은 ID가 존재하는 경우
					JOptionPane.showMessageDialog(null, "가입되어 있는 ID입니다", "비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
					lblCheckID.setText("");
				} else {
					lblCheckID.setText("존재하지 않는 ID입니다");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

	public void moveToPanel() {

		JButton btnAfterLogin = new JButton("처음으로");
		btnAfterLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findPWPanel.setVisible(false);
				new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !

			}
		});
		btnAfterLogin.setBounds(0, 0, 149, 48);
		btnAfterLogin.setBackground(Color.ORANGE);
		topOrangePanel.add(btnAfterLogin);

		JButton btnBefore = new JButton("이전");
		btnBefore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (Main.id != null) {
					findPWPanel.setVisible(false);
					new MyInfo(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);
				} else {
					findPWPanel.setVisible(false);
					mainPanel.setVisible(true);
					mainPanel.add(lblMainTime);
				}

			}
		});
		btnBefore.setBackground(Color.ORANGE);
		btnBefore.setBounds(0, 702, 343, 61);
		findPWPanel.add(btnBefore);

		JButton btnNext = new JButton("변경 완료");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				finalCheck();

				CkeyPad.textPW.setVisible(false);
				CkeyPad.textID.setText("010"); // finalCheck()보다 아래에 배치할 것. Main.id 에러.
				CkeyPad.textPW.setText("");
				CkeyPad.textPW2.setText("");
				CkeyPad.textCert.setText("");
			}
		});
		btnNext.setBackground(Color.ORANGE);
		btnNext.setBounds(343, 702, 343, 61);
		findPWPanel.add(btnNext);

	}

	// 최종 비밀번호 변경
	public void finalCheck() {

		if (CkeyPad.textID.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "다시 한번 핸드폰 번호를 확인해주세요.", "비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
			return;
		}

		if ((list_ID.contains(CkeyPad.textID.getText()))) {

			if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {

				Main.id = CkeyPad.textID.getText();
				userInfo = mapInfo.get(Main.id);

				System.out.println(userInfo);

				list_PW.set(list_ID.indexOf(Main.id), CkeyPad.textPW.getText());

				// Db 클래스의 메소드로 text파일에 PW 저장
//			        createDbInfo(CkeyPad.textID.getText(), CkeyPad.textPW.getText()); // 회원가입용 메소드

				// 최초 좌석 선택 후 확인 의사 질문(작은 패널)
				JPanel changePwDonePanel = new JPanel();
				changePwDonePanel.setVisible(!changePwDonePanel.isVisible());
				changePWDone(changePwDonePanel);

			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요", "비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
			}

		} else {
			JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다", "비밀번호 찾기", JOptionPane.DEFAULT_OPTION);
			CkeyPad.textID.setText("010");
		}
	}

	public void changePWDone(JPanel changePwDonePanel) {

		System.out.println("비밀번호변경 완료 안내 패널 진입");
		changePwDonePanel.setBounds(185, 191, 329, 189);
//		changePwDonePanel.setBounds(50, 130, 600, 300);
		findPWPanel.add(changePwDonePanel);
		changePwDonePanel.setLayout(null);
		changePwDonePanel.setVisible(true);

		// 완료 패널을 보이기 위해 뒤에 텍스트, ID, PW 등은 가림
		lblCheckID.setVisible(false);
		lblInputPW.setVisible(false);
		btn_checkID.setVisible(false);
		btnCert.setVisible(false);

		CkeyPad.textID.setVisible(false);
		CkeyPad.textPW.setVisible(false);
		CkeyPad.textPW2.setVisible(false);
		CkeyPad.textOnPW.setVisible(false);
		CkeyPad.textCert.setVisible(false);

		JButton btnCancel = new JButton("닫기");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Main.id != null) {
					findPWPanel.setVisible(false);
					new MyInfo(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);
				} else {
					changePwDonePanel.setVisible(false);
					findPWPanel.setVisible(false);
					inputPanel.setVisible(false);
					new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !
				}

			}
		});
		btnCancel.setBackground(new Color(128, 128, 128));
		btnCancel.setBounds(176, 148, 141, 31);
//		btnCancel.setBounds(350, 260, 141, 31);
//		btnCancel.setBounds(400, 400, 141, 31);
		changePwDonePanel.add(btnCancel);

		JLabel lblSeatNumQuestion = new JLabel("비밀번호 변경이 완료되었습니다.");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(35, 70, 246, 37);
		changePwDonePanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		changePwDonePanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelect = new JLabel("안내");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 50, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}

}
