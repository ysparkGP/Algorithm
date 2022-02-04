import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class P{
	int y;
	int x;
	P(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Test17142 {
	static int[] checkY = {0,-1,0,1};
	static int[] checkX = {-1,0,1,0};
	static int[][] lab;
	static int N,M;
	static List<P> virusList;
	static int minCnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lab = new int[N][N];
		virusList = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 2) {
					virusList.add(new P(i,j));
				}
			}
		}
		
		List<P> selectList = new ArrayList<>();
		minCnt = Integer.MAX_VALUE;
		brute(selectList, 0, 0);
		
		if(minCnt == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.print(minCnt);
	}
	
	static void brute(List<P> select, int cnt, int idx) {
		
		if(cnt >= M) {
			spread(select);
			
			return;
		}
		
		if(idx >= virusList.size()) {
			return;
		}
		
		select.add(virusList.get(idx));
		brute(select, cnt+1, idx+1);
		select.remove(cnt);
		brute(select, cnt, idx+1);
	}

	static void spread(List<P> selectVirus) {
	
		int[][] newLab = copyLab(lab); //-3: ∫Æ, -2: πŸ¿Ã∑ØΩ∫, -1: ∫Ûƒ≠
		Queue<P> virusQue = new LinkedList<>();
		for(P virus : selectVirus) {
			virusQue.add(virus);
			newLab[virus.y][virus.x] = 0;
		}
		
		while(!virusQue.isEmpty()) {
			boolean complete = true;
			loop: for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(newLab[i][j] == -1) {
						complete = false;
						break loop;
					}
						
				}
			}
			if(!complete) {
				P virus = virusQue.poll();
				int y = virus.y;
				int x = virus.x;
				for(int check = 0; check < 4; check++) {
					int yCheck = y + checkY[check];
					int xCheck = x + checkX[check];
					if(boundcheck(yCheck, xCheck)) {
						if(newLab[yCheck][xCheck] == -2 || newLab[yCheck][xCheck] == -1) {
							newLab[yCheck][xCheck] = newLab[y][x] + 1;
							virusQue.add(new P(yCheck, xCheck));
						}
					}
				}
			}
			
			else {
				break;
			}
		}
		
		int max = 0;
		loop: for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				max = Math.max(newLab[i][j], max);
				if(newLab[i][j] == -1) {
					max = Integer.MAX_VALUE;
					break loop;
				}
			}
		}
		if(minCnt > max) {
			minCnt = max;
		}
	}
	
	static int[][] copyLab(int[][] lab){
		int[][] newLab = new int[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(lab[i][j] == 1) {
					newLab[i][j] = -3;
				}
				else if(lab[i][j] == 2) {
					newLab[i][j] = -2;
				}
				else {
					newLab[i][j] = -1;
				}
			}
		}
		
		return newLab;
	}
	
	static boolean boundcheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= N)
			return false;
		
		return true;
	}
}
