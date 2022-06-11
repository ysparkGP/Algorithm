package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs, 분리집합
 */

public class Test16946 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
		
		@Override
		public boolean equals(Object o) {
			Pair p = (Pair) o;
			return this.y == p.y && this.x == p.x;
		}
		@Override
		public int hashCode() {
			return (""+y+x).hashCode();
		}
		
	}
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int[][] record = new int[N][M];
		HashMap<Integer, Integer> groupMap = new HashMap<>();
		int groupNumber = 1;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == '0' && record[i][j] == 0) {
					bfs(groupMap, record, map, i, j, N, M, groupNumber);
					groupNumber++;
				}
			}
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == '0') bw.write("0");
				else {
					bw.write(String.valueOf(count(groupMap, record, i, j, N,M)));
				}
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	static int count(HashMap<Integer, Integer> groupMap, int[][] record, int y, int x, int N, int M) {
		int cnt = 1;
		HashSet<Integer> visit = new HashSet<>();
		for(int i = 0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(boundCheck(ny,nx,N,M) && record[ny][nx] != 0 && !visit.contains(record[ny][nx])) {
				cnt += groupMap.get(record[ny][nx]);
				visit.add(record[ny][nx]);
			}
		}
		
		return cnt % 10;
	}
	
	static void bfs(HashMap<Integer, Integer> groupMap, int[][] record, char[][] map, int y, int x, int N, int M, int groupNumber) {
		
		Queue<Pair> que = new LinkedList<>();
		que.add(new Pair(y,x));
		record[y][x] = groupNumber;
		int groupCnt = 0;
		while(!que.isEmpty()) {
			Pair p = que.poll();
			groupCnt++;
			for(int i = 0; i<4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(boundCheck(ny,nx,N,M) && map[ny][nx] == '0' && record[ny][nx] == 0) {
					que.add(new Pair(ny,nx));
					record[ny][nx] = groupNumber;
				}
			}
		}
		groupMap.put(groupNumber, groupCnt);
	}
	
	static boolean boundCheck(int y, int x, int N, int M) {
		if(y<0||x<0||y>=N||x>=M) return false;
		return true;
	}
}
