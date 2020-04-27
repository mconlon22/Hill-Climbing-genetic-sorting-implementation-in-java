package assignment2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Initialize {
    static ArrayList<Project> projects;
    static ArrayList<Student> students;
    static ArrayList<StaffMember> staffMembers;
    static File studentFile;
    static File projectFile;


    public static void main(String [] args){
        Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Enter student size: ");
        int studentSize = scanner.nextInt();
        loadData(studentSize);
        writeData();

    }

    private static void loadData(int studentSize){
        // TODO Read data from CSV 
    	try{
    	    FileInputStream readData = new FileInputStream("staffmembers.csv");
    	    ObjectInputStream readStream = new ObjectInputStream(readData);

    	    projects = (ArrayList<Project>) readStream.readObject();
    	    readStream.close();
    	    System.out.println(projects.toString());
    	}
    	catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	try{
    	    FileInputStream readData = new FileInputStream("student_data.csv");
    	    ObjectInputStream readStream = new ObjectInputStream(readData);

    	    students = (ArrayList<Student>) readStream.readObject();
    	    readStream.close();
    	    System.out.println(projects.toString());
    	}
    	catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	try{
    	    FileInputStream readData = new FileInputStream("MiskatonicStaffMembers.csv");
    	    ObjectInputStream readStream = new ObjectInputStream(readData);

    	    staffMembers = (ArrayList<StaffMember>) readStream.readObject();
    	    readStream.close();
    	    System.out.println(projects.toString());
    	}
    	catch (Exception e) {
    	    e.printStackTrace();
    	}
    }

    private static void writeData(){
        // TODO Write three array lists to disk 
    	try{
            FileOutputStream write = new FileOutputStream("project.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(write);

            writeStream.writeObject(projects);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try{
            FileOutputStream write = new FileOutputStream("students.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(write);

            writeStream.writeObject(students);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try{
            FileOutputStream write = new FileOutputStream("staffmember.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(write);

            writeStream.writeObject(staffMembers);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}