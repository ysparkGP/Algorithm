import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Cloud{
	int y;
	int x;
	Cloud(int y, int x){
		this.y = y;
		this.x = x;
	}
}
public class Test21610 {

	static int[] checkX = {0,-1,-1,0,1,1,1,0,-1};
	static int[] checkY = {0,0,-1,-1,-1,0,1,1,1};
	static int N,M;
	static int[][] map;
	static boolean[][] visit;
	static List<Cloud> cloudList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		visit = new boolean[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] order = new int[M][2];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<2; j++) {
				order[i][j] = Integer.parseInt(st.nextToken()); //방향, 이동 횟수
			}
		}
		
		viva();
		for(int i = 0; i<M; i++) {
			moveCloud(order[i]);
			increment();
			waterCopy();
			last();
		}
			
		
		int answer = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				//System.out.print(map[i][j] + " ");
				answer += map[i][j];
			}
			//System.out.println();
		}
				
		
		System.out.println(answer);
	}
	
	static void viva() { // step 0
		cloudList.add(new Cloud(N-1,1));
		cloudList.add(new Cloud(N-1,2));
		cloudList.add(new Cloud(N,1));
		cloudList.add(new Cloud(N,2));
	}
	static void moveCloud(int[] o) { // step 1
		for(int i = 0; i<cloudList.size(); i++) {
			int y = cloudList.get(i).y;
			int x = cloudList.get(i).x;
			y += (o[1]*checkY[o[0]]) % N;
			x += (o[1]*checkX[o[0]]) % N;
			//moveException
			if(y < 1)
				y += N;
			else if(y > N)
				y -= N;
			if(x < 1)
				x += N;
			else if(x > N)
				x -= N;
			cloudList.get(i).y = y;
			cloudList.get(i).x = x;
		}
		
		
	
	}
	static void increment() { // step 2
		for(int i = 0; i<cloudList.size(); i++) {
			int y = cloudList.get(i).y;
			int x = cloudList.get(i).x;
			map[y][x] += 1;
			visit[y][x] = true;
		}
		
		
	}
	static void waterCopy() { // step 3
		int[][] tempMap = new int[N+1][N+1];
		
		for(int i = 0; i<cloudList.size(); i++) {
		
			//대각선 : 2,4,6,8
			int y = cloudList.get(i).y;
			int x = cloudList.get(i).x;
			int count = 0;
			for(int check = 2; check<10; check+=2) {
				int searchY = y + checkY[check];
				int searchX = x + checkX[check];
				if(boundCheck(searchY, searchX)) {
					if(map[searchY][searchX] > 0)
						count++;
				}
			}
			tempMap[y][x] = count;
		}
		
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				map[y][x] = map[y][x] + tempMap[y][x];
			}
		}
		
	}
	
	static void last() { // step 4
		fadeOut();
		
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				if(map[y][x] >= 2 && !visit[y][x]) {
					map[y][x] -= 2;
					cloudList.add(new Cloud(y,x));
				}
				visit[y][x] = false;
			}
		}
	}
	
	static void fadeOut() { // step 5
		cloudList.clear();
	}
	static boolean boundCheck(int y, int x) {
		if(y<1 || y>N || x<1 || x>N)
			return false;
		return true;
	}
}
