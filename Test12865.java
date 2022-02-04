import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Test12865 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 물건의 개수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		
		int[][] wv = new int[N+1][2]; // 물건의 무게, 가치
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<2; j++) {
				wv[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/*Arrays.sort(wv, new Comparator<int[]>() {
			@Override
			public int compare(int[] v1, int[] v2) {
				return v2[1] - v1[1];
			}
		});*/
		
		
		int[][] dp = new int[N+1][K+1];
		int max = 0;
		for(int i = 1; i<=N; i++) {
			
			for(int j = 0; j<=K; j++) {
				if(j >= wv[i][0]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wv[i][0]] + wv[i][1]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
				
				max = Math.max(max, dp[i][j]);
			}
			
		}
		
		System.out.println(max);
		
	}
}
