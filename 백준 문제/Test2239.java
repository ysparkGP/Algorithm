package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 백트레킹
 */

public class Test2239 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[9][9];
		for(int i = 0; i<9; i++) {
			String str = br.readLine();
			for(int j = 0; j<9; j++)
				board[i][j] = str.charAt(j) - '0';
		}
		
		dfs(board, 0,0);
	
	}
	
	static void dfs(int[][] board, int row, int col) {
		if(row >= 9) {
			boardPrint(board);
			System.exit(0);
		}
		if(col >= 9) {
			dfs(board,row+1, 0);
			return;
		}
		
		if(board[row][col] != 0) {
			dfs(board, row, col+1);
		}
		else {
			for(int i = 1; i<10; i++) {
				board[row][col] = i;
				if(isValid(board, row, col)) {
					dfs(board, row, col+1);
				}
				board[row][col] = 0;
			}
		}
		
	}
	
	static boolean isValid(int[][] board, int row, int col) {
		boolean[] visit = new boolean[10];
		// 행 검사
		for(int i = 0; i<9; i++) {
			if(board[i][col] == 0) continue;
			if(visit[board[i][col]]) return false;
			else visit[board[i][col]] = true;
		}
		// 열 검사
		Arrays.fill(visit, false);
		for(int i = 0; i<9; i++) {
			if(board[row][i] == 0) continue;
			if(visit[board[row][i]]) return false;
			else visit[board[row][i]] = true;
		}
		// 3x3 박스 검사
		Arrays.fill(visit, false);
		int initRow = (row/3)*3;
		int initCol = (col/3)*3;
		for(int i = initRow; i<initRow+3; i++) {
			for(int j = initCol; j<initCol+3; j++) {
				if(board[i][j] == 0) continue;
				if(visit[board[i][j]]) return false;
				else visit[board[i][j]] = true;
			}
		}
		
		return true;
	}
	static void boardPrint(int[][] board) {
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<9; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}

}
