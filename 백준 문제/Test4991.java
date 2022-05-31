package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs, tsp, bitmasking
 */

public class Test4991 {

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static class Point{
		int y;
		int x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static int minDist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) break;
			Point[] points = new Point[11];
			int dust = 1;
			char[][] map = new char[M][N];
			for(int i = 0; i<M; i++) {
				String str = br.readLine();
				for(int j = 0; j<N; j++) {
					char c = str.charAt(j);
					map[i][j] = c;
					if(c == '*') {
						points[dust++] = new Point(i,j);
					}
					else if(c == 'o') {
						points[0] = new Point(i,j);
					}
				}
			}
			
			int[][] dist = new int[dust][dust];
			boolean possible = true;
			loop: for(int i = 0; i<dust-1; i++) {
				for(int j = i+1; j<dust; j++) {
					int distance = bfs(points[i], points[j], map, M, N);
					if(distance == -1) {
						possible = false;
						break loop;
					}
					else {
						dist[i][j] = dist[j][i] = distance;
					}
				}
			}
			/*for(int i = 0; i<11; i++) {
				for(int j = 0; j<11; j++) {
					System.out.print(dist[i][j] + " ");
				}
				System.out.println();
			}*/
			if(possible) {
				int[][] dp = new int[dust][1<<dust];
				for(int i = 0; i<dust; i++) Arrays.fill(dp[i], -1);
				minDist = bitMasking(0,1<<0,dp,dist);
			}
			else {
				minDist = -1;
			}
			sb.append(minDist+"\n");
		}
		System.out.println(sb.toString());

	}
	// 로봇(먼지)과 먼지사이의 최단거리 리턴
	static int bfs(Point p1, Point p2, char[][] map, int M, int N) {
		Queue<Point> que = new LinkedList<>();
		que.add(p1);
		int[][] record = new int[M][N];
		for(int i = 0; i<record.length; i++) Arrays.fill(record[i], Integer.MAX_VALUE);
		record[p1.y][p1.x] = 0;
		while(!que.isEmpty()) {
			Point p = que.poll();
			if(p.y == p2.y && p.x == p2.x) {
				return record[p.y][p.x];
			}
			for(int i = 0; i<4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				if(validCheck(ny,nx, M, N) && map[ny][nx] != 'x' && record[ny][nx] > record[p.y][p.x] + 1) {
					que.add(new Point(ny,nx));
					record[ny][nx] = record[p.y][p.x]+1;
				}
			}
		}
		return -1;
	}
	// tsp
	static int bitMasking(int now, int visited, int[][] dp, int[][] dist) {
		if(dp[now][visited] != -1) return dp[now][visited];
		if(visited == (1<<dp.length)-1) { // 모든 거리 전부 방문한 경우
			return 0;
		}
		
		dp[now][visited] = Integer.MAX_VALUE;
		for(int i = 0; i<dp.length; i++) {
			if((visited&(1<<i)) > 0) continue;
			dp[now][visited] = Math.min(dp[now][visited], dist[now][i] + bitMasking(i,visited|(1<<i), dp, dist));
		}
		return dp[now][visited];
	}
	
	static boolean validCheck(int y, int x, int M, int N) {
		if(y<0||x<0||y>=M||x>=N) return false;
		return true;
	}

}
