package programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 2020 KAKAO BLIND RECRUITMENT
 * 블록 이동하기
 * 구현, BFS, DP
 */

public class Test_KAKAO_BLIND34 {

	static class Robot{
		int y1;
		int x1;
		int y2;
		int x2;
		int cost;
		Robot(int y1, int x1, int y2, int x2, int cost){
			this.y1 = y1;
			this.x1 = x1;
			this.y2 = y2;
			this.x2 = x2;
			this.cost = cost;
		}
	}
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
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	public static void main(String[] args) {
		int[][] board = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
		int N = board.length;
		int[][][] record = new int[2][N][N]; // 0: 가로, 1: 세로
		for(int i = 0; i<2; i++) {
			for(int j = 0; j<N; j++) Arrays.fill(record[i][j], Integer.MAX_VALUE);
		}
		HashMap<Pair, Integer> map = new HashMap<>(); // 방향 정보
		init(record, map);
		Queue<Robot> que = new LinkedList<>();
		que.add(new Robot(0,0,0,1,0));
		
		while(!que.isEmpty()) {
			Robot robot = que.poll();
			
			// 상하좌우 이동
			for(int i = 0; i<4; i++) {
				int ny1 = robot.y1 + dy[i];
				int nx1 = robot.x1 + dx[i];
				int ny2 = robot.y2 + dy[i];
				int nx2 = robot.x2 + dx[i];
				Pair np = new Pair(robot.y2 - robot.y1, robot.x2 - robot.x1);
				int nd = map.get(np);
				if(boundCheck(ny1,nx1,ny2,nx2,N,board)) {
					if(record[nd%2][ny1][nx1] > robot.cost+1 || record[nd%2][ny2][nx2] > robot.cost+1) {
						record[nd%2][ny1][nx1] = robot.cost+1;
						record[nd%2][ny2][nx2] = robot.cost+1;
						que.add(new Robot(ny1,nx1,ny2,nx2,robot.cost+1));
					}
				}
			}
			
			// y1,x1를 기준으로 회전 1
			for(int i = -1; i<=1; i+=2) {
				Pair np = new Pair(robot.y2 - robot.y1, robot.x2 - robot.x1);
				int d = map.get(np);
				int nd = d+i;
				if(nd == 4) nd = 0;
				else if(nd == -1) nd = 3;
				
				int ny = robot.y1 + dy[nd];
				int nx = robot.x1 + dx[nd];
				int my = robot.y1 + dy[d] + dy[nd]; // 대각선
				int mx = robot.x1 + dx[d] + dx[nd];
				// 대각선과 회전할 방향에 방해물 있는지 체크
				if(boundCheck(ny,nx,my,mx,N,board)) {
					if(record[nd%2][ny][nx] > robot.cost + 1) {
						record[nd%2][ny][nx] = robot.cost + 1;
						que.add(new Robot(robot.y1,robot.x1,ny,nx,robot.cost+1));
					}
				}
			}
			
			// y2,x2축을 기준으로 회전 2
			for(int i = -1; i<=1; i+=2) {
				Pair np = new Pair(robot.y1 - robot.y2, robot.x1 - robot.x2);
				int d = map.get(np);
				int nd = d+i;
				if(nd == 4) nd = 0;
				else if(nd == -1) nd = 3;
				
				int ny = robot.y2 + dy[nd];
				int nx = robot.x2 + dx[nd];
				int my = robot.y2 + dy[d] + dy[nd]; // 대각선
				int mx = robot.x2 + dx[d] + dx[nd];
				// 대각선과 회전할 방향에 방해물 있는지 체크
				if(boundCheck(ny,nx,my,mx,N,board)) {
					if(record[nd%2][ny][nx] > robot.cost + 1) {
						record[nd%2][ny][nx] = robot.cost + 1;
						que.add(new Robot(robot.y2,robot.x2,ny,nx,robot.cost+1));
					}
				}
			} 
			
		}
		int answer = Math.min(record[0][N-1][N-1], record[1][N-1][N-1]);
		System.out.println(answer);
	}
	static void mapPrint(int[][][] record, int N) {
		for(int i = 0; i<2; i++) {
			for(int j = 0; j<N; j++) {
				for(int k = 0; k<N; k++) {
					System.out.print(record[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println("-----------------");
		}
	}
	
	static boolean boundCheck(int y1, int x1, int y2, int x2, int N, int[][] board) {
		if(y1<0||x1<0||y2<0||x2<0||y1>=N||x1>=N||y2>=N||x2>=N) return false;
		if(board[y1][x1] == 1 || board[y2][x2] == 1) return false;
		return true;
	}
	static void init(int[][][] record, HashMap<Pair, Integer> map) {
		record[0][0][0] = 0;
		record[0][0][1] = 0;
		record[1][0][0] = 0;
		record[1][0][1] = 0;
		map.put(new Pair(0,1), 0);
		map.put(new Pair(1,0), 1);
		map.put(new Pair(0,-1), 2);
		map.put(new Pair(-1,0), 3);
	}

}
