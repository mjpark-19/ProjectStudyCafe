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

public class SignUp extends JPanel {

	// ���� ����
	JFrame frame;

	JPanel signUpPanel;
	JPanel mainPanel;
	JPanel topOrangePanel;

	JLabel lblMainTime;

	KeyPadSignUp CkeyPad;

	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;

	// ������
	public SignUp(JLabel lblMainTime) {

		this.lblMainTime = lblMainTime;

		service = UserService.getInstance();
		try {
			mapInfo = service.findAll();
			list_ID = service.getIdList();
			list_PW = service.getPwList();
		} catch (FindException e) {
			e.printStackTrace();
		}
//		
		initialize();
	}

	// �޼ҵ� ����
	public void initialize() {
		setBackground(Color.GRAY);
		setLayout(null);

		// ���� �ð� ���̺�
		add(lblMainTime);

		// �ٹ̱� ��
		JLabel lblSignUp = new JLabel("ȸ������");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setBounds(130, 15, 150, 20);
		add(lblSignUp);

		JButton btnCertSend = new JButton("������ȣ �߼�");
		btnCertSend.setBounds(470, 258, 151, 47);
		btnCertSend.setBackground(Color.LIGHT_GRAY);
		add(btnCertSend);

		JLabel lblCheckID = new JLabel("ID�� �ڵ��� ��ȣ�� �Է����ּ���.");
		lblCheckID.setBounds(100, 150, 309, 47);
		add(lblCheckID);
		lblCheckID.setFont(new Font("����", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		// Ű�е� ����
		CkeyPad = new KeyPadSignUp(this);
		CkeyPad.textOnPW.setVisible(true);

		JButton btn_checkID = new JButton("�ߺ� Ȯ��");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				checkID(lblCheckID);
			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);

		add(btn_checkID);

		// ��� ���� �� �г�1
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 0, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		add(topOrangePanel);
		topOrangePanel.setLayout(null);

		// �̵� ���� �޼ҵ�
		moveToPanel();
	}

	// ID, PW ���� üũ
	public void finalCheck() {

		if (CkeyPad.textID.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "�ٽ� �ѹ� �ڵ��� ��ȣ�� Ȯ�����ּ���.", "signup", JOptionPane.DEFAULT_OPTION);
			return;
		}

//			for (String string : service.list_ID) {
//				System.out.println("list_ID : "+ string);
//			}

		if (!(list_ID.contains(CkeyPad.textID.getText()))) {

			if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {

				// ���� ������ ID ����
				MainPanel.id = CkeyPad.textID.getText();

				// �� ���� ��ü ����
				userInfo = new User.Builder(MainPanel.id, CkeyPad.textPW.getText()).build();

				// �ʿ� �Ҵ�
				mapInfo.put(MainPanel.id, userInfo);

				// Db Ŭ������ �޼ҵ�� text���Ͽ� ID�� PW ����
				try {
					service.singUp(CkeyPad.textID.getText(), CkeyPad.textPW.getText());
				} catch (FindException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// �α��� �� �޴��� �̵�
//					new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !
				setVisible(false);

				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
				MainPanel.frame.move(afterLogin);

			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�����ּ���", "login", JOptionPane.DEFAULT_OPTION);
			}

		} else {
			JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ� �ִ� ID�Դϴ�", "signup", JOptionPane.DEFAULT_OPTION);
			CkeyPad.textID.setText("010");
		}

	}

	// ID �ߺ�üũ�� ���� �Լ�
	public void checkID(JLabel lblCheckID) {
		String ID = CkeyPad.textID.getText();
		String pattern = "^\\d{11}$"; // ����ǥ���� ���
		Boolean regex = Pattern.matches(pattern, ID);

		// �ߺ�Ȯ�ι�ư Ŭ��

		if (!regex) {
			lblCheckID.setText("�ٽ� �ѹ� �ڵ��� ��ȣ�� Ȯ�����ּ���.");
			CkeyPad.textID.setText("010");
		} else {
			try {
				if (list_ID.contains(CkeyPad.textID.getText())) { // map�� key������ �Է¹��� ID�� �����ϴ� ���
					JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ� �ִ� ID�Դϴ�", "signup", JOptionPane.DEFAULT_OPTION);
					lblCheckID.setText("");
				} else {
					lblCheckID.setText("��� ������ ID�Դϴ�");
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
	}

	// �̵� ���� �޼ҵ�
	public void moveToPanel() {

		// [ó������], [����] ��ư�� ��� Ŭ���� ����
		JButton btnHome = new JButton("ó������");
		btnHome.addActionListener(new MyActionListener());
		btnHome.setBounds(0, 0, 149, 48);
		btnHome.setBackground(Color.ORANGE);
		topOrangePanel.add(btnHome);

		JButton btnBefore = new JButton("����");
		btnBefore.addActionListener(new MyActionListener());
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

	public void reset() {

		CkeyPad.textID.setText("010"); // createUserInfo()���� �Ʒ��� ��ġ�� ��. Main.id ����.
		CkeyPad.textPW.setText("");
		CkeyPad.textPW2.setText("");
		CkeyPad.textOnPW.setVisible(true);
		CkeyPad.textPW.setVisible(false);
		CkeyPad.textCert.setText("");
	}

	public class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			setVisible(false);

			MainPanel mainPanel = new MainPanel(MainPanel.frame);
			MainPanel.frame.move(mainPanel);

		}
	}
}
