package all;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SelectAction implements ActionListener {

	// ���� ����
	JPanel afterLogInPanel;
	JPanel bigPanel;
	JPanel yellowBorderPanel;

	JButton btnSelected;

	UserInfo userInfo;

	// ������
	public SelectAction(JPanel bigPanel, JPanel yellowBorderPanel, UserInfo userInfo) {
		this.bigPanel = bigPanel;
		this.yellowBorderPanel = yellowBorderPanel;
		this.userInfo = userInfo;
	}

	// ��ư �̺�Ʈ
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Main.id != null) {
			btnSelected = (JButton) e.getSource();
			btnSelected.setBackground(Color.GREEN);
			
			int btnNum = Integer.parseInt(btnSelected.getText());
			
			// 5�ν� �̿�� ���� + 1�ν� �¼� ����  ||  1�ν� �̿�� ���� + 5�ν� �¼� ���� - �޽����� �ȳ�
			if (( (Seat.tempType != "group") && (btnNum> 30)) || ((Seat.tempType == "group") && (btnNum < 31 ))) {
				
				JPanel noSeatPanel = new JPanel();
				
				noSeatPanel.setVisible(!noSeatPanel.isVisible());
				
				// ���� �¼� ���� �� Ȯ�� �ǻ� ����(���� �г�)
				noSeatCheck(noSeatPanel, btnSelected.getText());
				return;
//				
			}
			

			// �¼� ���� Ȯ��â
			if (Seat.tempSeatRoomNo.equals("-1") && userInfo.getUserType() == "") {
				System.out.println(" ���� �Ǻ��� ���");
				JPanel seatSelectCheckPanel = new JPanel();
				seatSelectCheckPanel.setVisible(!seatSelectCheckPanel.isVisible());
				
				// ���� �¼� ���� �� ���� Ȯ�� �ǻ� ����(���� �г�)
				selectCheck(seatSelectCheckPanel, btnSelected.getText());

				// �¼� �̵� Ȯ��â
			} else {

				System.out.println(" �̵� �Ǻ��� ���");
				JPanel seatMoveCheckPanel = new JPanel();
				seatMoveCheckPanel.setVisible(!seatMoveCheckPanel.isVisible());
				
				// �̵��� �¼� ���� �� ���� Ȯ�� �ǻ� ����(���� �г�)
				moveCheck(seatMoveCheckPanel, btnSelected.getText());

			}
		}
	}
	
	// ������ �¼� ���� ����( 5�ν� �̿�� ���� + 1�ν� �¼� ����  ||  1�ν� �̿�� ���� + 5�ν� �¼� ���� - �޽����� �ȳ� )
	public void noSeatCheck(JPanel noSeatPanel, String btnNum) {

		System.out.println("������ �¼� ���� ����");
		
		noSeatPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(noSeatPanel);
		noSeatPanel.setLayout(null);
		noSeatPanel.setVisible(true);
		
		
		JButton btnCancel = new JButton("�ݱ�");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				noSeatPanel.setVisible(false);
				btnSelected.setBackground(Color.WHITE);
			}
		});
		btnCancel.setBackground(new Color(128, 128, 128));
		btnCancel.setBounds(176, 148, 141, 31);
		noSeatPanel.add(btnCancel);

		JLabel lblSeatNum = new JLabel("�¼� ��ȣ : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		noSeatPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("�����Ͻ� ���ð� �ٸ� �¼��Դϴ�.");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		noSeatPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		noSeatPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);
		
		JLabel lblSeatSelect = new JLabel("�ȳ�");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 50, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}
	
	
	// �¼� �̵� ���� �޼ҵ�
	public void moveCheck(JPanel seatMoveCheckPanel, String btnNum) {
		System.out.println("\n �̵� ����");

		seatMoveCheckPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(seatMoveCheckPanel);
		seatMoveCheckPanel.setLayout(null);
		seatMoveCheckPanel.setVisible(true);

		JButton chooseBtn = new JButton("�̵�");
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				seatMoveCheckPanel.setVisible(false);
				System.out.println("�̵� ����");

				// 1�μ�
				if ((Integer.parseInt(btnSelected.getText()) < 31) && (!userInfo.getUserType().equals("group"))) {


					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					System.out.println("userInfo.getSeatNo() : " + userInfo.getSeatNo() + " ����");

					// ���� �¼� ���� ��� ( �迭 �ε��� 0�� 1�μ� / 1�� 5�μ� )
					Seat.arySelectedBtn[0].setBackground(Color.WHITE);
					Seat.arySelectedBtn[0].setEnabled(true);
					Seat.arySelectedBtn[0] = btnSelected; // �̵� 2~3�� ���Խ� ���� ������
					Seat.arySelectedBtn[0].setEnabled(false);
					Seat.arySelectedBtn[0].setBackground(Color.RED);

					// 5�μ�
				} else if ((Integer.parseInt(btnSelected.getText()) > 30) && (userInfo.getUserType().equals("group"))) {

					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					// ���� �¼� ���� ��� ( �迭 �ε��� 0�� 1�μ� / 1�� 5�μ� )
					Seat.arySelectedBtn[1].setBackground(Color.WHITE);
					Seat.arySelectedBtn[1].setEnabled(true);
					Seat.arySelectedBtn[1] = btnSelected; // �̵� 2~3�� ���Խ� ���� ������
					Seat.arySelectedBtn[1].setEnabled(false);
					Seat.arySelectedBtn[1].setBackground(Color.RED);

					// �ٸ� ������ �¼� �� �̵� �Ұ�
				} else {
					System.out.println("�ٸ� ������ �¼� �� �̵� �Ұ�");
					btnSelected.setBackground(Color.WHITE);
				}
			}
		});
		chooseBtn.setBackground(new Color(32, 178, 170));
		chooseBtn.setBounds(12, 148, 141, 31);
		seatMoveCheckPanel.add(chooseBtn);

		JButton btnCancel = new JButton("���");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seatMoveCheckPanel.setVisible(false);
				btnSelected.setBackground(Color.WHITE);
			}
		});
		btnCancel.setBackground(new Color(128, 128, 128));
		btnCancel.setBounds(176, 148, 141, 31);
		seatMoveCheckPanel.add(btnCancel);

		JLabel lblSeatNum = new JLabel("�¼� ��ȣ : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		seatMoveCheckPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("�����Ͻ� �¼����� �̵��Ͻðڽ��ϱ�?");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		seatMoveCheckPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		seatMoveCheckPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelect = new JLabel("�¼� �̵�");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 92, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}

	// �¼� ���� ���� �޼ҵ�
	public void selectCheck(JPanel seatSelectCheckPanel, String btnNum) {

		System.out.println("���� ����");
		seatSelectCheckPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(seatSelectCheckPanel);
		seatSelectCheckPanel.setLayout(null);
		seatSelectCheckPanel.setVisible(true);
		
		JButton chooseBtn = new JButton("����");
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				seatSelectCheckPanel.setVisible(false);
				System.out.println("���� ����");
				System.out.println("SelecteAciton userInfo.getUserType() :" + userInfo.getUserType());
				System.out.println("SelecteAciton Seat.tempType :" + Seat.tempType);

				// 1�μ�
				if ((Integer.parseInt(btnSelected.getText()) < 31) && (!Seat.tempType.equals("group"))) {


					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					Seat.arySelectedBtn[0] = btnSelected; // �̵� 2~3�� ���Խ� ���� ������
					Seat.arySelectedBtn[0].setEnabled(false);

					// 5�μ�
				} else if ((Integer.parseInt(btnSelected.getText()) > 30) && (Seat.tempType.equals("group"))) {

					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					Seat.arySelectedBtn[1] = btnSelected; // �̵� 2~3�� ���Խ� ���� ������
					Seat.arySelectedBtn[1].setEnabled(false);

					// �ٸ� ������ �¼� �� �̵� �Ұ�
				} else {
					System.out.println("�ٸ� ������ �¼� �� �̵� �Ұ�");
					btnSelected.setBackground(Color.WHITE);
				}

			}
		});
		chooseBtn.setBackground(new Color(32, 178, 170));
		chooseBtn.setBounds(12, 148, 141, 31);
		seatSelectCheckPanel.add(chooseBtn);
		
		JButton btnCancel = new JButton("���");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				seatSelectCheckPanel.setVisible(false);
				btnSelected.setBackground(Color.WHITE);
			}
		});
		btnCancel.setBackground(new Color(128, 128, 128));
		btnCancel.setBounds(176, 148, 141, 31);
		seatSelectCheckPanel.add(btnCancel);

		JLabel lblSeatNum = new JLabel("�¼� ��ȣ : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		seatSelectCheckPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("�����Ͻ� �¼��� �̿��Ͻðڽ��ϱ�?");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		seatSelectCheckPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		seatSelectCheckPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);
		
		JLabel lblSeatSelect = new JLabel("�¼� ����");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 92, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}
}
