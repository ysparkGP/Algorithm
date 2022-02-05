package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Two{
	Integer y;
	Integer x;
	//Integer safe;
	
	Two(Integer y, Integer x){
		this.y = y;
		this.x = x;
		//this.safe = safe;
	}
}

public class Test17086 {
	static int N,M;
	static int cx[] = {-1,1,0,0,-1,1,1,-1};
	static int cy[] = {0,0,-1,1,-1,1,-1,1};
	static int max;
	static int[][] box;
	static int[][] distance;
	static Queue<Two> que = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		distance = new int[N][M];
		
		for(int i = 0; i<N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for(int j = 0; j<M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 1) {
					que.add(new Two(i,j));
				}
			}
		}
		

		bfs();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				max = Math.max(max, distance[i][j]);
				
			}
		}
		System.out.println(max);
	}
	
	static void bfs() {
		
		while(!que.isEmpty()) {
			Two shark = que.poll();
			int currentY = shark.y;
			int currentX = shark.x;
			
			for(int i = 0; i<8; i++) {
				int checkY = currentY + cy[i];
				int checkX = currentX + cx[i];
				
				if(!validCheck(checkY, checkX))
					continue;
				if(box[checkY][checkX] == 1)
					continue;
				if(distance[checkY][checkX] != 0)
					continue;
				distance[checkY][checkX] = distance[currentY][currentX] + 1;
				que.add(new Two(checkY,checkX));
				
			}
			
		}
		
	}
	
	/*
	static void bfs(int currentY, int currentX) { // 메모리 초과
		
		Queue<Three> que = new LinkedList<>();
		que.add(new Three(currentY, currentX, 0));
		visit[currentY][currentX] = true;
		
		while(!que.isEmpty()) {
			Three value = que.poll();
			int y = value.y;
			int x = value.x;
			int safe = value.safe;
			
			for(int k = 0; k < 8; k++) {
				
				int yCheck = y + cy[k];
				int xCheck = x + cx[k];
				int nowSafe = safe + 1;
				
				if(validCheck(yCheck, xCheck)) {
					
					if(box[yCheck][xCheck] == 1) {
						max = Math.max(max, nowSafe);
						que.clear();
						break;
					}
					
					que.add(new Three(yCheck, xCheck, nowSafe));
				}
			
			}
		}
		
	}*/
	
	static boolean validCheck(int y, int x) {
		if(x < 0 || x >= M || y < 0 || y >= N) {
			return false;
		}
		
		return true;
	}
}
