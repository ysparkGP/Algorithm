package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test1657 {

	static int M,N;
	static char[][] grade;
	static int[][] dp;
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grade = new char[N][M];
		dp = new int[N*M][(1<<M)];
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				grade[i][j] = str.charAt(j);
			}
		}
		for(int i = 0; i<N*M; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dp(0,0));
		
	}

	static int dp(int index, int state) {
		if(index >= M*N) {
			if(index == M*N && state == 0) return 0;
			else return Integer.MIN_VALUE;
		}
		
		if(dp[index][state] >= 0) return dp[index][state];
		
		int res = 0;
		res = dp(index+1, state>>1);
		if((state & 1) == 0)
		{
			// 좌우 두부
			if(index % M < M-1 && (state&2) == 0) {
				res = Math.max(res, dp(index+2, state>>2) + calPrice(grade[index/M][index%M], grade[index/M][index%M + 1]));
			}
			
			if(index/M < N-1) {
				// 상하 두부
				res = Math.max(res, dp(index+1, (state>>1) | (1<<(M-1))) + calPrice(grade[index/M][index%M],
						grade[index/M+1][index%M]));
			}
			 
		}
		return dp[index][state] = res;
	}
	
	static int calPrice(char c1, char c2) {
		if(c1 > c2) {
			char temp = c1;
			c1 = c2;
			c2 = temp;
		}
		
		if(c1 == 'A') {
			if(c2 == 'A') {
				return 10;
			}
			else if(c2 == 'B') {
				return 8;
			}
			else if(c2 == 'C') {
				return 7;
			}
			else if(c2 == 'D') {
				return 5;
			}
			else{
				return 1;
			}
		}
		else if(c1 == 'B') {
			if(c2 == 'B') {
				return 6;
			}
			else if(c2 == 'C') {
				return 4;
			}
			else if(c2 == 'D') {
				return 3;
			}
			else {
				return 1;
			}
		}
		else if(c1 == 'C') {
			if(c2 == 'C') {
				return 3;
			}
			else if(c2 == 'D') {
				return 2;
			}
			else {
				return 1;
			}
		}
		else if(c1 == 'D') {
			if(c2 == 'D') {
				return 	2;
			}
			else {
				return 1;
			}
		}
		else {
			return 0;
		}
			
	}
}
