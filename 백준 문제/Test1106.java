package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1106 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		int C = Integer.parseInt(st.nextToken());  // 목표 고객 수
		int N = Integer.parseInt(st.nextToken());  // 도시 개수
		
		int[][] cc = new int[N][2];  //비용, 고객 수
		int[] dp = new int[C+101];
		int result = Integer.MAX_VALUE;
		
		for(int i = 0; i<N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			cc[i][0] = Integer.parseInt(st.nextToken());
			cc[i][1] = Integer.parseInt(st.nextToken());
			
		}
		
		for(int i = 1; i<=C+100; i++)
			dp[i] = 1000;
		
		
		for(int i = 0; i<N; i++) {
			for(int j = 1; j<=C+100; j++) {
				if(j >= cc[i][1]) {
					dp[j] = Math.min(dp[j - cc[i][1]] + cc[i][0], dp[j]);
				}
				
			}
		}
		
		/*for(int i = 0; i<N; i++) {
			for(int j = cc[i][1]; j<=C+100; j++) {
				int prev = dp[j - cc[i][1]];
				if(prev != Integer.MAX_VALUE) {
					dp[j] = Math.min(prev + cc[i][0], dp[j]);
				}
			}
		}*/
		
		
		for(int i = C; i<=C+100; i++)
			result = result > dp[i]? dp[i] : result;
			
		System.out.println(result);
	}
}
