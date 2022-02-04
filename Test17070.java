import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test17070 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] house = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] DP = new int [N+1][N+1][3];
		DP[1][2][0] = 1;
		
		for(int i = 1; i<=N; i++) {
			
			for(int j = 3; j<=N; j++) {
				if(house[i][j] == 0) {
					DP[i][j][0] = DP[i][j-1][0] + DP[i][j-1][2]; // 가로방향
					DP[i][j][1] = DP[i-1][j][1] + DP[i-1][j][2]; // 세로방향
					if(house[i][j-1] == 0 && house[i-1][j] == 0)
						DP[i][j][2] = DP[i-1][j-1][0] + DP[i-1][j-1][1] + DP[i-1][j-1][2]; // 대각선방향
				}
				
			}
		}
		int result = DP[N][N][0] + DP[N][N][1] + DP[N][N][2];
		System.out.println(result);
		
	}
}
