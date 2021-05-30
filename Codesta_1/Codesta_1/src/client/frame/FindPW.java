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

public class FindPW extends JPanel {

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
	KeyPadSignUp CkeyPad;

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	
	// ������
	public FindPW(JLabel lblMainTime) {
		this.lblMainTime = lblMainTime;

		
			service = UserService.getInstance();
			try {
				mapInfo = service.findAll();
				list_ID = service.getIdList();
				list_PW = service.getPwList();
			} catch (FindException e) {
				e.printStackTrace();
			} 
			System.out.println("FindPW ����");


		initialize();
	}

	// �޼ҵ� ����
	public void initialize() {
		System.out.println("FindPW ����2");

//		findPWPanel = new JPanel();
//		setBounds(0, 0, 700, 800); // �߿�
//		frame.getContentPane().add(findPWPanel);
//		setBackground(Color.DARK_GRAY);

		setBackground(Color.GRAY);
		setLayout(null);
		
		
		inputPanel = new JPanel();
		inputPanel.setBounds(0, 130, 700, 500); // �߿�
		
//		frame.getContentPane().add(findPWPanel);
		inputPanel.setBackground(Color.GRAY);
		inputPanel.setLayout(null);
		inputPanel.setVisible(false);
		add(inputPanel);

		add(lblMainTime);

		// ���� - �ٹ��� ���� ����
		JLabel lbl = new JLabel("��й�ȣ ����");
		lbl.setFont(new Font("����", Font.PLAIN, 15));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(130, 5, 200, 40);
		add(lbl);

		// �ٹ��� ���� ����
		btnCert = new JButton("������ȣ �߼�");
		btnCert.setBackground(Color.LIGHT_GRAY);
		btnCert.setBounds(470, 258, 151, 47);
		add(btnCert);
//		inputPanel.add(btnCert);

		lblCheckID = new JLabel("ID�� �ڵ��� ��ȣ�� �Է����ּ���.");
		lblCheckID.setBounds(100, 150, 309, 47);
		add(lblCheckID);
//		inputPanel.add(lblCheckID);
		lblCheckID.setFont(new Font("����", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		lblInputPW = new JLabel("���ο� ��й�ȣ 4�ڸ��� �Է����ּ���.");
		lblInputPW.setBounds(100, 300, 309, 47);
		add(lblInputPW);
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
		add(btn_checkID);

		// Ű�е� Ŭ���� ��������
		CkeyPad = new KeyPadSignUp(this);
		CkeyPad.textOnPW.setVisible(true);

		// ��� ���� �� �г�1(���)
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 0, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		add(topOrangePanel);
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
		btnAfterLogin.addActionListener( new MyActionListener());
		btnAfterLogin.setBounds(0, 0, 149, 48);
		btnAfterLogin.setBackground(Color.ORANGE);
		topOrangePanel.add(btnAfterLogin);

		JButton btnBefore = new JButton("����");
		btnBefore.addActionListener( new MyActionListener());
		btnBefore.setBackground(Color.ORANGE);
		btnBefore.setBounds(0, 702, 343, 61);
		add(btnBefore);

		JButton btnNext = new JButton("���� �Ϸ�");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				finalCheck();
				reset();
			}
		});
		btnNext.setBackground(Color.ORANGE);
		btnNext.setBounds(343, 702, 343, 61);
		add(btnNext);

	}

	// ���� ��й�ȣ ����
	public void finalCheck() {

		if (CkeyPad.textID.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "�ٽ� �ѹ� �ڵ��� ��ȣ�� Ȯ�����ּ���.", "��й�ȣ ã��", JOptionPane.DEFAULT_OPTION);
			return;
		}

		if ((list_ID.contains(CkeyPad.textID.getText()))) {

			if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {

				MainPanel.id = CkeyPad.textID.getText();
				userInfo = mapInfo.get(MainPanel.id);

				System.out.println(userInfo);

				list_PW.set(list_ID.indexOf(MainPanel.id), CkeyPad.textPW.getText());

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
		add(changePwDonePanel);
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
				if (MainPanel.id != null) {
					setVisible(false);
				} else {
					changePwDonePanel.setVisible(false);
					setVisible(false);
					inputPanel.setVisible(false);
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
	
	public void reset() {

		CkeyPad.textPW.setVisible(false);
		CkeyPad.textID.setText("010"); // finalCheck()���� �Ʒ��� ��ġ�� ��. Main.id ����.
		CkeyPad.textPW.setText("");
		CkeyPad.textPW2.setText("");
		CkeyPad.textCert.setText("");
		
	}
	public class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			
			if (MainPanel.id != null) {
				setVisible(false);
				MyInfo myInfo = new MyInfo(userInfo, lblMainTime);
				MainPanel.frame.move(myInfo);
			} else {
				setVisible(false);
				MainPanel mainPanel = new MainPanel(MainPanel.frame); 
				MainPanel.frame.move(mainPanel);
				
			}

		}
	};
}
