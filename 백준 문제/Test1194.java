package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 비트마스킹, bfs
 */

public class Test1194 {

	static class Min{
		int y;
		int x;
		int cnt;
		int key;
		Min(int y, int x, int cnt, int key){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.key = key;
		}
	}
	static int[] dy = {0,-1,0,1};
	static int[] dx = {-1,0,1,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];

		int sy = 0;
		int sx = 0;
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					sy = i;
					sx = j;
					map[i][j] = '.';
				}
			}
		}
		
		boolean[][][] bitMap = new boolean[1<<6][N][M];
		System.out.println(bfs(map,bitMap,sy,sx,N,M));
	}
	static int bfs(char[][] map, boolean[][][] bitMap, int startY, int startX, int N, int M) {
		
		Queue<Min> que = new LinkedList<>();
		que.add(new Min(startY, startX, 0, 0));
		bitMap[0][startY][startX] = true;
		
		while(!que.isEmpty()) {
			Min now = que.poll();
			if(map[now.y][now.x] == '1') {
				return now.cnt;
			}
			
			for(int d = 0; d<4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if(!boundCheck(ny,nx,N,M) || map[ny][nx] == '#') continue;
				
				if((map[ny][nx] == '.' || map[ny][nx] == '1') && !bitMap[now.key][ny][nx]) { // 빈칸
					bitMap[now.key][ny][nx] = true;
					que.add(new Min(ny,nx,now.cnt+1,now.key));
				}
				else if(map[ny][nx] >= 97 && map[ny][nx] <= 102 && !bitMap[now.key][ny][nx]) { // 열쇠
					bitMap[now.key][ny][nx] = true;
					int newKey = now.key|(1<<(map[ny][nx] - 97));
					bitMap[newKey][ny][nx] = true;
					que.add(new Min(ny,nx,now.cnt+1, newKey));
				}
				else if(map[ny][nx] >= 65 && map[ny][nx] <= 70 && !bitMap[now.key][ny][nx]){ // 문
					int lock = 1 << (map[ny][nx] - 65);
					if((now.key & (lock)) > 0) { // 문 열 수 있음
						bitMap[now.key][ny][nx] = true;
						que.add(new Min(ny,nx,now.cnt+1, now.key));
					}
				}
			}
		}
		
		return -1;
	}
	static boolean boundCheck(int y, int x, int N, int M) {
		if(y<0||x<0||y>=N||x>=M) return false;
		return true;
	}

}
