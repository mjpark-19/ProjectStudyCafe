package all;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;


public class AfterLogIn extends DB {

	// 변수 선언
	JFrame frame;
	JPanel afterLogInPanel;
	JPanel purchasePanel;
	JPanel paymentPanel;
	JPanel mainPanel;
	JPanel bigPanel;

	JButton btnCheckIn;

	JLabel lblMainTime;
	JLabel lblTimer;

	HashMap<String, UserInfo> mapInfo;
	UserInfo userInfo;

	static int pageCnt = 0;

	// 생성자 ( 메인 로그인, 구입 이후 접근 시)

	public AfterLogIn(JFrame frame, JPanel mainPanel, UserInfo userInfo) {
		System.out.println("After 생성");
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.userInfo = userInfo;
		initialize();
	}

	// 생성자2 ( 회원가입에서 접근 시)
	public AfterLogIn(JFrame frame, JPanel mainPanel, UserInfo userInfo, HashMap<String, UserInfo> mapInfo) {
		this(frame, mainPanel, userInfo);
		this.mapInfo = mapInfo;
	}

	// 메소드 시작
	public void initialize() {
		afterLogInPanel = new JPanel();
//		frame.getContentPane().remove(afterLogInPanel);

		afterLogInPanel.setBackground(Color.DARK_GRAY);
		afterLogInPanel.setBounds(0, 0, 700, 800);
		afterLogInPanel.setLayout(null);
		frame.getContentPane().add(afterLogInPanel);

		bigPanel = new JPanel();
		bigPanel.setLayout(null);
		bigPanel.setBounds(0, 0, 700, 800);
		bigPanel.setVisible(false);

		JLabel lblID = new JLabel(Main.id + " 님 반갑습니다.");
		lblID.setFont(new Font("굴림", Font.BOLD, 17));
		lblID.setForeground(Color.WHITE);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(160, 0, 300, 30);
		afterLogInPanel.add(lblID);

		// 현재 시간 표시
		lblMainTime = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("굴림", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblMainTime.setBounds(536, 0, 150, 34);
		lblMainTime.setHorizontalAlignment(SwingConstants.CENTER);
		afterLogInPanel.add(lblMainTime);

		// 자동 로그아웃 ( 에러 )
		lblTimer = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("굴림", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);

		lblTimer.setBounds(400, 10, 150, 34);
		afterLogInPanel.add(lblTimer);

		// 현재 시간 및 자동 로그아웃 호출
		if (AfterLogIn.pageCnt == 0) {
			TimeCal Time = new TimeCal(frame, lblMainTime, lblTimer, mainPanel, afterLogInPanel);
			Thread thread1 = new Thread(Time);
			thread1.start();
		}

		// 시간권 구입으로 이동
		JButton btnTimePurchase = new JButton("시간권 구입");
		btnTimePurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afterLogInPanel.setVisible(false);

				new Payment(frame, mainPanel, userInfo);
//				new Payment(frame, mainPanel, userInfo, lblMainTime, lblTimer, bigPanel);
//				new Payment(frame, mainPanel, userInfo, lblMainTime, lblTimer);
			}
		});
		btnTimePurchase.setBackground(Color.ORANGE);
		btnTimePurchase.setBounds(342, 420, 170, 132);
		afterLogInPanel.add(btnTimePurchase);

		// 기간권 구입으로 이동
		JButton btnPeriodPurchase = new JButton("기간권 구입");
		btnPeriodPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				afterLogInPanel.setVisible(false);
				new Payment(frame, mainPanel, userInfo);
			}
		});
		btnPeriodPurchase.setBackground(Color.WHITE);
		btnPeriodPurchase.setBounds(513, 420, 170, 132);
		afterLogInPanel.add(btnPeriodPurchase);

		// 시간권 이용 패널 띄우기
		JButton btnUseTime = new JButton("시간권 이용");
		btnUseTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel useTimePanel = new JPanel();
				useTimePanel.setVisible(false);

				if ((userInfo.getUserType().equals(""))) {
					useSmallPanelOpen(useTimePanel, "time");
				} else {
					doubleUseCheck(useTimePanel);
				}

			}
		});
		btnUseTime.setBackground(Color.ORANGE);
		btnUseTime.setBounds(0, 420, 170, 132);
		afterLogInPanel.add(btnUseTime);

		// 기간권 이용 패널 띄우기
		JButton btnUsePeriod = new JButton("기간권 이용");
		btnUsePeriod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel useTimePanel = new JPanel();
				useTimePanel.setVisible(false);

				if ((userInfo.getUserType().equals(""))) {
					useSmallPanelOpen(useTimePanel, "week");
				} else {
					doubleUseCheck(useTimePanel);
				}

			}
		});
		btnUsePeriod.setBackground(Color.WHITE);
		btnUsePeriod.setBounds(171, 420, 170, 132);
		afterLogInPanel.add(btnUsePeriod);

		JButton btnMoveSeat = new JButton("좌석이동");
		btnMoveSeat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (userInfo.getUserType() != "") {
					Seat.tempType = userInfo.getUserType();
					afterLogInPanel.setVisible(false);

					new Seat(frame, afterLogInPanel, userInfo);
				} else {
					JPanel useTimePanel = new JPanel();
					useTimePanel.setVisible(false);

					emptyUserType(useTimePanel);
				}

			}
		});
		btnMoveSeat.setForeground(new Color(255, 255, 255));
		btnMoveSeat.setBackground(Color.LIGHT_GRAY);
		btnMoveSeat.setBounds(1, 629, 227, 55);
		afterLogInPanel.add(btnMoveSeat);

		// 내정보 확인
		JButton btnMyInfo = new JButton("내 정보");
		btnMyInfo.setForeground(new Color(255, 255, 255));
		btnMyInfo.setBackground(Color.LIGHT_GRAY);
		btnMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				afterLogInPanel.setVisible(false);
				System.out.println("내 정보 클릭");
				new MyInfo(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);

			}
		});
		btnMyInfo.setBounds(229, 629, 227, 55);
		afterLogInPanel.add(btnMyInfo);

		JButton btnNewButton_2_1_1 = new JButton("관리자 호출");
		btnNewButton_2_1_1.setForeground(Color.WHITE);
		btnNewButton_2_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2_1_1.setBounds(457, 629, 227, 55);
		afterLogInPanel.add(btnNewButton_2_1_1);

		LogOut();

	}

	// 입실 중인 상품이 없는 상태에서 좌석 이동 패널 열 때 - 현재 입실중인 상품이 없습니다.
	public void emptyUserType(JPanel useTimePanel) {
		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		afterLogInPanel.add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("좌석 이동");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		// 체크인 완료 패널
		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("현재 입실중인 상품이 없습니다.");
		lblEmptyCart.setBounds(36, 45, 380, 98);
		checkInDonePanel.add(lblEmptyCart);

	}

	// 시간권/기간권 이용 패널 클릭 시
	public void useSmallPanelOpen(JPanel useTimePanel, String strTimeWeek) {

		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		afterLogInPanel.add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("입실");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		btnCheckIn = new JButton("입실"); // 입 실
		btnCheckIn.setEnabled(false);
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				afterLogInPanel.setVisible(false);
				useTimePanel.setVisible(false);
				new Seat(frame, afterLogInPanel, userInfo);

			}
		});
		btnCheckIn.setBackground(new Color(32, 178, 170));
		btnCheckIn.setBounds(38, 246, 195, 38);
		useTimePanel.add(btnCheckIn);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("보유하신 상품으로 입실하시겠습니까?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("이용중인 상품이 없습니다. 시간권/기간권을 구입해주세요.");
		lblEmptyCart.setVisible(false);
		checkInDonePanel.add(lblEmptyCart);

		// 시간권/기간권 이용 패널에서 상세 상품 선택하기
		useTimePanelSelect(checkInDonePanel, lblCheckInQuestion, lblEmptyCart, strTimeWeek);

	}

	// 시간권/기간권 이용 패널에서 상세 상품 선택하기
	public void useTimePanelSelect(JPanel checkInDonePanel, JLabel lblCheckInQuestion, JLabel lblEmptyCart,
			String strTimeWeek) {
		System.out.println(" useTimePanelSelect ");

		// 테스트용 - 실제 운영 시 삭제
		userInfo.setCart("자유석 시간권(10시간);자유석 기간권(4주);5인실 일회권(3시간)");
//		userInfo.setCart("자유석 시간권(10시간)");
//		userInfo.setCart("");

		// 보유한 상품이 있으면 아래 코드 진행
		if (userInfo.getCart() != "") {

			// 시간권과 기간권 보유 상품을 String[]로 분리하기
			String[] aryCart = regExr(userInfo.getCart(), strTimeWeek);
			System.out.println(aryCart.length);

			ButtonGroup btnGroupRadio = new ButtonGroup();

			for (int i = 0; i < aryCart.length; i++) {

				JRadioButton rdbtn = new JRadioButton(aryCart[i]);
				rdbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (e.getActionCommand().contains("5인실")) {
							Seat.tempType = "group";
						} else if (e.getActionCommand().contains("시간권")) {
							Seat.tempType = "time";
						} else if (e.getActionCommand().contains("일회권")) {
							Seat.tempType = "hour";
						} else if (e.getActionCommand().contains("기간권")) {
							Seat.tempType = "week";
						}

						// 상품 선택 없이 입실 불가(버튼 비활성화)
						btnCheckIn.setEnabled(true);

					}
				});
				rdbtn.setBounds(45, 10 + 30 * (i + 1), 151, 34);
				checkInDonePanel.add(rdbtn);

				btnGroupRadio.add(rdbtn);
			}

			// 보유한 상품이 없을 경우 아래 코드 진행
		} else {

			lblEmptyCart.setBounds(36, 45, 380, 98);
			lblEmptyCart.setVisible(true);
			lblCheckInQuestion.setVisible(false);

		}

	}

	// 시간권과 기간권 보유 상품을 String[]로 분리하기
	public String[] regExr(String strCart, String strTimeWeek) {

		String regEx = "";
		if (strTimeWeek == "time")
			regEx = ".{3}\\s기.{5,9}\\)+;?"; // 비기간권만 남기기 replaceAll

		else if (strTimeWeek == "week")
			regEx = ".{3}\\s(?!기).{5,9}\\);?"; // 기간권만 남기기 replaceAll

		String[] ary = strCart.replaceAll(regEx, "").split(";");

		return ary;
	}

	public void LogOut() {

		JPanel bottomLogOutPanel = new JPanel();
		bottomLogOutPanel.setLayout(null);
		bottomLogOutPanel.setBackground(Color.ORANGE);
		bottomLogOutPanel.setBounds(0, 713, 686, 48);
		afterLogInPanel.add(bottomLogOutPanel);
//		 bottomLogOutPanel.add(lblTimer); // 자동 로그아웃

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n          로그아웃 ");
				afterLogInPanel.setVisible(false);
				afterLogInPanel = null;

				// 초기화
				mainPanel.setVisible(true);
				Main.id = null;
				userInfo = null;
			}
		});
		btnLogOut.setBounds(204, 10, 200, 28);
		btnLogOut.setBounds(0, 0, 686, 48);
		btnLogOut.setBackground(Color.ORANGE);
		bottomLogOutPanel.add(btnLogOut);
	}

	public void doubleUseCheck(JPanel useTimePanel) {

		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		afterLogInPanel.add(useTimePanel);
		useTimePanel.setVisible(true);

		JPanel checkInTitlePanel = new JPanel();
		checkInTitlePanel.setLayout(null);
		checkInTitlePanel.setBackground(Color.ORANGE);
		checkInTitlePanel.setBounds(0, 0, 494, 36);
		useTimePanel.add(checkInTitlePanel);

		JLabel lblCheckIntitle = new JLabel("퇴실");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckIn = new JButton("퇴실");
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
				afterLogInPanel.setVisible(true);

				// 입실 중 상품이 있는데, 시간권/기간권 이용 메뉴에 접근할 경우 - 기존 상품 퇴실 안내
				new DoubleCheckOut(userInfo);

			}
		});
		btnCheckIn.setBackground(new Color(32, 178, 170));
		btnCheckIn.setBounds(38, 246, 195, 38);
		useTimePanel.add(btnCheckIn);

		JButton btnCheckInClose = new JButton("닫기");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("입실 중인 상품이 있습니다. 퇴실 처리하시겠습니까?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblCheckInDone = new JLabel("퇴실 처리가 완료되었습니다.");
		lblCheckInDone.setBounds(36, 45, 380, 98);
		lblCheckInDone.setVisible(false);
		checkInDonePanel.add(lblCheckInDone);

	}
}
