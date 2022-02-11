package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 벨만-포드 알고리즘 (음의 가중치가 있을 꼉우 사용하는 최단경로 알고리즘)
 */

class Bus{
	int start;
	int dest;
	int cost;
	Bus(int start, int dest, int cost){
		this.start = start;
		this.dest = dest;
		this.cost = cost;
	}
}

public class Test11657 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Bus> graph = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.add(new Bus(start,end,cost));
		}
		
		long[] dp = new long[N+1];
		Arrays.fill(dp,Long.MAX_VALUE);
		dp[1] = 0;
		
		/*
		 * 최단 경로란 V개의 정점이 있을 때, 최대 V-1개의 경로를 거쳐서 가야함
		 * 하지만 음의 사이클을 체크하려면 V번 반복해야함
		 * V-1개의 경로를 찾아서 최단경로를 찾았을 때, V번째에서 감소한다면 음의 사이클이 있는 경우라는 것
		 */
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<graph.size(); j++) { // 모든 간선 확인
				int start = graph.get(j).start;
				int dest = graph.get(j).dest;
				int cost = graph.get(j).cost;
				
				/*
				 * 간선을 꺼냈을 때, 시작하는 지점이 아직 갱신이 안 된 경우
				 * 또는, 아예 탐색을 할 수 없는 경우
				 */
				if(dp[start] == Long.MAX_VALUE) 
					continue;
				
				if(dp[dest] > dp[start] + cost) {
					
					if(i == N-1) { //V번째 반복 때, 음의 변화가 일어나는 경우
						System.out.println(-1);
						return;
					}
					dp[dest] = dp[start] + cost;
				}
			}
		}
		for(int i = 2; i<=N; i++) {
			if(dp[i] == Long.MAX_VALUE) {
				System.out.println(-1);
			}
			else System.out.println(dp[i]);
		}
	}

}
