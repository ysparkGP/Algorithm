package programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
 * BFS
 * 2017 카카오코드 예선
 * 카카오프렌즈 컬러링북
 */

public class Test_KAKAO_BLIND5 {

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static class Pair{
		int y;
		int x;
		int value;
		Pair(int y, int x, int value){
			this.y = y;
			this.x = x;
			this.value = value;
		}
	}
	static int m,n;
	public static void main(String[] args) {
		m = 6;
		n = 4;
		int[][] map = {
				{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}
		};
		
		int[][] picture = new int[m][n];
        for(int i = 0; i<m; i++) picture[i] = map[i].clone();
        
		
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		boolean[][] visit = new boolean[m][n];
		Queue<Pair> que = new LinkedList<>();
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				if(picture[i][j] == 0 || visit[i][j]) continue;
				numberOfArea += 1;
				visit[i][j] = true;
				que.add(new Pair(i,j,picture[i][j]));
				
				int cnt = 0;
				while(!que.isEmpty()) {
					Pair p = que.poll();
					cnt++;
					for(int direction = 0; direction<4; direction++) {
						int ny = p.y + dy[direction];
						int nx = p.x + dx[direction];
						if(!boundCheck(ny,nx) || picture[ny][nx] != p.value || visit[ny][nx]) continue;
						
						que.add(new Pair(ny,nx,picture[ny][nx]));
						visit[ny][nx] = true;
					}
				}
				
				maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
			}
		}
		int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
		System.out.println(numberOfArea +", "+ maxSizeOfOneArea);
	}
	static boolean boundCheck(int y, int x) {
		if(y<0||x<0||y>=m||x>=n) return false;
		return true;
	}

}
