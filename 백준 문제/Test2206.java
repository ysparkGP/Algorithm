package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int y;
	int x;
	boolean smash;
	int dis;
	Pair(int y, int x, boolean smash, int dis){
		this.y = y;
		this.x = x;
		this.smash = smash;
		this.dis = dis;
	}
}

public class Test2206 {
	static int[][] map;
	static int N,M;
	static int[] checkX = {0,-1,0,1};
	static int[] checkY = {-1,0,1,0};
 	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		List<Pair> wallList = new ArrayList<>();
		for(int i = 1; i<=N; i++) {
			String str = br.readLine();
			for(int j = 1; j<=M; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j-1));
			}
		}
		int result = bfs();
		
		System.out.println(result);
	}
 	
 	static int bfs() {
 		boolean[][][] visit = new boolean[N+1][M+1][2];
 		Queue<Pair> que = new LinkedList<>();
 		que.add(new Pair(1,1,false,1));
 		
 		while(!que.isEmpty()) {
 			Pair p = que.poll();
 			
 			int y = p.y;
 			int x = p.x;
 			boolean smash = p.smash;
 			int dis = p.dis;
 			if(y == N && x == M) {
 				return dis;
 			}
 			//System.out.println(y+","+x);
 			for(int i = 0; i<4; i++) {
 				int searchY = y + checkY[i];
 				int searchX = x + checkX[i];
 				if(validCheck(searchY, searchX)) {
 					if(map[searchY][searchX] == 0) { //벽이 없는 경우
 						if(!smash && !visit[searchY][searchX][0]) { //여태까지 벽을 한 번도 부순 적 없는 경우
 							visit[searchY][searchX][0] = true;
 	 						que.add(new Pair(searchY, searchX, false, dis+1));
 						}
 						else if(smash && !visit[searchY][searchX][1]) { //벽을 한 번 부순 경우
 							visit[searchY][searchX][1] = true;
 							que.add(new Pair(searchY, searchX, true, dis+1));
 						}
 						
 					}
 					else if(map[searchY][searchX] == 1){ // 벽인 경우
 						if(!smash && !visit[searchY][searchX][1]) {
 							visit[searchY][searchX][1] = true;
 	 						que.add(new Pair(searchY, searchX, true, dis+1));
 						}
 					}
 				}
 			}
 		}
 		return -1;
 	}
 	
 	
 	static boolean validCheck(int y, int x) {
 		if(y < 1 || y > N || x < 1 || x > M)
 			return false;
 		return true;
 	}

}
