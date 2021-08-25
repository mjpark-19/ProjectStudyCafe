package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

import client.keypad.KeyPadCheckOut;

public class MyInfo extends JPanel {
	JFrame frame;
	JPanel mainPanel;
	JPanel afterLogInPanel;
//	UserInfo userInfo;
	JLabel lblMainTime;
	KeyPadCheckOut CKeyPad;

	JPanel myInfoPanel;

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;

	private JTable seatInfoTable;
	private JTable cartInfoTable;
	private JTable paymentHistoryTable;

	public MyInfo(User userInfo, JLabel lblMainTime) {
		this.userInfo = userInfo;
		this.lblMainTime = lblMainTime;

		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
			this.userInfo = mapInfo.get(MainPanel.id);

		} catch (FindException e) {
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {

		setBackground(Color.GRAY);
		setLayout(null);

		// �� ���� ���̺�
		JLabel lblNewLabel = new JLabel("\uAC1C\uC778\uC815\uBCF4");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 85, 116, 32);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC88C\uC11D/\uC0AC\uBB3C\uD568 \uC774\uC6A9\uD604\uD669");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_1.setBounds(232, 212, 235, 32);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uBCF4\uC720\uC0C1\uD488");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2.setBounds(287, 398, 116, 32);
		add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("\uACB0\uC81C\uB0B4\uC5ED");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1.setFont(new Font("����", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(287, 575, 116, 32);
		add(lblNewLabel_2_1);

		// ��������
		JPanel innerPanel = new JPanel();
		innerPanel.setBounds(41, 127, 618, 50);
		add(innerPanel);
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
		lblPoint.setBounds(320, 17, 100, 15);
		innerPanel.add(lblPoint);

		// ��ư ����Ʈ KeyPad Ŭ������ ���� - ��� �г��� �� �� ��ư���� ���ļ� �Բ� ���ִ� ��Ȳ ����

		JButton btnNewButton = new JButton("\uD328\uC2A4\uC6CC\uB4DC\uBCC0\uACBD"); // pw�����ư
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FindPW findPw = new FindPW(lblMainTime);
				MainPanel.frame.move(findPw);
			}
		});
		btnNewButton.setBounds(480, 6, 119, 37);
		innerPanel.add(btnNewButton);

		// �̵���ư �߰�
		moveOut();
		creatTable();

	}

	/**
	 * 
	 * @return ������� �������� ������ ����
	 */
	public String[][] returnPaymentHistoryData() {
		String[] arr = userInfo.getPaymentHistory().split(";", -1);
		String[][] data = new String[arr.length][];

		try {

			for (int i = 0; i < arr.length; i++) {
				data[i] = arr[i].split("/", -1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}

	/**
	 * 
	 * @return ������� ������ǰ ������ ����
	 */
	public String[][] returnCartData() {

		String[] cartArr = userInfo.getCart().split(";", -1);
		for (int i = 0; i < cartArr.length; i++) {
			cartArr[i] = cartArr[i].substring(0, 7);
		}

		LinkedHashSet<String> cartSet = new LinkedHashSet<String>();

		for (String cart : cartArr) {
			cartSet.add(cart);
		}

		Iterator<String> it = cartSet.iterator();

		String[][] data = new String[cartSet.size()][3];

		try {
			for (int i = 0; i < cartSet.size(); i++) {
				data[i][0] = it.next();
				System.out.println("test" + data[i][0]);
				if (data[i][0].charAt(0) == '��' & data[i][0].charAt(4) == '1') {
					data[i][1] = userInfo.getFreeADayPassTime();
					data[i][2] = "";
				} else if (data[i][0].charAt(4) == '��') {
					data[i][1] = userInfo.getFreeDaysPassTime();
					data[i][2] = "";
				} else if (cartArr[i].charAt(0) == '5') {
					data[i][1] = userInfo.getGroupADayPassTime();
					data[i][2] = "";
				} else {
					data[i][1] = "";
					data[i][2] = userInfo.getFreeWeekPassLastDay();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return data;

	}

	public String[][] returnSeatData() {

		String[][] data = { { "�¼�", userInfo.getSeatNo() + userInfo.getRoomNo(), "-" } };

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
		add(sp1);
		alignCenter(seatInfoTable);

		cartInfoTable = new JTable(data2, colNames2);
		cartInfoTable.getTableHeader().setBackground(Color.black);
		cartInfoTable.getTableHeader().setForeground(Color.white);
		cartInfoTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
		cartInfoTable.setBounds(41, 579, 618, 121);
		JScrollPane sp2 = new JScrollPane(cartInfoTable);
		sp2.setBounds(41, 435, 618, 104);
		add(sp2);
		alignCenter(cartInfoTable);

		paymentHistoryTable = new JTable(data3, colNames3);
		paymentHistoryTable.getTableHeader().setBackground(Color.black);
		paymentHistoryTable.getTableHeader().setForeground(Color.white);
		paymentHistoryTable.getColumnModel().getColumn(0).setPreferredWidth(130);
		paymentHistoryTable.getTableHeader().setPreferredSize(new Dimension(0, 35));
		paymentHistoryTable.setBounds(41, 616, 618, 114);
		JScrollPane sp3 = new JScrollPane(paymentHistoryTable);
		sp3.setBounds(41, 607, 618, 114);
		add(sp3);
		alignCenter(paymentHistoryTable);

	}

	public void alignCenter(JTable table) {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();

		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {

			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);

		}
	}

	public void moveOut() {

		JButton backToHome = new JButton("ó������");
		backToHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime);
				MainPanel.frame.move(afterLogin);
			}
		});
		backToHome.setBackground(Color.ORANGE);
		backToHome.setFont(new Font("����", Font.BOLD, 15));
		backToHome.setForeground(new Color(0, 0, 0));
		backToHome.setBounds(8, 32, 212, 49);
		backToHome.setBounds(0, 0, 212, 49);
		add(backToHome);

		// �α׾ƿ�
		JButton logOut = new JButton("LogOut");
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n\n\n          �α׾ƿ� ");
				setVisible(false);

				MainPanel mainPanel = new MainPanel(MainPanel.frame);
				MainPanel.frame.move(mainPanel);

				MainPanel.id = null;
				userInfo = null;
			}
		});
		logOut.setForeground(new Color(0, 0, 0));
		logOut.setBackground(Color.ORANGE);
		logOut.setFont(new Font("����", Font.BOLD, 15));
		logOut.setBounds(476, 0, 212, 49);
		add(logOut);
	}
}
