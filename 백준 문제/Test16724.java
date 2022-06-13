package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * union-find, dfs
 */

public class Test16724 {
	static class Point{
		int y;
		int x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}

	static int cnt;
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		/* dfs
		int[][] area = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		cnt = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(area[i][j] == 0) {
					area[i][j] = searchArea(map, area, visit, i, j);
				}
					
			}
		}
		//areaPrint(area);
		*/
		// union-find
		parent = new int[N*M];
		for(int i = 0; i<N*M; i++) parent[i] = i;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				int cur = i*M+j;
				int next = nextPoint(map, i, j, M);
				if(find(cur) != find(next)) {
					union(cur,next);
				}
			}
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i = 0; i<N*M; i++) set.add(find(i));
		
		System.out.println(set.size());
	}
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return;
		else {
			if(parentA < parentB) parent[parentB] = parentA;
			else parent[parentA] = parentB;
		}
	}
	static int nextPoint(char[][] map, int y, int x, int M) {
		if(map[y][x] == 'D') {
			y++;
		}
		else if(map[y][x] == 'L') {
			x--;
		}
		else if(map[y][x] == 'R') {
			x++;
		}
		else {
			y--;
		}
		return y*M+x;
	}
	
	static int searchArea(char[][] map, int[][] area, boolean[][] visit, int y, int x) {
		if(area[y][x] != 0) return area[y][x];
		
		if(!visit[y][x]) {
			visit[y][x] = true;
		}
		else {
			return cnt+=1;
		}
		Point np = move(y,x,map);
		
		return area[y][x] = searchArea(map, area, visit, np.y, np.x);
	}
	
	
	
	static Point move(int y, int x, char[][] map) {
		if(map[y][x] == 'D') {
			y++;
		}
		else if(map[y][x] == 'L') {
			x--;
		}
		else if(map[y][x] == 'R') {
			x++;
		}
		else {
			y--;
		}
		return new Point(y,x);
	}
	static void areaPrint(int[][] area) {
		for(int i = 0; i<area.length; i++) {
			for(int j = 0; j<area[0].length; j++)
				System.out.print(area[i][j] + " ");
			System.out.println();
		}
	}
}
