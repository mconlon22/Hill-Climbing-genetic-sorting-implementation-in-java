package LoadStore.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoadStore.DataHandling;
import LoadStore.Project;
import LoadStore.Student;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class DataLoader extends JFrame {

	private JPanel contentPane;
	private JTextField txtGiveProjectLoaction;
	private JTextField txtGiveStudentsFile;
	private JTextField txtGiveStaffMembers;
	JButton btnBrowseFiles_3;
	JButton btnBrowseFiles_2;

	
  


    DataHandling data = new DataHandling();
   
	
	/**
	 * Create the frame.
	 */
	
	
	
	
	public DataLoader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtGiveStaffMembers = new JTextField();
		txtGiveStaffMembers.setText("Give Staff Members File Location");
		GridBagConstraints gbc_txtGiveStaffMembers = new GridBagConstraints();
		gbc_txtGiveStaffMembers.insets = new Insets(0, 0, 5, 5);
		gbc_txtGiveStaffMembers.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGiveStaffMembers.gridx = 1;
		gbc_txtGiveStaffMembers.gridy = 1;
		contentPane.add(txtGiveStaffMembers, gbc_txtGiveStaffMembers);
		txtGiveStaffMembers.setColumns(10);
		
		JLabel lblNoFileSelected_1_1Staff = new JLabel("No File Selected");
		GridBagConstraints gbc_lblNoFileSelected_1_1Staff = new GridBagConstraints();
		gbc_lblNoFileSelected_1_1Staff.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoFileSelected_1_1Staff.gridx = 2;
		gbc_lblNoFileSelected_1_1Staff.gridy = 1;
		contentPane.add(lblNoFileSelected_1_1Staff, gbc_lblNoFileSelected_1_1Staff);
		
		JButton btnBrowseFiles = new JButton("Browse Files");
		btnBrowseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File staffFile;
				fileChooser chooser = new fileChooser("Select Staff File");
                staffFile = chooser.getSelectedFile();
                txtGiveProjectLoaction .setText(staffFile.getPath());

                System.out.println("Handler:"+staffFile.toString());
                 String failOrSuccess=loadStaff(staffFile);
                 lblNoFileSelected_1_1Staff.setText(failOrSuccess);
                 txtGiveStaffMembers.setText(staffFile.getPath());
                 btnBrowseFiles_3.setEnabled(true);
				
			}
		});
		GridBagConstraints gbc_btnBrowseFiles = new GridBagConstraints();
		gbc_btnBrowseFiles.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseFiles.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles.gridx = 1;
		gbc_btnBrowseFiles.gridy = 2;
		contentPane.add(btnBrowseFiles, gbc_btnBrowseFiles);
		
		txtGiveProjectLoaction = new JTextField();
		txtGiveProjectLoaction.setText("Give Project Loaction");
		GridBagConstraints gbc_txtGiveProjectLoaction = new GridBagConstraints();
		gbc_txtGiveProjectLoaction.insets = new Insets(0, 0, 5, 5);
		gbc_txtGiveProjectLoaction.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGiveProjectLoaction.gridx = 1;
		gbc_txtGiveProjectLoaction.gridy = 3;
		contentPane.add(txtGiveProjectLoaction, gbc_txtGiveProjectLoaction);
		txtGiveProjectLoaction.setColumns(10);
		
		
		
		JLabel lblNoFileSelected_1Projects = new JLabel("No File Selected");
		GridBagConstraints gbc_lblNoFileSelected_1Projects = new GridBagConstraints();
		gbc_lblNoFileSelected_1Projects.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoFileSelected_1Projects.gridx = 2;
		gbc_lblNoFileSelected_1Projects.gridy = 3;
		contentPane.add(lblNoFileSelected_1Projects, gbc_lblNoFileSelected_1Projects);
		GridBagConstraints gbc_btnBrowseFiles_1 = new GridBagConstraints();
		gbc_btnBrowseFiles_1.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseFiles_1.gridx = 1;
		gbc_btnBrowseFiles_1.gridy = 4;
		
		JButton btnBrowseFiles_1 = new JButton("Browse Files");
		btnBrowseFiles_1.setEnabled(false);
		btnBrowseFiles_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 fileChooser chooser = new fileChooser("Select Projects File");
	                File projectFile = chooser.getSelectedFile();
	                txtGiveProjectLoaction .setText(projectFile.getAbsolutePath());

	                System.out.println("Handler:"+projectFile.toString());
	                 String failOrSuccess=loadProjects(projectFile);
	                 lblNoFileSelected_1Projects.setText(failOrSuccess);
	                 contentPane.add(btnBrowseFiles_1, gbc_btnBrowseFiles_1);
	         		btnBrowseFiles_3.setEnabled(true);


			}
		});
		
	btnBrowseFiles_3 = new JButton("Browse Files");
		btnBrowseFiles_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 fileChooser chooser = new fileChooser("Select Projects File");
	                File projectFile = chooser.getSelectedFile();
	                txtGiveProjectLoaction .setText(projectFile.getAbsolutePath());

	                System.out.println("Handler:"+projectFile.toString());
	                 String failOrSuccess=loadProjects(projectFile);
	                 lblNoFileSelected_1Projects.setText(failOrSuccess);
	                 contentPane.add(btnBrowseFiles_1, gbc_btnBrowseFiles_1);
	                 btnBrowseFiles_2.setEnabled(true);

			}
		});
		btnBrowseFiles_3.setEnabled(false);
		GridBagConstraints gbc_btnBrowseFiles_3 = new GridBagConstraints();
		gbc_btnBrowseFiles_3.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseFiles_3.gridx = 1;
		gbc_btnBrowseFiles_3.gridy = 4;
		contentPane.add(btnBrowseFiles_3, gbc_btnBrowseFiles_3);
		
		txtGiveStudentsFile = new JTextField(); 
		txtGiveStudentsFile.setText("Give students file location");
		GridBagConstraints gbc_txtGiveStudentsFile = new GridBagConstraints();
		gbc_txtGiveStudentsFile.insets = new Insets(0, 0, 5, 5);
		gbc_txtGiveStudentsFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGiveStudentsFile.gridx = 1;
		gbc_txtGiveStudentsFile.gridy = 5;
		contentPane.add(txtGiveStudentsFile, gbc_txtGiveStudentsFile);
		txtGiveStudentsFile.setColumns(10);
		
		JLabel lblNoFileSelectedStudents = new JLabel("No File Selected");
		GridBagConstraints gbc_lblNoFileSelectedStudents = new GridBagConstraints();
		gbc_lblNoFileSelectedStudents.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoFileSelectedStudents.gridx = 2;
		gbc_lblNoFileSelectedStudents.gridy = 5;
		contentPane.add(lblNoFileSelectedStudents, gbc_lblNoFileSelectedStudents);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(data.getStudents()==null||data.getStudents().size()==0||data.getProjects()==null||data.getProjects().size()==0||data.getStaff()==null||data.getStaff().size()==0) 
				{
					JOptionPane.showMessageDialog(contentPane, "Please Finish Loading Files");
				}
				else 
				{
							setVisible(false);
								SelectAlgorithm frame = new SelectAlgorithm(data);
								
								frame.setVisible(true);
				}
				
			}
		});
		
		btnBrowseFiles_2 = new JButton("Browse Files");
		btnBrowseFiles_2.setEnabled(false);
		btnBrowseFiles_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 fileChooser chooser = new fileChooser("Select Students File");
	                File studentsFile = chooser.getSelectedFile();
	                txtGiveStudentsFile.setText(studentsFile.getAbsolutePath());
	                System.out.println("Handler:"+studentsFile.toString());
	                String failOrSuccess=loadStudents(studentsFile);
	                lblNoFileSelectedStudents.setText(failOrSuccess);
	                
			}
		});
		GridBagConstraints gbc_btnBrowseFiles_2 = new GridBagConstraints();
		gbc_btnBrowseFiles_2.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseFiles_2.gridx = 1;
		gbc_btnBrowseFiles_2.gridy = 6;
		contentPane.add(btnBrowseFiles_2, gbc_btnBrowseFiles_2);
		
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.gridx = 2;
		gbc_btnContinue.gridy = 8;
		contentPane.add(btnContinue, gbc_btnContinue);
	}
	
	
	 public String loadProjects(File projectFile) {
		 List projects=data.loadProjects(projectFile);
         return (projects==null||projects.size()==0)?("Failed to Import Projects"):("Successfully Imported " + projects.size() + " Projects");
	 }
	 public String loadStudents(File studentsFile) {
		 List students=data.loadStudents(studentsFile);
         return (students==null||students.size()==0)?("Failed to Import Students"):("Successfully Imported " + students.size() + " Students");
	 }
	 public String loadStaff(File staffFile) {
		 List staff=data.loadStaffMembers(staffFile);
         return (staff==null)?("Failed to Import staff"):("Successfully Imported " + staff.size() + " Staff Members");
	 }

}
