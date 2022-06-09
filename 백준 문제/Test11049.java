package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * dp
 * 행렬 곱셈
 */

public class Test11049 {

	static class Pair{
		int s;
		int e;
		Pair(int s, int e){
			this.s = s;
			this.e = e;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pair[] arr = new Pair[N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[i] = new Pair(s,e);
		}
		
		int[][] dp = new int[N][N];
		for(int i = 0; i<N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			dp[i][i] = 0;
		}
		
		for(int length = 1; length<N; length++) {
			for(int i = 0; i+length < N; i++){
				for(int j = i; j<length+i; j++) {
					dp[i][i+length] = Math.min(dp[i][i+length], dp[i][j] + dp[j+1][i+length] + arr[i].s*arr[j].e*arr[i+length].e);
				}
			}	
		}
		System.out.println(dp[0][N-1]);

	}

}
