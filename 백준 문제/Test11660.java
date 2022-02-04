import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test11660 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		int[][] dp = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			int temp = 0;
			for(int j = 1; j<=N; j++) {
				temp = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i-1][j] + dp[i][j-1] + temp - dp[i-1][j-1]; 
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int row1 = Integer.parseInt(st.nextToken());
			int col1 = Integer.parseInt(st.nextToken());
			int row2 = Integer.parseInt(st.nextToken());
			int col2 = Integer.parseInt(st.nextToken());
			
			int result = dp[row2][col2] + dp[row1-1][col1-1] - dp[row1-1][col2] - dp[row2][col1-1];
			System.out.println(result);
			
		}
		
		
		/*for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0; 
			for(int row = x1; row<=x2; row++) {
				for(int col = y1; col<=y2; col++) {
					sum += mat[row][col];
				}
			}
			System.out.println(sum);
		}*/ //시간초과
	}
}
