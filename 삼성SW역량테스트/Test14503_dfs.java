import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14503_dfs {
	//북, 동, 남, 서
	static int[] checkY = {-1,0,1,0};
	static int[] checkX = {0,1,0,-1};
	static int[][] lab;
	static int cnt;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		lab = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(y,x,d);
		System.out.println(cnt);
	}
	
	static void dfs(int y, int x, int d) {
		
		if(lab[y][x] == 0) {
			lab[y][x] = 2;
			cnt+=1;
		}
		
		for(int check = -1; check >= -4; check--) { // 4방향 탐색
			int searchD = (4 + (d+check)) % 4;
			int searchY = y + checkY[searchD];
			int searchX = x + checkX[searchD];
			if(validCheck(searchY, searchX)) {
				if(lab[searchY][searchX] == 0) {
					dfs(searchY,searchX,searchD);
					return;
				}
			}
			
		}
		
		//4방향 탐색 후 청소할 곳이 없다면
		//후진을 못하는 상황이라면 종료
		int backD = (4+(d-2)) % 4;
		int backY = y + checkY[backD];
		int backX = x + checkX[backD];
		if(validCheck(backY,backX)) {
			if(lab[backY][backX] == 2) {
				dfs(backY,backX,d);
			}
		}
		
	}
	
	static boolean validCheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= M) 
			return false;
		
		return true;
	}
}
