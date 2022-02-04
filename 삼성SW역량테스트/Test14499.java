import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14499 {
	
	//동: 1, 서: 2, 북: 3, 남: 4
	static int[] xMove = {0, 1, -1, 0, 0};
	static int[] yMove = {0, 0, 0, -1, 1};
	static int N;
	static int M;
	
	static int front, back, left, right, top, bottom;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine()," ");
		int[] order = new int[K+1];
		order[0] = 0;
		for(int k = 1; k<=K; k++) {
			order[k] = Integer.parseInt(st.nextToken());
		}
		
		int i = x;
		int j = y;
		for(int k = 0; k<=K; k++) {
			if(order[k] == 0) {// 제자리
				if(board[i][j] != 0) {
					bottom = board[i][j];
					board[i][j] = 0;
				}
				else {
					board[i][j] = bottom;
				}
			}
			
			else if(order[k] == 1) { // 동
				i += yMove[order[k]];
				j += xMove[order[k]];
				if(!validCheck(i,j)) {
					i -= yMove[order[k]];
					j -= xMove[order[k]];
					continue;
				}
				
				int temp1 = right;
				int temp2 = bottom;
				int temp3 = left;
				int temp4 = top;
				bottom = temp1;
				left = temp2;
				top = temp3;
				right = temp4;
				
				if(board[i][j] != 0) {
					bottom = board[i][j];
					board[i][j] = 0;
				}
				else {
					board[i][j] = bottom;
				}
				
				System.out.println(top);
			}
			else if(order[k] == 2) { // 서
				i += yMove[order[k]];
				j += xMove[order[k]];
				if(!validCheck(i,j)) {
					i -= yMove[order[k]];
					j -= xMove[order[k]];
					continue;
				}
				
				int temp1 = right;
				int temp2 = top;
				int temp3 = left;
				int temp4 = bottom;
				top = temp1;
				left = temp2;
				bottom = temp3;
				right = temp4;
				if(board[i][j] != 0) {
					bottom = board[i][j];
					board[i][j] = 0;
				}
				else {
					board[i][j] = bottom;
				}
				System.out.println(top);
			}
			else if(order[k] == 3) { // 북
				i += yMove[order[k]];
				j += xMove[order[k]];
				if(!validCheck(i,j)) {
					i -= yMove[order[k]];
					j -= xMove[order[k]];
					continue;
				}
				
				int temp1 = top;
				int temp2 = back;
				int temp3 = bottom;
				int temp4 = front;
				back = temp1;
				bottom = temp2;
				front = temp3;
				top = temp4;
				if(board[i][j] != 0) {
					bottom = board[i][j];
					board[i][j] = 0;
				}
				else {
					board[i][j] = bottom;
				}
				
				System.out.println(top);
			}
			else if(order[k] == 4) { // 남
				i += yMove[order[k]];
				j += xMove[order[k]];
				if(!validCheck(i,j)) {
					i -= yMove[order[k]];
					j -= xMove[order[k]];
					continue;
				}
				
				int temp1 = top;
				int temp2 = front;
				int temp3 = bottom;
				int temp4 = back;
				front = temp1;
				bottom = temp2;
				back = temp3;
				top = temp4;
				if(board[i][j] != 0) {
					bottom = board[i][j];
					board[i][j] = 0;
				}
				else {
					board[i][j] = bottom;
				}
				System.out.println(top);
			}
			
			
		}
		
	}
	
	static boolean validCheck(int i, int j) {
		if(i < 0 || i >= N || j < 0 || j >= M)
			return false;
		return true;
	}
}
