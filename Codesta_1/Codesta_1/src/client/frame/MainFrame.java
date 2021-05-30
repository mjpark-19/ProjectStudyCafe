package client.frame;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.day.dto.User;
import com.day.exception.FindException;
import com.day.service.UserService;

public class MainFrame extends JFrame{
	
	private MainPanel mainPanel;
	Map<String, User> mapInfo;
	UserService service;
	
	public MainFrame() {
		System.out.println("MainFrame1"); 

		setBounds(0, 0, 700, 800); //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("MainFrame2"); 
		mainPanel = new MainPanel(this);
		System.out.println("MainFrame3"); 
		getContentPane().add(mainPanel);
		setVisible(true);


		
	}
	
	public void move(JPanel panelName) {
		getContentPane().removeAll();
		getContentPane().add(panelName);
		revalidate();
		repaint();
	}
}
