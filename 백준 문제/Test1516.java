package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상정렬, dp
 */

public class Test1516 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] build = new int[N+1];
		int[] time = new int[N+1];
		List<Integer>[] preList = new ArrayList[N+1];
		for(int i = 0; i<=N; i++) {
			preList[i] = new ArrayList<>();
		}
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			while(true) {
				int building = Integer.parseInt(st.nextToken());
				if(building == -1) break;
				
				preList[building].add(i);
				build[i]++;
			}
		}

		int[] dp = new int[N+1];
		
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i<=N; i++) {
			if(build[i] == 0) {
				dp[i] = time[i];
				que.add(i);
			}
		}
		
		while(!que.isEmpty()) {
			int building = que.poll();
			
			for(Integer i: preList[building]) {
				build[i]--;
				if(build[i] == 0) {
					que.add(i);
				}
				dp[i] = Math.max(dp[building]+time[i], dp[i]);
			}
		}
		
		for(int i = 1; i<=N; i++) System.out.println(dp[i]);
	}

}


