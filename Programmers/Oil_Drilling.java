package oilDrilling;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
	
	static Map<Integer, Integer> cntMap = new HashMap<>();
	static int[][] visit;
	static int[] rowDirection = {-1,0,1,0};
	static int[] colDirection = {0,1,0,-1};
	static int rowLen;
	static int colLen;
	
	public static void main(String[] args) {
//		int[][] land = {{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}};
		int[][] land = {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}};
		rowLen = land.length;
		colLen = land[0].length;
		visit = new int[rowLen][colLen];
		
		int div = 1;
		
		for(int i = 0; i<land.length; i++) {
			for(int j = 0; j<land[0].length; j++) {
				if (visit[i][j] == 0 && land[i][j] == 1)
					bfs(i,j,div++, land);
			}
		}
		
		int maxScore = 0;
		for(int i = 0; i<colLen; i++) {
			int score = 0;
			Set<Integer> tempSet = new HashSet<>();
			for(int j = 0; j<rowLen; j++) {
				if(visit[j][i] != 0) tempSet.add(visit[j][i]);
			}
			
			Iterator<Integer> iter = tempSet.iterator();
			while(iter.hasNext()) {
				int temp = iter.next();
				score += cntMap.get(temp);
			}
			
			maxScore = (score > maxScore)? score : maxScore;
		}
		
		System.out.println(maxScore);
	}
	
	static void bfs(int row, int col, int div, int[][] land) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[]{row,col});
		visit[row][col] = div;
		
		int cnt = 0;
		while(!que.isEmpty()) {
			int[] check = que.poll();
			cnt++;
			
			for(int i = 0; i<4; i++) {
				int nextRow = check[0] + rowDirection[i];
				int nextCol = check[1] + colDirection[i];
				if(boundCheck(nextRow, nextCol) && visit[nextRow][nextCol] == 0 && land[nextRow][nextCol] == 1) {
					que.add(new int[] {nextRow, nextCol});
					visit[nextRow][nextCol] = div;
					
				}
			}
		}
		
		cntMap.put(div, cnt);
		
	}
	
	static boolean boundCheck(int row, int col) {
		if(row < 0 || col < 0 || row >= rowLen || col >= colLen) return false;
		return true;
	}

}
