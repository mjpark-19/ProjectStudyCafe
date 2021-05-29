package studyCafe;

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

	// ���� ����
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

	// ������ ( ���� �α���, ���� ���� ���� ��)

	public AfterLogIn(JFrame frame, JPanel mainPanel, UserInfo userInfo) {
		System.out.println("After ����");
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.userInfo = userInfo;
		initialize();
	}

	// ������2 ( ȸ�����Կ��� ���� ��)
	public AfterLogIn(JFrame frame, JPanel mainPanel, UserInfo userInfo, HashMap<String, UserInfo> mapInfo) {
		this(frame, mainPanel, userInfo);
		this.mapInfo = mapInfo;
	}

	// �޼ҵ� ����
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

		JLabel lblID = new JLabel(Main.id + " �� �ݰ����ϴ�.");
		lblID.setFont(new Font("����", Font.BOLD, 17));
		lblID.setForeground(Color.WHITE);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(160, 0, 300, 30);
		afterLogInPanel.add(lblID);

		// ���� �ð� ǥ��
		lblMainTime = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("����", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblMainTime.setBounds(536, 0, 150, 34);
		lblMainTime.setHorizontalAlignment(SwingConstants.CENTER);
		afterLogInPanel.add(lblMainTime);

		// �ڵ� �α׾ƿ� ( ���� )
		lblTimer = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("����", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);

		lblTimer.setBounds(400, 10, 150, 34);
		afterLogInPanel.add(lblTimer);

		// ���� �ð� �� �ڵ� �α׾ƿ� ȣ��
		if (AfterLogIn.pageCnt == 0) {
			TimeCal Time = new TimeCal(frame, lblMainTime, lblTimer, mainPanel, afterLogInPanel);
			Thread thread1 = new Thread(Time);
			thread1.start();
		}

		// �ð��� �������� �̵�
		JButton btnTimePurchase = new JButton("�ð��� ����");
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

		// �Ⱓ�� �������� �̵�
		JButton btnPeriodPurchase = new JButton("�Ⱓ�� ����");
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

		// �ð��� �̿� �г� ����
		JButton btnUseTime = new JButton("�ð��� �̿�");
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

		// �Ⱓ�� �̿� �г� ����
		JButton btnUsePeriod = new JButton("�Ⱓ�� �̿�");
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

		JButton btnMoveSeat = new JButton("�¼��̵�");
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

		// ������ Ȯ��
		JButton btnMyInfo = new JButton("�� ����");
		btnMyInfo.setForeground(new Color(255, 255, 255));
		btnMyInfo.setBackground(Color.LIGHT_GRAY);
		btnMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				afterLogInPanel.setVisible(false);
				new MyInfo(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);

			}
		});
		btnMyInfo.setBounds(229, 629, 227, 55);
		afterLogInPanel.add(btnMyInfo);

		JButton btnNewButton_2_1_1 = new JButton("������ ȣ��");
		btnNewButton_2_1_1.setForeground(Color.WHITE);
		btnNewButton_2_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2_1_1.setBounds(457, 629, 227, 55);
		afterLogInPanel.add(btnNewButton_2_1_1);

		LogOut();

	}

	// �Խ� ���� ��ǰ�� ���� ���¿��� �¼� �̵� �г� �� �� - ���� �Խ����� ��ǰ�� �����ϴ�.
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

		JLabel lblCheckIntitle = new JLabel("�¼� �̵�");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckInClose = new JButton("�ݱ�");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		// üũ�� �Ϸ� �г�
		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("���� �Խ����� ��ǰ�� �����ϴ�.");
		lblEmptyCart.setBounds(36, 45, 380, 98);
		checkInDonePanel.add(lblEmptyCart);

	}

	// �ð���/�Ⱓ�� �̿� �г� Ŭ�� ��
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

		JLabel lblCheckIntitle = new JLabel("�Խ�");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		btnCheckIn = new JButton("�Խ�"); // �� ��
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

		JButton btnCheckInClose = new JButton("�ݱ�");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("�����Ͻ� ��ǰ���� �Խ��Ͻðڽ��ϱ�?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblEmptyCart = new JLabel("�̿����� ��ǰ�� �����ϴ�. �ð���/�Ⱓ���� �������ּ���.");
		lblEmptyCart.setVisible(false);
		checkInDonePanel.add(lblEmptyCart);

		// �ð���/�Ⱓ�� �̿� �гο��� �� ��ǰ �����ϱ�
		useTimePanelSelect(checkInDonePanel, lblCheckInQuestion, lblEmptyCart, strTimeWeek);

	}

	// �ð���/�Ⱓ�� �̿� �гο��� �� ��ǰ �����ϱ�
	public void useTimePanelSelect(JPanel checkInDonePanel, JLabel lblCheckInQuestion, JLabel lblEmptyCart,
			String strTimeWeek) {
		System.out.println(" useTimePanelSelect ");

		// ������ ��ǰ�� ������ �Ʒ� �ڵ� ����
		if (userInfo.getCart() != "") {

			// �ð��ǰ� �Ⱓ�� ���� ��ǰ�� String[]�� �и��ϱ�
			String[] aryCart = regExr(userInfo.getCart(), strTimeWeek);
			System.out.println(aryCart.length);

			ButtonGroup btnGroupRadio = new ButtonGroup();

			for (int i = 0; i < aryCart.length; i++) {

				JRadioButton rdbtn = new JRadioButton(aryCart[i]);
				rdbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (e.getActionCommand().contains("5�ν�")) {
							Seat.tempType = "group";
						} else if (e.getActionCommand().contains("�ð���")) {
							Seat.tempType = "time";
						} else if (e.getActionCommand().contains("1ȸ��")) {
							Seat.tempType = "hour";
						} else if (e.getActionCommand().contains("�Ⱓ��")) {
							Seat.tempType = "week";
						}

						// ��ǰ ���� ���� �Խ� �Ұ�(��ư ��Ȱ��ȭ)
						btnCheckIn.setEnabled(true);

					}
				});
				rdbtn.setBounds(45, 10 + 30 * (i + 1), 151, 34);
				checkInDonePanel.add(rdbtn);

				btnGroupRadio.add(rdbtn);
			}

			// ������ ��ǰ�� ���� ��� �Ʒ� �ڵ� ����
		} else {

			lblEmptyCart.setBounds(36, 45, 380, 98);
			lblEmptyCart.setVisible(true);
			lblCheckInQuestion.setVisible(false);

		}

	}

	// �ð��ǰ� �Ⱓ�� ���� ��ǰ�� String[]�� �и��ϱ�
	public String[] regExr(String strCart, String strTimeWeek) {

		String regEx = "";
		if (strTimeWeek == "time")
			regEx = ".{3}\\s��.{5,9}\\)+;?"; // ��Ⱓ�Ǹ� ����� replaceAll

		else if (strTimeWeek == "week")
			regEx = ".{3}\\s(?!��).{5,9}\\);?"; // �Ⱓ�Ǹ� ����� replaceAll

		String[] ary = strCart.replaceAll(regEx, "").split(";");

		return ary;
	}

	public void LogOut() {

		JPanel bottomLogOutPanel = new JPanel();
		bottomLogOutPanel.setLayout(null);
		bottomLogOutPanel.setBackground(Color.ORANGE);
		bottomLogOutPanel.setBounds(0, 713, 686, 48);
		afterLogInPanel.add(bottomLogOutPanel);
//		 bottomLogOutPanel.add(lblTimer); // �ڵ� �α׾ƿ�

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n          �α׾ƿ� ");
				afterLogInPanel.setVisible(false);
				afterLogInPanel = null;

				// �ʱ�ȭ
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

		JLabel lblCheckIntitle = new JLabel("���");
		lblCheckIntitle.setBounds(12, 0, 76, 36);
		checkInTitlePanel.add(lblCheckIntitle);

		JButton btnCheckIn = new JButton("���");
		btnCheckIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				useTimePanel.setVisible(false);
				afterLogInPanel.setVisible(true);

				// �Խ� �� ��ǰ�� �ִµ�, �ð���/�Ⱓ�� �̿� �޴��� ������ ��� - ���� ��ǰ ��� �ȳ�
				new DoubleCheckOut(userInfo);

			}
		});
		btnCheckIn.setBackground(new Color(32, 178, 170));
		btnCheckIn.setBounds(38, 246, 195, 38);
		useTimePanel.add(btnCheckIn);

		JButton btnCheckInClose = new JButton("�ݱ�");
		btnCheckInClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useTimePanel.setVisible(false);
			}
		});
		btnCheckInClose.setBackground(new Color(192, 192, 192));
		btnCheckInClose.setBounds(266, 246, 195, 38);
		useTimePanel.add(btnCheckInClose);

		JLabel lblCheckInQuestion = new JLabel("�Խ� ���� ��ǰ�� �ֽ��ϴ�. ��� ó���Ͻðڽ��ϱ�?");
		lblCheckInQuestion.setBounds(23, 55, 306, 26);
		lblCheckInQuestion.setVisible(true);

		useTimePanel.add(lblCheckInQuestion);

		JPanel checkInDonePanel = new JPanel();
		checkInDonePanel.setBounds(10, 46, 472, 185);
		checkInDonePanel.setLayout(null);
		checkInDonePanel.setVisible(true);
		useTimePanel.add(checkInDonePanel);

		JLabel lblCheckInDone = new JLabel("��� ó���� �Ϸ�Ǿ����ϴ�.");
		lblCheckInDone.setBounds(36, 45, 380, 98);
		lblCheckInDone.setVisible(false);
		checkInDonePanel.add(lblCheckInDone);

	}
}
