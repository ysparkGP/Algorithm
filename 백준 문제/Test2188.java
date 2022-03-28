package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Test2188 {
	
	static boolean[] visit;
	static int[] match;
	static List<Integer>[] wantedStable;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 소의 수
		int M = Integer.parseInt(st.nextToken()); // 축사의 수
		
		visit = new boolean[M+1];
		match = new int[M+1];
		Arrays.fill(match, -1);
		wantedStable = new ArrayList[N+1];
		for(int i = 0; i<=N; i++) {
			wantedStable[i] = new ArrayList<>();
		}
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int v = Integer.parseInt(st.nextToken());
			for(int j = 0; j<v; j++) {
				wantedStable[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int cnt = 0;
		for(int i = 1; i<=N; i++) {
			Arrays.fill(visit, false);
			if(dfs(i)) cnt++;
		}
		System.out.println(cnt);
	}
	
	static boolean dfs(int x) {
		
		for(Integer next: wantedStable[x]) {
			
			// dfs 에서 무한반복을 방지하기 위함
			if(!visit[next]) {
				visit[next] = true;
				// 아직 축사가 소들에게 배정받지 않았거나 배정받았지만 그 배정을 다시 조정할 수 있을 때
				if(match[next] == -1 || dfs(match[next])) {
					match[next] = x;
					return true;
				}
			}
		}
		
		return false;
	}

}
