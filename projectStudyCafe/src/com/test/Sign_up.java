package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Sign_up {

	String path_id = "A:\\kiosk\\projectStudyCafe\\User_info\\user_id.txt";
	String path_pw = "A:\\kiosk\\projectStudyCafe\\User_info\\user_pw.txt";
	String path_info = "A:\\kiosk\\projectStudyCafe\\User_info\\user_info.txt";

	// File file = new File(path);

	public Sign_up(String id, String pw) {

		try {
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(path_id, true));
			bw1.write(id + "\n");
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(path_pw, true));
			bw2.write(pw + "\n");

			bw1.close();
			bw2.close();


		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static void main(String[] args) {
		Sign_up user3 = new Sign_up("56567734", "8989");
	}

}
