import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class CCTV{
	int y;
	int x;
	int type;
	CCTV(int y, int x, int type){
		this.y = y;
		this.x = x;
		this.type = type;
	}
}

public class Test15683 {

	// 왼, 아래, 오른, 위
	static int[] yCheck = {0,1,0,-1};
	static int[] xCheck = {-1,0,1,0};
	static int[][][] function = {
			{{0}},
			{{2},{0},{1},{3}},
			{{0,2},{1,3}},
			{{2,3},{0,3},{0,1},{1,2}},
			{{0,2,3},{0,1,3},{0,1,2},{1,2,3}},
			{{0,1,2,3}}
	};
	static int max;
	static int N,M;
	static int count;
	static int wall;
	static int[][] office;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		office = new int[N][M];
		List<CCTV> cctvs = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			//0: 빈칸, 6: 벽, 1~5: CCTV
			for(int j = 0; j<M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if(office[i][j] >= 1 && office[i][j] <= 5) {
					cctvs.add(new CCTV(i,j,office[i][j]));
					count++;
				}
				else if(office[i][j] == 6) {
					wall++;
				}
			}
		}
		
		
		dfs(cctvs, 0, 0, office);
		int noSafe = (N*M) - max - count - wall;
		System.out.println(noSafe);
		
	}
	
	static void dfs(List<CCTV> cctvs, int index, int safe, int[][] visit) {
		
		if(index == count) {
			max = Math.max(max, safe);

			return;
		}
		
		CCTV cctv = cctvs.get(index);
		int y = cctv.y;
		int x = cctv.x;
		int type = cctv.type;
		//System.out.println(x +", " + y +", " + type);
		//회전 4방향
		for(int rotation = 0; rotation < function[type].length; rotation++) {
			//cctv타입의 기능
			int[][] newVisit = copyVisit(visit);
			int newSafe = 0;
			for(int f = 0; f < function[type][rotation].length; f++) {
				int check = function[type][rotation][f];
				newSafe += safeCheck(y,x,yCheck[check],xCheck[check], newVisit);
			}
			dfs(cctvs, index+1, safe + newSafe, newVisit);
		}

	}
	static int[][] copyVisit(int[][] visit) {
		int[][] newVisit = new int[N][M];
		
		for(int i = 0; i<N; i++)
			for(int j = 0; j<M; j++)
				newVisit[i][j] = visit[i][j];
		
		return newVisit;
	}
	
	static int safeCheck(int y, int x, int yDirection, int xDirection, int[][] visit) {
		int count = 0;

		y += yDirection;
		x += xDirection;
		while(validCheck(y,x)) {
			//System.out.println(y + ", " + x);
			if(visit[y][x] == 6)
				break;
			if(visit[y][x] == 0) {
				count += 1;
				visit[y][x] = -1;
			}
			y += yDirection;
			x += xDirection;
		}
		
		return count;
	}
	
	static boolean validCheck(int y, int x) {
		if(y<0 || y>=N || x<0 || x>=M)
			return false;
		
		return true;
	}

}
