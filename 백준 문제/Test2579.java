import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test2579 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] step = new int[N+1];
		int[] dp= new int[N+1];
		
		for(int i = 1; i<N+1; i++) {
			step[i] = Integer.parseInt(br.readLine());
		}
		
		if(N==1) {
			System.out.println(step[1]);
			return;
		}
		
		else if(N==2) {
			System.out.println(step[1] + step[2]);
			return;
		}
		else if(N==3) {
			System.out.println(Math.max(step[1] + step[3], step[2] + step[3]));
		}
		
		dp[1] = step[1];
		dp[2] = step[1] + step[2];
		dp[3] = Math.max(step[1] + step[3], step[2] + step[3]);
		
		for(int i = 4; i<=N; i++) {
			dp[i] = Math.max(dp[i-2], dp[i-3] + step[i-1]) + step[i];			
		}
		System.out.println(dp[N]);
		
	}
	
}
