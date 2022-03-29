package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 이분 매칭
 */

public class Test11375 {

	static List<Integer>[] checkList;
	static boolean[] visit;
	static int[] match;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 직원의 수
		int M = Integer.parseInt(st.nextToken()); // 일의 수
		
		checkList = new ArrayList[N+1];
		visit = new boolean[M+1];
		match = new int[M+1];
		for(int i = 0; i<=N; i++)
			checkList[i] = new ArrayList<>();
		
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0; j<k; j++) {
				checkList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int cnt = 0;
		Arrays.fill(match, -1);
		for(int i = 1; i<=N; i++) {
			for(int j = 0; j<2; j++) {
				Arrays.fill(visit, false);
				if(dfs(i)) cnt++;
			}
			
		}
		System.out.println(cnt);
	}
	static boolean dfs(int x) {
		
		for(Integer next : checkList[x]) {
			if(visit[next]) continue;
			visit[next] = true;
			if(match[next] == -1 || dfs(match[next])) {
				match[next] = x;
				return true;
			}
		}
		
		return false;
	}

}
