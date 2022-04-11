package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 삼성 SW 역량 기출문제
 * 구현
 * 개선할 점
 * 1. 구슬을 이동하는 메서드에서 0(파괴된 구슬)이 아닌 값들을 큐에 넣어서 1번부터 N번 맵에 할당해주는 방법
 * 2. 폭발하는 과정은 데큐를 이용해서 프론트부분에는 연속되지 않은 값들을 몰빵해주고, 뒤에는 연속되는 값들을 할당해주면서 다음 값이 일치하지 않고 연속되는 값들의 개수가 4이상일 때, 데큐의 뒤에서 부터 빼주는 방법
 * 3. 구슬이 변하는 부분도 큐를 이용한 처리방법
 */
public class Test21611 {
	
	// 반시계 방향으로 왼, 아래, 오른, 위
	static int[] dy = {0,1,0,-1};
	static int[] dx = {-1,0,1,0};
	
	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static class Order{
		int d;
		int s;
		Order(int d, int s){
			this.d = d;
			this.s = s;
		}
	}
	static List<Order> blizzards;
	static Map<Integer, Pair> yxMap;
	static int N;
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		blizzards = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if(d == 1) {
				d = 3;
			}
			else if(d == 2) {
				d = 1;
			}
			else if(d == 3) {
				d = 0;
			}
			else {
				d = 2;
			}
			blizzards.add(new Order(d,s));
		}
		
		coordinateSetting();
		
		for(Order blizzard: blizzards) {
			executeBlizzard(blizzard, map);
			while(true) {
				map = beadsMove(map);
				int cnt = beadsBomb(map);
				if(cnt == 0) break;
			}
			map = beadsChange(map);
		}
		System.out.println(result);
	}
	
		
	static int executeBlizzard(Order blizzard, int[][] map) {
		int y = N/2;
		int x = N/2;
		int d = blizzard.d;
		int s = blizzard.s;
		
		int cnt = 0; // 제거한 구슬들
		// 구슬들 제거(1~3 번호를 0번으로)
		for(int i = 1; i<=s; i++) {
			int ny = y + dy[d]*i;
			int nx = x + dx[d]*i;
			if(map[ny][nx] != 0) {
				cnt++;
				map[ny][nx] = 0;
			}
			
		}
		
		return cnt;
	}
	
	static int[][] beadsMove(int[][] map){
		
		int[][] newMap = new int[N][N];
		int[] count = {1,1,2,2};
		
		int y = N/2;
		int x = N/2;
		int check = 0;
		while(true) {
			for(int i = 0; i<4; i++) {
				for(int j = 0; j<count[i]; j++) {
					y = y+dy[i];
					x = x+dx[i];
					check++;
					if((y==0 && x==-1) || check >= N*N) return newMap;
					Pair p = yxMap.getOrDefault(check, null);
					while(map[p.y][p.x] == 0) {
						check++;
						p = yxMap.getOrDefault(check, null);
						if(p == null) return newMap;
					}
					newMap[y][x] = map[p.y][p.x];
				}
			}
			
			for(int i = 0; i<4; i++) count[i] += 2;
		}
		
	}
	
	static int beadsBomb(int[][] map){
		// 연속하는 구슬 4개 이상 폭발
		int cnt = 1;
		int val = 0;
		loop: while(true) {
			Pair p = yxMap.getOrDefault(cnt, null);
			if(p == null) break;
			
			int first = map[p.y][p.x];
			if(first != 0) {
				List<Pair> tempList = new ArrayList<>();
				tempList.add(new Pair(p.y, p.x));
				while(true) {
					p = yxMap.getOrDefault(++cnt, null);
					if(p == null) break loop;
					
					if(map[p.y][p.x] == 0) break;
					else {
						if(map[p.y][p.x] == first) tempList.add(new Pair(p.y, p.x));
						else break;
					}
				}
				
				if(tempList.size() >= 4) {
					val += tempList.size();
					result += first*tempList.size();
					for(Pair tp: tempList) {
						map[tp.y][tp.x] = 0;
					}
					
				}
			}
			else {
				break;
			}
		}
		return val;
	}
	// 하나의 그룹은 구슬 A와 B로 변한다. A는 그룹에 들어있는 구슬의 개수, B는 그 구슬의 번호
	static int[][] beadsChange(int[][] map){
		int[][] newMap = new int[N][N];
		
		int index = 1;
		int cnt = 1;
		while(true) {
			Pair p = yxMap.getOrDefault(cnt, null);
			if(p == null) break;
			
			if(map[p.y][p.x] == 0) break;
			else {
				List<Pair> tempList = new ArrayList<>();
				while(true) {
					Pair tp = yxMap.getOrDefault(cnt,null);
					if(tp == null) break;
					if(map[tp.y][tp.x] == map[p.y][p.x]) {
						tempList.add(new Pair(tp.y,tp.x));
						cnt++;
					}
					else break;
				}
				
				Pair indexA = yxMap.get(index);
				Pair indexB = yxMap.get(index+1);
				newMap[indexA.y][indexA.x] = tempList.size();
				newMap[indexB.y][indexB.x] = map[p.y][p.x];
			}
			index+=2;
			if(index >= N*N-1) break;
		}
		
		return newMap;
	}
	// 가운데에서 시작으로 반시계 방향의 나선형 모양의 좌표값들을 인덱싱
	static void coordinateSetting() {
		yxMap = new HashMap<>();
		int[] count = {1,1,2,2};
		int ty = N/2;
		int tx = N/2;
		int cnt = 0;
		while(true) {
			for(int i = 0; i<4; i++) {
				for(int j = 0; j<count[i]; j++) {
					ty = ty+dy[i];
					tx = tx+dx[i];
					cnt++;
					if(ty == 0 && tx == -1) return;
					yxMap.put(cnt, new Pair(ty,tx));
				}
			}
			for(int i = 0; i<4; i++) {
				count[i] += 2;
			}
		}
	}

	static void mapPrint(int[][] map) {
		System.out.println("-----------map-------------");
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static boolean validCheck(int y, int x) {
		if(y<0||x<0||y>=N||x>=N) return false;
		return true;
	}

}
