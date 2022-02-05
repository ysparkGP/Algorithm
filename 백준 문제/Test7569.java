package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair{
	int h,m,n;
	Pair(int h, int n, int m){
		this.h = h;
		this.n = n;
		this.m = m;
	}
}

public class Test7569 {
	
	static int[][][] mat;
	static boolean[][][] visit;
	static int M,N,H;
	static boolean flag = false;
	static int row[] = {-1,1,0,0,0,0};
	static int col[] = {0,0,-1,1,0,0};
	static int height[] = {0,0,0,0,-1,1};
	static Queue<Pair> que = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		mat = new int[H+1][N+1][M+1];
		visit = new boolean[H+1][N+1][M+1];
		int tomato = 0;

		for(int i = 1; i<=H; i++) {
			for(int j = 1; j<=N; j++){
				str = br.readLine();
				st = new StringTokenizer(str, " ");
				for(int k = 1; k<=M; k++) {
					tomato = Integer.parseInt(st.nextToken());
					if(tomato == 0) {
						flag = true;
					}
					else if(tomato == 1) {
						que.add(new Pair(i,j,k));
					}
					mat[i][j][k] = tomato;
					visit[i][j][k] = false;
				}
			}
		}
		if(flag == true) {
			bfs();
		}
		else if(flag == false) {
			System.out.println(0);
		}
	}
	
	public static void bfs() {
		
		
		while(!que.isEmpty()) {
			Pair p = que.poll();
			
			for(int i = 0; i<=5;i++) {
				int moveHeight = p.h + height[i];
				int moveCol = p.n + col[i];
				int moveRow = p.m + row[i];
				if(moveHeight >= 1 && moveHeight <=H && moveCol >= 1 && moveCol <= N && moveRow >= 1 && moveRow <= M) {
					if(mat[moveHeight][moveCol][moveRow] == 0) {
						mat[moveHeight][moveCol][moveRow] = mat[p.h][p.n][p.m] + 1;
						que.add(new Pair(moveHeight, moveCol, moveRow));
					}
				}
				
			}
			
		}
		
		boolean f = false;
		int max = 1;
		loop : for(int i = 1; i<=H; i++) {
			for(int j = 1; j<=N; j++){
				for(int k = 1; k<=M; k++) {
					if(mat[i][j][k] == 0) {
						f = true;
						break loop;
					}
					max = max > mat[i][j][k] ? max: mat[i][j][k];
				}
			}
		}
		
		if(f == true) {
			System.out.println(-1);
		}
		else if(f == false) {
			System.out.println(max-1);
		}
		
		
	}

}
