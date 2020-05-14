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
	private JTextField txtGiveStaffMembers;

	
  


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
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtGiveStaffMembers = new JTextField();
		txtGiveStaffMembers.setText("Select Data File ");
		GridBagConstraints gbc_txtGiveStaffMembers = new GridBagConstraints();
		gbc_txtGiveStaffMembers.insets = new Insets(0, 0, 5, 0);
		gbc_txtGiveStaffMembers.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGiveStaffMembers.gridx = 0;
		gbc_txtGiveStaffMembers.gridy = 1;
		contentPane.add(txtGiveStaffMembers, gbc_txtGiveStaffMembers);
		txtGiveStaffMembers.setColumns(10);
		JLabel lblNoFileSelected_1_1Staff = new JLabel("No File Selected");
		GridBagConstraints gbc_lblNoFileSelected_1_1Staff = new GridBagConstraints();
		gbc_lblNoFileSelected_1_1Staff.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoFileSelected_1_1Staff.gridx = 0;
		
		gbc_lblNoFileSelected_1_1Staff.gridy = 3;
		
		contentPane.add(lblNoFileSelected_1_1Staff, gbc_lblNoFileSelected_1_1Staff);
		JButton btnBrowseFiles = new JButton("Browse Files");
		btnBrowseFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file;
				fileChooser chooser = new fileChooser("Select Data File");
				if(chooser==null) {}
				else {
                file = chooser.getSelectedFile();

                System.out.println("Handler:"+file.toString());
                 String failOrSuccess = null;
				try {
					failOrSuccess = loadData(file);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 lblNoFileSelected_1_1Staff.setText(failOrSuccess);
                 txtGiveStaffMembers.setText(file.getPath());
				}
			}
		});
		GridBagConstraints gbc_btnBrowseFiles = new GridBagConstraints();
		gbc_btnBrowseFiles.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowseFiles.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles.gridx = 0;
		gbc_btnBrowseFiles.gridy = 2;
		contentPane.add(btnBrowseFiles, gbc_btnBrowseFiles);
		GridBagConstraints gbc_btnBrowseFiles_1 = new GridBagConstraints();
		gbc_btnBrowseFiles_1.anchor = GridBagConstraints.EAST;
		gbc_btnBrowseFiles_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBrowseFiles_1.gridx = 1;
		gbc_btnBrowseFiles_1.gridy = 4;
		
	
		
		
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.gridx = 0;
		gbc_btnContinue.gridy = 8;
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(data.getStudents()==null||data.getStudents().size()==0||data.getProjects()==null||data.getProjects().size()==0) 
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
		
				contentPane.add(btnContinue, gbc_btnContinue);

	}
	
	
	 
	 
	 public String loadData(File file) throws NumberFormatException, IOException {
		 data.readFile(file);
		 if(data.getProjects().size()==0||data.getProjects()==null||data.getStudents().size()==0||data.getStudents()==null) 
		 {
			 return "Failed to Import Data";
		 }
         return "Successfully Imported " +data.getProjects().size() +" Projects and "+ data.getStudents().size()+ " Students";
	 }

}
