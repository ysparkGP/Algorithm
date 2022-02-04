import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//분할 정복 + 행렬 곱
public class Test10830 {
	static int N;
	static int[][] omat;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		omat = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=N; j++) {
				omat[i][j] = Integer.parseInt(st.nextToken());
				omat[i][j] %= 1000;
			}
		}
		
		int[][] result = div(omat,B);
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	//분할 정복
	static int[][] div(int[][] mat, long pow){
		if(pow == 1) {
			return mat;
		}
		
		int[][] result = new int[N+1][N+1];
		result = div(mat, pow/2);
		
		result = multiply(result, result);
		
		if(pow % 2 == 1) //지수가 홀 수
			result = multiply(result, omat);
		
		return result;
	}
	
	static int[][] multiply(int[][] rmat, int[][] mat) {
		
		int[][] result = new int[N+1][N+1];
		
		for(int row = 1; row <= N; row++) {
			
			for(int col = 1; col <= N; col++) {
				for(int i = 1; i<=N; i++) {
					result[row][col] += rmat[row][i] * mat[i][col];
					result[row][col] %= 1000;
				}
			}
		}
		
		return result;
		
	}
	
}
