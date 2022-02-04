import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14890 {
	static int N,L;
	static int[][] map;
	static boolean[][] rowVisit;
	static boolean[][] colVisit;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		rowVisit = new boolean[N][N];
		colVisit = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		for(int i = 0; i<N; i++) {
			if(checkRow(i)) {
			
				answer++;
			}
				
			if(checkCol(i)){
			
				answer++;
			}
		}
		System.out.println(answer);
			
	}
	static boolean checkRow(int row) {
		boolean[] visit = new boolean[N];
		
		for(int i = 1; i<N; i++) {
			int diff = map[row][i] - map[row][i-1];
			
			if(diff > 1 || diff < -1) return false;
			else if(diff == -1) { //  \
				for(int j = 0; j<L; j++) {
					if(!validCheck(i+j)) return false;
					if(map[row][i + j] + 1 != map[row][i-1] || visit[i+j]) return false;
					visit[i+j] = true;
				}
			}
			else if(diff == 1) { //  /
				for(int j = 1; j<=L; j++) {
					if(!validCheck(i-j)) return false;
					if(map[row][i-j] + 1 != map[row][i] || visit[i-j]) return false;
					visit[i-j] = true;
				}
			}
		}
		
		return true;
	}
	static boolean checkCol(int col) {
		
		boolean[] visit = new boolean[N];
		
		for(int i = 1; i<N; i++) {
			int diff = map[i][col] - map[i-1][col];
			
			if(diff > 1 || diff < -1) return false;
			else if(diff == -1) { // \
				for(int j = 0; j<L; j++) {
					if(!validCheck(i+j)) return false;
					if(map[i+j][col] + 1 != map[i-1][col] || visit[i+j]) return false;
					visit[i+j] = true;
				}
			}
			else if(diff == 1) { // /
				for(int j = 1; j<=L; j++) {
					if(!validCheck(i-j)) return false;
					if(map[i-j][col] + 1 != map[i][col] || visit[i-j] ) return false;
					visit[i-j] = true;
				}
			}
		}
		
		return true;
	}
		
	static boolean validCheck(int i) {
		if(i<0 || i>=N)
			return false;
		return true;
	}

}
