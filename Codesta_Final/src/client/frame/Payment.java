package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

import client.time.WeekPass;

public class Payment extends JPanel {

	static int buyOption; // 정적 변수 : Payment.java 에서 시간권, 기간권 구입창 중에 지정 후 이동 위함

	JFrame frame;
	JPanel mainPanel;
	JPanel afterLogInPanel;

	JPanel bigPanel;
	JPanel paymentPanel;
	JPanel buyPanel;

	JLabel lblMainTime;
	JLabel lblTimer;
	JToggleButton btnUseAllPoints;
	String strCart = "";
	String strPaymentHistory = "";
	String Point = "";
	String usedPoint = "0";
	String loginDate;
	private JTextField selectedProductDetail;
	private JLabel totalAmount;
	String s1, s2, s3;
	String price = "";
	String product = "";

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;

	public Payment(User userInfo, JLabel lblMainTime) {
		this.userInfo = userInfo;
		this.lblMainTime = lblMainTime;

		service = UserService.getInstance();
		lblMainTime.setBounds(550, 40, 150, 50);
		add(lblMainTime);

		initialize();

	}

	public void initialize() {

		setBackground(Color.GRAY);
		setLayout(null);

		buyPanel = new JPanel();
		buyPanel.setBackground(Color.GRAY);
		buyPanel.setBounds(0, 50, 700, 713);
		buyPanel.setLayout(null);
		add(buyPanel);

		// 결제창패널
		paymentPanel = new JPanel();
		paymentPanel.setBackground(Color.GRAY);
		paymentPanel.setBounds(0, 0, 700, 800);
		paymentPanel.setBounds(0, 50, 700, 713);
		paymentPanel.setLayout(null);
		paymentPanel.setVisible(false);
		add(paymentPanel);

		// 타이틀 '시간권/기간권' 구입
		JLabel BuyPass = new JLabel("시간권 기간권 구입");
		BuyPass.setBounds(219, 31, 270, 35);
		BuyPass.setFont(new Font("굴림", Font.BOLD, 30));
		BuyPass.setForeground(Color.ORANGE);
		BuyPass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.ORANGE));
		buyPanel.add(BuyPass);

		// 전체타입
		JTextField ProductType = new JTextField();
		ProductType.setBackground(Color.ORANGE);
		ProductType.setFont(new Font("굴림", Font.BOLD, 15));
		ProductType.setHorizontalAlignment(SwingConstants.CENTER);
		ProductType.setText("전체타입");
		ProductType.setBounds(56, 79, 136, 49);
		ProductType.setColumns(10);
		buyPanel.add(ProductType);

		// 전체상품
		JTextField AllProduct = new JTextField();
		AllProduct.setText("전체상품");
		AllProduct.setHorizontalAlignment(SwingConstants.CENTER);
		AllProduct.setFont(new Font("굴림", Font.BOLD, 15));
		AllProduct.setColumns(10);
		AllProduct.setBackground(Color.ORANGE);
		AllProduct.setBounds(56, 144, 136, 49);
		buyPanel.add(AllProduct);

		// 자유석
		JButton FreeSeats = new JButton("자유석");
		FreeSeats.setBackground(new Color(65, 105, 225));
		FreeSeats.setForeground(Color.WHITE);
		FreeSeats.setBounds(219, 79, 106, 49);
		buyPanel.add(FreeSeats);

		// 5인실
		JButton GroupRoom = new JButton("5인실");
		GroupRoom.setForeground(Color.WHITE);
		GroupRoom.setBackground(new Color(65, 105, 225));
		GroupRoom.setBounds(337, 79, 106, 49);
		buyPanel.add(GroupRoom);

		// 일회권
		JButton ADayPass = new JButton("일회권");
		ADayPass.setForeground(Color.WHITE);
		ADayPass.setBackground(new Color(65, 105, 225));
		ADayPass.setBounds(219, 144, 106, 49);
		buyPanel.add(ADayPass);

		// 시간권
		JButton DayPass = new JButton("시간권");
		DayPass.setForeground(Color.WHITE);
		DayPass.setBackground(new Color(65, 105, 225));
		DayPass.setBounds(337, 144, 106, 49);
		buyPanel.add(DayPass);

		// 기간권
		JButton WeekPass = new JButton("기간권");
		WeekPass.setForeground(Color.WHITE);
		WeekPass.setBackground(new Color(65, 105, 225));
		WeekPass.setBounds(455, 144, 106, 49);
		buyPanel.add(WeekPass);

		// 시간
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");
		loginDate = Date.format(new Date());

		// 자유석 미니패널
		JPanel MiniPanelFreeSeats = new JPanel();
		MiniPanelFreeSeats.setBackground(Color.GRAY);
		MiniPanelFreeSeats.setBounds(25, 213, 646, 377);
		buyPanel.add(MiniPanelFreeSeats);
		MiniPanelFreeSeats.setLayout(null);

		// 자유석 기간권(주)
		String[] FreeSeat = { "<HTML><body style='text-align:center;'>자유석 1회권(2시간)<br> 3,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권(4시간)<br> 5,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권(6시간)<br> 7,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권 (추가 1시간)<br> 1,000원" + "</body></HTML>",
				"<HTML><body style='text-align:center;'>자유석 시간권(20시간)<br> 29,000원</body></HTML>",
				"<HTML><body style='text-align:center;'>자유석 시간권(50시간)<br> 59,000원</body></HTML>",
				"<HTML><body style='text-align:" + "center;'>자유석 시간권(100시간)<br> 88,000원</body></HTML>",
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D"
						+ " \uAE30\uAC04\uAD8C(1\uC8FC)<br> 40,000\uC6D0</body></HTML>",
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D "
						+ "\uAE30\uAC04\uAD8C(2\uC8FC)<br> 75,000\uC6D0</body></HTML>",
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D"
						+ " \uAE30\uAC04\uAD8C(4\uC8FC)<br> 100,000\uC6D0</body></HTML>" };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int temp = i;
				int temp2 = j;
				JToggleButton freeSeatBtn = new JToggleButton(FreeSeat[i + (j * 3)]);
				freeSeatBtn.setForeground(Color.WHITE);
				freeSeatBtn.setFont(new Font("굴림", Font.BOLD, 15));
				freeSeatBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
				freeSeatBtn.setBackground(Color.GRAY);
				freeSeatBtn.setBounds(24 + (207 * i), 22 + (95 * j), 182, 67);
				freeSeatBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 상품, 가격 변수에 넣기
						product = FreeSeat[temp + (temp2 * 3)].split("<br>")[0];
						s1 = product.substring(39, product.length());
						price = FreeSeat[temp + (temp2 * 3)].split("<br>")[1];
						s3 = price.substring(1, price.length() - 15);
						s2 = s3.replace(",", "");

						usePointsMethod();
						System.out.println("s1:" + s1);
						System.out.println("s3:" + s3);

						// 결제창 화면에 구입상품, 가격 띄우기
						selectedProductDetail.setText(s1 + "\0" + s2 + "원");
						totalAmount.setText(s3 + "원");

					}
				});
				MiniPanelFreeSeats.add(freeSeatBtn);
			}
		}
		JToggleButton FreeSeatBtn = new JToggleButton(FreeSeat[9]);
		FreeSeatBtn.setForeground(Color.WHITE);
		FreeSeatBtn.setFont(new Font("굴림", Font.BOLD, 15));
		FreeSeatBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
		FreeSeatBtn.setBackground(Color.GRAY);
		FreeSeatBtn.setBounds(24, 309, 182, 67);
		FreeSeatBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				product = FreeSeat[9];
				String a = product.split("<br>")[1];
				totalAmount.setText(a.substring(3, a.length() - 14));
				product = FreeSeat[9].split("<br>")[0];
				s1 = product.substring(39, product.length());
				price = FreeSeat[9].split("<br>")[1];
				s3 = price.substring(1, price.length() - 15);
				s2 = s3.replace(",", "");
				usePointsMethod();
				selectedProductDetail.setText(s1 + "\0" + s2 + "원");
				totalAmount.setText(s3 + "원");
			}
		});
		MiniPanelFreeSeats.add(FreeSeatBtn);

		// 5인실 미니패널
		JPanel MiniPanelGroupRoom = new JPanel();
		MiniPanelGroupRoom.setBackground(Color.GRAY);
		MiniPanelGroupRoom.setBounds(25, 213, 646, 377);
		buyPanel.add(MiniPanelGroupRoom);
		MiniPanelGroupRoom.setLayout(null);

		String[] groupRoom = { "<HTML><body style='text-align:center;'>5인실 1회권(2시간)<br>12,000원" + "</body></HTML>",
				"<HTML><body style='text-align:center;'>5인실 1회권(추가 1시간)<br>" + "6,000원</body></HTML>" };

		for (int i = 0; i < 2; i++) {
			int temp = i;
			JToggleButton GroupRoomBtn = new JToggleButton(groupRoom[i]);
			GroupRoomBtn.setForeground(Color.WHITE);
			GroupRoomBtn.setFont(new Font("굴림", Font.BOLD, 15));
			GroupRoomBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
			GroupRoomBtn.setBackground(Color.GRAY);
			GroupRoomBtn.setBounds(24 + (207 * i), 22, 182, 67);
			GroupRoomBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					product = groupRoom[temp].split("<br>")[0];
					s1 = product.substring(39, product.length());
					price = groupRoom[temp].split("<br>")[1];
					s3 = price.substring(0, price.length() - 15);
					s2 = s3.replace(",", "");
					usePointsMethod();

					// 결제창 화면에 구입상품, 가격 띄우기
					selectedProductDetail.setText(s1 + "\0" + s2 + "원");
					totalAmount.setText(s3 + "원");
					System.out.println("s1:" + s1);
					System.out.println("s3:" + s3);

				}
			});
			MiniPanelGroupRoom.add(GroupRoomBtn);
		}
		// 일회권 미니패널
		JPanel MiniPanelADayPass = new JPanel();
		MiniPanelADayPass.setBackground(Color.GRAY);
		MiniPanelADayPass.setBounds(25, 213, 646, 377);
		buyPanel.add(MiniPanelADayPass);
		MiniPanelADayPass.setLayout(null);

		String[] aDayPass = { "<HTML><body style='text-align:center;'>자유석 1회권(2시간)<br>3,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권(4시간)<br>5,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권(6시간)<br>7,000원</body>" + "</HTML>",
				"<HTML><body style='text-align:center;'>자유석 1회권(추가 1시간)<br>1,000원" + "</body></HTML>",
				"<HTML><body style='text-align:center;'>5인실 1회권(2시간)<br>12,000원" + "</body></HTML>",
				"<HTML><body style='text-align:center;'>5인실 1회권(추가 1시간)<br>" + "6,000원</body></HTML>" };

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				int temp = i;
				int temp2 = j;
				JToggleButton ADayPassBtn = new JToggleButton(aDayPass[i + (j * 3)]);
				ADayPassBtn.setForeground(Color.WHITE);
				ADayPassBtn.setFont(new Font("굴림", Font.BOLD, 15));
				ADayPassBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
				ADayPassBtn.setBackground(Color.GRAY);
				ADayPassBtn.setBounds(24 + (207 * i), 22 + (95 * j), 182, 67);

				ADayPassBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						product = aDayPass[temp + (temp2 * 3)].split("<br>")[0];
						s1 = product.substring(39, product.length());
						price = aDayPass[temp + (temp2 * 3)].split("<br>")[1];
						s3 = price.substring(0, price.length() - 15);
						s2 = s3.replace(",", "");
						usePointsMethod();
						System.out.println("s1:" + s1);
						System.out.println("s3:" + s3);

						// 결제창 화면에 구입상품, 가격 띄우기
						selectedProductDetail.setText(s1 + "\0" + s2 + "원");
						totalAmount.setText(s3 + "원");

					}
				});
				MiniPanelADayPass.add(ADayPassBtn);
			}
		}
		// 기간권 미니패널
		JPanel MiniPanelWeekPass = new JPanel();
		MiniPanelWeekPass.setBackground(Color.GRAY);
		MiniPanelWeekPass.setBounds(25, 213, 646, 377);
		buyPanel.add(MiniPanelWeekPass);
		MiniPanelWeekPass.setLayout(null);

		String[] weekpass = {
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D"
						+ " \uAE30\uAC04\uAD8C(1\uC8FC)<br>40,000원</body></HTML>",
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D "
						+ "\uAE30\uAC04\uAD8C(2\uC8FC)<br>75,000원</body></HTML>",
				"<HTML><body style='text-align:center;'>\uC790\uC720\uC11D"
						+ " \uAE30\uAC04\uAD8C(4\uC8FC)<br>100,000원</body></HTML>" };

		for (int i = 0; i < 3; i++) {
			int temp = i;
			JToggleButton WeekPassBtn = new JToggleButton(weekpass[i]);
			WeekPassBtn.setForeground(Color.WHITE);
			WeekPassBtn.setFont(new Font("굴림", Font.BOLD, 15));
			WeekPassBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
			WeekPassBtn.setBackground(Color.GRAY);
			WeekPassBtn.setBounds(24 + (207 * i), 22, 182, 67);
			WeekPassBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					product = weekpass[temp].split("<br>")[0];
					s1 = product.substring(39, product.length());
					price = weekpass[temp].split("<br>")[1];
					s3 = price.substring(0, price.length() - 15);
					s2 = s3.replace(",", "");
					usePointsMethod();
					System.out.println("s1:" + s1);
					System.out.println("s3:" + s3);

//	               System.out.println("s2.substring(0, 3): "+s2.substring(0, 3));
//	               System.out.println("s2.substring(4) : "+s2.substring(4));
					// 결제창 화면에 구입상품, 가격 띄우기
					selectedProductDetail.setText(s1 + "\0" + s2 + "원");
					totalAmount.setText(s2 + "원");

				}
			});
			MiniPanelWeekPass.add(WeekPassBtn);

		}
		// 시간권 미니패널
		JPanel MiniPanelDayPass = new JPanel();
		MiniPanelDayPass.setBackground(Color.GRAY);
		MiniPanelDayPass.setBounds(25, 213, 646, 377);
		buyPanel.add(MiniPanelDayPass);
		MiniPanelDayPass.setLayout(null);

		String[] dayPass = { "<HTML><body style='text-align:center;'>자유석 시간권(20시간)<br>" + "29,000원</body></HTML>",
				"<HTML><body style='text-align:center;'>" + "자유석 시간권(50시간)<br>59,000원</body></HTML>",
				"<HTML><body style='text-align:" + "center;'>자유석 시간권(100시간)<br>88,000원</body></HTML>" };
		for (int i = 0; i < 3; i++) {
			int temp = i;
			JToggleButton DayPassBtn = new JToggleButton(dayPass[i]);
			DayPassBtn.setForeground(Color.WHITE);
			DayPassBtn.setFont(new Font("굴림", Font.BOLD, 15));
			DayPassBtn.setBorder((new LineBorder(new Color(255, 200, 0), 1)));
			DayPassBtn.setBackground(Color.GRAY);
			DayPassBtn.setBounds(24 + (207 * i), 22, 182, 67);
			DayPassBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					product = dayPass[temp].split("<br>")[0];
					s1 = product.substring(39, product.length());
					price = dayPass[temp].split("<br>")[1];

					s3 = price.substring(0, price.length() - 15);
					s2 = s3.replace(",", "");
					usePointsMethod();
					System.out.println("s1:" + s1);
					System.out.println("s3:" + s3);

					// 결제창 화면에 구입상품, 가격 띄우기
					selectedProductDetail.setText(s1 + "\0" + s2 + "원");
					totalAmount.setText(s3 + "원");

				}
			});
			MiniPanelDayPass.add(DayPassBtn);
		}

		MiniPanelFreeSeats.setVisible(false);
		MiniPanelGroupRoom.setVisible(false);
		MiniPanelADayPass.setVisible(false);
		MiniPanelWeekPass.setVisible(false);
		MiniPanelDayPass.setVisible(false);

		ArrayList<JPanel> AllType = new ArrayList<JPanel>();
		AllType.add(MiniPanelFreeSeats); // 자유석
		AllType.add(MiniPanelGroupRoom); // 5인실
		AllType.add(MiniPanelADayPass); // 일회권
		AllType.add(MiniPanelWeekPass); // 기간권
		AllType.add(MiniPanelDayPass); // 시간권

		FreeSeats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (JPanel p : AllType) {
					if (p != MiniPanelFreeSeats)
						p.setVisible(false);
					else
						MiniPanelFreeSeats.setVisible(!MiniPanelFreeSeats.isVisible());
				}
			}
		});

		GroupRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel p : AllType) {
					if (p != MiniPanelGroupRoom)
						p.setVisible(false);
					else
						MiniPanelGroupRoom.setVisible(!MiniPanelGroupRoom.isVisible());
				}
			}
		});
		WeekPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel p : AllType) {
					if (p != MiniPanelWeekPass) {
						p.setVisible(false);
						// ADayPass.setVisible(false);
						// DayPass.setVisible(false);

					} else
						MiniPanelWeekPass.setVisible(!MiniPanelWeekPass.isVisible());
					// ADayPass.setVisible(!MiniPanelWeekPass.isVisible());
					// DayPass.setVisible(!MiniPanelWeekPass.isVisible());

				}
			}
		});

		ADayPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel p : AllType) {
					if (p != MiniPanelADayPass) {
						p.setVisible(false);
						// WeekPass.setVisible(false);
						// DayPass.setVisible(false);
					} else
						MiniPanelADayPass.setVisible(!MiniPanelADayPass.isVisible());
					// WeekPass.setVisible(!MiniPanelADayPass.isVisible());
					// DayPass.setVisible(!MiniPanelADayPass.isVisible());

				}
			}
		});
		DayPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel p : AllType) {
					if (p != MiniPanelDayPass) {
						p.setVisible(false);
						// WeekPass.setVisible(false);
						// ADayPass.setVisible(false);
					} else
						MiniPanelDayPass.setVisible(!MiniPanelDayPass.isVisible());
					// WeekPass.setVisible(!MiniPanelDayPass.isVisible());
					// ADayPass.setVisible(!MiniPanelDayPass.isVisible());

				}
			}
		});

		// 회원정보
		JTextField id = new JTextField(MainPanel.id + "님");
		id.setFont(new Font("굴림", Font.BOLD, 15));
		id.setForeground(new Color(0, 0, 0));
		id.setBounds(214, 0, 260, 49);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		add(id);
		id.setColumns(10);

		// 안내문구
		JTextField Notice1 = new JTextField();
		Notice1.setBackground(Color.GRAY);
		Notice1.setForeground(Color.WHITE);
		Notice1.setText("\u203B\uC77C\uD68C\uAD8C \uC0C1\uD488\uC740 \uD1F4\uC2E4\uC2DC "
				+ "\uC18C\uBA78\uB418\uBA70 \uD658\uBD88\uC774 \uBD88\uAC00\uD569\uB2C8\uB2E4");
		Notice1.setFont(new Font("굴림", Font.BOLD, 13));
		Notice1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		Notice1.setBounds(15, 600, 668, 21);
		buyPanel.add(Notice1);
		Notice1.setColumns(10);

		JTextField Notice2 = new JTextField();
		Notice2.setText("\u203B\uC2DC\uAC04\uAD8C \uC774\uC6A9\uC2DC \uB0A8\uC740 \uC2DC"
				+ "\uAC04\uC740 \uC790\uB3D9 \uC801\uB9BD\uB418\uC5B4 \uC7AC\uBC29\uBB38\uC2DC "
				+ "\uC774\uC6A9\uAC00\uB2A5\uD569\uB2C8\uB2E4.");
		Notice2.setForeground(Color.WHITE);
		Notice2.setFont(new Font("굴림", Font.BOLD, 13));
		Notice2.setBorder(new LineBorder(Color.GRAY, 0));
		Notice2.setColumns(10);
		Notice2.setBackground(Color.GRAY);
		Notice2.setBounds(15, 620, 668, 21);
		buyPanel.add(Notice2);

		// 홈버튼 (상단)
		JButton backToHome = new JButton("처음으로");
		backToHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				buyPanel.setVisible(false);
				paymentPanel.setVisible(false);
//				new AfterLogIn(frame, mainPanel, userInfo);
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime);
				MainPanel.frame.move(afterLogin);
			}
		});
		backToHome.setBackground(Color.ORANGE);
		backToHome.setFont(new Font("굴림", Font.BOLD, 15));
		backToHome.setForeground(new Color(0, 0, 0));
		backToHome.setBounds(8, 32, 212, 49);
		backToHome.setBounds(0, 0, 212, 49);
		add(backToHome);

		// 로그아웃
		JButton logOut = new JButton("LogOut");
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n\n          로그아웃 ");
				setVisible(false);
				buyPanel.setVisible(false);
				paymentPanel.setVisible(false);

				MainPanel mainPanel = new MainPanel(MainPanel.frame);
				MainPanel.frame.move(mainPanel);

				MainPanel.id = null;
				userInfo = null;
			}
		});
		logOut.setForeground(new Color(0, 0, 0));
		logOut.setBackground(Color.ORANGE);
		logOut.setFont(new Font("굴림", Font.BOLD, 15));
		logOut.setBounds(476, 0, 212, 49);
		add(logOut);

		// 이전버튼
		JButton btnPrevious = new JButton("<<이전");
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				buyPanel.setVisible(false);
				paymentPanel.setVisible(false);
//				new AfterLogIn(frame, mainPanel, userInfo);
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime);
				MainPanel.frame.move(afterLogin);

			}
		});
		btnPrevious.setFont(new Font("굴림", Font.BOLD, 18));
		btnPrevious.setBackground(Color.ORANGE);
		btnPrevious.setBounds(0, 650, 345, 63);
//		btnPrevious.setBounds(0, 700, 345, 63);
		buyPanel.add(btnPrevious);

		// 다음버튼
		JButton btnNext = new JButton(">>다음");
		// 다음버튼누르면 결제창으로 이동
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyPanel.setVisible(!buyPanel.isVisible());
				paymentPanel.setVisible(true);
			}
		});
		btnNext.setFont(new Font("굴림", Font.BOLD, 18));
		btnNext.setBackground(Color.ORANGE);
		btnNext.setBounds(345, 650, 345, 63);
//		btnNext.setBounds(345, 700, 345, 63);
		buyPanel.add(btnNext);

		//////////////////////////// 결제 패널/////////////////////////////////////////

		JPanel miniPanel_payment = new JPanel();
		miniPanel_payment.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
		miniPanel_payment.setBackground(Color.GRAY);
		miniPanel_payment.setBounds(23, 33 - 50, 646, 420);
		paymentPanel.add(miniPanel_payment);
		miniPanel_payment.setLayout(null);

		JLabel paymentSelect = new JLabel("\uACB0\uC81C\uBC29\uBC95\uC120\uD0DD");
		paymentSelect.setForeground(Color.ORANGE);
		paymentSelect.setFont(new Font("굴림", Font.BOLD, 30));
		paymentSelect.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.ORANGE));
		paymentSelect.setBounds(220, 90 - 50, 190, 35);
		miniPanel_payment.add(paymentSelect);

		// 구입상품
		JLabel selectedProduct = new JLabel("\uAD6C\uC785\uC0C1\uD488");
		selectedProduct.setHorizontalAlignment(SwingConstants.CENTER);
		selectedProduct.setForeground(Color.WHITE);
		selectedProduct.setBackground(Color.GRAY);
		selectedProduct.setFont(new Font("굴림", Font.BOLD, 15));
		selectedProduct.setBounds(12, 149 - 50, 136, 49);
		miniPanel_payment.add(selectedProduct);

		// 보유포인트
		JLabel points = new JLabel("\uBCF4\uC720\uD3EC\uC778\uD2B8");
		points.setBackground(Color.GRAY);
		points.setHorizontalAlignment(SwingConstants.CENTER);
		points.setForeground(Color.WHITE);
		points.setFont(new Font("굴림", Font.BOLD, 15));
		points.setBounds(12, 214 - 50, 136, 49);
		miniPanel_payment.add(points);

		// 보유포인트
		JPanel pointsDetail = new JPanel();
		pointsDetail.setBounds(148, 214 - 50, 464, 49);
		pointsDetail.setBackground(Color.WHITE);
		pointsDetail.setLayout(null);
		miniPanel_payment.add(pointsDetail);

		btnUseAllPoints = new JToggleButton("\uC804\uC561\uC0AC\uC6A9");
		btnUseAllPoints.setBackground(Color.BLUE);
		btnUseAllPoints.setForeground(Color.WHITE);
		btnUseAllPoints.setFont(new Font("굴림", Font.BOLD, 10));
		btnUseAllPoints.setBounds(250, 10, 77, 30);
		pointsDetail.add(btnUseAllPoints);
		btnUseAllPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usePointsMethod();

			}
		});

		JTextField ownPoints = new JTextField();
		ownPoints.setHorizontalAlignment(SwingConstants.RIGHT);
		ownPoints.setText(userInfo.getPoint());
		ownPoints.setBounds(330, 10, 100, 30);
		ownPoints.setFont(new Font("굴림", Font.BOLD, 14));
		pointsDetail.add(ownPoints);

		JTextField P = new JTextField();
		P.setText("P");
		P.setBounds(429, 9, 23, 30);
		P.setFont(new Font("굴림", Font.BOLD, 16));
		P.setBorder((new LineBorder(new Color(0, 0, 0), 0)));
		pointsDetail.add(P);

		// 사용포인트
		JLabel usedPoints = new JLabel("\uC0AC\uC6A9\uD3EC\uC778\uD2B8");
		usedPoints.setHorizontalAlignment(SwingConstants.CENTER);
		usedPoints.setBackground(Color.GRAY);
		usedPoints.setForeground(Color.WHITE);
		usedPoints.setFont(new Font("굴림", Font.BOLD, 15));
		usedPoints.setBounds(12, 289 - 50, 136, 49);
		miniPanel_payment.add(usedPoints);

		selectedProductDetail = new JTextField();
		selectedProductDetail.setFont(new Font("굴림", Font.BOLD, 15));
		selectedProductDetail.setBackground(Color.WHITE);
		selectedProductDetail.setBounds(148, 149 - 50, 464, 49);
		selectedProductDetail.setLayout(null);
		selectedProductDetail.setHorizontalAlignment(SwingConstants.RIGHT);
		miniPanel_payment.add(selectedProductDetail);

		JPanel usedPointsDetail = new JPanel();
		usedPointsDetail.setBounds(148, 289 - 50, 464, 49);
		usedPointsDetail.setBackground(Color.WHITE);
		usedPointsDetail.setLayout(null);
		miniPanel_payment.add(usedPointsDetail);

		JTextField cancelPoints = new JTextField();
		cancelPoints.setHorizontalAlignment(SwingConstants.RIGHT);
		cancelPoints.setText(ownPoints.getText());
		cancelPoints.setBounds(330, 10, 100, 30);
		cancelPoints.setFont(new Font("굴림", Font.BOLD, 14));
		usedPointsDetail.add(cancelPoints);

		JButton btncancelPoints = new JButton("\uC0AC\uC6A9\uCDE8\uC18C");
		btncancelPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelPoints.setText("0"); // 버튼이벤트 설정 (누르면 0으로 초기화)
			}
		});
		btncancelPoints.setBackground(Color.RED);
		btncancelPoints.setForeground(Color.WHITE);
		btncancelPoints.setFont(new Font("굴림", Font.BOLD, 10));
		btncancelPoints.setBounds(250, 10, 77, 30);
		usedPointsDetail.add(btncancelPoints);

		JTextField notice = new JTextField();
		notice.setForeground(new Color(250, 128, 114));
		notice.setHorizontalAlignment(SwingConstants.RIGHT);
		notice.setText("(1,000P 단위로 사용가능)");
		notice.setBorder((new LineBorder(new Color(0, 0, 0), 0)));
		notice.setBounds(47, 10, 193, 30);
		notice.setFont(new Font("굴림", Font.BOLD, 14));
		usedPointsDetail.add(notice);

		JTextField P2 = new JTextField();
		P2.setText("P");
		P2.setBounds(430, 10, 23, 30);
		P2.setFont(new Font("굴림", Font.BOLD, 16));
		P2.setBorder((new LineBorder(new Color(0, 0, 0), 0)));
		usedPointsDetail.add(P2);

		// 총결제금액
		JLabel total = new JLabel("\uCD1D\uACB0\uC81C\uAE08\uC561");
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setBackground(Color.GRAY);
		total.setForeground(Color.WHITE);
		total.setFont(new Font("굴림", Font.BOLD, 15));
		total.setBounds(12, 364 - 50, 136, 49);
		miniPanel_payment.add(total);

		totalAmount = new JLabel();
		totalAmount.setBackground(Color.WHITE);
		totalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		totalAmount.setBounds(148, 364 - 50, 464, 49);
		totalAmount.setForeground(Color.ORANGE);
		totalAmount.setFont(new Font("굴림", Font.BOLD, 15));
		miniPanel_payment.add(totalAmount);

		JPanel miniPanel_paymentMethod = new JPanel();
		miniPanel_paymentMethod.setBackground(Color.GRAY);
		miniPanel_paymentMethod.setBounds(23, 450 - 50, 646, 270);
		miniPanel_paymentMethod.setBounds(23, 450 - 50, 300, 200);
		paymentPanel.add(miniPanel_paymentMethod);
		miniPanel_paymentMethod.setLayout(null);

		JTextField Paytext = new JTextField("결제 방법을 선택하시고 다음버튼을 눌러주세요.");
		Paytext.setBackground(Color.GRAY);
		Paytext.setHorizontalAlignment(SwingConstants.CENTER);
		Paytext.setBounds(130, 5 - 50, 403, 28);
		Paytext.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		Paytext.setFont(new Font("굴림", Font.BOLD, 15));
		Paytext.setForeground(Color.ORANGE);
		miniPanel_paymentMethod.add(Paytext);

		JLabel payCard = new JLabel(new ImageIcon("image\\credit img.jpg"));
		payCard.setSize(127, 100);
		payCard.setLocation(55, 60);
		payCard.setFont(new Font("굴림", Font.BOLD, 15));
		payCard.setForeground(Color.ORANGE);
		payCard.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		miniPanel_paymentMethod.add(payCard);

		JRadioButton payWithCard = new JRadioButton("카드결제(삼성페이가능)");

		payWithCard.setBackground(Color.GRAY);
		payWithCard.setBounds(40, 160, 177, 23);
		payWithCard.setFont(new Font("굴림", Font.PLAIN, 12));
		payWithCard.setForeground(Color.WHITE);
		miniPanel_paymentMethod.add(payWithCard);

		// 구입 패널

		// 결제 패널

		// 이전버튼 -누르면 시간권/기간권 선택창 이동
		JButton btnPrevious2 = new JButton("<<이전");
		btnPrevious2.setFont(new Font("굴림", Font.BOLD, 18));
		btnPrevious2.setBackground(Color.ORANGE);
		btnPrevious2.setBounds(0, 650, 345, 63);
		btnPrevious2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentPanel.setVisible(false);
				setVisible(true);
				buyPanel.setVisible(true);

			}
		});
		paymentPanel.add(btnPrevious2);

		// 다음버튼(결제버튼)
		JButton btnNext2 = new JButton(">>결제");
		btnNext2.setFont(new Font("굴림", Font.BOLD, 18));
		btnNext2.setBackground(Color.ORANGE);
		btnNext2.setBounds(345, 650, 345, 63);
		btnNext2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("총값:" + strCart);
				System.out.println("구매내역:" + strPaymentHistory);

				System.out.println("결제 전 paymentHito : " + userInfo.getPaymentHistory());
				System.out.println("결제 전 cart : " + userInfo.getCart());
				System.out.println("결제 전 freeAday : " + userInfo.getFreeADayPassTime());
				System.out.println("결제 전 freeDays : " + userInfo.getFreeDaysPassTime());
				System.out.println("결제 전 freeWeek : " + userInfo.getFreeWeekPassPeriod());

				System.out.println("결제 전 point : " + Point);

				// 포인트 누적
				try {
					if (userInfo.getPoint().equals("")) {
						System.out.println("userInfo.getPoint()==\" \"");
						userInfo.setPoint(Point);
					} else {
						System.out.println("! userInfo.getPoint()==\" \"");

						int p1 = Integer.parseInt(userInfo.getPoint());
						int p2 = Integer.parseInt(Point);

						userInfo.setPoint((p1 + p2) + "");
					}

					if (userInfo.getCart().equals("")) {
						userInfo.setCart(strCart);
						userInfo.setPaymentHistory(strPaymentHistory);
						setTime();

					} else {
						userInfo.setCart(userInfo.getCart() + ";" + strCart);
						userInfo.setPaymentHistory(userInfo.getPaymentHistory() + ";" + strPaymentHistory);
						setTime();

					}
				} catch (NumberFormatException exception) {
					System.out.println("오류");
				}
				System.out.println("결제 후 paymentHito : " + userInfo.getPaymentHistory());
				System.out.println("결제 후 cart : " + userInfo.getCart());
				System.out.println("결제 후 freeAday : " + userInfo.getFreeADayPassTime());
				System.out.println("결제 후 freeDays : " + userInfo.getFreeDaysPassTime());
				System.out.println("결제 후 freeWeek : " + userInfo.getFreeWeekPassPeriod());
				System.out.println("결제 후 group : " + userInfo.getGroupADayPassTime());
				////////////////// 아래 부분은 /////////////////

				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}

				setVisible(false);
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime);
				MainPanel.frame.move(afterLogin);

			}
		});
		paymentPanel.add(btnNext2);
	}

	public void setTime() { // 수정

		System.out.println(strCart.charAt(1));

		if (strCart.charAt(4) == '1' & strCart.charAt(0) == '자') {
			System.out.println("1회권 일치");

			String removeRegEx = ".*\\("; //
			System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));

			int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));

			if (userInfo.getFreeADayPassTime().equals("")) {
				System.out.println("********첫번째 결제*************");
				userInfo.setFreeADayPassTime(twoDigit(n2) + ":00:00");

			} else {
				int n1 = Integer.parseInt(userInfo.getFreeADayPassTime().substring(0, 2));
				int sum = n1 + n2;
				System.out.println("TESTEST : " + sum);

				userInfo.setFreeADayPassTime(userInfo.getFreeADayPassTime()
						.replace(userInfo.getFreeADayPassTime().substring(0, 2), twoDigit(sum) + "")); // 특정 값이 추가될 때,
				System.out.println("********누적 결제*************");
			}

		} else if (strCart.charAt(4) == '시') {
			System.out.println("시간권 일치");

			String removeRegEx = ".*\\("; //

			int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));

			if (userInfo.getFreeDaysPassTime().equals("")) {
				System.out.println("********첫번째 결제*************");
				userInfo.setFreeDaysPassTime(twoDigit(n2) + ":00:00");

			} else {
				System.out.println("********누적 결제*************");
				System.out.println(n2);
				int n1 = Integer.parseInt(userInfo.getFreeDaysPassTime().substring(0, 2));
				int sum = n1 + n2;
				System.out.println("TESTEST : " + sum);

				userInfo.setFreeDaysPassTime(userInfo.getFreeDaysPassTime()
						.replace(userInfo.getFreeDaysPassTime().substring(0, 2), twoDigit(sum) + "")); // 특정 값이 추가될 때,

			}

		} else if (strCart.charAt(4) == '기') {
			System.out.println("기간권 일치");

			Integer.parseInt(strCart.charAt(8) + "");
			String removeRegEx = ".*\\("; //
			System.out.println(Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7);

			int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7;
			int dayPass = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7;

			try {
				if (userInfo.getFreeWeekPassLastDay().equals("")) {
					System.out.println("********첫번째 결제*************");
					userInfo.setFreeWeekPassLastDay(returnDays(WeekPass.lastDayCal(dayPass)));
					System.out.println(dayPass);

				} else {
					System.out.println("********누적 결제*************");
					System.out.println(dayPass);
					System.out.println(n2);
					int n1 = Integer.parseInt(userInfo.getFreeWeekPassLastDay());
					int sum = n1 + dayPass;
					System.out.println("TESTEST : " + sum);

					userInfo.setFreeWeekPassLastDay(sum + "");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (strCart.charAt(0) == '5') {
			System.out.println("5인석 일치");
			String removeRegEx = ".*\\(";

			System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));

			int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));

			if (userInfo.getGroupADayPassTime().equals("")) {
				System.out.println("********첫번째 결제*************");
				userInfo.setGroupADayPassTime(twoDigit(n2) + ":00:00");

			} else {
				int n1 = Integer.parseInt(userInfo.getGroupADayPassTime().substring(0, 2));
				int sum = n1 + n2;
				System.out.println("TESTEST : " + sum);

				userInfo.setGroupADayPassTime(userInfo.getGroupADayPassTime()
						.replace(userInfo.getGroupADayPassTime().substring(0, 2), twoDigit(sum) + "")); // 특정 값이 추가될 때,
				System.out.println("********누적 결제*************");
			}

		}

	}

	static String twoDigit(int t) {
		return (0 <= t && t < 10) ? "0" + t : "" + t;
	}

	public String returnDays(String inputDay) throws ParseException {

		Calendar today = Calendar.getInstance();
		today.setTime(new Date()); // 금일 날짜

		String lastDay = inputDay;
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lastDay);
		Calendar cmpDay = Calendar.getInstance();
		cmpDay.setTime(date); // 특정 일자

		long diffSec = (cmpDay.getTimeInMillis() - today.getTimeInMillis()) / 1000;
		long diffDays = diffSec / (24 * 60 * 60); // 일자수 차이

		return (diffDays + "");

	}

	public void usePointsMethod() {
		int pointcal = 0;
		usedPoint = userInfo.getPoint();
		try {
			if (btnUseAllPoints.isSelected()) {// 포인트사용 버튼을 눌렀을 경우
				System.out.println("포인트를 사용한다");
				if (Integer.parseInt(userInfo.getPoint()) >= 1000) {
					totalAmount.setText((Integer.parseInt(s2) - Integer.parseInt(userInfo.getPoint())) + "");

					if (s2.length() == 4) {
						totalAmount.setText(
								totalAmount.getText().substring(0, 1) + "," + totalAmount.getText().substring(1) + "원");
						s2 = totalAmount.getText().replace(",", "");
						s2 = s2.replace("원", "");
						System.out.println(s2 + "포인트사용 천단위");

					} else if (s2.length() == 5) {
						totalAmount.setText(
								totalAmount.getText().substring(0, 2) + "," + totalAmount.getText().substring(2) + "원");
						s2 = totalAmount.getText().replace(",", "");
						s2 = s2.replace("원", "");
						System.out.println(s2 + "포인트사용 만단위");
					} else {
						totalAmount.setText(
								totalAmount.getText().substring(0, 2) + "," + totalAmount.getText().substring(2) + "원");
						s2 = totalAmount.getText().replace(",", "");
						s2 = s2.replace("원", "");
						System.out.println(s2 + "포인트사용 십만단위");
					}

					// 카트, 결제내역 저장
					pointcal = Integer.parseInt(s2);
					System.out.println(pointcal + "포인트칼이다");
					Point = (pointcal / 20) + "";
					System.out.println(Point + "포인트제발 변경된값나와라");

					strCart = s1;
					strPaymentHistory = s1 + "/" + s2 + "/" + loginDate + "/" + Point + "/" + usedPoint + "/" + "이용중";
					System.out.println(s2 + "//포인트 차감된 금액");
					
					userInfo.setPoint(Point);
				}

			} else {
				// 포인트사용을 누르지 않은 보통의 경우
				pointcal = Integer.parseInt(s2);
				Point = (pointcal / 20) + "";

				// 카트, 결제내역 저장
				strCart = s1;
				strPaymentHistory = s1 + "/" + s2 + "/" + loginDate + "/" + Point + "/" + usedPoint + "/" + "이용중";
			}

		} catch (NumberFormatException exception) {
			// System.out.println("error입니다");
		}

	}

}

//public void setTime() { // 수정
////String strCart = "자유석 1회권(2시간)";
////String strCart = "자유석 1회권(추가 1시간)";
////String strCart = "자유석 시간권(20시간)";
////String strCart = "자유석 시간권(100시간)";
////String strCart = "자유석 기간권(4주)";
////String strCart = "5인실 1회권(2시간)";
////String strCart = "5인실 1회권(추가 1시간)";
//
//System.out.println(strCart.charAt(1));
//
//if (strCart.charAt(4) == '1') {
//   System.out.println("1회권 일치");
//
//   if (strCart.charAt(8) == '추') {
//      System.out.println("추가 일치 " + strCart.charAt(11));
//      String removeRegEx = ".*\\(추가\\s"; //
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      int n1 = Integer.parseInt(userInfo.getFreeADayPassTime());
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      userInfo.setFreeADayPassTime(n1 + n2 + "");
//      userInfo.setFreeDaysPassTime("");
//      userInfo.setFreeWeekPassPeriod("");
//
//   } else {
//      String removeRegEx = ".*\\("; //
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//
//      userInfo.setFreeADayPassTime(userInfo.getFreeADayPassTime() + twoDigit(n2) + ":00:00;"); // 특정 값이 추가될 때,
//                                                                           // 다른 시간 or
//                                                                           // 기간값도 공백으로
//                                                                           // 넣어줌
//      userInfo.setFreeDaysPassTime(userInfo.getFreeDaysPassTime() + ";");
//      userInfo.setFreeWeekPassPeriod(userInfo.getFreeWeekPassPeriod() + ";");
//      userInfo.setGroupADayPassTime(userInfo.getGroupADayPassTime() + ";");
//      userInfo.setFreeWeekPassLastDay(userInfo.getFreeWeekPassLastDay() + ";");
//
//   }
//
//} else if (strCart.charAt(4) == '시') {
//   System.out.println("시간권 일치");
//
//   if (strCart.charAt(8) == '추') {
//      System.out.println("추 일치 " + strCart.charAt(11));
//      String removeRegEx = ".*\\(추가\\s"; //
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      int n1 = Integer.parseInt(userInfo.getFreeDaysPassTime());
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      userInfo.setFreeDaysPassTime(n1 + n2 + "");
//      userInfo.setFreeADayPassTime("");
//      userInfo.setFreeWeekPassPeriod("");
//
//   } else {
//      System.out.println("추 비일치 ");
//      String removeRegEx = ".*\\("; //
//
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//
//      userInfo.setFreeADayPassTime(userInfo.getFreeADayPassTime() + ";");
//      userInfo.setFreeDaysPassTime(userInfo.getFreeDaysPassTime() + twoDigit(n2) + ":00:00;");
//      userInfo.setFreeWeekPassPeriod(userInfo.getFreeWeekPassPeriod() + ";");
//      userInfo.setGroupADayPassTime(userInfo.getGroupADayPassTime() + ";");
//      userInfo.setFreeWeekPassLastDay(userInfo.getFreeWeekPassLastDay() + ";");
//
//   }
//
//} else if (strCart.charAt(4) == '기') {
//   System.out.println("기간권 일치");
//
//   Integer.parseInt(strCart.charAt(8) + "");
//   String removeRegEx = ".*\\("; //
//   System.out.println(Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7);
//
//   int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7;
//
//   int dayPass = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", "")) * 7;
//
//   userInfo.setFreeWeekPassLastDay(userInfo.getFreeWeekPassLastDay() + WeekPass.lastDayCal(dayPass) + ";");
//   userInfo.setFreeADayPassTime(userInfo.getFreeADayPassTime() + ";");
//   userInfo.setFreeDaysPassTime(userInfo.getFreeDaysPassTime() + ";");
//   userInfo.setFreeWeekPassPeriod(userInfo.getFreeWeekPassPeriod() + ";");
//   userInfo.setGroupADayPassTime(userInfo.getGroupADayPassTime() + ";");
//
//} else if (strCart.charAt(0) == '5') {
//   System.out.println("5인석 일치");
//
//   if (strCart.charAt(8) == '추') {
//      System.out.println("추 일치");
//      String removeRegEx = ".*\\(추가\\s"; //
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      int n1 = Integer.parseInt(userInfo.getGroupADayPassTime());
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//      userInfo.setGroupADayPassTime(n1 + n2 + "");
//
//   } else {
//      System.out.println("추 비일치 ");
//      String removeRegEx = ".*\\("; //
//      System.out.println(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//
//      int n2 = Integer.parseInt(strCart.replaceAll(removeRegEx, "").replaceAll("\\D", ""));
//
//      userInfo.setGroupADayPassTime(userInfo.getGroupADayPassTime() + twoDigit(n2) + ":00:00;");
//      userInfo.setFreeWeekPassLastDay(userInfo.getFreeWeekPassLastDay() + ";");
//      userInfo.setFreeADayPassTime(userInfo.getFreeADayPassTime() + ";");
//      userInfo.setFreeDaysPassTime(userInfo.getFreeDaysPassTime() + ";");
//      userInfo.setFreeWeekPassPeriod(userInfo.getFreeWeekPassPeriod() + ";");
//
//   }
//
//}
//}