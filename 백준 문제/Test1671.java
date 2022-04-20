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

public class Test1671 {

	static boolean[] visit;
	static int[] match;
	static List<Integer>[] matchingList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] state = new int[N][3];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			state[i][0] = Integer.parseInt(st.nextToken());
			state[i][1] = Integer.parseInt(st.nextToken());
			state[i][2] = Integer.parseInt(st.nextToken());
		}
		matchingList = new ArrayList[N];
		
		for(int i = 0; i<N; i++) {
			matchingList[i] = new ArrayList<>();
			for(int j = 0; j<N; j++) {
				if(i==j) continue;
				
				if(state[i][0] == state[j][0] && state[i][1] == state[j][1] && state[i][2] == state[j][2]) {
					if(i<j) matchingList[i].add(j);
					continue;
				}
				
				if(state[i][0] < state[j][0]) continue;
				if(state[i][1] < state[j][1]) continue;
				if(state[i][2] < state[j][2]) continue;
				
				matchingList[i].add(j);
			}
		}
		
		/*for(List<Integer> li: matchingList) {
			for(int i = 0; i<li.size(); i++) {
				System.out.print(li.get(i) + " ");
			}
			System.out.println();
		}*/
		int answer = 0;
		visit = new boolean[N];
		match = new int[N];
		Arrays.fill(match, -1);
		
		for(int i = 0; i<N; i++) {
			for(int cnt = 0; cnt<2; cnt++) {
				Arrays.fill(visit, false);
				if(dfs(i)) answer++;
				
				/*for(int j = 0; j<match.length; j++) System.out.print(match[j] + " ");
				System.out.println();*/
			}
		}
		System.out.println(N-answer);
	}
	
	static boolean dfs(int index) {
		
		for(int i = 0; i<matchingList[index].size(); i++) {
			int target = matchingList[index].get(i);
			if(!visit[target]) {
				visit[target] = true;
				if(match[target] == -1 ||dfs(match[target])) {
					match[target] = index;
					return true;
				}
			}
		}
		
		return false;
	}

}
