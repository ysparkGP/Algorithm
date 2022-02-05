package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1495 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] li = new int[N+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			li[i] = Integer.parseInt(st.nextToken());
		}
		
		
		boolean[][] dp = new boolean[N+1][M+1]; //°î´ç ÀüÃ¼ º¼·ý ¸®½ºÆ®
		dp[0][S] = true;
		for(int i = 1; i<=N; i++) {
			for(int j = 0; j<=M; j++) {
				if(dp[i-1][j] == true) {
					if(j - li[i] >= 0) {
						dp[i][j-li[i]] = true;
					}
					
					if(j + li[i] <= M) {
						dp[i][j+li[i]] = true;
					}
				}
			}
		}
		
		for(int i = M; i>=0; i--) {
			if(dp[N][i] == true) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(-1);
	}
}
