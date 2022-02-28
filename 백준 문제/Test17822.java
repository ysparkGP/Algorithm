package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 구현, 시뮬레이션
 * deque를 이용해 원판 회전을 표현... 시계방향이면, pollLast() -> addFirst() 반시계 방향이면, pollFirst() -> addLast()
 * 작업2인 주위의 중복된 값을 찾아서 업데이트 해주는 것... deque를 이용하다보니 업데이트 쳐줄때마다 deque도 업데이트를 쳐주었어야함
 * 다른 사람들의 답 중 베스트는, deque가 아니라 1차원 배열을 선언해서 for문 안에서 직접 갈아주었음
 * int[] temp = new int[M+1];
 * if(d == 0){
 * 		for(int j = 1; j<=M; j++){
 * 			temp[(j-1+k) % M + 1] = circle[i][j];
 * 		}
 * }			
 * 
 * 업데이트 쳐줄땐, HashSet에 좌표값들을 담아서 간단한 로직으로 업데이트
 *  
 */

public class Test17822 {
	
	static int N,M;
	static int[][] curCircle;
	static Deque<Integer>[] que;
	static int[] cy = {0,0,-1,1};
	static int[] cx = {-1,1,0,0};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N  = Integer.parseInt(st.nextToken());
		M  = Integer.parseInt(st.nextToken());
		int T  = Integer.parseInt(st.nextToken());
		
		curCircle = new int[N+1][M+1];
		que = new ArrayDeque[N+1];
		for(int i = 0; i<=N; i++) {
			que[i] = new ArrayDeque<>();
		}
		for(int i = 1; i<=N; i++) {
			st  = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=M; j++) {
				int value = Integer.parseInt(st.nextToken());
				curCircle[i][j] =  value;
				que[i].addLast(value);
			}
		}
		
		int result = 0;
		for(int i = 0; i<T; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken()); // x의 배수인 원판
			int d = Integer.parseInt(st.nextToken()); // 0: 시계 방향, 1: 반시계 방향
			int k = Integer.parseInt(st.nextToken()); // k칸 회전
			rotation(x,d,k);
			result = check();
		}
		
		System.out.println(result);
	}
	
	static void rotation(int x, int d, int k) {
		
		for(int i = x; i<=N; i+=x) {
			
			for(int c = 0; c<k; c++) {
				if(d == 0) {
					int value = que[i].pollLast();
					que[i].addFirst(value);
				}
				else if(d == 1) {
					int value = que[i].pollFirst();
					que[i].addLast(value);
				}
			}
			
			for(int j = 1; j<=M; j++) {
				curCircle[i][j] = que[i].pollFirst();
				que[i].addLast(curCircle[i][j]);
			}
		}
	}
	
	static int check() {
		
		boolean[][] del = new boolean[N+1][M+1];
		int result = 0;
		boolean sw = false;
		int sum = 0;
		int cnt = 0;
		double avg = 0;
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=M; x++) {
				if(curCircle[y][x] == 0) continue;
				
				sum += curCircle[y][x];
				cnt += 1;
				for(int check = 0; check<4; check++) {
					int ny = y+cy[check];
					int nx = x+cx[check];
					if(!boundCheck(ny,nx)) continue;
					
					if(nx > M) nx = 1;
					else if(nx < 1) nx = M;
					
					
					if(curCircle[y][x] == curCircle[ny][nx]) {
						sw = true;
						del[y][x] = true;
					}
				}
			}
		}
		
		avg = (double) sum / cnt;
		if(sw) { //0
			for(int y = 1; y<=N; y++) {
				for(int x = 1; x<=M; x++) {
					if(del[y][x]) {
						curCircle[y][x] = 0;
					}
					que[y].pollFirst();
					que[y].addLast(curCircle[y][x]);
					result += curCircle[y][x];
				}
			}	
		}
		else {
			for(int y = 1; y<=N; y++) {
				for(int x = 1; x<=M; x++) {
					if(curCircle[y][x] == 0) {
						que[y].pollFirst();
						que[y].addLast(curCircle[y][x]);
						continue;
					}
					if(curCircle[y][x] > avg) {
						curCircle[y][x]--;
					}
					else if(curCircle[y][x] < avg){
						curCircle[y][x]++;
					}
					que[y].pollFirst();
					que[y].addLast(curCircle[y][x]);
					result += curCircle[y][x];
				}
			}
		}
		
		return result;
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 1 || y > N) return false;
		
		return true;
	}
	
}
