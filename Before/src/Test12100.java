import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test12100 {

	static int N;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/*int[][] resultMap = solve2048(2,map);
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.print(resultMap[i][j] + " ");
			}
			System.out.println();
		}*/
		brute(0,map);
		System.out.println(max);
	}
	
	static void brute(int cnt, int[][] originMap) {
		if(cnt > 5) {
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					max = Math.max(max, originMap[i][j]);
				}
			}
			return;
		}
		for(int d = 0; d<4; d++) {
			int[][] resultMap = solve2048(d, originMap);
			brute(cnt+1, resultMap);
		}
		
	}
	
	static int[][] solve2048(int direction, int[][] cMap) {
		boolean[][] visit = new boolean[N][N];
		int[][] map = copyMap(cMap);
		
		if(direction < 2) { // y축의 이동
			for(int col = 0; col<N; col++) {
				if(direction == 0) { //위로
					for(int row = 1; row < N; row++) {
						if(map[row][col] == 0) continue;
						int r = row;
						boolean tf = false;
						while(true) {
							if(!validCheck(r-1, col)) break;
							if(map[r-1][col] == 0) {
								map[r-1][col] = map[r][col];
								map[r][col] = 0;
							}
							else {
								if(tf)
									break;
								if(map[r-1][col] == map[r][col] && !visit[r-1][col]) {
									map[r-1][col] *= 2;
									map[r][col] = 0;
									visit[r-1][col] = true;
									tf = true;
								}
								else if(map[r-1][col] != map[r][col] || visit[r-1][col]) {
									break;
								}
							}
							r--;
						}
					}
				}
				else if(direction == 1) { //아래로
					for(int row = N-2; row >= 0; row--) {
						if(map[row][col] == 0) continue;
						int r = row;
						boolean tf = false;
						while(true) {
							if(!validCheck(r+1,col)) break;
							if(map[r+1][col] == 0) {
								map[r+1][col] = map[r][col];
								map[r][col] = 0;
							}
							else {
								if(tf)
									break;
								if(map[r+1][col] == map[r][col] && !visit[r+1][col]) {
									map[r+1][col] *= 2;
									map[r][col] = 0;
									visit[r+1][col] = true;
									tf = true;
								}
								else if(map[r+1][col] != map[r][col] || visit[r+1][col]) {
									break;
								}
							}
							r++;
						}
					}
				}
			}
		}
		else if(direction >= 2) { //x축의 이동
			for(int row = 0; row<N; row++) {
				if(direction == 2) { // 왼쪽으로
					for(int col = 1; col < N; col++) {
						if(map[row][col] == 0) continue;
						int c = col;
						boolean tf = false;
						while(true) {
							if(!validCheck(row,c-1)) break;
							if(map[row][c-1] == 0) {
								map[row][c-1] = map[row][c];
								map[row][c] = 0;
							}
							else {
								if(tf)
									break;
								if(map[row][c-1] == map[row][c] && !visit[row][c-1]) {
									map[row][c-1] *= 2;
									map[row][c] = 0;
									visit[row][c-1] = true;
									tf = true;
								}
								else if(map[row][c-1] != map[row][c] || visit[row][c-1])
									break;
							}
							c--;
						}
					}
				}
				else if(direction == 3) { //오른쪽으로
					for(int col = N-2; col >= 0; col--) {
						if(map[row][col] == 0) continue;
						int c = col;
						boolean tf = false;
						while(true) {
							if(!validCheck(row,c+1)) break;
							if(map[row][c+1] == 0) {
								map[row][c+1] = map[row][c];
								map[row][c] = 0;
							}
							else {
								if(tf)
									break;
								if(map[row][c+1] == map[row][c] && !visit[row][c+1]) {
									map[row][c+1] *= 2;
									map[row][c] = 0;
									visit[row][c+1] = true;
									tf = true;
								}
								else if(map[row][c+1] != map[row][c] || visit[row][c+1])
									break;
							}
							c++;
						}
					}
				}
			}
		}
		return map;
	}
	static int[][] copyMap(int[][] map){
		int[][] newMap = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++)
				newMap[i][j] = map[i][j];
		}
		return newMap;
	}
	
	static boolean validCheck(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N)
			return false;
		return true;
	}

}
