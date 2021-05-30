package all;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUp extends DB{

	// ���� ����
	JFrame frame ;
	
	JPanel signUpPanel;
	JPanel mainPanel;
	JPanel topOrangePanel;
	
	JLabel lblMainTime;

	HashMap<String, UserInfo> mapInfo;
	UserInfo userInfo;
	KeyPadSignUp CkeyPad;
	
	
	// ������
	public SignUp(JFrame frame, JPanel mainPanel, UserInfo userInfo, HashMap<String, UserInfo> mapInfo, JLabel lblMainTime) {
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.userInfo = userInfo;
		this.mapInfo = mapInfo;
		this.lblMainTime = lblMainTime;
		initialize();
	}
	
	// �޼ҵ� ����
	public void initialize() {
		signUpPanel = new JPanel();
		signUpPanel.setBounds(0, 0, 700, 800);                      // �߿�
		frame.getContentPane().add(signUpPanel);
		signUpPanel.setBackground(Color.DARK_GRAY);
		signUpPanel.setLayout(null);
		signUpPanel.setFocusable(true); // false������ idĭ�� focus�� ���� Ű�е� ���� ����
		
		// ���� �ð� ���̺� 
		signUpPanel.add(lblMainTime);
		
		// �ٹ̱� ��
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setBounds(181, 65, 48, 14);
		signUpPanel.add(lblSignUp);
		
		// �ٹ̱� ��
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(192, 86, 309, 47);
		signUpPanel.add(lblNewLabel);

		
		JButton btnCertSend = new JButton("������ȣ �߼�");
		btnCertSend.setBounds(470, 258, 151, 47);
		btnCertSend.setBackground(Color.LIGHT_GRAY);
		signUpPanel.add(btnCertSend);
		
		
		JLabel lblCheckID = new JLabel("ID�� �ڵ��� ��ȣ�� �Է����ּ���.");
		lblCheckID.setBounds(100, 150, 309, 47);
		signUpPanel.add(lblCheckID);
		lblCheckID.setFont(new Font("����", Font.PLAIN, 15));
		lblCheckID.setForeground(Color.WHITE);

		// Ű�е� ����
		CkeyPad = new KeyPadSignUp(frame, signUpPanel);
		CkeyPad.textOnPW.setVisible(true);
		
		
		JButton btn_checkID = new JButton("�ߺ� Ȯ��");
		btn_checkID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				checkID(lblCheckID);
			}
		});
		btn_checkID.setBounds(470, 185, 151, 47);
		btn_checkID.setBackground(Color.LIGHT_GRAY);

		signUpPanel.add(btn_checkID);

		
		// ��� ���� �� �г�1
		JPanel topTitlePanel = new JPanel();
		topTitlePanel.setBounds(0, 0, 686, 45);
		signUpPanel.add(topTitlePanel);
		topTitlePanel.setLayout(null);

		// ��� ���� �� �г�1
		topOrangePanel = new JPanel();
		topOrangePanel.setBounds(0, 45, 686, 48);
		topOrangePanel.setBackground(Color.ORANGE);
		signUpPanel.add(topOrangePanel);
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
			
			if (!(list_ID.contains(CkeyPad.textID.getText()) )){
				

				if (CkeyPad.textPW.getText().equals(CkeyPad.textPW2.getText())) {
					
					// ���� ������ ID ����
					Main.id = CkeyPad.textID.getText();
					
					// �� ���� ��ü ����
					userInfo = new UserInfo.Builder(Main.id, CkeyPad.textPW.getText()).build();
					
					// �ʿ� �Ҵ�
					mapInfo.put(Main.id, userInfo);
			         
			        // Db Ŭ������ �޼ҵ�� text���Ͽ� ID�� PW ����
			        createDbInfo(CkeyPad.textID.getText(), CkeyPad.textPW.getText());
			         
			        // �α��� �� �޴��� �̵�
					new AfterLogIn(frame, mainPanel, userInfo, mapInfo); // mainPanel ? For LogOut btn !
					
					signUpPanel.setVisible(false);
					mainPanel.setVisible(false);
					
				} else {
			         JOptionPane.showMessageDialog(null, "��й�ȣ�� Ȯ�����ּ���", "login", JOptionPane.DEFAULT_OPTION);						}
					
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
			JButton btnAfterLogin = new JButton("ó������");
			btnAfterLogin.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e) {
					signUpPanel.setVisible(false);
					mainPanel.setVisible(true);
					mainPanel.add(lblMainTime);
					
				}
			});
			btnAfterLogin.setBounds(0, 0, 149, 48);
			btnAfterLogin.setBackground(Color.ORANGE);
			topOrangePanel.add(btnAfterLogin);
			
			
			JButton btnBefore = new JButton("����");
			btnBefore.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e) {
					signUpPanel.setVisible(false);
					mainPanel.setVisible(true);
					mainPanel.add(lblMainTime);

				}
			});
			btnBefore.setBackground(Color.ORANGE);
			btnBefore.setBounds(0, 702, 343, 61);
			signUpPanel.add(btnBefore);

			
			
			JButton btnNext = new JButton("���� �Ϸ�");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed( ActionEvent e ) {
					
					finalCheck();
					CkeyPad.textID.setText("010"); // createUserInfo()���� �Ʒ��� ��ġ�� ��. Main.id ����.
					CkeyPad.textPW.setText("");
					CkeyPad.textPW2.setText("");
					CkeyPad.textOnPW.setVisible(true);
					CkeyPad.textPW.setVisible(false);
					CkeyPad.textCert.setText("");

				}
			});
			btnNext.setBackground(Color.ORANGE);
			btnNext.setBounds(343, 702, 343, 61);
			signUpPanel.add(btnNext);
			
		}
}
