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

public class Test21609 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static int[] cy = {0,-1,0,1};
	static int[] cx = {-1,0,1,0};
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while(true) {
			List<Pair> resultGroup = searchGroup(map);
			
			if(resultGroup == null) break;			
			score += groupRemoveAndCalcScore(resultGroup, map);
			gravity(map);
			map = rotation90(map);
			gravity(map);
		}
		
		System.out.println(score);
	}
	
	// step 1. 최대 그룹 블록 찾기
	static List<Pair> searchGroup(int[][] map){
		Queue<List> resultQue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		Queue<Pair> que = new LinkedList<>();
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] > 0) { // 일반 블록 발견
					
					List<Pair> groupList = new ArrayList<>();
					que.add(new Pair(i,j));
					groupList.add(new Pair(i,j));
					visit[i][j] = true;
					while(!que.isEmpty()) { // bfs 로 인접한 그룹 블록들 찾기
						Pair p = que.poll();
						for(int k = 0; k<4; k++) {
							int ny = p.y+cy[k];
							int nx = p.x+cx[k];
							if(validCheck(ny,nx) && (map[ny][nx] == 0 || map[ny][nx] == map[i][j]) && !visit[ny][nx]) {
								que.add(new Pair(ny,nx));
								groupList.add(new Pair(ny,nx));
								visit[ny][nx] = true;
							}
						}
					}
					
					// 조건에서 무지개 블록은 얼마든지 타고다닐 수 있기에 항상 방문처리를 false 로 변경
					for(int k = 0; k<N; k++) {
						for(int l = 0; l<N; l++) {
							if(map[k][l] == 0) visit[k][l] = false;
						}
					}
					if(groupList.size() < 2) continue;
					// 첫 번째로 찾았다면 그냥 바로 결과큐에
					if(resultQue.isEmpty()) resultQue.add(groupList);
					else {
						// 기존 결과큐에 있던 리스트와 방금 찾은 리스트 들의(그룹간의) 사이즈 비교
						List<Pair> tempList = resultQue.poll();
						if(tempList.size() == groupList.size()) { // 사이즈가 같다면
							int tempCnt = 0;
							int groupCnt = 0;
							for(int k = 0; k<tempList.size(); k++) {  // 무지개 블록(0) 개수 비교
								if(map[tempList.get(k).y][tempList.get(k).x] == 0) tempCnt++;
								if(map[groupList.get(k).y][groupList.get(k).x] == 0) groupCnt++;
							}
							if(tempCnt == groupCnt) { // 무지개 블록 개수가 같다면 기준 블록 비교(기준 블록은 y,x 오름차순에서 첫번째 요소)
								Collections.sort(tempList, new Comparator<Pair>() {
									@Override
									public int compare(Pair p1, Pair p2) {
										
										if(p1.y == p2.y) return Integer.compare(p1.x, p2.x);
										return Integer.compare(p1.y, p2.y);
									}
								});
								Collections.sort(groupList,new Comparator<Pair>() {
									@Override
									public int compare(Pair p1, Pair p2) {
										
										if(p1.y == p2.y) return Integer.compare(p1.x, p2.x);
										return Integer.compare(p1.y, p2.y);
									}
								});
								int tempY= 0;
								int tempX = 0;
								int groupY = 0;
								int groupX = 0;
								for(int k=0; k<tempList.size(); k++) {
									if(map[tempList.get(k).y][tempList.get(k).x] == 0) continue;
									else {
										tempY= tempList.get(k).y;
										tempX = tempList.get(k).x;
										break;
									}
								}
								for(int k=0; k<groupList.size(); k++) {
									if(map[groupList.get(k).y][groupList.get(k).x] == 0) continue;
									else {
										groupY= groupList.get(k).y;
										groupX = groupList.get(k).x;
										break;
									}
								}
								if(tempY == groupY) { // 기준 블록간의  행이 같다면
									if(tempX > groupX) { // 열 비교로 열이 더 큰 그룹 넣기
										resultQue.add(tempList);
									}
									else {
										resultQue.add(groupList);
									}
								}
								else if(tempY > groupY){ // 기준 블록의 행이 더 큰 그룹 넣기
									resultQue.add(tempList);
								}
								else {
									resultQue.add(groupList);
								}
							}
							else if(tempCnt > groupCnt) {
								resultQue.add(tempList);
							}
							else {
								resultQue.add(groupList);
							}
						}
						else if(tempList.size() > groupList.size()) {
							resultQue.add(tempList);
						}
						else {
							resultQue.add(groupList);
						}
					}
				}
				
			}
			
		}
		// 결과 큐가 비었다면 더이상 진행x
		if(resultQue.isEmpty()) return null;
		else return resultQue.poll();
	}
	
	// step 2. 그룹 블록 제거 후 점수 계산
	static int groupRemoveAndCalcScore(List<Pair> blockGroup, int[][] map) {
		int score = (int) Math.pow(blockGroup.size(), 2);
		
		for(Pair p : blockGroup) {
			map[p.y][p.x] = -2; // -2를 빈 칸으로 처리
		}
		
		return score;
	}
	
	// step 3, 5. 중력
	static void gravity(int[][] map){
		
		for(int i = N-1; i>=0; i--) {
			for(int j = N-1; j>=0; j--) {
				if(map[i][j] >= 0) {
					int row = i;
					
					while(row+1<N && map[row+1][j] < -1) {
						row+=1;
					}
					int tmp = map[i][j];
					map[i][j] = map[row][j];
					map[row][j] = tmp;
				}
			}
		}
		
	}
	
	// step 4. 90도 반시계 회전
	static int[][] rotation90(int[][] map) {
		int[][] newMap = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				newMap[N-1-j][i] = map[i][j];
			}
		}
		return newMap;
	}
	
	// 경계 검사
	static boolean validCheck(int y, int x) {
		if(y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}

	static void mapPrint(int[][] map) {
		System.out.println("---------map-----------");
		for(int i =0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
