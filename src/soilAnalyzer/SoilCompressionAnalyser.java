package soilAnalyzer;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SoilCompressionAnalyser {
	private int[][] soilNodes;
	private WQUWPCUF union;
	private int N;	
	
	public boolean analyzeSoilSample(String file) {
		
		N = numberOfLines(file);
		soilNodes = new int[N][N];
		union = new WQUWPCUF((N*N)+2);
		
		/* Fill soilNodes[][] with the data from the
		 * specified file, making it a matrix of 1s and 0s
		 */
		try {
			int row = 0;
			int column = 0;
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				column = 0;
			for(int i=0; i<line.length(); i++) {
				char num = line.charAt(i);
				
				if(num != ' ') { //do not evaluate if char is a space
				soilNodes[row][column] = Character.getNumericValue(num);
				column++;
				}
				
			}
			row++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Union first "outside" point with all elements in first row
		int upperPoint = (N*N);
		for(int i=0; i<N; i++) {
			if(soilNodes[0][i]==1) {
				union.union(upperPoint, i);
			}
		}
		//Union second "outside" point to all elements in bottom row
		int lowerPoint = (N*N)+1;
		for(int i=0; i<N; i++) {
			if(soilNodes[N-1][i]==1) {
				int point = matrixToLinear((N-1), i, N);
				union.union(lowerPoint, point);
			}
		}
		
		//Go through array and union adjacent nodes with value 1
		//(note: diagonal nodes do not count as adjacent)
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(soilNodes[i][j] == 1) {
				checkLeft(i, j);
				checkRight(i,j);
				checkDown(i,j);
				}

			}
		}
		
		
		return union.connected(upperPoint, lowerPoint); //if they are connected, water can pass through
	}
	
	//check if there is a 1 to the left of the current
	// 1 in matrix
	private void checkLeft(int row, int col) {
		if(col == 0)
				return;
		if(soilNodes[row][col-1] == 1) {
			int p = matrixToLinear(row, col, N);
			int q = matrixToLinear(row, col-1, N);
			union.union(p,q);
			return;
		}
	}
	//check if there is a 1 to the right of the current
	// 1 in matrix
	private void checkRight(int row, int col) {
		if(col == N-1)
				return;
		if(soilNodes[row][col+1] == 1) {
			int p = matrixToLinear(row, col, N);
			int q = matrixToLinear(row, col+1, N);
			union.union(p,q);
			return;
		}
	}
	//check if there is a 1 below the current
	// 1 in matrix
	private void checkDown(int row, int col) {
		if(row == N-1)
				return;
		if(soilNodes[row+1][col] == 1) {
			int p = matrixToLinear(row, col, N);
			int q = matrixToLinear(row+1, col, N);
			union.union(p,q);
			return;
		}
	}
	
	private int matrixToLinear(int row, int column, int n) {
		row++;
		return ((row-1) * n) + column;
	}
	
	private int numberOfLines(String file) {
		Path path = Paths.get(file);
		try {
			long numberOfLines = Files.lines(path).count();
			return (int) numberOfLines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}


}
