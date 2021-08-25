package all;

import java.awt.Color;
//import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Seat {

	// ���� ����
	static Set<String> selectedBtnNums = new HashSet<String>();
	static JButton[] arySelectedBtn = new JButton[2];
	static String tempSeatRoomNo = "-1";
	static String tempType = "-1";

	private JFrame frame;
	
	private JPanel afterLogInPanel;
	private JPanel mainPanel;
	
	private String seatNo;
	private String roomNo;

	UserInfo userInfo;
	ImageIcon img1, img2;


	// ������
	public Seat(JFrame frame, JPanel mainPanel, UserInfo userInfo) {
		this.frame = frame;
		this.mainPanel = mainPanel;
		this.userInfo = userInfo;

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	// �޼ҵ� ����
	private void initialize() {

		JPanel seatPanel = new JPanel();
		seatPanel.setBounds(0, 0, 700, 800);
		seatPanel.setBorder(new LineBorder(null, 0));
		seatPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(seatPanel);
		seatPanel.setLayout(null);

		JPanel yellowBorderPanel = new JPanel();
		yellowBorderPanel.setBounds(7, 96, 669, 600);
		yellowBorderPanel.setBackground(Color.GRAY);
		yellowBorderPanel.setBorder(new LineBorder(new Color(255, 200, 0), 3));
		seatPanel.add(yellowBorderPanel);
		yellowBorderPanel.setLayout(null);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(5, 10, 676, 42);
		seatPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelectTitle = new JLabel("�¼� ����");
		lblSeatSelectTitle.setBackground(Color.ORANGE);
		lblSeatSelectTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelectTitle.setBounds(0, 0, 156, 32);
		seatSelectTitlePanel.add(lblSeatSelectTitle);

		JButton btnSeatPanelDone = new JButton("�� �� �� ��"); // �ݱ�

		
		// ��Ȳ�� �¼� �г��� ���� ���� ( �ǽð��¼���Ȳ / �¼� �̵� / �¼� ���� ) 
		if (userInfo == null) {

			btnSeatPanelDone.setVisible(false);
			
			lblSeatSelectTitle.setText("�ǽð��¼���Ȳ");

		} else if ((userInfo.getSeatNo() != "") || (userInfo.getRoomNo() != "")) {

			lblSeatSelectTitle.setText("�¼� �̵�");

		} else {

			lblSeatSelectTitle.setText("�¼� ����");
		}

		// �¼� ���� �Ϸ� �޼ҵ�� ����
		seatSelectionDone(seatPanel, btnSeatPanelDone);

		// ��ư addActionListener �̺�Ʈ �ܺ� Ŭ����ȭ
		SelectAction select = new SelectAction(seatPanel, yellowBorderPanel, userInfo);

		// �Ϲ� �¼� ��ư
		System.out.println("�Ϲ� For�� Seat.selectedBtnNums: " + Seat.selectedBtnNums);
		for (int i = 0; i <= 4; i++) {

			JButton btnSeat = new JButton(i + 1 + "");
			btnSeat.addActionListener(select);
			btnSeat.setFont(new Font("����", Font.BOLD, 15));

			// ������ �¼�
			if ((userInfo != null) && (userInfo.getSeatNo().equals(btnSeat.getText()))) {
				btnSeat.setBackground(Color.RED);
				btnSeat.setEnabled(false);

				if (Integer.parseInt(btnSeat.getText()) > 30) {

					arySelectedBtn[1] = btnSeat; // �̵� 2~3�� ���Խ� ���� ������ ( SelectAction Ŭ���� ���� )

				} else {
					arySelectedBtn[0] = btnSeat; // �̵� 2~3�� ���Խ� ���� ������ ( SelectAction Ŭ���� ���� )
				}

				// �̿��� �¼�
			} else if (Seat.selectedBtnNums.contains(btnSeat.getText())) {
				btnSeat.setBackground(Color.YELLOW);
				btnSeat.setEnabled(false);

				// �� �¼�
			} else {
				btnSeat.setBackground(Color.WHITE);
			}

			btnSeat.setBounds(27, 20 + 80 * i, 60, 60);
			yellowBorderPanel.add(btnSeat);
		}

		// 5�μ� �¼� ��ư
		System.out.println("Seat 5�μ� For�� Seat.selectedBtnNums: " + Seat.selectedBtnNums);
		for (int i = 0; i < 2; i++) {

			JButton btnSeat = new JButton(i + 31 + "");
			btnSeat.addActionListener(select);
			btnSeat.setFont(new Font("����", Font.BOLD, 15));

//			System.out.println("Seat userInfo.getRoomNo() : " + userInfo.getRoomNo());
			// ������ �¼�
			if ((userInfo != null) && (userInfo.getRoomNo().equals(btnSeat.getText()))) {
				btnSeat.setBackground(Color.RED);
				btnSeat.setEnabled(false);
//				arySelectedBtn.add(btnSeat);

				if (Integer.parseInt(btnSeat.getText()) > 30) {
					arySelectedBtn[1] = btnSeat;

				} else {
					arySelectedBtn[0] = btnSeat;
				}

				// �̿��� �¼�
			} else if (Seat.selectedBtnNums.contains(btnSeat.getText())) {
				System.out.println("Seat �̿� ���� �¼� - btnSeat.getText() : " + btnSeat.getText());

				btnSeat.setBackground(Color.YELLOW);
				btnSeat.setEnabled(false);

				// �� �¼�
			} else {
				btnSeat.setBackground(Color.WHITE);
			}

			btnSeat.setBounds(520, 300 + 150 * i, 120, 120);
			yellowBorderPanel.add(btnSeat);
		}

	}

	public void seatSelectionDone(JPanel seatPanel, JButton btnSeatPanelDone) {

		btnSeatPanelDone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnSeatPanelDone - Seat.tempSeatRoomNo : " + Seat.tempSeatRoomNo);

				// ���� ���� ������ �¼� ��ȣ �ӽ� �����(Seat.tempSeatRoomNo) - �������� �ʾ��� �� �⺻�� "-1"
				if ((Seat.tempSeatRoomNo != "-1")) {

					// ���� ���� (�ð���, 1ȸ��, 5�ν�, �Ⱓ��) ��ǰ�� ������ ���� ����
					userInfo.setUserType(Seat.tempType);


					// 5�μ� ( 31�� �̻� )
					if (Integer.parseInt(Seat.tempSeatRoomNo) > 30) {

						
						Seat.selectedBtnNums.remove(userInfo.getRoomNo()); // �̵�������, ���� �� �¼��� ��ü �̿� �¼����� ����

						Seat.selectedBtnNums.add(Seat.tempSeatRoomNo); // ���� �̵��� �ӽ� �¼� ��ȣ��, ��ü �̿� �¼��� �߰�
						
						userInfo.setRoomNo(Seat.tempSeatRoomNo); // ���� �̵��� �ӽ� �¼� ��ȣ��, �� ������ �ݿ�
					
						// 1�μ� ( 1-30�� )
					} else if (Integer.parseInt(Seat.tempSeatRoomNo) > 0) {

						Seat.selectedBtnNums.remove(userInfo.getSeatNo()); // �̵�������, ���� �� �¼��� ��ü �̿� �¼����� ����

						Seat.selectedBtnNums.add(Seat.tempSeatRoomNo); // ���� �̵��� �ӽ� �¼� ��ȣ��, ��ü �̿� �¼��� �߰�
						
						userInfo.setSeatNo(Seat.tempSeatRoomNo); // ���� �̵��� �ӽ� �¼� ��ȣ��, �� ������ �ݿ�
					}

			        // �Խ� �ð� ���
					TimeCal timeCal = new TimeCal();
					userInfo.setStartTime(timeCal.currentTime());
					
					
					mainPanel.setVisible(!mainPanel.isVisible());
					seatPanel.setVisible(!seatPanel.isVisible());
					System.out.println();

				}
				
				// �ʱ�ȭ
				Seat.tempType = "-1";
				Seat.tempSeatRoomNo = "-1";
			}
		});
		btnSeatPanelDone.setFont(new Font("����", Font.BOLD, 12));
		btnSeatPanelDone.setForeground(Color.WHITE);
		btnSeatPanelDone.setBounds(7, 703, 330, 45);
		seatPanel.add(btnSeatPanelDone);
		btnSeatPanelDone.setBackground(Color.DARK_GRAY);

		
		// �¼� ���� ū �г��� �ݱ� ��ư 
		JButton btnSeatPanelClose = new JButton("�� �� / �� ��"); // �ݱ�
		btnSeatPanelClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// ���� �� �Ϸ����� �ʰ� ��� ���� ��� - �ʱ�ȭ
				Seat.tempType = "-1";
				Seat.tempSeatRoomNo = "-1";
				
				mainPanel.setVisible(!mainPanel.isVisible());
				seatPanel.setVisible(!seatPanel.isVisible());
			}
		});
		btnSeatPanelClose.setFont(new Font("����", Font.BOLD, 12));
		btnSeatPanelClose.setForeground(Color.WHITE);
		btnSeatPanelClose.setBounds(337, 703, 330, 45);
		seatPanel.add(btnSeatPanelClose);
		btnSeatPanelClose.setBackground(Color.DARK_GRAY);

		JLabel unavailable = new JLabel("�̿밡��", img1, JLabel.LEFT);
		unavailable.setBounds(15, 56, 93, 37);
		seatPanel.add(unavailable);
		unavailable.setForeground(Color.WHITE);

		JLabel available = new JLabel("�̿���", img2, JLabel.LEFT);
		available.setBounds(133, 56, 93, 37);
		seatPanel.add(available);
		available.setForeground(Color.WHITE);

	}

}
