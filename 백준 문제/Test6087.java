package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * bfs
 */
public class Test6087 {

	static class Pair{
		int y;
		int x;
		int direction;
		int reflect;
		
		Pair(int y, int x, int reflect, int direction){
			this.y = y;
			this.x = x;
			this.reflect = reflect;
			this.direction = direction;
		}
	}
	
	static int[][] map;
	static List<Pair> lazerList;
	static Pair startPoint;
	static Pair endPoint;
	// . : 1 (빈 칸)
	// * : 2 (벽)
	// C : 3 (레이저로 연결해야 하는 칸)
	// 위, 오른쪽, 아래, 왼쪽
	static int[] cx = {0,1,0,-1};
	static int[] cy = {-1,0,1,0};
	static int H,W;
	static int minMirror = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		
		lazerList = new ArrayList<>();
		for(int i = 0; i<H; i++) {
			String str = br.readLine();
			for(int j = 0; j<W; j++) {
				char value = str.charAt(j);
				
				if(value == '.') {
					map[i][j] = 1;
				}
				else if(value == '*') {
					map[i][j] = 2;
				}
				else {
					map[i][j] = 3;
					if(startPoint == null)
						startPoint = new Pair(i,j,0,-1);
					else
						endPoint = new Pair(i,j,0,0);
				}
			}	
		}
		
		bfs();
		System.out.println(minMirror);
	}
	
	static void bfs() {
		PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p1.reflect - p2.reflect;
			}
		}); 
		que.add(startPoint);
		
		int[][] visit = new int[H][W];
		visit[startPoint.y][startPoint.x] = -1;
		
		while(!que.isEmpty()) {
			Pair nowState = que.poll();
			int y = nowState.y;
			int x = nowState.x;
			int reflect = nowState.reflect;
			int direction = nowState.direction;
			
			if(y == endPoint.y && x == endPoint.x) {
				minMirror = Math.min(minMirror, reflect);
				continue;
			}
			
			for(int i = 0; i<4; i++) {
				int ny = y + cy[i];
				int nx = x + cx[i];
				int nd = i;
				int nr = reflect;
				if(!boundCheck(ny,nx)) continue;
				if(map[ny][nx] == 2) continue;
				
				if(direction == nd || direction == -1) // 처음 시작 or 방향이 똑같은 상황
					nr = reflect;
				else // 방향이 다른 상황
					nr = reflect + 1;
				
				if(visit[ny][nx] == 0 || visit[ny][nx] == -1 || visit[ny][nx] >= nr) {
					que.add(new Pair(ny,nx,nr,nd));
					visit[ny][nx] = nr;
				}
				
				
			}
		}
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || x < 0 || y >= H || x >= W)
			return false;
		
		return true;
	}

}
