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

	// 변수 선언
	JPanel afterLogInPanel;
	JPanel bigPanel;
	JPanel yellowBorderPanel;

	JButton btnSelected;

	UserInfo userInfo;

	// 생성자
	public SelectAction(JPanel bigPanel, JPanel yellowBorderPanel, UserInfo userInfo) {
		this.bigPanel = bigPanel;
		this.yellowBorderPanel = yellowBorderPanel;
		this.userInfo = userInfo;
	}

	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (Main.id != null) {
			btnSelected = (JButton) e.getSource();
			btnSelected.setBackground(Color.GREEN);
			
			int btnNum = Integer.parseInt(btnSelected.getText());
			
			// 5인실 이용권 선택 + 1인식 좌석 선택  ||  1인실 이용권 선택 + 5인실 좌석 선택 - 메시지로 안내
			if (( (Seat.tempType != "group") && (btnNum> 30)) || ((Seat.tempType == "group") && (btnNum < 31 ))) {
				
				JPanel noSeatPanel = new JPanel();
				
				noSeatPanel.setVisible(!noSeatPanel.isVisible());
				
				// 최초 좌석 선택 후 확인 의사 질문(작은 패널)
				noSeatCheck(noSeatPanel, btnSelected.getText());
				return;
//				
			}
			

			// 좌석 선택 확인창
			if (Seat.tempSeatRoomNo.equals("-1") && userInfo.getUserType() == "") {
				System.out.println(" 선택 판별문 통과");
				JPanel seatSelectCheckPanel = new JPanel();
				seatSelectCheckPanel.setVisible(!seatSelectCheckPanel.isVisible());
				
				// 최초 좌석 선택 후 최종 확인 의사 묻기(작은 패널)
				selectCheck(seatSelectCheckPanel, btnSelected.getText());

				// 좌석 이동 확인창
			} else {

				System.out.println(" 이동 판별문 통과");
				JPanel seatMoveCheckPanel = new JPanel();
				seatMoveCheckPanel.setVisible(!seatMoveCheckPanel.isVisible());
				
				// 이동할 좌석 선택 후 최종 확인 의사 묻기(작은 패널)
				moveCheck(seatMoveCheckPanel, btnSelected.getText());

			}
		}
	}
	
	// 비정상 좌석 선택 진입( 5인실 이용권 선택 + 1인식 좌석 선택  ||  1인실 이용권 선택 + 5인실 좌석 선택 - 메시지로 안내 )
	public void noSeatCheck(JPanel noSeatPanel, String btnNum) {

		System.out.println("비정상 좌석 선택 진입");
		
		noSeatPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(noSeatPanel);
		noSeatPanel.setLayout(null);
		noSeatPanel.setVisible(true);
		
		
		JButton btnCancel = new JButton("닫기");
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

		JLabel lblSeatNum = new JLabel("좌석 번호 : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		noSeatPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("보유하신 선택과 다른 좌석입니다.");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		noSeatPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		noSeatPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);
		
		JLabel lblSeatSelect = new JLabel("안내");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 50, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}
	
	
	// 좌석 이동 관련 메소드
	public void moveCheck(JPanel seatMoveCheckPanel, String btnNum) {
		System.out.println("\n 이동 진입");

		seatMoveCheckPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(seatMoveCheckPanel);
		seatMoveCheckPanel.setLayout(null);
		seatMoveCheckPanel.setVisible(true);

		JButton chooseBtn = new JButton("이동");
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				seatMoveCheckPanel.setVisible(false);
				System.out.println("이동 진입");

				// 1인석
				if ((Integer.parseInt(btnSelected.getText()) < 31) && (!userInfo.getUserType().equals("group"))) {


					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					System.out.println("userInfo.getSeatNo() : " + userInfo.getSeatNo() + " 변경");

					// 기존 좌석 선택 취소 ( 배열 인덱스 0은 1인석 / 1은 5인석 )
					Seat.arySelectedBtn[0].setBackground(Color.WHITE);
					Seat.arySelectedBtn[0].setEnabled(true);
					Seat.arySelectedBtn[0] = btnSelected; // 이동 2~3번 진입시 에러 방지용
					Seat.arySelectedBtn[0].setEnabled(false);
					Seat.arySelectedBtn[0].setBackground(Color.RED);

					// 5인석
				} else if ((Integer.parseInt(btnSelected.getText()) > 30) && (userInfo.getUserType().equals("group"))) {

					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					// 기존 좌석 선택 취소 ( 배열 인덱스 0은 1인석 / 1은 5인석 )
					Seat.arySelectedBtn[1].setBackground(Color.WHITE);
					Seat.arySelectedBtn[1].setEnabled(true);
					Seat.arySelectedBtn[1] = btnSelected; // 이동 2~3번 진입시 에러 방지용
					Seat.arySelectedBtn[1].setEnabled(false);
					Seat.arySelectedBtn[1].setBackground(Color.RED);

					// 다른 종류의 좌석 간 이동 불가
				} else {
					System.out.println("다른 종류의 좌석 간 이동 불가");
					btnSelected.setBackground(Color.WHITE);
				}
			}
		});
		chooseBtn.setBackground(new Color(32, 178, 170));
		chooseBtn.setBounds(12, 148, 141, 31);
		seatMoveCheckPanel.add(chooseBtn);

		JButton btnCancel = new JButton("취소");
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

		JLabel lblSeatNum = new JLabel("좌석 번호 : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		seatMoveCheckPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("선택하신 좌석으로 이동하시겠습니까?");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		seatMoveCheckPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		seatMoveCheckPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);

		JLabel lblSeatSelect = new JLabel("좌석 이동");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 92, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}

	// 좌석 선택 관련 메소드
	public void selectCheck(JPanel seatSelectCheckPanel, String btnNum) {

		System.out.println("선택 진입");
		seatSelectCheckPanel.setBounds(185, 191, 329, 189);
		yellowBorderPanel.add(seatSelectCheckPanel);
		seatSelectCheckPanel.setLayout(null);
		seatSelectCheckPanel.setVisible(true);
		
		JButton chooseBtn = new JButton("선택");
		chooseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				seatSelectCheckPanel.setVisible(false);
				System.out.println("선택 진입");
				System.out.println("SelecteAciton userInfo.getUserType() :" + userInfo.getUserType());
				System.out.println("SelecteAciton Seat.tempType :" + Seat.tempType);

				// 1인석
				if ((Integer.parseInt(btnSelected.getText()) < 31) && (!Seat.tempType.equals("group"))) {


					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					Seat.arySelectedBtn[0] = btnSelected; // 이동 2~3번 진입시 에러 방지용
					Seat.arySelectedBtn[0].setEnabled(false);

					// 5인석
				} else if ((Integer.parseInt(btnSelected.getText()) > 30) && (Seat.tempType.equals("group"))) {

					Seat.tempSeatRoomNo = btnSelected.getActionCommand();

					Seat.arySelectedBtn[1] = btnSelected; // 이동 2~3번 진입시 에러 방지용
					Seat.arySelectedBtn[1].setEnabled(false);

					// 다른 종류의 좌석 간 이동 불가
				} else {
					System.out.println("다른 종류의 좌석 간 이동 불가");
					btnSelected.setBackground(Color.WHITE);
				}

			}
		});
		chooseBtn.setBackground(new Color(32, 178, 170));
		chooseBtn.setBounds(12, 148, 141, 31);
		seatSelectCheckPanel.add(chooseBtn);
		
		JButton btnCancel = new JButton("취소");
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

		JLabel lblSeatNum = new JLabel("좌석 번호 : " + btnNum);
		lblSeatNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNum.setBounds(39, 41, 246, 37);
		seatSelectCheckPanel.add(lblSeatNum);

		JLabel lblSeatNumQuestion = new JLabel("선택하신 좌석을 이용하시겠습니까?");
		lblSeatNumQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatNumQuestion.setBounds(39, 88, 246, 37);
		seatSelectCheckPanel.add(lblSeatNumQuestion);

		JPanel seatSelectTitlePanel = new JPanel();
		seatSelectTitlePanel.setBackground(Color.ORANGE);
		seatSelectTitlePanel.setBounds(0, 0, 329, 31);
		seatSelectCheckPanel.add(seatSelectTitlePanel);
		seatSelectTitlePanel.setLayout(null);
		
		JLabel lblSeatSelect = new JLabel("좌석 선택");
		lblSeatSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatSelect.setBounds(0, 0, 92, 31);
		seatSelectTitlePanel.add(lblSeatSelect);

	}
}
