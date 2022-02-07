import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class fireBall{
	int m; // 질량
	int s; // 속도
	int d; // 방향
	fireBall(int m, int s, int d){
		this.m = m;
		this.s = s;
		this.d = d;
	}
}
public class Test20056 {

	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static List<fireBall>[][] map;
	static int N,M,K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[y][x].add(new fireBall(m,s,d));
		}
		
		for(int k = 0; k<K; k++) {
			order();
		}
		
		
		int totalM = 0;
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				if(map[y][x].size() >= 1) {
					for(fireBall f : map[y][x]) {
						totalM += f.m;
					}
				}
			}
		}
		
		System.out.println(totalM);
	}
	
	static void order() {
		//moveFireBall
		List<fireBall>[][] nextMap = new ArrayList[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j =1; j<=N; j++) {
				nextMap[i][j] = new ArrayList<>();
			}
		}
		
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				if(map[y][x].size() >= 1) {
					for(int index = 0; index<map[y][x].size(); index++) {
						int moveDistance = map[y][x].get(index).s % N;
						int nextY = y + dy[map[y][x].get(index).d] * moveDistance;
						int nextX = x + dx[map[y][x].get(index).d] * moveDistance;
						
						if(nextY < 1)
							nextY += N;
						else if(nextY > N)
							nextY -= N;
						
						if(nextX < 1)
							nextX += N;
						else if(nextX > N)
							nextX -= N;
						//System.out.println(nextY + ", " + nextX);
						
						nextMap[nextY][nextX].add(new fireBall(map[y][x].get(index).m,map[y][x].get(index).s,map[y][x].get(index).d));
					}	
				}
				
			}
		}
		
		//divideFireBall
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				if(nextMap[y][x].size() >= 2) {
					int newM = 0;
					int newS = 0;
					int newD = 0;
					boolean tf = true;
					int cnt = nextMap[y][x].size();
					int value = nextMap[y][x].get(0).d % 2;
					for(fireBall f : nextMap[y][x]) {
						newM += f.m;
						newS += f.s;
						if(value != (f.d %2))
							tf = false;
					}
					nextMap[y][x].clear();
					if(newM/5 != 0) {
						if(tf) {
							for(int i = 0; i<8; i+=2) {
								nextMap[y][x].add(new fireBall(newM/5, newS/cnt, i));
							}
						}
						else {
							for(int i = 1; i<8; i+=2) {
								nextMap[y][x].add(new fireBall(newM/5, newS/cnt, i));
							}
						}	
					}
					
				}
			}
		}
		
		map = nextMap;
		
		/*for(int y = 1; y <= N; y++) {
			for(int x = 1; x<=N; x++) {
				for(fireBall f : map[y][x]) {
					System.out.print(f.m + ", " + y + ", " + x + " ");
				}
			}
			System.out.println();
		}*/
	}
	
}
