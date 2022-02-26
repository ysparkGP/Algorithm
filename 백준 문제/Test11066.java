package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * DP 연쇄 행렬 최소 곱셈 알고리즘 비슷
 */

public class Test11066 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0 ; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int[] arr = new int[K];
			int[] sum = new int[K];
			for(int k = 0; k<K; k++) {
				arr[k] = Integer.parseInt(st.nextToken());
				if(k == 0)
					sum[k] = arr[k];
				else {
					sum[k] = sum[k-1] +  arr[k];
				}
			}
			
			int[][] dp = new int[K][K]; // i->j 까지 최소 비용
			for(int i = 0; i<K-1; i++) { // a->b, b->c, c->d 는 최소비용이 단순하게 합친 것과 같음
				dp[i][i+1] = arr[i] + arr[i+1];
			}
			
			//dp[i][i+gap] = Math.min(dp[i][i+gap], dp[i][k]+dp[k+1][i+gap]+sum(i,i+gap);
			for(int gap = 2; gap < K; gap++) {
				for(int i = 0; i+gap < K; i++) {
					for(int k = i; k<i+gap; k++) {
						if(dp[i][i+gap] == 0)
							dp[i][i+gap] = dp[i][k] + dp[k+1][i+gap] + sumDist(sum,i,i+gap);
						else
							dp[i][i+gap] = Math.min(dp[i][i+gap], dp[i][k] + dp[k+1][i+gap]+sumDist(sum,i,i+gap));
					}
				}
			}
			System.out.println(dp[0][K-1]);
		}
	}
	
	static int sumDist(int[] sum, int start, int end) {
		
		if(start == 0)
			return sum[end];
		return  sum[end] - sum[start-1];
	}

}
