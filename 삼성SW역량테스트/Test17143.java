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
 * 삼성 SW 기출 문제
 * 구현
 */

public class Test17143{

	static int[] cx = {0,0,0,1,-1};
	static int[] cy = {0,-1,1,0,0};
	static class Shark{
		int y,x,s,d,z;
		public Shark() {};
		public Shark(int y, int x, int s, int d, int z){
			this.y = y;
			this.x = x;
			this.s = s; // 속력
			this.d = d; // 이동 방향... 1: 위, 2: 아래 3: 오른쪽 4: 왼쪽
			this.z = z; // 크기
		}
		/*public static Shark copy(Shark originalShark) {
			Shark copyShark = new Shark();
			copyShark.y = originalShark.y;
			copyShark.x = originalShark.x;
			copyShark.d = originalShark.d;
			copyShark.s = originalShark.s;
			copyShark.z = originalShark.z;
			return copyShark;
		}*/
	}
	static List<Shark>[][] map;
	static int R,C,M;
	static int fisher;
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken()); // 격자판 세로 크기
		C = Integer.parseInt(st.nextToken()); // 격자판 가로 크기
		M = Integer.parseInt(st.nextToken()); // 상어 수
		
		map = new ArrayList[R+1][C+1];
		for(int i = 0; i<=R; i++) {
			for(int j = 0; j<=C; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c].add(new Shark(r,c,s,d,z));
		}
		while(fisher + 1 <= C) {
			fisherMove();
			catchShark();
			sharksMove();
			sharkHunt();
		}
		System.out.println(result);
	}
	static void fisherMove() {
		fisher++;
	}
	
	static void catchShark() {
		for(int y = 1; y<=R; y++) {
			if(map[y][fisher].size() == 0) continue; // 상어가 없을 경우
			else {
				result += map[y][fisher].get(0).z;
				map[y][fisher].clear();
				break;
			}
		}
	}
	static void sharksMove() {
		Queue<Shark> que = new LinkedList<>();
		for(int y = 1; y<=R; y++) {
			for(int x = 1; x<=C; x++) {
				if(map[y][x].size() == 0) continue;
				else {
					Shark newShark = sharkMoveAfter(map[y][x].get(0));
					map[y][x].clear();
					que.add(newShark);
				}
			}
		}
		while(!que.isEmpty()) {
			Shark shark = que.poll();
			map[shark.y][shark.x].add(shark);
		}
	}
	static Shark sharkMoveAfter(Shark shark) {
		int s = shark.s;
		int d = shark.d;
		int y = shark.y;
		int x = shark.x;
		int z = shark.z;
		if(d == 1 || d == 2) { // 상하
			s %= 2*(R-1);
		}
		else { // 좌우
			s %= 2*(C-1);
		}
		
		for(int i = 0; i<s; i++) {
			if(d == 1 || d == 2) {
				if(y+cy[d] < 1 || y+cy[d] > R) {
					if(d == 1) d=2;
					else d=1;
					y = y+cy[d];
				}
				else y = y+cy[d];
			}
			else {
				if(x+cx[d] < 1 || x+cx[d] > C) {
					if(d==3) d=4;
					else d=3;
					x = x+cx[d];
				}
				else x = x+cx[d];
			}
		}
		
		Shark newShark = new Shark(y,x,s,d,z);
		return newShark;
	}
	
	static void sharkHunt() {
		for(int y = 1; y<=R; y++) {
			for(int x = 1; x<=C; x++) {
				if(map[y][x].size() > 1) {
					//System.out.println(map[y][x].size());
					Collections.sort(map[y][x], new Comparator<Shark>() {
						@Override
						public int compare(Shark s1, Shark s2) {
							return Integer.compare(s2.z, s1.z);
						}
					});
					Shark shark = map[y][x].get(0);
					map[y][x].clear();
					map[y][x].add(shark);
				}
			}
		}
	}

}
