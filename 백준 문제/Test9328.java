package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs
 */

public class Test9328 {

	static int N,M;
	static char[][] map;
	static class Point{
		int y;
		int x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	static int key;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t= 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N+2][M+2];
			
			key = 0;
			for(int i = 1; i<N+1; i++) {
				String str = br.readLine();
				for(int j = 1; j<M+1; j++) {
					map[i][j] = str.charAt(j-1);
				}
			}
			
			String strKey = br.readLine();
			if(!strKey.equals("0")) {
				for(int i = 0; i<strKey.length(); i++) {
					key |= (1 << strKey.charAt(i) - 97);
				}
			}
			
			System.out.println(bfs(0,0));
		}
	}
	static int bfs(int y, int x) {
		int get = 0;
		int[][] visit = new int[N+2][M+2];
		for(int i = 0; i<N+2; i++) Arrays.fill(visit[i], -1);
		
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(y,x));
		visit[y][x] = key;
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
//			System.out.println(cur.y+", " + cur.x);
			for(int i = 0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if(boundCheck(ny,nx) && map[ny][nx] != '*' && visit[ny][nx] != key) {
					if(map[ny][nx] >= 97 && map[ny][nx] <= 122) { // 열쇠
						key |= (1<<map[ny][nx] - 97);
					}
					else if(map[ny][nx] >= 65 && map[ny][nx] <= 90) { // 문
						if((key & (1<<map[ny][nx] - 65)) == 0) continue;
					}
					else if(map[ny][nx] == '$'){ // "$"
						get++;
					}
					map[ny][nx] = '.';
					visit[ny][nx] = key;
					que.add(new Point(ny,nx));
				}
			}
		}
		return get;
	}
	static boolean boundCheck(int y, int x) {
		if(y<0||x<0||y>=N+2||x>=M+2) return false;
		return true;
	}

}
