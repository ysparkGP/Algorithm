import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

public class Test16234 {

	//왼,아래,오른,위
	static int[] checkY = {0,1,0,-1};
	static int[] checkX = {-1,0,1,0};
	static List<List<Pair>> openList;
	static int[][] world;
	static int N,L,R;
	static boolean[][] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		world = new int[N][N];
		visit = new boolean[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				world[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		while(true) {
			openList = new ArrayList<>();
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					
					List<Pair> tempList = new ArrayList<>();
					dfs(tempList,i,j);
					if(tempList.isEmpty())
						continue;
					openList.add(tempList);
				}
			}
			
			if(openList.size() == N*N)
				break;
			else {
				count++;
				for(int k = 0; k<openList.size(); k++) {
					if(openList.get(k).size() == 1)
						continue;
					else
						movement(openList.get(k));
				}
				for(int k = 0; k<N; k++) {
					Arrays.fill(visit[k], false);
				}
			}
				
		}
		
		System.out.println(count);
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= N)
			return false;
		
		return true;
	}
	
	static void dfs(List<Pair> tempList, int y, int x) {
		if(visit[y][x]) return;
		
		visit[y][x] = true;
		tempList.add(new Pair(y,x));
		
		for(int check = 0; check<4; check++) {
			int ny = y + checkY[check];
			int nx = x + checkX[check];
			if(boundCheck(ny,nx)) {
				if(Math.abs(world[ny][nx] - world[y][x]) >= L && Math.abs(world[ny][nx] - world[y][x]) <= R) {
					dfs(tempList, ny, nx);
				}
			}
		}
	}
	
	static void movement(List<Pair> list) {
		Iterator<Pair> iter = list.iterator();
		int cnt = 0;
		int sum = 0;
		while(iter.hasNext()) {
			cnt++;
			Pair openCountry = iter.next();
			sum += world[openCountry.y][openCountry.x];
		}
		int result = sum/cnt;
		Iterator<Pair> changeIter = list.iterator();
		while(changeIter.hasNext()) {
			Pair changeCountry = changeIter.next();
			world[changeCountry.y][changeCountry.x] = result;
		}
	}
	
}
