package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상 정렬
 */

public class Test2623 {

	static boolean[][] graph;
	static int[] inDegree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N+1][N+1];
		inDegree = new int[N+1];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int K = Integer.parseInt(st.nextToken());
			int[] kGraph = new int[K];
			for(int j = 0; j<K; j++) {
				kGraph[j] = Integer.parseInt(st.nextToken());
			}
			for(int j = 0; j<K-1; j++) {
				for(int k = j+1; k<K; k++) {
					// 그래프의 중복된 요소를 확인하여 (2 1 2) (2 1 2) 과 같은
					// 입력이 들어왔을 때, inDegree가 0 2 가 되는 것을 방지한다.
					if(graph[kGraph[j]][kGraph[k]]) continue;
					
					graph[kGraph[j]][kGraph[k]] = true;
					inDegree[kGraph[k]]++;
				}
			}
		}
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i<=N; i++) {
			if(inDegree[i] == 0) {
				que.add(i);
			}
		}
		//for(int i = 1; i<=N; i++) System.out.print(inDegree[i] + " ");
		
		Queue<Integer> resultQue = new LinkedList<>();
		for(int i = 0; i<N; i++) {
			if(que.isEmpty()) {
				System.out.println(0);
				return;
			}
			int num = que.poll();
			resultQue.add(num);
			for(int j = 1; j<=N; j++) {
				if(graph[num][j]) {
					inDegree[j]--;
					if(inDegree[j] == 0) {
						que.add(j);
					}
				}
			}
		}
		
		while(!resultQue.isEmpty()) {
			System.out.println(resultQue.poll());
		}
	}

}
