import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test2580 {
	
	
	static int[][] box;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		box = new int[9][9];
		for(int i = 0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<9; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backTracking(0,0);
		
		
	}
	
	//best
	static boolean checkValid(int row, int col, int value) {
		for(int i = 0; i<9; i++) { //행 검사
			if(value == box[i][col])
				return false;
		}
		
		for(int i = 0; i<9; i++) { //열 검사
			if(value == box[row][i])
				return false;
		}
		
		int rowStart = (row/3) * 3;
		int colStart = (col/3) * 3;
		
		for(int r = rowStart; r<rowStart+3; r++) {
			for(int c = colStart; c<colStart+3; c++) {
				if(value == box[r][c])
					return false;
			}
		}
		
		
		return true;
	}
	
	static void backTracking(int row, int col) {
		
		if(col >= 9) {
			backTracking(row+1, 0);
			return;
		}
		
		if(row >= 9) {
			for(int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++) 
					System.out.print(box[i][j] + " ");
				System.out.println();
			}
			System.exit(0);
		}
		
		if(box[row][col] == 0) {
			for(int i = 1; i<=9; i++) {
				if(checkValid(row,col,i)) {
					box[row][col] = i;
					backTracking(row,col+1);
				}
			}
			box[row][col] = 0;
			return;
		}
		
		backTracking(row, col+1);
	}
	
	/*
	static boolean checkValid(int row, int col) {
		
		boolean[] rowVisit = new boolean[10];
		boolean[] colVisit = new boolean[10];
		boolean[] boxVisit = new boolean[10];
		
		for(int i = 0; i<9; i++) {
			if(box[row][i] == 0)
				continue;
			
			if(!rowVisit[box[row][i]]) {
				rowVisit[box[row][i]] = true;
			}
			else {
				return false;
			}
		}
		
		for(int i = 0; i<9; i++) {
			if(box[i][col] == 0)
				continue;
			
			if(!colVisit[box[i][col]]) {
				colVisit[box[i][col]] = true;
			}
			else {
				return false;
			}
		}
		
		int rowStart = (row/3) * 3;
		int colStart = (col/3) * 3;
		
		for(int r = rowStart; r<rowStart+3; r++) {
			for(int c = colStart; c<colStart+3; c++) {
				if(box[r][c] == 0)
					continue;
				
				if(!boxVisit[box[r][c]]) {
					boxVisit[box[r][c]] = true;
				}
				else {
					return false;
				}
			}
		}
		
		
		return true;
	}
	
	
	static void backTracking(int row, int col) {
		
		if(row >= 9) {
			for(int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++)
					System.out.print(box[i][j] + " ");
				
				System.out.println();
			}
			
			System.exit(0); //되돌아가지않고 시스템을 끝낸다.
		}
		
		if(box[row][col] == 0) {
			
			for(int i = 1; i<=9; i++) {
				box[row][col] = i;
				if(checkValid(row,col)) {
					if(col < 8) {
						backTracking(row, col+1);
					}
					else {
						backTracking(row+1, 0);
					}
				}
				
			}
			
			//틀렸을 때, 되돌아가기위함
			box[row][col] = 0;
			return;
		}
		else {
			if(col < 8) {
				backTracking(row, col+1);
			}
			else {
				backTracking(row+1, 0);
			}
		}
		
	}*/
}

