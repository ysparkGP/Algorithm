package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 구현, bfs, 분할정복
 */

public class Test20058 {

	static int[][] map;
	static int rowCol;
	static int[] orderList;
	static int[] cy = {-1,0,1,0};
	static int[] cx = {0,1,0,-1};
	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		rowCol = (int)Math.pow(2, N);
		map = new int[rowCol][rowCol];

		int result = 0;
		for(int i = 0; i<rowCol; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<rowCol; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		orderList = new int[Q];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<Q; i++) {
			orderList[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i<orderList.length; i++) {
			int[][] newMap = rotation90((int)Math.pow(2, orderList[i]), map);
			map = check(newMap);
		}
		int sum = 0;
		for(int i = 0; i<rowCol; i++) {
			for(int j = 0; j<rowCol; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
		System.out.println(bfs(map));
	}
	
	static int bfs(int[][] map) {
		
		int max = 0;
		boolean[][] visit = new boolean[rowCol][rowCol];
		Queue<Pair> que = new LinkedList<>();
		for(int i = 0; i<rowCol; i++) {
			for(int j = 0; j<rowCol; j++) {
				if(!visit[i][j] && map[i][j] > 0) {
					que.add(new Pair(i,j));
					int cnt = 0;
					visit[i][j] = true;
					while(!que.isEmpty()) {
						Pair p = que.poll();
						cnt++;
						for(int k = 0; k<4; k++) {
							int ny = p.y + cy[k];
							int nx = p.x + cx[k];
							if(validCheck(ny,nx) && map[ny][nx] > 0 && !visit[ny][nx]) {
								que.add(new Pair(ny,nx));
								visit[ny][nx] = true;
							}
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
		
		return max;
		
	}
	
	static int[][] check(int[][] map){
		int[][] newMap = new int[rowCol][rowCol];
		
		for(int i = 0; i<rowCol; i++) {
			for(int j = 0; j<rowCol; j++) {
				if(map[i][j] == 0) continue;
				int cnt = 0;
				for(int k = 0; k<4; k++) {
					int ny = i+cy[k];
					int nx = j+cx[k];
					if(validCheck(ny,nx)) {
						if(map[ny][nx] > 0) cnt++;
					}
				}
				if(cnt>=3) {
					newMap[i][j] = map[i][j];
				}
				else {
					newMap[i][j] = map[i][j] - 1;
				}
			}
		}
		
		return newMap;
	}
	static int[][] rotation90(int range, int[][] map) {
		
		int[][] newMap = new int[rowCol][rowCol];
		for(int i = 0; i<rowCol; i++) {
			for(int j = 0; j<rowCol; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		for(int r = 0; r<rowCol; r+=range) {
			for(int c = 0; c<rowCol; c+=range) {
				for(int i = 0; i<range; i++) {
					for(int j = 0; j<range; j++) {
						newMap[r+j][c+range-1-i] = map[r+i][c+j];
					}
				}
			}
		}
		
		return newMap;
	}
	
	static boolean validCheck(int y, int x) {
		if(y<0||x<0||y>=rowCol||x>=rowCol) return false;
		return true;
	}
	
	static void mapPrint(int[][] map) {
		for(int i = 0; i<rowCol; i++) {
			for(int j = 0; j<rowCol; j++) {
				System.out.print(map[i][j] +" ");
			}
			System.out.println();
		}
	}
}
