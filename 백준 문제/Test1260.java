package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*class Pair implements Comparable<Pair>{
	private int x;
	private int y;
	
	@Override
	public int compareTo(Pair p) {
		if(this.x > p.x) {//오름차순 정렬
			return 1;
		}
		else if(this.x == p.x) { //x가 같다면 y를 기준으로 오름차순 정렬
			if(this.y > p.y) {
				return 1;
			}
		}
		
		return -1;
	}

	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}	
}*/

public class Test1260 {
	static int N,M,start;
	static boolean[] visit;
	static int[][] mat;
	static int[] dfsList;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		visit = new boolean[N+1];
		mat = new int[N+1][N+1];
		for(int i = 0; i<=N;i++) {
			visit[i] = false;
		}
		
		int x,y;
		for(int i = 0; i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			mat[x][y] = 1;
			mat[y][x] = 1;
		}	
		//Collections.sort(lists);
		
		dfs(start);
		System.out.println();
		
		visit = new boolean[N+1];
		
		bfs(start);
		
	}
	
	static void bfs(int index) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(index);
		visit[index] = true;
		while(queue.size() != 0) {
			
			index = queue.poll();
			System.out.print(index + " ");
			
			for(int i = 1; i<=N; i++) {
				if(mat[index][i] == 1 && !visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
			
			
		}
	}
	
	static void dfs(int index) {
		
		visit[index] = true;
		System.out.print(index + " ");
		
		for(int j = 1; j<=N; j++) {
			if(mat[index][j] == 1  && !visit[j]) {
				dfs(j);
			}
		}
		
		return;
	}
}
