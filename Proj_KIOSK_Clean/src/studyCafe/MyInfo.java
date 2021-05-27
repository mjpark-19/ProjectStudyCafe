package studyCafe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class MyInfo extends DB {
	JFrame frame;
	JPanel mainPanel;
	JPanel afterLogInPanel;
	UserInfo userInfo;
	JLabel lblMainTime;
	KeyPadCheckOut CKeyPad;

	JPanel myInfoPanel;
	private JTable seatInfoTable;
	private JTable cartInfoTable;
	private JTable paymentHistoryTable;

	public MyInfo(JFrame frame, JPanel mainPanel, HashMap<String, UserInfo> mapInfo, UserInfo userInfo,
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

	private void initialize() {
		myInfoPanel = new JPanel();
		myInfoPanel.setLayout(null);
		myInfoPanel.setBackground(Color.DARK_GRAY);
		myInfoPanel.setBounds(0, 0, 700, 800);
		frame.getContentPane().add(myInfoPanel);

		// �� ���� ���̺�
		JLabel lblNewLabel = new JLabel("\uAC1C\uC778\uC815\uBCF4");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 85, 116, 32);
		myInfoPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC88C\uC11D/\uC0AC\uBB3C\uD568 \uC774\uC6A9\uD604\uD669");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_1.setBounds(232, 212, 235, 32);
		myInfoPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uBCF4\uC720\uC0C1\uD488");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2.setBounds(287, 398, 116, 32);
		myInfoPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("\uACB0\uC81C\uB0B4\uC5ED");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(287, 575, 116, 32);
		myInfoPanel.add(lblNewLabel_2_1);

		// ��������
		JPanel innerPanel = new JPanel();
		innerPanel.setBounds(41, 127, 618, 50);
		myInfoPanel.add(innerPanel);
		innerPanel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("\uD578\uB4DC\uD3F0\uBC88\uD638 : ");
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(12, 13, 83, 23);
		innerPanel.add(lblNewLabel_3);

		JLabel lblId = new JLabel(String.format("%s-%s-%s", userInfo.getID().substring(0, 3),
				userInfo.getID().substring(3, 7), userInfo.getID().substring(7, userInfo.getID().length()))); // �����
																												// �ڵ�����ȣ
		lblId.setFont(new Font("����", Font.PLAIN, 18));
		lblId.setBounds(94, 13, 244, 23);
		innerPanel.add(lblId);

		JLabel lblPoint = new JLabel(String.format("����Ʈ : %sP", userInfo.getPoint())); // ���� ����Ʈ
		lblPoint.setForeground(Color.RED);
		lblPoint.setBounds(347, 17, 57, 15);
		innerPanel.add(lblPoint);

		// ��ư ����Ʈ KeyPad Ŭ������ ���� - ��� �г��� �� �� ��ư���� ���ļ� �Բ� ���ִ� ��Ȳ ����

		JButton btnNewButton = new JButton("\uD328\uC2A4\uC6CC\uB4DC\uBCC0\uACBD"); // pw�����ư
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myInfoPanel.setVisible(false);
				new FindPW(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);
			}
		});
		btnNewButton.setBounds(408, 6, 119, 37);
		innerPanel.add(btnNewButton);

		// �̵���ư �߰�
		moveOut();

		// add table
		creatTable();

		myInfoPanel.setVisible(true);
		frame.add(myInfoPanel);

	}

	/**
	 * 
	 * @return ������� �������� ������ ����
	 */
	public String[][] returnPaymentHistoryData() {

		String[] arr = userInfo.getPaymentHistory().split(";", -1);
		String[][] data = new String[arr.length][];
		for (int i = 0; i < arr.length; i++) {
			data[i] = arr[i].split("/", -1);
		}
		return data;
	}

	/**
	 * 
	 * @return ������� ������ǰ ������ ����
	 */
	public String[][] returnCartData() {

		String[] cartArr = userInfo.getCart().split(";", -1);
		String[] freeADayPassTime = userInfo.getFreeADayPassTime().split(";", -1);
		String[] freeDaysPassTime = userInfo.getFreeDaysPassTime().split(";", -1);
		String[] freeWeekPassPeriod = userInfo.getFreeWeekPassPeriod().split(";", -1);
		String[][] data = new String[cartArr.length][3];

		for (int i = 0; i < cartArr.length; i++) {
			data[i][0] = cartArr[i];
			data[i][1] = freeADayPassTime[i] + freeDaysPassTime[i];
			data[i][2] = freeWeekPassPeriod[i];
		}

		return data;

	}

	public String[][] returnSeatData() {

		String[][] data = { { "�¼�", userInfo.getSeatNo(), "O" } };

		return data;

	}

	public void creatTable() {

		String[] colNames1 = { "����", "�¼���ȣ", "�Ⱓ" };
		String[] colNames2 = { "��ǰ��", "�ܿ��ð�", "�ܿ��Ⱓ" };
		String[] colNames3 = { "��ǰ��", "�����ݾ�", "������", "��������Ʈ", "�������Ʈ", "����" };
		String[][] data1 = { { "", "", "" } };
		String[][] data2 = { { "", "", "" } };
		String[][] data3 = { { "", "", "", "", "", "" } };

		if (!userInfo.getCart().equals("")) {
			data1 = returnSeatData();
			data2 = returnCartData();
			data3 = returnPaymentHistoryData();
		}

		seatInfoTable = new JTable(data1, colNames1);
		seatInfoTable.getTableHeader().setBackground(Color.black);
		seatInfoTable.getTableHeader().setForeground(Color.white);
		seatInfoTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
		seatInfoTable.setForeground(new Color(0, 0, 0));
		seatInfoTable.setBounds(41, 358, 618, 134);
		JScrollPane sp1 = new JScrollPane(seatInfoTable);
		sp1.setBounds(41, 254, 618, 104);
		myInfoPanel.add(sp1);

		cartInfoTable = new JTable(data2, colNames2);
		cartInfoTable.getTableHeader().setBackground(Color.black);
		cartInfoTable.getTableHeader().setForeground(Color.white);
		cartInfoTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
		cartInfoTable.setBounds(41, 579, 618, 121);
		JScrollPane sp2 = new JScrollPane(cartInfoTable);
		sp2.setBounds(41, 435, 618, 104);
		myInfoPanel.add(sp2);

		paymentHistoryTable = new JTable(data3, colNames3);
		paymentHistoryTable.getTableHeader().setBackground(Color.black);
		paymentHistoryTable.getTableHeader().setForeground(Color.white);
		paymentHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(130);
		paymentHistoryTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
		paymentHistoryTable.setBounds(41, 616, 618, 114);
		JScrollPane sp3 = new JScrollPane(paymentHistoryTable);
		sp3.setBounds(41, 607, 618, 114);
		myInfoPanel.add(sp3);

	}

	public void moveOut() {

		JButton backToHome = new JButton("ó������");
		backToHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myInfoPanel.setVisible(false);
				new AfterLogIn(frame, mainPanel, userInfo, mapInfo);
			}
		});
		backToHome.setBackground(Color.ORANGE);
		backToHome.setFont(new Font("����", Font.BOLD, 15));
		backToHome.setForeground(new Color(0, 0, 0));
		backToHome.setBounds(8, 32, 212, 49);
		backToHome.setBounds(0, 0, 212, 49);
		myInfoPanel.add(backToHome);

		// �α׾ƿ�
		JButton logOut = new JButton("LogOut");
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n\n          �α׾ƿ� ");
				myInfoPanel.setVisible(false);
				mainPanel.setVisible(true);
				Main.id = null;
				userInfo = null;
			}
		});
		logOut.setForeground(new Color(0, 0, 0));
		logOut.setBackground(Color.ORANGE);
		logOut.setFont(new Font("����", Font.BOLD, 15));
		logOut.setBounds(476, 0, 212, 49);
		myInfoPanel.add(logOut);
	}
}
