package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구현, 시뮬레이션, bfs
 */

public class Test2933 {

	static int R,C;
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
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i = 0; i<R; i++) {
			String str = br.readLine();
			for(int j = 0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int cnt = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		for(int index = 1; index <= cnt; index++) {
			int order = Integer.parseInt(st.nextToken());
			mineralRemove(index, order);
			map = updateMap(findCluster());
		}
		mapPrint();
		
	}
	static void mapPrint() {
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	// step 1. 미네랄 제거(구현)
	static void mineralRemove(int index, int order) {
		int removeRow = R - order;
		if(index % 2 == 1) { // 왼 -> 오
			for(int col = 0; col<C; col++) {
				if(map[removeRow][col] == 'x') {
					map[removeRow][col] = '.';
					return;
				}
			}
		}
		else { // 오 -> 왼
			for(int col = C-1; col>=0; col--) {
				if(map[removeRow][col] == 'x') {
					map[removeRow][col] = '.';
					return;
				}
			}
		}
		
	}
	// step 2-1. 미네랄 클러스터 찾기(bfs)
	static ArrayList<ArrayList<Point>> findCluster(){
		ArrayList<ArrayList<Point>> clusters = new ArrayList<>();
		boolean[][] visit = new boolean[R][C];
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(!visit[i][j] && map[i][j] == 'x') {
					clusters.add(bfs(i,j,visit));
				}
			}
		}
		return clusters;
	}
	
	// step 2-2.
	static ArrayList<Point> bfs(int y, int x, boolean[][] visit){
		ArrayList<Point> result = new ArrayList<>();
		
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(y,x));
		visit[y][x] = true;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			result.add(p);
			for(int i = 0; i<4; i++) {
				int ny = p.y+dy[i];
				int nx = p.x+dx[i];
				if(boundCheck(ny,nx) && !visit[ny][nx] && map[ny][nx] == 'x') {
					que.add(new Point(ny,nx));
					visit[ny][nx] = true;
				}
			}
		}
		
		return result;
	}
	
	// step 3. 찾은 클러스터 밑으로 떨구기(구현)
	static char[][] updateMap(ArrayList<ArrayList<Point>> clusters){
		char[][] newMap = new char[R][C];
		
		for(int i = 0; i<clusters.size(); i++) {
			char[][] copyMap = copyMap(clusters, i);
			ArrayList<Point> cluster = clusters.get(i);
			int cnt = 0;
			while(downPossible(cluster, copyMap)) {
				cnt++;
			}
			if(cnt > 0) break;
		}
		newMap = copyMap(clusters, -1);
		return newMap;
	}
	static char[][] copyMap(ArrayList<ArrayList<Point>> clusters, int index){
		char[][] newMap = new char[R][C];
		for(int i = 0; i<R; i++) Arrays.fill(newMap[i], '.');
		for(int i = 0; i<clusters.size(); i++) {
			if(index == i) continue;
			for(Point p: clusters.get(i)) {
				newMap[p.y][p.x] = 'x';
			}
		}
		return newMap;
	}
	static boolean downPossible(ArrayList<Point> cluster, char[][] map) {
		ArrayList<Point> copyCluster = new ArrayList<>();
		for(int i = 0; i<cluster.size(); i++){
			copyCluster.add(new Point(cluster.get(i).y, cluster.get(i).x));
		}
		Collections.sort(copyCluster, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return Integer.compare(p2.y, p1.y);
			}
		});
		
		for(Point p: copyCluster) {
			int ny = p.y+1;
			if(!boundCheck(ny,p.x) || map[ny][p.x] == 'x') return false;
			p.y = ny;
		}
		
		for(int i = 0; i<cluster.size(); i++) {
			cluster.get(i).y++;
		}
		return true;
	}
	
	static boolean boundCheck(int y, int x) {
		if(y<0||x<0||y>=R||x>=C) return false;
		return true;
	}

}
