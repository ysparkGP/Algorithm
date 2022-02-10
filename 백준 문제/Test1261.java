import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 0+1 BFS
 */

class Pair{
	int y;
	int x;
	int aoj;
	Pair(int y, int x, int aoj){
		this.y = y;
		this.x = x;
		this.aoj = aoj;
	}
}

public class Test1261 {
	static int N,M;
	static int[] cx = {0,1,0,-1};
	static int[] cy = {1,0,-1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M+1][N+1];
		for(int i = 1; i<=M; i++) {
			String str = br.readLine();
			for(int j = 1; j<=N; j++) {
				char value = str.charAt(j-1);
				map[i][j] = Character.getNumericValue(value);
			}
		}
		
		boolean[][] visit = new boolean[M+1][N+1];
		
		
		//깨진 횟수를 최소 기준으로 먼저 탐색
		PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p1.aoj - p2.aoj;
			}
		});
		visit[1][1] = true;
		que.add(new Pair(1,1,0));
		while(!que.isEmpty()) {
			Pair current = que.poll();
			
			if(current.y == M && current.x == N) {
				System.out.println(current.aoj);
				return;
			}
			
			for(int i = 0; i<4; i++) {
				int y = current.y + cy[i];
				int x = current.x + cx[i];
				
				if(!validCheck(y,x)) continue;
				if(visit[y][x]) continue;
				
				que.add(new Pair(y,x,current.aoj+map[y][x]));
				visit[y][x] = true;
			}
		}
	}
	
	static boolean validCheck(int y, int x) {
		if(y<1 || x<1 || y>M || x>N)
			return false;
		return true;
	}

}
