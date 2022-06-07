package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp
 */

public class Test10942 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		boolean[][] dp = new boolean[N+1][N+1];
		setDp(dp, arr,N);
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(dp[start][end]) sb.append("1\n");
			else sb.append("0\n");
			
		}
		System.out.println(sb.toString());
	}
	
	public static void setDp(boolean[][] dp, int[] arr, int N) {
		// 길이가 1인 펠린드롬
		for(int i = 1; i<=N; i++) {
			dp[i][i] = true;
		}
		
		// 길이가 2인 펠린드롬
		for(int i = 1; i<N; i++) {
			if(arr[i] == arr[i+1])
				dp[i][i+1] = true;
		}
		
		// 길이가 3이상인 펠릳드롬
		for(int i = 2; i<N; i++) {
			for(int j = i+1; j<=N; j++) {
				if(arr[j] == arr[j-i] && dp[j-i+1][j-1]) {
					dp[j-i][j] = true;
				}
			}
		}
	}

}
