package testClasses;

import java.io.File;
import java.io.IOException;

import LoadStore.DataHandling;
import LoadStore.Project;

public class TestingDataHandling {
	public static void main(String[] args) {
        DataHandling data= new DataHandling();
        //testing reading csv
        File csv=new File("LoadStore//csvdata//samplepreferencematrix(2).csv");
        try {
			data.readFile(csv);
		} catch (NumberFormatException | IOException e) {
           System.out.println("csv reading failed");

			e.printStackTrace();
		}
        
        if(data.getProjects().size()==147) {
            System.out.println("successfully read  in csv projects");

        }
        else     System.out.println("failed to read  in csv projects");

        if(data.getStudents().size()==106) {
            System.out.println("successfully read  in csv students");
        }
        else     System.out.println("failed to read  in csv students");

        
        
        
        //testing reading xls
        
        
        DataHandling data1= new DataHandling();
        
        File xls=new File("LoadStore//csvdata//sample preference matrix (2).xls");
        try {
        	data1.readFile(xls);
			

		} catch (NumberFormatException | IOException e) {
           System.out.println("xls reading failed");
			e.printStackTrace();
		}
        if(data1.getProjects().size()==142) {
            System.out.println("successfully read  in xls projects");

        }
        else     System.out.println("failed to read  in xls projects");

        if(data1.getStudents().size()==106) {
            System.out.println("successfully read  in xls students");
        }
        else     System.out.println("failed to read  in xls students");

        
        //testing reading xlsx
        DataHandling data2= new DataHandling();

        File xlsx=new File("LoadStore//csvdata//samplepreferencematrix(2).xlsx");
        try {
        	data2.readFile(xlsx);        	
        	
        } catch (NumberFormatException | IOException e) {
        	System.out.println("xlsx reading failed");
        	e.printStackTrace();
        }
        if(data2.getProjects().size()==142) {
        	System.out.println("successfully read  in xlsx projects");
        	
        }
        else     System.out.println("failed to read  in xlsx projects");

        
        if(data2.getStudents().size()==106) {
        	System.out.println("successfully read  in xlsx students");
        }
        else     System.out.println("failed to read  in xlsx students");


    }
}
