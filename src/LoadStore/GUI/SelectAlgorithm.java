package LoadStore.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LoadStore.DataHandling;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectAlgorithm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					
					SelectAlgorithm frame = new SelectAlgorithm();
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
	public SelectAlgorithm(DataHandling data) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSelectAlgorithm = new JLabel("Select Algorithm");
		lblSelectAlgorithm.setFont(new Font("Sitka Text", Font.PLAIN, 24));
		GridBagConstraints gbc_lblSelectAlgorithm = new GridBagConstraints();
		gbc_lblSelectAlgorithm.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectAlgorithm.gridx = 4;
		gbc_lblSelectAlgorithm.gridy = 0;
		contentPane.add(lblSelectAlgorithm, gbc_lblSelectAlgorithm);
		
		JButton btnGeneticAlgorithm = new JButton("Genetic Algorithm");
		btnGeneticAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AlgorithmRunner runner=new AlgorithmRunner(Algo.Genetic, data);
				
				
				runner.setVisible(true);
				
			}
		});
		GridBagConstraints gbc_btnGeneticAlgorithm = new GridBagConstraints();
		gbc_btnGeneticAlgorithm.insets = new Insets(0, 0, 5, 5);
		gbc_btnGeneticAlgorithm.gridx = 4;
		gbc_btnGeneticAlgorithm.gridy = 2;
		contentPane.add(btnGeneticAlgorithm, gbc_btnGeneticAlgorithm);
		
		JButton btnHillClimbingAlgorithm = new JButton("Hill Climbing Algorithm");
		btnHillClimbingAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AlgorithmRunner runner=new AlgorithmRunner(Algo.HillClimb, data);
				runner.setVisible(true);
				
			}
		});
		GridBagConstraints gbc_btnHillClimbingAlgorithm = new GridBagConstraints();
		gbc_btnHillClimbingAlgorithm.insets = new Insets(0, 0, 0, 5);
		gbc_btnHillClimbingAlgorithm.gridx = 4;
		gbc_btnHillClimbingAlgorithm.gridy = 4;
		contentPane.add(btnHillClimbingAlgorithm, gbc_btnHillClimbingAlgorithm);
	}

}
