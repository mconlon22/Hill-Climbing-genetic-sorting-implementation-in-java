package testClasses;

import java.io.File;
import java.io.IOException;

import LoadStore.DataHandling;

public class TestingCandidate {
	 public static void main(String[] args) 
	 {
		 DataHandling data=new DataHandling();
		 setUp(data);
		 
	 }
	 public static void setUp(DataHandling data)
	 {
	        File file=new File("LoadStore//csvdata//sample preference matrix (2).xls");
	        try {
				data.readFile(file);
			} catch (NumberFormatException | IOException e) {
				System.out.println("failed to read file in setup");				
				e.printStackTrace();
			}
	 }
}
