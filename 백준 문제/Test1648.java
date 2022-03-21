package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * DP + BitMasking
 *
 */

public class Test1648 {

	static int[][] dp;
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N*M][(1<<M)];
		for(int i = 0; i<N*M; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dp(0,0));
	}
	
	static int dp(int x, int state) {
		//System.out.println(x+","+state);
		if(x == N*M) {
			if(state == 0) return 1;
			else return 0;
		}
		if(x > M*N)
			return 0;
		
		if(dp[x][state] >= 0) return dp[x][state];
		
		dp[x][state] = 0;
		// 이미 그 칸에 도미노가 채워져 있다면
		if((state & (1<<0)) == 1) dp[x][state] = dp(x+1, (state >> 1));
		else {
			// 마지막 벽을 제외하고 앞의 칸이 있는 것도 제외
			if((state & (1 << 1)) == 0 && (x % M < M-1)) {
				dp[x][state] += dp(x+2, (state >> 2));
			}
			// 아래는 무조건 비어있을 예정
			dp[x][state] += dp(x+1, (state >> 1) | (1 << M -1));
		}
		dp[x][state] %= 9901;
		return dp[x][state];
	}

}
