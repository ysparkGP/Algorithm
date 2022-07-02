package programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 행렬 회전
 */

public class Dev_Matching2021_1 {

	// 0 : 오른 1: 아래 2: 왼 3: 위
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
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
	public static void main(String[] args) {
		int rows = 3; 
		int columns = 3; 
		int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
		
		int[][] mat = new int[rows+1][columns+1];
		for(int i = 1; i<=rows; i++) {
			for(int j = 1; j<=columns; j++) {
				mat[i][j] = columns*(i-1) + j;
			}
		}
		int[] answer = new int[queries.length];
		for(int i = 0; i<queries.length; i++) {
			answer[i] = rotationMat(mat, queries[i]);
		}
		
		for(int i = 0; i<answer.length; i++) System.out.print(answer[i] + " ");
	}

	static int rotationMat(int[][] mat, int[] query) {
		Queue<Pair> que = new LinkedList<>();
		
		int y = query[0];
		int x = query[1];
		int[] cnt = new int[4];
		for(int i = 0; i<4; i++) {
			if(i%2 == 0) {
				cnt[i] = query[3] - query[1];
			}
			else {
				cnt[i] = query[2] - query[0];
			}
		}
		for(int i = 0; i<4; i++) {
			
			while(cnt[i] > 0) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				que.add(new Pair(ny,nx,mat[y][x]));
				y = ny;
				x = nx;
				cnt[i]--;
			}
		}
		
		int min = Integer.MAX_VALUE;
		while(!que.isEmpty()) {
			Pair p = que.poll();
			mat[p.y][p.x] = p.value;
			min = Math.min(min, p.value);
		}
		return min;
	}
}
