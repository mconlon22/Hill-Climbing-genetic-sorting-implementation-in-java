import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Random;
import com.opencsv.*;

public class ProjectSupervisor 
{
	static Random rand = new Random();
	static final int DATA_SIZE = 30;
    public static void main(String [] args) throws Exception{ 
       ArrayList<String> Supervisor = new ArrayList<String>();
       ArrayList<String> Projects = new ArrayList<String>();
       ArrayList<String> Target = new ArrayList<String>();
       
       
       String file = "data\\staffmember.csv";
     
       CSVReader reader = new CSVReader(new FileReader(file));
       String[] nextLine;
       while((nextLine = reader.readNext()) != null)
       {
    	   Supervisor.add(nextLine[0]);
    	   Projects.add(nextLine[1]);
    	   if(nextLine[3]== "")
    	   {
    		   
    		   int p = rand.nextInt(100);
               if (p <= 39) 
                   Target.add("CS+DS");
                else 
                   Target.add("CS");
    	   }
    	   else
    		   Target.add(nextLine[3]);   
       }
       
       PrintWriter writer = new PrintWriter(new File ("data\\super-project.csv"));
       writer.write("Supervisor");
       writer.write(",");
       writer.write("Projects");
       writer.write(",");
       writer.write("Target");
       writer.write("\n");
       for (int i = 0; i < DATA_SIZE; i++)
       {
    	   int c = 1 + (rand.nextInt(1032));
    	   writer.write(Supervisor.get(c));
           writer.write(",");
           writer.write(Projects.get(c));
           writer.write(",");
           writer.write(Target.get(c));
           writer.write("\n");
       }
       
    }
}