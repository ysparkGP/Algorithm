package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * bfs
 * 2021 카카오 채용연계형 인턴십
 * 거리두기 확인하기
 */

public class Test_KAKAO_BLIND12 {

	static class Pair{
		int y;
		int x;
		int dis;
		Pair(int y, int x, int dis){
			this.y = y;
			this.x = x;
			this.dis = dis;
		}
	}
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	public static void main(String[] args) {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
		                     {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
		                     {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
		                     {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
		                     {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		int[] answer = new int[places.length];
		for(int i = 0; i<answer.length; i++) {
			answer[i] = bfs(places[i])? 1: 0;
			//System.out.print(answer[i] + " ");
		}

	}
	
	static boolean bfs(String[] place) {
		char[][] map = new char[5][5];
		for(int i = 0; i<5; i++) {
			String str = place[i];
			for(int j = 0; j<5; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(map[i][j] == 'P') {
					PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
						@Override
						public int compare(Pair p1, Pair p2) {
							return Integer.compare(p1.dis, p2.dis);
						}
					});
					que.add(new Pair(i,j,0));
					boolean[][] visit = new boolean[5][5];
					visit[i][j] = true;
					
					while(!que.isEmpty()) {
						Pair now = que.poll();
						if(now.dis > 2) break;
						if((now.y != i || now.x != j) && map[now.y][now.x] == 'P') return false;
						
						for(int c=0; c<4; c++) {
							int ny = now.y + dy[c];
							int nx = now.x + dx[c];
							
							if(boundCheck(ny,nx) && map[ny][nx] != 'X' && !visit[ny][nx]) {
								que.add(new Pair(ny,nx, now.dis+1));
								visit[ny][nx] = true;
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	static boolean boundCheck(int y, int x) {
		if(y<0||x<0||y>=5||x>=5) return false;
		return true;
	}

}
