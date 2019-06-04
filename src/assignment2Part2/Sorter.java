package assignment2Part2;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;

import sortingAlgorithms.*;

public class Sorter {
	private static final int MAX = 500000;
	private JTextField textField;
	private SortingAlgorithm algorithm;
	private ArrayList<SortingAlgorithm> algList = new ArrayList<SortingAlgorithm>();
	private int listIndex = -1; //variable to index the list
	
	public Sorter(int listIndex) {
		setAlgorithm(listIndex);
		
		//add algorithms to algorithm list
		algList.add(new SelectionSort());
		algList.add(new InsertionSort());
		algList.add(new ShellSort());
		algList.add(new BubbleSort());
		algList.add(new MergeSort());
		algList.add(new QuickSort());		
		algList.add(new HeapSort());
	}
	
	public void addTextField(JTextField textField) {
		this.textField = textField;
	}
	
	//return how many algorithms are available to use for sorting
	public int algorithmCount() {
		return algList.size();
	}
	
	public String algorithmName(int i) {
		return algList.get(i).getName();
	}
	
	//set the index for the algorithm list to choose a sorting alg
	public void setAlgorithm(int index) {
		if(index >= 0 && index < algorithmCount())
			listIndex = index;
		else
			listIndex = -1; //no algorithm is selected
	}
	
	public double sort() {
		double timeForSort = 0;
		int amount = 0;
		//get the amount of numbers to generate from the text field
		try {
		  amount = Integer.parseInt(textField.getText());
		}catch(NumberFormatException e) {
			return -2;
		}

		  if( amount < 0 || amount > MAX) {
			  return -2;
		  }
		  
		  //generate random numbers to fill a Comparable array
		  Random rand = new Random(amount);
		  Comparable[] array = new Comparable[amount];
		  for(int i=0; i<amount; i++) {
			  array[i] = rand.nextInt();
		  }
		  
		  //sort the array with chosen algorithm, or
		  //not at all if no sorting is selected
		  if(listIndex >= 0) {
			  algorithm = algList.get(listIndex);
			 
			  //time sorting
			  timeForSort = System.nanoTime();
			  algorithm.sort(array);
			  timeForSort = System.nanoTime() - timeForSort;
			
			  //write the sorted array to the file
			  writeToFile(array, "sorted500000.txt");
		  }else
			  writeToFile(array, "random500000.txt"); //write when no sorting has been done
					
		  if(timeForSort > 0) //array was sorted
		  return  timeForSort / 1000000000; //convert from nanoseconds to seconds
		  else
			  return -1;
	}
	
	private void writeToFile(Comparable[] array, String fileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		 
		for(int i=0; i<array.length; i++) {
			writer.write((int) array[i] + " ");
			//write 10 numbers per line
			if(i%10 == 0 && i!=0)
				writer.write("\n");
		}
		writer.close();
		  
		  System.out.println("File written");
	}
	
	

}
