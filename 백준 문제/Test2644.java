package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test2644 {
	
	static int[][] rel;
	static int[] relation;
	static int A,B;
	static int N;
	static boolean[] visit;
	static ArrayList<Integer> candidate = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		visit = new boolean[N+1];
		relation = new int[N+1];
		
		for(int i = 1; i<=N;i++) {
			visit[i] = false;
		}
		
		rel = new int[N+1][N+1];
		for(int i = 0; i<M; i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			rel[x][y] = 1;
			rel[y][x] = 1;
		}
		
		/*dfs(A, 0);
		if(candidate.size() == 0) {
			System.out.println(-1);
		}
		else {
			Collections.sort(candidate);
			System.out.println(candidate.get(0));
		}*/
		bfs(A);
	}
	
	static void bfs(int index) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(index);
		
		while(!queue.isEmpty()) {
			int check = queue.poll();
			
			if(check == B)
				break;
			
			for(int i = 1; i<=N;i++) {
				//관계가 이미 기록이 되어있으면 갱신 x... 점차 커지므로 갱신 안해도 되고 중복 방지
				if(rel[check][i] == 1 && relation[i] == 0) {
					queue.add(i);
					relation[i] = relation[check] + 1;
				}
			}
			
		}
		if(relation[B] == 0) {
			System.out.println(-1);
		}	
		else {
			System.out.println(relation[B]);
		
		}
		
	}
	
	static void dfs(int index,int cnt) {
		
		if(index == B) {
			candidate.add(cnt);
			return;
		}
		
		for(int i = 1; i<=N; i++) {
			if(rel[index][i] == 1 && !visit[i]) {
				visit[i] = true;
				dfs(i,cnt+1);
				
			}
		}
	}
}
