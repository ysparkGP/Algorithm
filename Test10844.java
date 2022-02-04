import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test10844 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N==1) {
			System.out.print(9);
			return;
		}
		
		int[][] dp = new int[N+1][10];
		dp[2][0] = 1;
		dp[2][9] = 1;
		for(int i = 1; i<=8; i++) {
			dp[2][i] = 2;
		}
		
		for(int i = 3; i<=N; i++) {
			
			for(int j = 0; j<10; j++) {
				if(j == 0)
					dp[i][j] = dp[i-1][j+1] % 1000000000;
				else if(j == 9)
					dp[i][j] = dp[i-1][j-1] % 1000000000;
				
				else {
					dp[i][j] = dp[i-1][j-1] % 1000000000 + dp[i-1][j+1] % 1000000000;
				}
			}
			
		}
		long sum = 0;
		for(int i = 1; i<10; i++) {
			sum += dp[N][i];
			//sum %= 1000000000;
			//System.out.print(dp[N][i] + " ");
		}
		System.out.println(sum%1000000000);
	}
}
