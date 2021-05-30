package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

import client.time.CheckOutCal;
import client.time.TimeCal;

public class AfterLogIn extends JPanel {

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

//	HashMap<String, UserInfo> mapInfo;
//	UserInfo userInfo;

	Map<String, User> mapInfo;
	User userInfo;
	UserService service;

	static int pageCnt = 0;

	// ������ ( ���� �α���, ���� ���� ���� ��)

	public AfterLogIn(User userInfo, JLabel lblMainTime) {
		
		this.lblMainTime = lblMainTime;
		add(lblMainTime);
		
		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
		} catch (FindException e) {
			e.printStackTrace();
		} 
		this.userInfo = mapInfo.get(MainPanel.id);
		
		
		System.out.println("�α��� �� ����");
		initialize();
	}

	// �޼ҵ� ����
	public void initialize() {

		setBackground(Color.GRAY);
		setLayout(null);
		
		

		JLabel lblID = new JLabel(MainPanel.id + " �� �ݰ����ϴ�.");
		lblID.setFont(new Font("����", Font.BOLD, 17));
		lblID.setForeground(Color.WHITE);
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(160, 0, 300, 30);
		add(lblID);


		// �ð��� �������� �̵�
		JButton btnTimePurchase = new JButton("�ð��� ����");
		btnTimePurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				Payment payment = new Payment(userInfo, lblMainTime);
				MainPanel.frame.move(payment);
				
			}
		});
		btnTimePurchase.setBackground(Color.ORANGE);
		btnTimePurchase.setBounds(342, 420, 170, 132);
		add(btnTimePurchase);

		// �Ⱓ�� �������� �̵�
		JButton btnPeriodPurchase = new JButton("�Ⱓ�� ����");
		btnPeriodPurchase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				Payment payment = new Payment(userInfo, lblMainTime);
				MainPanel.frame.move(payment);
//				new Payment(frame, mainPanel, userInfo);
			}
		});
		btnPeriodPurchase.setBackground(Color.WHITE);
		btnPeriodPurchase.setBounds(513, 420, 170, 132);
		add(btnPeriodPurchase);

		// �ð��� �̿� �г� ����
		JButton btnUseTime = new JButton("�ð��� �̿�");
		btnUseTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel useTimePanel = new JPanel();
				useTimePanel.setVisible(false);
				System.out.println("userInfo.getUserType().equals(\"\")"+ userInfo.getUserType());
				System.out.println("userInfo.getUserType().equals(\"\")"+ userInfo.getID());
				if ((userInfo.getUserType().equals(""))) {
					useSmallPanelOpen(useTimePanel, "time");
				} else {
					doubleUseCheck(useTimePanel);
				}

			}
		});
		btnUseTime.setBackground(Color.ORANGE);
		btnUseTime.setBounds(0, 420, 170, 132);
		add(btnUseTime);

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
		add(btnUsePeriod);

		JButton btnMoveSeat = new JButton("�¼��̵�");
		btnMoveSeat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�¼� �̵� userInfo.getUserType() : " + userInfo.getUserType());
				if (userInfo.getUserType() != "") {
					Seat.tempType = userInfo.getUserType();
					setVisible(false);

					Seat seat = new Seat(userInfo, lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
					MainPanel.frame.move(seat);
					
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
		add(btnMoveSeat);

		// ������ Ȯ��
		JButton btnMyInfo = new JButton("�� ����");
		btnMyInfo.setForeground(new Color(255, 255, 255));
		btnMyInfo.setBackground(Color.LIGHT_GRAY);
		btnMyInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				System.out.println("�� ���� Ŭ��");
//				new MyInfo(lblMainTime);

				MyInfo myInfo = new MyInfo(userInfo, lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
				MainPanel.frame.move(myInfo);

			}
		});
		btnMyInfo.setBounds(229, 629, 227, 55);
		add(btnMyInfo);

		JButton btnNewButton_2_1_1 = new JButton("������ ȣ��");
		btnNewButton_2_1_1.setForeground(Color.WHITE);
		btnNewButton_2_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2_1_1.setBounds(457, 629, 227, 55);
		add(btnNewButton_2_1_1);

		LogOut();

	}

	// �Խ� ���� ��ǰ�� ���� ���¿��� �¼� �̵� �г� �� �� - ���� �Խ����� ��ǰ�� �����ϴ�.
	public void emptyUserType(JPanel useTimePanel) {
		useTimePanel.setBounds(106, 87, 494, 294);
		useTimePanel.setBackground(Color.WHITE);
		useTimePanel.setLayout(null);
		add(useTimePanel);
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
		add(useTimePanel);
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

				setVisible(false);
				useTimePanel.setVisible(false);
//				new Seat();
				Seat seat = new Seat(userInfo, lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
				MainPanel.frame.move(seat);
				
				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}
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


		System.out.println("userInfo.getCart().length():"+ userInfo.getCart().length() + " �� ");
		// ������ ��ǰ�� ������ �Ʒ� �ڵ� ����
		if (!userInfo.getCart().equals("")) {

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
		add(bottomLogOutPanel);
//		 bottomLogOutPanel.add(lblTimer); // �ڵ� �α׾ƿ�

		JButton btnLogOut = new JButton("LogOut");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n          �α׾ƿ� ");
				setVisible(false);

				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}
				
				MainPanel.id = null;
				userInfo = null;
			
				
				MainPanel mainPanel = new MainPanel(MainPanel.frame); 
				MainPanel.frame.move(mainPanel);
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
		add(useTimePanel);
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
				setVisible(true);

				// �Խ� �� ��ǰ�� �ִµ�, �ð���/�Ⱓ�� �̿� �޴��� ������ ��� - ���� ��ǰ ��� �ȳ�
				new CheckOutCal(userInfo);
				
				try {
					service.backUp(userInfo);
				} catch (FindException e1) {
					e1.printStackTrace();
				}

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
