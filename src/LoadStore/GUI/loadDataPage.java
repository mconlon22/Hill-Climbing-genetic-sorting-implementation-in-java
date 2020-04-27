package LoadStore.GUI;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import LoadStore.DataHandling;
import LoadStore.Project;
import LoadStore.Student;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



public class loadDataPage extends JFrame {
    JButton button1;
    JButton button2;
    File projectFile;
    File studentsFile;
    static Boolean projectsLoaded = false;
    static Boolean studentsLoaded = false;
    Boolean dataTested = false;
    JLabel projectMessage=new JLabel("Please add projects file");
    JLabel studentMessage=new JLabel("Please add students file");


    DataHandling data = new DataHandling();
    List<Student> students = new ArrayList<>();
    List<Project> projects = new ArrayList<>();
    JFrame frame = new JFrame();


    public loadDataPage() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(200, 200,300, 300));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight=200;

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty=2;
        gbc.insets=new Insets(10,10,10,10);

        theHandler handler = new theHandler();
        button1 = new JButton("Choose Projects File");
        button2 = new JButton("Choose Students File");

        
        button1.addActionListener(handler);
        button2.addActionListener(handler);
        panel.add(button1,gbc);
       panel.add(projectMessage,gbc);
        panel.add(button2,gbc);
        panel.add(studentMessage,gbc);

        
        frame.setSize(300, 300);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Student Project Allocation");
        frame.pack();
        frame.setVisible(true);

    }
public boolean checkDataLoaded() {
        return (studentsLoaded && projectsLoaded) ? (true) : (false);
        }
    
    
    
        private class theHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == button1) {

                fileChooser chooser = new fileChooser("Select Projects");
                projectFile = chooser.getSelectedFile();
                System.out.println("Handler:"+projectFile.toString());
                projectsLoaded = true;
                loadData();
            }
             if (event.getSource() == button2) {
                    System.out.println("action");
                    fileChooser chooser1 = new fileChooser("Select Students");
                    studentsFile = chooser1.getSelectedFile();
                    System.out.println("Handler:"+projectFile.toString());
                    studentsLoaded = true;
                    loadData();
    
                    }
            }
        }

    
    public void loadData()
    {
        if(projectsLoaded == true&&studentsLoaded==true){
            try {
                data.loadStaffMembers();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            data.loadProjects(projectFile);
            data.loadStudents(studentsFile);
            projects = data.getProjects();
            students = data.getStudents();
            studentMessage.setText("Loaded in " + students.size() + " students");
            projectMessage.setText("Loaded in " + projects.size() + " projects");
            frame.revalidate();
            frame.repaint();
        }

    }

    
    

    
    } 







