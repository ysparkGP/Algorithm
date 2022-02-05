package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1074 {
	static int[][] A;
	static int N,r,c;
	static int row, col;
	static int index = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		row = (int)Math.pow(2, N);
		col = (int)Math.pow(2, N);
		
		//A = new int[row][col];
		
		int alpha = (int)Math.pow(4, N-1);
		dfs(0, 0, 0, row, alpha);
		
	}
	
	/*  시간초과
	static void dfs(int i, int j, int cnt) {
		
		if(cnt == 1) {
			index++;
			
			if(i == r && j == c)
				System.out.println(--index);
			
			return;
		}
		
		cnt /= 2;
		
		dfs(i, j, cnt);
		dfs(i, j+cnt, cnt);
		dfs(i+cnt, j, cnt);
		dfs(i+cnt, j+cnt, cnt);
		
		return;
	}*/
	
	static void dfs(int i, int j, int val, int cnt, int alpha) {
		//System.out.println(val + " " + cnt);
		if(cnt == 1) {
			if(i == r && j == c) {
				System.out.println(val);
				
			}
			return;
		}
		
		
		
		if(i <= r && r < i+cnt/2 && j <= c && c < j+cnt/2) {  //좌측 상단
			dfs(i, j, val + alpha*0, cnt/2, alpha/4);
		}
		
		else if(i <= r && r < i+cnt/2 && c >= j+cnt/2) {  //우측 상단
			dfs(i, j+cnt/2, val + alpha*1, cnt/2, alpha/4);
		}
		else if(i+cnt/2 <= r && j <= c && c < j+cnt/2) {  //좌측 하단
			dfs(i+cnt/2, j, val + alpha*2, cnt/2, alpha/4);
		}
		else if(i+cnt/2 <= r && c >= j+cnt/2){  //우측 하단
			dfs(i+cnt/2, j+cnt/2, val + alpha*3, cnt/2, alpha/4);
		}
		
		
		return;
	}
}
