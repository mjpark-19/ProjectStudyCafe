package com.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HashMapToTxt {

	
	// Java program to write HashMap to a file


			final static String outputFilePath
				= "F:/Serialisation/write.txt";

			public static void main(String[] args)
			{

				// create new HashMap
				HashMap<String, String> map
					= new HashMap<String, String>();

				// key-value pairs
				map.put("rohit", "one");
				map.put("Sam", "two");
				map.put("jainie", "three");

				// new file object
				File file = new File(outputFilePath);

				BufferedWriter bf = null;

				try {

					// create new BufferedWriter for the output file
					bf = new BufferedWriter(new FileWriter(file));

					// iterate map entries
					for (Map.Entry<String, String> entry :
						map.entrySet()) {

						// put key and value separated by a colon
						bf.write(entry.getKey() + ":"
								+ entry.getValue());

						// new line
						bf.newLine();
					}

					bf.flush();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				finally {

					try {

						// always close the writer
						bf.close();
					}
					catch (Exception e) {
					}
				}
			}
		}



