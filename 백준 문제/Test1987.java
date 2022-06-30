package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트레킹
 */

public class Test1987 {

	static int max = 0;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int R,C;
	static class Node{
		int y;
		int x;
		int cnt;
		Node(int y, int x, int cnt){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for(int i = 0; i<R; i++) {
			String str = br.readLine();
			for(int j = 0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		boolean[] visit = new boolean[26];
		visit[map[0][0] - 65] = true;
		dfs(map,0,0,visit, 1);
		System.out.println(max);
	}
	
	public static void dfs(char[][] map, int y, int x, boolean[] visit, int cnt) {
		max = Math.max(max, cnt);
		
		for(int i = 0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(validCheck(ny,nx) && !visit[map[ny][nx] - 65]) {
				visit[map[ny][nx] - 65] = true;
				dfs(map, ny, nx, visit, cnt+1);
				visit[map[ny][nx] - 65] = false;
			}
		}
		
	}
	
	public static boolean validCheck(int y, int x) {
		if(y<0||y>=R||x<0||x>=C) return false;
		return true;
	}
}
