package assignment2Part2;

import javax.swing.*;

import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SortingAlgorithm;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public class JavaSortingGUI extends JPanel{
		
		JTextField textField;

		  public JavaSortingGUI(){
		  JFrame frame = new JFrame("Sort 500,000");
		  Sorter sorter = new Sorter(0);
		
		  //Label to tell user about text field
		  JLabel label = new JLabel();		
		  label.setText("Enter Amount to Sort:");
		  label.setBounds(10, 220, 200, 100);
		  
		  //Label to report sorting algorithm time
		  JLabel timeLabel = new JLabel();	
		  timeLabel.setText("Please Enter a number between 0-500,00 in the field");
		  timeLabel.setBounds(20, 185, 400, 100);
		  
		  //Sort Button
		  JButton button = new JButton("Sort");
		  button.setBounds(130, 300, 100, 50);
		  button.addActionListener(new ButtonAction(sorter, timeLabel));
		  
		  //Text field to enter int amount
		  textField = new JTextField();
		  textField.setBounds(165, 255, 130, 30);
		  sorter.addTextField(textField);
		  

		
		  //Create list of names for combo box
		  String[] sortingAlgs = new String[sorter.algorithmCount()+1];
		  sortingAlgs[0] = "Random";
		  for(int i=1; i<sorter.algorithmCount()+1; i++) {
			  sortingAlgs[i] = sorter.algorithmName(i-1);
		  }
		  //Add combination box to choose the sorting algorithm
		  JComboBox algList = new JComboBox(sortingAlgs);
		  algList.addActionListener(new ListAction(sorter));
		  algList.setBounds(10, 10, 150,30);
			
		  //add all components to JFrame
		  frame.setLayout(null); //allow for components to be precisely set    
		  frame.add(algList);
		  frame.add(label);
		  frame.add(timeLabel);
		  frame.add(button);
		  frame.add(textField);
		  frame.setSize(400, 400);
		  frame.setVisible(true);
		  frame.setResizable(false);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  }

		  private class ButtonAction implements ActionListener{
			  private Sorter sorter;
			  private JLabel label;
			  public ButtonAction(Sorter sorter, JLabel label){
				  this.sorter = sorter;
				  this.label = label;
			  }
		  
			  public void actionPerformed(ActionEvent e){
				  double timeForSort = sorter.sort();
				  if(timeForSort > 0) //a sorting algorithm was used
			  label.setText("Time for sort: " + timeForSort +" seconds");
				  else if(timeForSort == -1) //no sorting algorithm was used
					  label.setText("No sorting algorithm was used");
				  else if(timeForSort == -2) //invalid or no number was entered
					  label.setText("Please Enter a number between 0-500,00 in the field");
					  
			  }
		  
		  }
		  
		private class ListAction implements ActionListener{
		private Sorter sorter;
		public ListAction(Sorter sorter){this.sorter = sorter;}
		
		//set the algorithm based on the option set in the combination box	
		@Override
		public void actionPerformed(ActionEvent e) {
			 	@SuppressWarnings("unchecked")
				JComboBox<String> box = (JComboBox<String>)e.getSource();
			 	sorter.setAlgorithm(box.getSelectedIndex()-1);
				}
		}
		
		
}
