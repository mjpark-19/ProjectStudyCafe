package com.db;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Seats {

	private JFrame frame;
	private JPanel panel;
	private JButton button;
	private JButton button2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Seats window = new Seats();
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
	public Seats() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 808, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.YELLOW, 3));
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 80, 776, 602);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//ÁÂ¼® ¹öÆ° Å¬¸¯½Ã ÀÌº¥Æ® 
		JButton btn_1 = new JButton("1");
		btn_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//¸¶¿ì½º¸¦ Å¬¸¯ÇÏ¿´À»¶§ ÀÌº¥Æ® ½ÇÇà
			}
			public void mouseEntered(MouseEvent e) {
				//¸¶¿ì½º¸¦ ¹öÆ°¿¡ °¡Á®°¬À»¶§ ÀÌº¥Æ® ½ÇÇà
				btn_1.setBackground(Color.yellow);
			}
			public void mouseExited(MouseEvent e) {
				//
				btn_1.setBackground(Color.WHITE);
			}
		});
		btn_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_1.setBackground(Color.WHITE);
		btn_1.setBounds(12, 10, 60, 60);
		panel.add(btn_1);
		
		JButton btn_2 = new JButton("2");
		btn_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				btn_2.setBackground(Color.yellow);
			}
			public void mouseExited(MouseEvent e) {
				btn_2.setBackground(Color.WHITE);
			}
		});
		btn_2.setBackground(Color.WHITE);
		btn_2.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_2.setBounds(12, 80, 60, 60);
		panel.add(btn_2);
		
		JButton btn_3 = new JButton("3");
		btn_3.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_3.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_3.setBackground(Color.WHITE);
		}
	});
		btn_3.setBackground(Color.WHITE);
		btn_3.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_3.setBounds(12, 150, 60, 60);
		panel.add(btn_3);
		
		JButton btn_4 = new JButton("4");
		btn_4.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_4.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_4.setBackground(Color.WHITE);
		}
	});
		btn_4.setBackground(Color.WHITE);
		btn_4.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_4.setBounds(12, 220, 60, 60);
		panel.add(btn_4);
		
		JButton btn_5 = new JButton("5");
		btn_5.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_5.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_5.setBackground(Color.WHITE);
		}
	});
		btn_5.setBackground(Color.WHITE);
		btn_5.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_5.setBounds(12, 290, 60, 60);
		panel.add(btn_5);
		
		JButton btn_6 = new JButton("6");
		btn_6.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_6.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_6.setBackground(Color.WHITE);
		}
	});
		btn_6.setBackground(Color.WHITE);
		btn_6.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_6.setBounds(12, 441, 60, 60);
		panel.add(btn_6);
		
		JButton btn_7 = new JButton("7");
		btn_7.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_7.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_7.setBackground(Color.WHITE);
		}
	});
		btn_7.setBackground(Color.WHITE);
		btn_7.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_7.setBounds(12, 511, 60, 60);
		panel.add(btn_7);
		
		JButton btn_8 = new JButton("8");
		btn_8.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_8.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_8.setBackground(Color.WHITE);
		}
	});
		btn_8.setBackground(Color.WHITE);
		btn_8.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_8.setBounds(147, 10, 60, 60);
		panel.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_9.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_9.setBackground(Color.WHITE);
		}
	});
		btn_9.setBackground(Color.WHITE);
		btn_9.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_9.setBounds(218, 10, 60, 60);
		panel.add(btn_9);
		
		JButton btn_10 = new JButton("10");
		btn_10.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_10.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_10.setBackground(Color.WHITE);
		}
	});
		btn_10.setBackground(Color.WHITE);
		btn_10.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_10.setBounds(290, 10, 60, 60);
		panel.add(btn_10);
		
		JButton btn_11 = new JButton("11");
		btn_11.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_11.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_11.setBackground(Color.WHITE);
		}
	});
		btn_11.setBackground(Color.WHITE);
		btn_11.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_11.setBounds(361, 10, 60, 60);
		panel.add(btn_11);
		
		JButton btn_12 = new JButton("12");
		btn_12.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_12.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_12.setBackground(Color.WHITE);
		}
	});
		btn_12.setBackground(Color.WHITE);
		btn_12.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_12.setBounds(433, 10, 60, 60);
		panel.add(btn_12);
		
		JButton btn_13 = new JButton("13");
		btn_13.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_13.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_13.setBackground(Color.WHITE);
		}
	});
		btn_13.setBackground(Color.WHITE);
		btn_13.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_13.setBounds(218, 150, 60, 60);
		panel.add(btn_13);
		
		JButton btn_14 = new JButton("14");
		btn_14.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_14.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_14.setBackground(Color.WHITE);
		}
	});
		btn_14.setBackground(Color.WHITE);
		btn_14.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_14.setBounds(290, 150, 60, 60);
		panel.add(btn_14);
		
		JButton btn_15 = new JButton("15");
		btn_15.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_15.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_15.setBackground(Color.WHITE);
		}
	});
		btn_15.setBackground(Color.WHITE);
		btn_15.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_15.setBounds(218, 220, 60, 60);
		panel.add(btn_15);
		
		JButton btn_16 = new JButton("16");
		btn_16.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_16.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_16.setBackground(Color.WHITE);
		}
	});
		btn_16.setBackground(Color.WHITE);
		btn_16.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_16.setBounds(290, 220, 60, 60);
		panel.add(btn_16);
		
		JButton btn_17 = new JButton("17");
		btn_17.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_17.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_17.setBackground(Color.WHITE);
		}
	});
		btn_17.setBackground(Color.WHITE);
		btn_17.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_17.setBounds(218, 290, 60, 60);
		panel.add(btn_17);
		
		JButton btn_18 = new JButton("18");
		btn_18.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_18.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_18.setBackground(Color.WHITE);
		}
	});
		btn_18.setBackground(Color.WHITE);
		btn_18.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_18.setBounds(290, 290, 60, 60);
		panel.add(btn_18);
		
		JButton btn_19 = new JButton("19");
		btn_19.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_19.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_19.setBackground(Color.WHITE);
		}
	});
		btn_19.setBackground(Color.WHITE);
		btn_19.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_19.setBounds(218, 441, 60, 60);
		panel.add(btn_19);
		
		JButton btn_20 = new JButton("20");
		btn_20.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_20.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_20.setBackground(Color.WHITE);
		}
	});
		btn_20.setBackground(Color.WHITE);
		btn_20.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_20.setBounds(290, 441, 60, 60);
		panel.add(btn_20);
		
		JButton btn_21 = new JButton("21");
		btn_21.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_21.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_21.setBackground(Color.WHITE);
		}
	});
		btn_21.setBackground(Color.WHITE);
		btn_21.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_21.setBounds(218, 511, 60, 60);
		panel.add(btn_21);
		
		JButton btn_22 = new JButton("22");
		btn_22.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_22.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_22.setBackground(Color.WHITE);
		}
	});
		btn_22.setBackground(Color.WHITE);
		btn_22.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_22.setBounds(290, 511, 60, 60);
		panel.add(btn_22);
		
		JButton btn_23 = new JButton("23");
		btn_23.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_23.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_23.setBackground(Color.WHITE);
		}
	});
		btn_23.setBackground(Color.WHITE);
		btn_23.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_23.setBounds(413, 150, 60, 60);
		panel.add(btn_23);
		
		JButton btn_24 = new JButton("24");
		btn_24.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_24.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_24.setBackground(Color.WHITE);
		}
	});
		btn_24.setBackground(Color.WHITE);
		btn_24.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_24.setBounds(485, 150, 60, 60);
		panel.add(btn_24);
		
		JButton btn_25 = new JButton("25");
		btn_25.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_25.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_25.setBackground(Color.WHITE);
		}
	});
		btn_25.setBackground(Color.WHITE);
		btn_25.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_25.setBounds(413, 220, 60, 60);
		panel.add(btn_25);
		
		JButton btn_26 = new JButton("26");
		btn_26.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_26.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_26.setBackground(Color.WHITE);
		}
	});
		btn_26.setBackground(Color.WHITE);
		btn_26.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_26.setBounds(485, 220, 60, 60);
		panel.add(btn_26);
		
		JButton btn_27 = new JButton("27");
		btn_27.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_27.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_27.setBackground(Color.WHITE);
		}
	});
		btn_27.setBackground(Color.WHITE);
		btn_27.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_27.setBounds(704, 10, 60, 60);
		panel.add(btn_27);
		
		JButton btn_28 = new JButton("28");
		btn_28.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_28.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_28.setBackground(Color.WHITE);
		}
	});
		btn_28.setBackground(Color.WHITE);
		btn_28.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_28.setBounds(704, 80, 60, 60);
		panel.add(btn_28);
		
		JButton btn_29 = new JButton("29");
		btn_29.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_29.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_29.setBackground(Color.WHITE);
		}
	});
		btn_29.setBackground(Color.WHITE);
		btn_29.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_29.setBounds(704, 150, 60, 60);
		panel.add(btn_29);
		
		JButton btn_30 = new JButton("30");
		btn_30.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_30.setBackground(Color.yellow);
		}
		public void mouseExited(MouseEvent e) {
			btn_30.setBackground(Color.WHITE);
		}
	});
		btn_30.setBackground(Color.WHITE);
		btn_30.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_30.setBounds(704, 220, 60, 60);
		panel.add(btn_30);
		
		JButton btn_31 = new JButton("31");
		btn_31.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_31.setBackground(Color.yellow);
			
		}
		public void mouseExited(MouseEvent e) {
			btn_31.setBackground(Color.WHITE);
		}
	});
		btn_31.setBackground(Color.WHITE);
		btn_31.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_31.setBounds(639, 311, 125, 125);
		panel.add(btn_31);
		
		JButton btn_32 = new JButton("32");
		button = btn_32;
		btn_32.addMouseListener(new MouseAdapter() {
			//ÁÂ¼®À» Å¬¸¯ÇÏ¸é È®ÀÎ Ã¢ ÆË¾÷ 
		public void mousePressed(MouseEvent e) { 
			setSeats("32");
			getid();
			showOptionDialog();
			
		}
		public void mouseEntered(MouseEvent e) {
			btn_32.setBackground(Color.yellow);
			
		}
		public void mouseExited(MouseEvent e) {
			btn_32.setBackground(Color.WHITE);
			
		}
	});
		btn_32.setBackground(Color.WHITE);
		btn_32.setFont(new Font("±¼¸²", Font.BOLD, 15));
		btn_32.setBounds(639, 446, 125, 125);
		panel.add(btn_32);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(12, 10, 768, 57);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		 //ÀÌ¿ëÁß, ÀÌ¿ëºÒ°¡ ¹öÆ° (ÀÌ°Ç ÀÓ½Ã·Î,, ´Ù½Ã ±â´ÉÀ» Ã£¾Æ¾ß) 
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uC774\uC6A9\uD604\uD669");  
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rdbtnNewRadioButton.setBounds(8, 17, 75, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uC774\uC6A9\uC911");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		rdbtnNewRadioButton_1.setBackground(Color.DARK_GRAY);
		rdbtnNewRadioButton_1.setBounds(87, 17, 75, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		
		

	}
	public void setSeats(String SeatNumber){
		System.out.println("ÁÂ¼®¹øÈ£:"+SeatNumber);
		
	}
	public void getid() {
		
		UploadDAO data = new UploadDAO();
		System.out.println("ID:");
		
	//	System.out.println(data.id);
		
	}
	
	public void showOptionDialog() { //JDialog·Î »ç¿ëÀÚ Á¤ÀÇÇü ´ëÈ­»óÀÚ·Î º¯°æÇÏ±â (»õ·Î¿î Å¬¶ó½º ¸¸µé±â) 
		String[] answer = {"¼±ÅÃ", "Ãë¼Ò"};
		int ans = JOptionPane.showOptionDialog(button, "¿¹¾àÇÏ½Ã°Ú½À´Ï±î?", "ÁÂ¼®¿¹¾à", 
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, answer, answer[0]);
		System.out.println("Answer:" + ans );
	}
}
