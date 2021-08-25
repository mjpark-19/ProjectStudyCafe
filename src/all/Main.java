package all;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class Main extends DB {

	static String id; // ���� ����

	JFrame frame;

	JPanel mainPanel;
	JPanel mainBtnPanel;
	
	JButton btnLogIn;
	JButton btnSignUp;
	JButton btnFindPW;
	JButton btnSeatMap;

	JLabel lblMainTime;

	UserInfo userInfo;
	KeyPadCheckOut CkeyPad;
	ArrayList<Object> obj; // �г� ��ĥ �� ��ư ���߱��

	int y = 150;
	int x = -140;
	// private HashMap<String, UserInfo> mapInfo = null;
//	HashMap<String, UserInfo> mapInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Main window = new Main();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, 700, 800); //
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBounds(0, 0, 700, 800);

		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		mainPanel.setFocusable(true); // false������ idĭ�� focus�� ���� Ű�е� ���� ���� - ��������

		System.out.println("Main list_ID : " + list_ID); // DB���� list_ID �޾������� Ȯ��

		labelSet(); // ���̺� ���� ����

		moveToPanel(); // �̵� ���� �޼ҵ� ���� - (���� �߿� new KeyPadCheckOut ������ ���� �� ��)

		tempMethod(); // �ӽ� �׽�Ʈ�� mapInfo Ȯ�ο� �޼ҵ� - map To print

		// �ٹ̱��
		JPanel panel = new JPanel();
		panel.setBounds(0, 727, 684, 36);
		mainPanel.add(panel);
		panel.setLayout(null);

		// �ٹ̱��
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(521, 10, 57, 15);
		panel.add(lblNewLabel);


	}

	// �̵� ���� �޼ҵ�
	public void moveToPanel() {
		mainPanel.setLayout(null);

		// �α���
		btnLogIn = new JButton("�α���(Login)");
		btnLogIn.setBackground(Color.ORANGE);
		btnLogIn.setBounds(500 + x, 185 + y + 10, 270, 120 - 10);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 11�ڸ� Ȯ��
				if (CkeyPad.textID.getText().length() != 11) {
					JOptionPane.showMessageDialog(null, "ID�� �ڵ��� ��ȣ�Դϴ�.", "login", JOptionPane.DEFAULT_OPTION);
					return;
				}

				// �α��� ���̵� üũ �� �� ���� ��ü ����
				checkIDPW();

				// �ʱ�ȭ
				CkeyPad.textID.setText("010");
				CkeyPad.textPW.setVisible(false);
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("��й�ȣ�� �Է��ϼ���.");
				mainBtnPanel.setVisible(true);
			}
		});
		mainPanel.add(btnLogIn);
		

		mainBtnPanel = new JPanel();
		mainBtnPanel.setBounds(0, 488, 684, 110);
		mainBtnPanel.setBackground(Color.DARK_GRAY);
		mainPanel.add(mainBtnPanel);
		mainBtnPanel.setLayout(null);

		// ȸ������
		btnSignUp = new JButton("ȸ������");
		btnSignUp.setBounds(170, 10, 170, 90);
		mainBtnPanel.add(btnSignUp);
		btnSignUp.setBackground(Color.LIGHT_GRAY);

		// ��й�ȣ ã��
		btnFindPW = new JButton("��й�ȣ ã��");
		btnFindPW.setBounds(342, 10, 170, 90);
		mainBtnPanel.add(btnFindPW);
		btnFindPW.setBackground(Color.LIGHT_GRAY);

		btnSeatMap = new JButton("�ǽð��¼���Ȳ");
		btnSeatMap.setBounds(514, 10, 170, 90);
		mainBtnPanel.add(btnSeatMap);
		btnSeatMap.setBackground(Color.LIGHT_GRAY);
		btnSeatMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);

				if (Main.id == null) { // �¼� ��ġ���� �뵵�� �����Ѵ�. (null�̸� �¼� ����, �̵� �޴��� �ƴ� �¼� �ľǸ� ������)
					userInfo = null;
				}
				new Seat(frame, mainPanel, userInfo); // �¼� ��ġ��, �¼� �̵�, ���� ����
			}
		});
		btnFindPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);
				new FindPW(frame, mainPanel, mapInfo, userInfo, list_ID, list_PW, lblMainTime);

				// �ʱ�ȭ
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("��й�ȣ�� �Է��ϼ���.");
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainPanel.setVisible(false);
				new SignUp(frame, mainPanel, userInfo, mapInfo, lblMainTime);

				// �ʱ�ȭ
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("��й�ȣ�� �Է��ϼ���.");
			}
		});

		// KeyPad Ŭ����
		CkeyPad = new KeyPadCheckOut(frame, mainPanel, userInfo, mapInfo, mainBtnPanel, btnLogIn);

	}

	// �α��� ���̵� üũ �� �� ���� ��ü ����
	public void checkIDPW() {
		System.out.println(list_ID);
		try {

			// ���̵� Ȯ�� �� PW Ȯ��
			if ((list_ID.contains(CkeyPad.textID.getText()))
					&& (CkeyPad.textPW.getText().equals(list_PW.get(list_ID.indexOf(CkeyPad.textID.getText()))))) {

				if (CkeyPad.textID.getText().length() == 11) {
					Main.id = CkeyPad.textID.getText();
				}

				userInfo = mapInfo.get(Main.id); // �α����� ���̵� �ش��ϴ� value(=Userinfo ��ü) �� ����

				mainPanel.setVisible(false);

				new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // �α��� �� �޴��� �̵�

			} else {
				JOptionPane.showMessageDialog(null, "ID�� PW�� Ȯ�����ּ���", "login", JOptionPane.DEFAULT_OPTION);
			}

			// �ʱ�ȭ
			CkeyPad.textID.setText("");
			CkeyPad.textPW.setText("");

		} finally {

		}
	}

	// ���̺� ���� ����
	public void labelSet() {

		// ���� �ð� ǥ��
		lblMainTime = new JLabel("00:00:00");
		lblMainTime.setFont(new Font("����", Font.BOLD, 24));
		lblMainTime.setForeground(Color.WHITE);
		lblMainTime.setBounds(536, 0, 150, 34);
		lblMainTime.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblMainTime);

		// ���� �ð� ȣ��
		TimeCal Time = new TimeCal(lblMainTime);
		Thread thread1 = new Thread(Time);
		thread1.start();
//		

	}

	public void tempMethod() {
		JButton btnMapToPrint = new JButton("mapToPrint");
		btnMapToPrint.setBounds(589, 740, 97, 23);
		btnMapToPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("mapInfo : " + mapInfo + " Main - mapToPrint");
			}
		});
		mainPanel.add(btnMapToPrint);

	}
}
