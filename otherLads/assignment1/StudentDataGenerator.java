/**
 * @author: Oisín Redmond  
 * @version: 1.0
 * Generates a list of students with random names, student id, and stream(with a 60/40 ratio). Writes to a CSV file. 
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class StudentDataGenerator {

     
    static final int DATA_SIZE = 60;
    public static void main(String [] args) throws Exception{ 
       ArrayList<Integer> studentIDs = new ArrayList<Integer>();
       ArrayList<String> firstNames = new ArrayList<String>();
       ArrayList<String> lastNames = new ArrayList<String>();
       ArrayList<String> fullNames = new ArrayList<String>();
       ArrayList<String> studentStreams = new ArrayList<String>();
       
       // student ids are eight digits
       for (int i = 0; i <= DATA_SIZE; i++){ 
           studentIDs.add(getNumberInRange(10000000, 99999999));
           }

       File firstNamesFile = new File("data\\first_names.txt");
       Scanner sc0 = new Scanner(firstNamesFile);
       while(sc0.hasNextLine()){
           firstNames.add(sc0.nextLine());
       }
       File lastNamesFile = new File("data\\last_names.txt");
       Scanner sc1 = new Scanner(lastNamesFile);
       while(sc1.hasNextLine()){
           lastNames.add(sc1.nextLine());
       }    
       for (int i = 0; i <= DATA_SIZE; i++){

           String randomName = firstNames.get(getNumberInRange(0, firstNames.size()))
                                + " " 
                                + lastNames.get(getNumberInRange(0, lastNames.size())); 
            fullNames.add(randomName);
       }
       
       for (int i = 0; i <= DATA_SIZE; i++){
           int prob = getNumberInRange(1, 100);
           if (prob <= 40) {
               studentStreams.add("DS");
           } else {
               studentStreams.add("CS");
           }
       }

      PrintWriter writer = new PrintWriter(new File ("data\\student_data.csv"));
      for (int i = 0; i < DATA_SIZE; i++){
          writer.write(Integer.toString(studentIDs.get(i)));
          writer.write(",");
          writer.write(fullNames.get(i));
          writer.write(",");
          writer.write(studentStreams.get(i));
          writer.write("\n");
      }
      writer.close();
       
    }

    private static int getNumberInRange(int min, int max){
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }
}
