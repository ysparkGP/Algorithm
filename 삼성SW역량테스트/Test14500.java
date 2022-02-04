import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

class Pair{
	int y;
	int x;
	Pair(int y, int x){
		this.y = y;
		this.x = x;
	}
}

public class Test14500 {
	//위,아래,오른쪽,왼쪽
	static int[] xCheck = {0,0,1,-1};
	static int[] yCheck = {-1,1,0,0};
	static int[][] exCheck = {{0,0,0,-1}, {0,0,0,1}, {0,0,-1,1}, {0,1,0,-1}};
	static int[][] eyCheck = {{-1,0,1,0}, {-1,0,1,0}, {-1,0,0,0}, {0,0,1,0}};
	static int N;
	static int M;
	static int[][] box;
	static boolean[][] visit;
	static int max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //세로 크기
		M = Integer.parseInt(st.nextToken()); //가로 크기
		
		box = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int startRow = 0; startRow < N; startRow++) {
			for(int startCol = 0; startCol < M; startCol++) {
				backTracking(startRow, startCol, 0, 0);
				exception(startRow,startCol);
			}
		}
		
		System.out.println(max);
	}
	
	static void backTracking(int i, int j, int cnt, int sum) {
		if(cnt == 4) {
			max = Math.max(sum, max);
			
			return;
		}
		
		for(int check = 0; check<4; check++) {
			int y = i + yCheck[check];
			int x = j + xCheck[check];
			
			if(validCheck(y,x)) {
				if(!visit[y][x]) {
					visit[y][x] = true;
					backTracking(y,x,cnt+1, sum+box[y][x]);
					visit[y][x] = false;
				}
			}
		}
		
	}
	
	static void exception(int i, int j) {
		
		for(int check1 = 0; check1 < 4; check1++) {
			int cnt = 0;
			int eSum = 0;
			for(int check2 = 0; check2 < 4; check2++ ) {
				int y = i + eyCheck[check1][check2];
				int x = j + exCheck[check1][check2];
				if(validCheck(y,x)) {
					cnt++;
					eSum += box[y][x];
					
				}
				
			}
			if(cnt == 4) {
				max = Math.max(eSum, max);
			}
		}
		
	}
	
	
	static boolean validCheck(int i, int j) {
		
		if(i<0 || i>=N || j<0 || j>=M) {
			return false;
		}
		
		return true;
	}
}
