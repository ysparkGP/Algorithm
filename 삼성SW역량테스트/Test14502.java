import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Pair{
	int y;
	int x;
	Pair(int y, int x){
		this.y = y;
		this.x = x;
	}
}
public class Test14502 {
	static int N,M;
	static int[][] lab;
	static int max;
	static boolean[][] visit;
	static int temp;
	
	//위, 아래, 왼쪽, 오른쪽
	static int[] xCheck = {0,0,-1,1};
	static int[] yCheck = {1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		temp = 0;
		lab = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 1) {
					temp++;
				}
			}
		}
		
		backTracking(0,0,0);
		System.out.println(max);
		
	}
	
	static void backTracking(int row, int col, int cnt) {
		
		
		if(cnt == 3) {
			int virus = virus();
			max = Math.max(N*M - (virus + temp + 3), max);
			return;
		}
		
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(!visit[i][j] && lab[i][j] == 0) {
					visit[i][j] = true;
					lab[i][j] = 1;
					backTracking(i,j,cnt+1);
					visit[i][j] = false;
					lab[i][j] = 0;
				}	
				
			}
		}
		
	}
	
	static int virus() {
	
		int cnt = 0;
		
		Queue<Pair> que = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(lab[i][j] == 2) {
					que.add(new Pair(i,j));
					v[i][j] = true;
					
				}
					
			}
		}
		while(!que.isEmpty()) {
			Pair p = que.poll();
			int y = p.y;
			int x = p.x;
			
			cnt+=1;
			for(int check = 0; check < 4; check++) {
				int cy = y+yCheck[check];
				int cx = x+xCheck[check];
				if(validCheck(cy,cx) && !v[cy][cx] && lab[cy][cx] == 0) {
					que.add(new Pair(cy,cx));
					v[cy][cx] = true;
				}
			}
		}
		
		return cnt;
		
	}
	
	static boolean validCheck(int i, int j) {
		if(i < 0 || i >= N || j < 0 || j >= M)
			return false;
		
		return true;
	}
}
