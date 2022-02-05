package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test11053 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int[] A = new int[N];
		
		for(int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<i; j++) {
				if(A[j] < A[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int result = 0;
		for(int i = 0; i<N; i++) {
			
			result = Math.max(result, dp[i]);
			//System.out.print(dp[i] + " ");
			
		}
		
	}
}
