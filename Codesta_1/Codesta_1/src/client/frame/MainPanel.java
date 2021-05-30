package client.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;




import client.keypad.KeyPadCheckOut;
import client.time.TimeCal;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	
	public static String id; // ���� ����
	private Image img;
//	JPanel mainPanel;
	JPanel mainBtnPanel;
	
	JButton btnLogIn;
	JButton btnSignUp;
	JButton btnFindPW;
	JButton btnSeatMap;

	JLabel lblMainTime;

//	UserInfo userInfo;
	KeyPadCheckOut CkeyPad;
	ArrayList<Object> obj; // �г� ��ĥ �� ��ư ���߱��

	int y = 150;
	int x = -140;
	
	
	ArrayList<String> list_ID;
	ArrayList<String> list_PW;
	Map<String, User> mapInfo;
	User userInfo;
	UserService service;
	
	public static MainFrame frame;
	
	public MainPanel(MainFrame frame) {
		
		MainPanel.frame = frame;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setFocusable(true); // false������ idĭ�� focus�� ���� Ű�е� ���� ���� - ��������
		MainPanel(new ImageIcon("image\\mainimg.jpg").getImage());
		
		service = UserService.getInstance();
		try {
			
			mapInfo = service.findAll();
			list_ID = service.getIdList();
			list_PW = service.getPwList();
			
		} catch (FindException e) {
			e.printStackTrace();
		} 
		
		System.out.println("Main cart "+ mapInfo.get("01012341234").getCart());
		
		System.out.println("Main list_ID : " + list_ID); // DB���� list_ID �޾������� Ȯ��


		labelSet(); // ���̺� ���� ����

		moveToPanel(); // �̵� ���� �޼ҵ� ���� - (���� �߿� new KeyPadCheckOut ������ ���� �� ��)




	}





	// �̵� ���� �޼ҵ�
	public void moveToPanel() {
//		mainPanel.setLayout(null);

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
		add(btnLogIn);
		

		mainBtnPanel = new JPanel();
		mainBtnPanel.setBounds(0, 488, 684, 110);
		mainBtnPanel.setBackground(new Color(255, 255, 240));
		add(mainBtnPanel);
		mainBtnPanel.setLayout(null);

		// ȸ������
		btnSignUp = new JButton("ȸ������");
		btnSignUp.setBounds(170, 10, 170, 90);
		mainBtnPanel.add(btnSignUp);
		btnSignUp.setBackground(Color.LIGHT_GRAY);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				
				SignUp signUp = new SignUp(lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
				MainPanel.frame.move(signUp);
				
				// �ʱ�ȭ
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("��й�ȣ�� �Է��ϼ���.");
			}
		});
		
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

				setVisible(false);

				if (MainPanel.id == null) { // �¼� ��ġ���� �뵵�� �����Ѵ�. (null�̸� �¼� ����, �̵� �޴��� �ƴ� �¼� �ľǸ� ������)
					userInfo = null;
				}
				Seat seat = new Seat(userInfo, lblMainTime); // �¼� ��ġ��, �¼� �̵�, ���� ����
				MainPanel.frame.move(seat);
				
			}
		});
		btnFindPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				FindPW findPw = new FindPW(lblMainTime); 
				MainPanel.frame.move(findPw);
				
				// �ʱ�ȭ
				CkeyPad.textID.setText("010");
				CkeyPad.textOnPW.setVisible(true);
				CkeyPad.textOnPW.setText("��й�ȣ�� �Է��ϼ���.");
			}
		});


		// KeyPad Ŭ����
		CkeyPad = new KeyPadCheckOut(this, mainBtnPanel, btnLogIn);

	}

	// �α��� ���̵� üũ �� �� ���� ��ü ����
	public void checkIDPW() {
		System.out.println("Main checkIDPW - ID :"+list_ID);
		System.out.println("Main checkIDPW - PW :"+list_PW);
		try {

			// ���̵� Ȯ�� �� PW Ȯ��
			if ((list_ID.contains(CkeyPad.textID.getText()))
					&& (CkeyPad.textPW.getText().equals(list_PW.get(list_ID.indexOf(CkeyPad.textID.getText()))))) {

				if (CkeyPad.textID.getText().length() == 11) {
					MainPanel.id = CkeyPad.textID.getText();
				}

				userInfo = mapInfo.get(MainPanel.id); // �α����� ���̵� �ش��ϴ� value(=Userinfo ��ü) �� ����

				setVisible(false);

//				new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // �α��� �� �޴��� �̵�
				
				AfterLogIn afterLogin = new AfterLogIn(userInfo, lblMainTime); 
				MainPanel.frame.move(afterLogin);

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
		add(lblMainTime);

		// ���� �ð� ȣ��
		TimeCal Time = new TimeCal(lblMainTime);
		Thread thread1 = new Thread(Time);
		thread1.start();
		
		lblMainTime.setText(Time.currentTime());
	}
	private void MainPanel(Image img) {
		this.img = img;		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
}
