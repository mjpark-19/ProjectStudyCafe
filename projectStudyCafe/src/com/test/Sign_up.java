package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Sign_up {

	String path_id = "A:\\Swing\\database\\User_info\\user_id.txt";
	String path_idpw = "A:\\Swing\\database\\User_info\\user_idpw.txt";
	String path_id_info = "A:\\Swing\\database\\User_info\\user_info.txt";
	
	// File file = new File(path);

	public Sign_up(String id, int pw, String name, String addr) {

		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(path_id, true));
			bw1.write(id + "\n");
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(path_idpw, true));
			bw2.write(id + ";");
			bw2.write(pw + "\n");
			BufferedWriter bw3 = new BufferedWriter(new FileWriter(path_id_info, true));
			bw3.write(id + ";");
			bw3.write(name + ";");
			bw3.write(addr + "\n");
			
			bw1.close();
			bw2.close();
			bw3.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void getInfo() {
	
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(path));
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}
}
