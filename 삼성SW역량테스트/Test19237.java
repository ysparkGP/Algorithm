package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 삼성 SW 역량 기출문제
 * 구현
 */

public class Test19237 {

	static int N,M,K;
	static class Smell{
		int num;
		int cnt;
		public Smell(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	static class Shark{
		int y;
		int x;
		int num;
		int dir;
		public Shark(int y, int x, int num, int dir) {
			this.y = y;
			this.x = x;
			this.num = num;
			this.dir = dir;
		}
		public void setDir(int dir) {
			this.dir = dir;
		}
		
	}
	// 1. 위 2. 아래 3. 왼 4. 오른
	static int[] cy = {0,-1,1,0,0};
	static int[] cx = {0,0,0,-1,1};
	static Smell[][] map;
	static List<Shark> sharkList;
	static Queue<Shark> sharkQue;
	static int[][][] sharkPriority;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken()); // 세로, 가로
		M = Integer.parseInt(st.nextToken()); // 상어 수
		K = Integer.parseInt(st.nextToken()); // 냄새 유효 기간
		map = new Smell[N][N];
		sharkQue = new LinkedList<>();
		sharkList = new ArrayList<>();
		sharkPriority = new int[M+1][5][5];
		for(int y = 0; y<N; y++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int x = 0; x<N; x++) {
				int value = Integer.parseInt(st.nextToken());
				if(value == 0) continue;
				sharkList.add(new Shark(y,x,value,0));
				map[y][x] = new Smell(value, K);
			}
		}
		Collections.sort(sharkList, new Comparator<Shark>() {
			@Override
			public int compare(Shark s1, Shark s2) {
				return Integer.compare(s1.num, s2.num);
			}
		});
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<M; i++) {
			sharkList.get(i).setDir(Integer.parseInt(st.nextToken()));
		}
		for(int num = 1; num<=M; num++) {
			for(int i = 1; i<5; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j = 1; j<5; j++) {
					sharkPriority[num][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		for(int i = 1; i<=1000; i++) {
			sharksMove();
			smell();
			if(sharkList.size() == 1) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
	
	static void sharksMove() {
		// 상어들 이동
		for(int i = 0; i<sharkList.size(); i++) {
			boolean tf = false;
			// 우선순위로 빈 칸 찾기
			for(int j = 1; j<=4; j++) {
				int y = sharkList.get(i).y;
				int x = sharkList.get(i).x;
				int dir = sharkList.get(i).dir;
				int num = sharkList.get(i).num;
				int ndir = sharkPriority[num][dir][j];
				int ny = y + cy[ndir];
				int nx = x + cx[ndir];
				if(!validCheck(ny,nx)) continue;
				if(map[ny][nx] == null) {
					sharkQue.add(new Shark(ny,nx,num, ndir));
					tf = true;
					break;
				}
			}
			if(!tf) {
				// 빈 칸 찾기 실패 후 우선순위로 자신의 냄새 칸 찾기
				for(int j = 1; j<=4; j++) {
					int y = sharkList.get(i).y;
					int x = sharkList.get(i).x;
					int dir = sharkList.get(i).dir;
					int num = sharkList.get(i).num;
					int ndir = sharkPriority[num][dir][j];
					int ny = y + cy[ndir];
					int nx = x + cx[ndir];
					if(!validCheck(ny,nx)) continue;
					if(map[ny][nx].num == sharkList.get(i).num) {
						sharkQue.add(new Shark(ny,nx,sharkList.get(i).num,ndir));
						break;
					}
				}
			}
		}
	}
	
	static void smell() {
		// 나머지 냄새들 정리 후 큐에서 상어들 냄새 뿌림
		for(int y = 0; y<N; y++) {
			for(int x = 0; x<N; x++) {
				if(map[y][x] != null) { // 냄새가 있다면 정리
					map[y][x].cnt--;
					if(map[y][x].cnt <= 0) map[y][x] = null;
				}
			}
		}
		
		sharkList.clear();
		while(!sharkQue.isEmpty()) {
			Shark shark = sharkQue.poll();
			if(map[shark.y][shark.x] == null || map[shark.y][shark.x].num == shark.num) {
				map[shark.y][shark.x] = new Smell(shark.num, K);
				sharkList.add(shark);
			}
			else {
				if(map[shark.y][shark.x].num > shark.num) { // 기존에 뿌렸던 상어의 번호가 작을 때,
					for(Shark s : sharkList) {
						if(s.num == map[shark.y][shark.x].num) {
							sharkList.remove(s);
							break;
						}
					}
				}
			}
			
		}
		
	}
	
	static boolean validCheck(int y, int x) {
		if(y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}

}
