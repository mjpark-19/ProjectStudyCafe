package TimeCal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TimeCal_S {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel Label1 ;
	private JLabel Label2 ;
	private JLabel Label3 ;
	private JLabel Label4 ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeCal_S window = new TimeCal_S();
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
	public TimeCal_S() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(34, 21, 358, 216);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Label4 = new JLabel("00:00:00");
		Label4.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label4.setHorizontalAlignment(SwingConstants.CENTER);
		Label4.setForeground(new Color(0, 0, 0));
		Label4.setBackground(new Color(224, 255, 255));
		Label4.setBounds(203, 149, 141, 35);
		panel.add(Label4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(154, 205, 50));
		panel_1.setBounds(106, 247, 286, 83);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(12, 10, 246, 29);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setColumns(10);
		textField_1.setBounds(12, 49, 246, 29);
		panel_1.add(textField_1);
		
		Label3 = new JLabel("00:00:00");
		Label3.setHorizontalAlignment(SwingConstants.CENTER);
		Label3.setForeground(Color.BLACK);
		Label3.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label3.setBackground(new Color(224, 255, 255));
		Label3.setBounds(203, 103, 141, 35);
		panel.add(Label3);
		
		Label1 = new JLabel("00:00:00");
		Label1.setHorizontalAlignment(SwingConstants.CENTER);
		Label1.setForeground(Color.BLACK);
		Label1.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label1.setBackground(new Color(224, 255, 255));
		Label1.setBounds(203, 11, 141, 35);
		panel.add(Label1);
		
		JLabel Label1_4 = new JLabel("\uC794\uC5EC \uC2DC\uAC04  (-)");
		Label1_4.setHorizontalAlignment(SwingConstants.LEFT);
		Label1_4.setForeground(Color.BLACK);
		Label1_4.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label1_4.setBackground(new Color(224, 255, 255));
		Label1_4.setBounds(12, 149, 179, 35);
		panel.add(Label1_4);
		
		JLabel Label1_3 = new JLabel("\uBCF4\uC720 \uC2DC\uAC04(\uACE0\uC815)");
		Label1_3.setHorizontalAlignment(SwingConstants.LEFT);
		Label1_3.setForeground(Color.BLACK);
		Label1_3.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label1_3.setBackground(new Color(224, 255, 255));
		Label1_3.setBounds(12, 103, 161, 35);
		panel.add(Label1_3);
		
		JLabel Label1_1 = new JLabel("\uD604\uC7AC \uC2DC\uAC04  (+)");
		Label1_1.setHorizontalAlignment(SwingConstants.LEFT);
		Label1_1.setForeground(Color.BLACK);
		Label1_1.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label1_1.setBackground(new Color(224, 255, 255));
		Label1_1.setBounds(12, 11, 161, 35);
		panel.add(Label1_1);
		
		JLabel Label1_2 = new JLabel("\uC2DC\uC791 \uC2DC\uAC04(\uACE0\uC815)");
		Label1_2.setHorizontalAlignment(SwingConstants.LEFT);
		Label1_2.setForeground(Color.BLACK);
		Label1_2.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label1_2.setBackground(new Color(224, 255, 255));
		Label1_2.setBounds(12, 57, 161, 35);
		panel.add(Label1_2);
		
		Label2 = new JLabel("00:00:00");
		Label2.setHorizontalAlignment(SwingConstants.CENTER);
		Label2.setForeground(Color.BLACK);
		Label2.setFont(new Font("±º∏≤", Font.BOLD, 21));
		Label2.setBackground(new Color(224, 255, 255));
		Label2.setBounds(203, 57, 141, 35);
		panel.add(Label2);
		
		
		
		timeCount(Label1, Label2, Label3, Label4); // ¡§ªÛ ¿€µø

//		timeCount(Label4); // ¡§ªÛ ¿€µø
	}
		
	void timeCount(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4) {
		TimeCal_T count = new TimeCal_T(lbl1, lbl2, lbl3, lbl4);
	}
	
	//¡§ªÛ ¿€µø
//	void timeCount(JLabel lbl) {
//		TimeCal_T count = new TimeCal_T(lbl);
//	}
}
