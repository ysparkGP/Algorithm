package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * bfs + 방향에 따른 방문 처리
 */

public class Test1726 {

	static class Robot{
		int y;
		int x;
		int dir;
		int exe;
		Robot(int y, int x, int dir, int exe){
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.exe = exe;
		}
	}
	static int[][] map;
	static Robot startPoint, endPoint;
	static int M,N;
	static int minResult = Integer.MAX_VALUE;
	//동서남북
	static int[] cy = {0,0,0,1,-1};
	static int[] cx = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken()); // 세로길이
		N = Integer.parseInt(st.nextToken()); // 가로길이
		
		map = new int[M][N];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<2; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			if(startPoint == null) {
				startPoint = new Robot(y-1,x-1,dir,0);
			}
			else {
				endPoint = new Robot(y-1,x-1,dir,0);
			}
		
		}
		bfs();
		System.out.println(minResult);
	}
	
	static void bfs() {
		
		boolean[][][] rotation = new boolean[5][M][N];
		PriorityQueue<Robot> que = new PriorityQueue<>(new Comparator<Robot>() {
			@Override
			public int compare(Robot r1, Robot r2) {
				return r1.exe - r2.exe;
			}
		});
		que.add(startPoint);
		
		while(!que.isEmpty()) {
			Robot nowPoint = que.poll();
			int y = nowPoint.y;
			int x = nowPoint.x;
			int dir = nowPoint.dir;
			int exe = nowPoint.exe;
			
			//System.out.println(y +", " + x + ", " + dir + ", " + exe );
			if(y == endPoint.y && x == endPoint.x && dir == endPoint.dir) {
				minResult = Math.min(minResult, exe);
				continue;
			}
			
			//직진
			for(int i = 1; i<=3; i++) {
				int ny = y + i*cy[dir];
				int nx = x + i*cx[dir];
				if(!boundCheck(ny,nx)) break;
				if(map[ny][nx] == 1) break;
				if((!rotation[dir][ny][nx])) {
					que.add(new Robot(ny,nx,dir,exe+1));
					rotation[dir][ny][nx] = true;	
				}
			}
			//회전
			if(dir == 1 || dir == 2) {
				if((!rotation[3][y][x])) {
					que.add(new Robot(y,x,3,exe+1));
					rotation[3][y][x] = true;
				}
				
				if((!rotation[4][y][x])) {
					que.add(new Robot(y,x,4,exe+1));
					rotation[4][y][x] = true;
					
				}
			}
			else if(dir == 3 || dir == 4) {
				if((!rotation[1][y][x])) {
					que.add(new Robot(y,x,1,exe+1));
					rotation[1][y][x] = true;
					
				}
				
				if((!rotation[2][y][x])) {
					que.add(new Robot(y,x,2,exe+1));
					rotation[2][y][x] = true;
				}
			}
			
		}
		
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || x < 0 || y >= M || x >= N)
			return false;
		return true;
	}
}
