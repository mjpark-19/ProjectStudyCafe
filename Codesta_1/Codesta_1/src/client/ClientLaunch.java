package client;

import client.frame.MainFrame;

public class ClientLaunch {

	public static void main(String[] args) {
		
		try {
			System.out.println("ClientLaunch1"); 
			MainFrame mainFrame = new MainFrame();
			System.out.println("ClientLaunch2"); 
			mainFrame.setVisible(true);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ClientLaunch ¿¹¿Ü"); 

		}
		
		
	}
}
