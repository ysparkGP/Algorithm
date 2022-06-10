package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 구현, dfs
 */

public class Test13460 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
		@Override
		public boolean equals(Object o) {
			Pair p = (Pair)o;
			return this.y == p.y && this.x == p.x;
		}
		@Override
		public int hashCode() {
			return (""+y+x).hashCode();
		}
	}
	static Pair goal;
	static int answer = Integer.MAX_VALUE;
	static int[] dy = {0,-1,0,1};
	static int[] dx = {-1,0,1,0};
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		Pair red = new Pair(-1,-1);
		Pair blue = new Pair(-1,-1);
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O') {
					goal = new Pair(i,j);
				}
				else if(map[i][j] == 'R') {
					red = new Pair(i,j);
				}
				else if(map[i][j] == 'B') {
					blue = new Pair(i,j);
				}
			}
		}
		dfs(red,blue,map,0);
		System.out.println(answer == Integer.MAX_VALUE? -1: answer);
		
	}
	static void dfs(Pair redMarble, Pair blueMarble, char[][] map, int cnt) {
		if(cnt >=10) {
			return;
		}
		if(blueMarble.equals(goal)) {
			return;
		}
		if(redMarble.equals(goal)) {
			answer = Math.min(cnt, answer);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			List<Pair> moveMarbles = move(i, redMarble, blueMarble, map);
			Pair moveRedMarble = moveMarbles.get(0);
			Pair moveBlueMarble = moveMarbles.get(1);
			if(blueMarble.equals(moveBlueMarble) && redMarble.equals(moveRedMarble)) continue;
			dfs(moveRedMarble, moveBlueMarble, map, cnt+1);
		}
	}
	static List<Pair> move(int direction, Pair redMarble, Pair blueMarble, char[][] map) {
		List<Pair> moveMarbles = new ArrayList<>();
		Pair moveRedMarble = new Pair(redMarble.y, redMarble.x);
		Pair moveBlueMarble = new Pair(blueMarble.y, blueMarble.x);
		while(true) {
			if(moveRedMarble.equals(goal) && moveBlueMarble.equals(goal)) { // 빨강, 파랑 구슬이 골인 햇을때
				break;
			}
			else if(moveRedMarble.equals(goal)) { // 빨강만 골인 했을때
				int bny = moveBlueMarble.y + dy[direction];
				int bnx = moveBlueMarble.x + dx[direction];
				
				if(map[bny][bnx] == '#') break;
				else {
					moveBlueMarble.y = bny;
					moveBlueMarble.x = bnx;
				}
			}
			else if(moveBlueMarble.equals(goal)) { // 파랑만 골인 했을때
				int rny = moveRedMarble.y + dy[direction];
				int rnx = moveRedMarble.x + dx[direction];
				
				if(map[rny][rnx] == '#') break;
				else {
					moveRedMarble.y = rny;
					moveRedMarble.x = rnx;
				}
			}
			else { // 둘 다 골인 안했을때
				int rny = moveRedMarble.y + dy[direction];
				int rnx = moveRedMarble.x + dx[direction];
				int bny = moveBlueMarble.y + dy[direction];
				int bnx = moveBlueMarble.x + dx[direction];
				
				if(map[rny][rnx] == '#' && map[bny][bnx] == '#') break;
				else {
					if(map[rny][rnx] == '#') {
						if(moveRedMarble.equals(new Pair(bny, bnx))) break;
						moveBlueMarble.y = bny;
						moveBlueMarble.x = bnx;
					}
					else if(map[bny][bnx] == '#') {
						if(moveBlueMarble.equals(new Pair(rny, rnx))) break;
						moveRedMarble.y = rny;
						moveRedMarble.x = rnx;
					}
					else {
						moveBlueMarble.y = bny;
						moveBlueMarble.x = bnx;
						moveRedMarble.y = rny;
						moveRedMarble.x = rnx;
					}
				}
			}
			
			
		}
		moveMarbles.add(moveRedMarble);
		moveMarbles.add(moveBlueMarble);
		return moveMarbles;
	}

}
