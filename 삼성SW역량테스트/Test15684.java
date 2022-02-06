import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test15684 {

	static int[][] map;
	static int N,M,H;
	static int[] cc = {-1,1};
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		M = Integer.parseInt(st.nextToken()); // 놓여질 가로선 개수
		H = Integer.parseInt(st.nextToken()); // 가로선 개수
		
		map = new int[H+1][N+1];
		int cnt = 1;
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = cnt;
			map[row][col+1] = cnt;
			cnt++;
		}
		
		if(ladderTest()) {
			System.out.println(0);
			return;
		}
			
		dfs(1,cnt,1,1);
		if(result != Integer.MAX_VALUE) {
			System.out.println(result);
			return;
		}
		System.out.println(-1);
	
	}
	static void dfs(int cnt, int index, int row, int col) {
		if(cnt > 3) {
			return;
		}
		for(int r = row; r <= H; r++) {
			for(int c = col; c<N; c++) {
				if(!validCheck(c+1))
					continue;
				if(map[r][c] == 0 && map[r][c+1] == 0) {
					map[r][c] = index;
					map[r][c+1] = index;
					if(ladderTest()) {
						result = Math.min(result, cnt);
					}
					dfs(cnt+1,index+1,r,col);
					map[r][c] = 0;
					map[r][c+1] = 0;
				}
			}
		}
	}
	
	static boolean ladderTest() {
		
		int[] resultUnit = new int[N+1];
		for(int col = 1; col <= N; col++) {
			int unit = col;
			for(int row = 1; row <= H; row++) {
				if(map[row][unit] == 0)
					continue;
				else if(map[row][unit] > 0) {
					for(int check = 0; check < 2; check++) {
						int colCheck = cc[check] + unit;
						if(!validCheck(colCheck))
							continue;
						if(map[row][unit] == map[row][colCheck]) {
							unit += cc[check];
							break;
						}
					}
				}
			}
			resultUnit[col] = unit;
			if(resultUnit[col] != col)
				return false;
		}
		return true;
	}
	static boolean validCheck(int col) {
		if(col < 1 || col > N)
			return false;
		
		return true;
	}
}
