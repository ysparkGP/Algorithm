package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 비트마스킹을 이용한 DP, 계단수
 */

public class Test1562 {

	static int MOD = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][][] dp = new long[N+1][10][1<<10]; // 자릿수, 끝수, 등장하는 수들
		for(int i = 1; i<10; i++) {
			dp[1][i][1<<i] = 1;
		}
		
		for(int i = 2; i<=N; i++) { // 자릿수
			for(int j = 0; j<10; j++) { // 끝수
				for(int k = 0; k<1024; k++) { // 0~9까지 체크된 수를 비트로 표현
					int bit = k | (1<<j);
					if(j ==0) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % MOD;
					}
					else if(j == 9) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % MOD;
					}
					else {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % MOD; 
					}
				}
			}
		}
		
		long sum = 0;
		for(int i = 0; i<10; i++)
			sum = (sum + dp[N][i][1023]) % MOD;
		System.out.println(sum);
	}

}
