package LoadStore.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoadStore.DataHandling;
import LoadStore.HillClimbing;
import LoadStore.Student;
import LoadStore.geneticAlgorithem;
import LoadStore.CandidateFunction.CandidateSet;
import LoadStore.CandidateFunction.CandidateSolution;
import LoadStore.CandidateFunction.FitnessFunction;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class AlgorithmRunner extends JFrame {

	private JPanel contentPane;
	private DataHandling data;
	Boolean running=false;
	JLabel lblNumduplicateprojects;
	JLabel lblAveragePrefrence; 
	JLabel ProjectsNotPreferred;
	JLabel mismatchingStreams;
	geneticAlgorithem algorithm;
	CandidateSet set;
	CandidateSolution candidate=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataHandling data=new DataHandling();
					data.load();
					AlgorithmRunner frame = new AlgorithmRunner(Algo.Genetic,data);

					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlgorithmRunner(Algo algo,DataHandling data) {
		 this.data=data;
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(200,200);
		

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{51, 303, 61, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		contentPane.setPreferredSize(new Dimension(300, 500));
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SelectAlgorithm frame = new SelectAlgorithm(data);
				frame.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 0;
		contentPane.add(btnBack, gbc_btnBack);
		
		JLabel lblSelectedAlgorithm = new JLabel("Selected Algorithm");
		if(algo==Algo.Genetic) {
			lblSelectedAlgorithm.setText("Genetic Algorithm");
		}
		else {
			lblSelectedAlgorithm.setText("HillClimbing Algorithm");

		}
		lblSelectedAlgorithm.setFont(new Font("Tahoma", Font.PLAIN, 19));
		GridBagConstraints gbc_lblSelectedAlgorithm = new GridBagConstraints();
		gbc_lblSelectedAlgorithm.anchor = GridBagConstraints.WEST;
		gbc_lblSelectedAlgorithm.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectedAlgorithm.gridx = 1;
		gbc_lblSelectedAlgorithm.gridy = 1;
		contentPane.add(lblSelectedAlgorithm, gbc_lblSelectedAlgorithm);
		
		 lblNumduplicateprojects = new JLabel("numDuplicateProjects");
		GridBagConstraints gbc_lblNumduplicateprojects = new GridBagConstraints();
		gbc_lblNumduplicateprojects.anchor = GridBagConstraints.WEST;
		gbc_lblNumduplicateprojects.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumduplicateprojects.gridx = 1;
		gbc_lblNumduplicateprojects.gridy = 2;
		contentPane.add(lblNumduplicateprojects, gbc_lblNumduplicateprojects);
		
		 lblAveragePrefrence = new JLabel("Average Prefrence");
		GridBagConstraints gbc_lblAveragePrefrence = new GridBagConstraints();
		gbc_lblAveragePrefrence.anchor = GridBagConstraints.WEST;
		gbc_lblAveragePrefrence.insets = new Insets(0, 0, 5, 5);
		gbc_lblAveragePrefrence.gridx = 1;
		gbc_lblAveragePrefrence.gridy = 3;
		contentPane.add(lblAveragePrefrence, gbc_lblAveragePrefrence);
		
		 ProjectsNotPreferred = new JLabel("ProjectsNotPreferred");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(ProjectsNotPreferred, gbc_lblNewLabel_1);
		
		 mismatchingStreams = new JLabel("MismatchingStreams");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 5;
		contentPane.add(mismatchingStreams, gbc_lblNewLabel_2);
		
		JButton btnStartRunning = new JButton("Start Running");
		btnStartRunning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(algo==Algo.Genetic) {
					try {
						if(running==true) { 
							running=false;
							
							String path=data.saveCandidate(set.get(0));
							label.setText("Saved To : "+path);
							thread1.stop();
							thread2.stop();
						}
						
						else 
						{	
						running=true;
						runGeneticAlgorithm();
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(algo==Algo.HillClimb) {
					try {
						if(running==true) {

							running=false;
							
							String path=data.saveCandidate(candidate);
							label.setText("Saved To : "+path);
							thread3.stop();
							thread4.stop();
							

						}
						else 
						{	
						running=true;
						btnStartRunning.setText("Stop Running And Save");
						runHillClimbAlgorithm();
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnStartRunning = new GridBagConstraints();
		gbc_btnStartRunning.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartRunning.gridx = 1;
		gbc_btnStartRunning.gridy = 6;
		contentPane.add(btnStartRunning, gbc_btnStartRunning);
		
		label = new JLabel("   ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);
		
		lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 8;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnDone = new JButton("Done");
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.gridx = 2;
		gbc_btnDone.gridy = 11;
		contentPane.add(btnDone, gbc_btnDone);

	}
	private void runGeneticAlgorithm() throws InterruptedException {
		if(set==null) {
		List<Student> students=data.getStudents();
		System.out.println(students.size());
		 set=new CandidateSet(data.getStudents());
		set.candidateSetTest();
		 algorithm=new geneticAlgorithem(set);
		}
		 thread1.start();
		 thread2.start();
		
		
	}
	Thread thread1 = new Thread() {
	    public void run() {
			algorithm.mateCandidates();

	        
	      }
	};
	  
	
	Thread thread2 = new Thread() {
	    public void run() {
	        while(running) 
		{
			List<Double> best = set.getBestCandidateValues();
			String numDuplicatesString=       "Number Of Duplicate Projects :                   "+best.get(0);
			String mismatchingStreamString=   "Number Of Mismatching Streams :               "+best.get(1);
			String projectsNotPreferredString="Number Of Projects Not Wanted :            "+best.get(2);
			String averagePrefrenceString=    "Average Prefrence Of Project Allocations : "+best.get(3);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lblNumduplicateprojects.setText(String.format(numDuplicatesString));
			mismatchingStreams.setText(String.format(mismatchingStreamString));
			ProjectsNotPreferred.setText(String.format(projectsNotPreferredString));
			lblAveragePrefrence.setText(String.format(averagePrefrenceString));

			
			

		}
	    }
	};
	private void runHillClimbAlgorithm() throws InterruptedException {
		if(set==null) {
		List<Student> students=data.getStudents();
		  candidate=new CandidateSolution(students);
		}
		thread3.start();
		thread4.start();

		
		 
		
		
	}
	Thread thread3 = new Thread() {
	    public void run() {
	    	HillClimbing climb=new HillClimbing(candidate);
	        climb.startHillClimbing();
	      }
	};
	  
	
	Thread thread4 = new Thread() {
	    public void run() {
	        while(running) 
		{
			List<Double> best = candidate.getData();
			String numDuplicatesString=       "Number Of Duplicate Projects :                  "+best.get(0);
			String mismatchingStreamString=   "Number Of Mismatching Streams :             "+best.get(1);
			String projectsNotPreferredString="Number Of Projects Not Wanted :               "+best.get(2);
			String averagePrefrenceString=    "Average Prefrence Of Project Allocations : "+best.get(3);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lblNumduplicateprojects.setText(String.format(numDuplicatesString));
			mismatchingStreams.setText(String.format(mismatchingStreamString));
			ProjectsNotPreferred.setText(String.format(projectsNotPreferredString));
			lblAveragePrefrence.setText(String.format(averagePrefrenceString));

			
			

		}
	    }
	};
	private JLabel lblNewLabel;
	private JLabel label;
	

}
