package studyCafe5;

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

	// ���� ����
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

	// ������
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

	// �޼ҵ� ����
	public void initialize() {
		findPWPanel = new JPanel();
		findPWPanel.setBounds(0, 0, 700, 800); // �߿�
		frame.getContentPane().add(findPWPanel);
		findPWPanel.setBackground(Color.DARK_GRAY);

		findPWPanel.setLayout(null);
		findPWPanel.setFocusable(true); // false������ idĭ�� focus�� ���� Ű�е� ���� ����

		inputPanel = new JPanel();
		inputPanel.setBounds(0, 130, 700, 500); // �߿�
		frame.getContentPane().add(findPWPanel);
		inputPanel.setBackground(Color.GRAY);
		inputPanel.setLayout(null);
		inputPanel.setVisible(false);
		findPWPanel.add(inputPanel);

		findPWPanel.add(lblMainTime);

		// ���� - �ٹ��� ���� ����
		JLabel lbl = new JLabel("Sign Up");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(181, 65, 48, 14);
		findPWPanel.add(lbl);

		// �ٹ��� ���� ����
		btnCert = new JButton("������ȣ �߼�");
		btnCert.setBackground(Color.LIGHT_GRAY);
		btnCert.setBounds(470, 258, 151, 47);
		findPWPanel.add(btnCert);
//		inputPanel.add(btnCert);

		lblCheckID = new JLabel("ID�� �ڵ��� ��ȣ�� �Է����ּ���.");
		lblCheckID.setBounds(100, 150, 309, 47);
		findPWPanel.add(lblCheckID);
//		inputPanel.add(lblCheckID);
		lblCheckID.setFont(new Font("����", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		lblInputPW = new JLabel("���ο� ��й�ȣ 4�ڸ��� �Է����ּ���.");
		lblInputPW.setBounds(100, 300, 309, 47);
		findPWPanel.add(lblInputPW);
//		inputPanel.add(lblInputPW);

		lblInputPW.setFont(new Font("����", Font.PLAIN, 15));
		lblInputPW.setForeground(Color.WHITE);

		btn_checkID = new JButton("���� Ȯ��");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ID�� üũ�ϴ� �޼ҵ�
				checkID(lblCheckID);

			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);
		findPWPanel.add(btn_checkID);
//		inputPanel.add(btn_checkID);

		// Ű�е� Ŭ���� ��������
		CkeyPad = new KeyPadSignUp(frame, findPWPanel);
		CkeyPad.textOnPW.setVisible(true);

		// ��� ���� �� �г�1(���)
		JPanel topTitlePanel = new JPanel();
		topTitlePanel.setBounds(0, 0, 686, 45);
		findPWPanel.add(topTitlePanel);
		topTitlePanel.setLayout(null);

		// ��� ���� �� �г�1(���)
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 45, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		findPWPanel.add(topOrangePanel);
		topOrangePanel.setLayout(null);

		// ������ �̵� ���� �޼ҵ�
		moveToPanel();
	}

	// ID �ߺ�üũ�� ���� �Լ�
	public void checkID(JLabel lblCheckID) {
		String ID = CkeyPad.textID.getText();
		String pattern = "^\\d{11}$"; // ����ǥ���� ���
		Boolean regex = Pattern.matches(pattern, ID);

		// �ߺ�Ȯ�� ��ư Ŭ��
		System.out.println("FindPW list_ID : " + list_ID);
		if (!regex) {
			lblCheckID.setText("�ٽ� �ѹ� �ڵ��� ��ȣ�� Ȯ�����ּ���.");
			CkeyPad.textID.setText("010");
		} else {
			try {
				if (list_ID.contains(CkeyPad.textID.getText())) { // map�� key������ �Է¹��� ID�� �����ϴ� ���
					JOptionPane.showMessageDialog(null, "���ԵǾ� �ִ� ID�Դϴ�", "��й�ȣ ã��", JOptionPane.DEFAULT_OPTION);
					lblCheckID.setText("");
				} else {
					lblCheckID.setText("�������� �ʴ� ID�Դϴ�");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

	public void moveToPanel() {

		JButton btnAfterLogin = new JButton("ó������");
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

		JButton btnBefore = new JButton("����");
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

		JButton btnNext = new JButton("���� �Ϸ�");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				finalCheck();

				CkeyPad.textPW.setVisible(false);
				CkeyPad.textID.setText("010"); // finalCheck()���� �Ʒ��� ��ġ�� ��. Main.id ����.
				CkeyPad.textPW.setText("");
				CkeyPad.textPW2.setText("");
				CkeyPad.textCert.setText("");
			}
		});
		btnNext.setBackground(Color.ORANGE);
		btnNext.setBounds(343, 702, 343, 61);
		findPWPanel.add(btnNext);

	}

	// ���� ��й�ȣ ����
	public void finalCheck() {

		if (CkeyPad.textID.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "�ٽ� �ѹ� �ڵ��� ��ȣ�� Ȯ�����ּ���.", "��й�ȣ ã��", JOptionPane.DEFAULT_OPTION);
			return;
		}

		if ((list_ID.contains(CkeyPad.textID.getText()))) {

			if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {

				Main.id = CkeyPad.textID.getText();
				userInfo = mapInfo.get(Main.id);

				System.out.println(userInfo);

				list_PW.set(list_ID.indexOf(Main.id), CkeyPad.textPW.getText());

				// Db Ŭ������ �޼ҵ�� text���Ͽ� PW ����
//			        createDbInfo(CkeyPad.textID.getText(), CkeyPad.textPW.getText()); // ȸ�����Կ� �޼ҵ�

				// ���� �¼� ���� �� Ȯ�� �ǻ� ����(���� �г�)
				JPanel changePwDonePanel = new JPanel();
				changePwDonePanel.setVisible(!changePwDonePanel.isVisible());
				changePWDone(changePwDonePanel);

			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�����ּ���", "��й�ȣ ã��", JOptionPane.DEFAULT_OPTION);
			}

		} else {
			JOptionPane.showMessageDialog(null, "�������� �ʴ� ID�Դϴ�", "��й�ȣ ã��", JOptionPane.DEFAULT_OPTION);
			CkeyPad.textID.setText("010");
		}
	}

	public void changePWDone(JPanel changePwDonePanel) {

		System.out.println("��й�ȣ���� �Ϸ� �ȳ� �г� ����");
		changePwDonePanel.setBounds(185, 191, 329, 189);
//		changePwDonePanel.setBounds(50, 130, 600, 300);
		findPWPanel.add(changePwDonePanel);
		changePwDonePanel.setLayout(null);
		changePwDonePanel.setVisible(true);

		// �Ϸ� �г��� ���̱� ���� �ڿ� �ؽ�Ʈ, ID, PW ���� ����
		lblCheckID.setVisible(false);
		lblInputPW.setVisible(false);
		btn_checkID.setVisible(false);
		btnCert.setVisible(false);

		CkeyPad.textID.setVisible(false);
		CkeyPad.textPW.setVisible(false);
		CkeyPad.textPW2.setVisible(false);
		CkeyPad.textOnPW.setVisible(false);
		CkeyPad.textCert.setVisible(false);

		JButton btnCancel = new JButton("�ݱ�");
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

		JLabel lblSeatNumQuestion = new JLabel("��й�ȣ ������ �Ϸ�Ǿ����ϴ�.");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(35, 70, 246, 37);
		changePwDonePanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		changePwDonePanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelect = new JLabel("�ȳ�");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 50, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}

}
