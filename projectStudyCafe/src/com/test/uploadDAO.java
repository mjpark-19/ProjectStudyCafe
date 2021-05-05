package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class uploadDAO {
	public void bufferedTxt () {
		
//		uploadDAO load = new uploadDAO();
//		load.bufferedTxt();
//		System.out.println();
		
		
		String path_id = "User_info\\user_id.txt";
		String path_idpw = "\\User_info\\user_idpw.txt";
		String path_id_info = "\\User_info\\user_info.txt";
		
		String id ;
		String pw ;
		String info ;
		try {
			
			File file = new File(path_id);
//			FileReader r = new FileReader(file);
//			BufferedReader br = new BufferedReader(r);
			FileReader reader = new FileReader(file);
			BufferedReader r = new BufferedReader(reader);
			System.out.println(r.readLine());
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
			
	}
	
	
	
}
