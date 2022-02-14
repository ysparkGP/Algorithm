package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상 정렬
 * 시간 복잡도: O(V+E)
 * V개의 정점을 뽑고, 그 안의 E개의 정점들을 처리하는 로직
 */

public class Test2252 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<>();
		Queue<Integer> result = new LinkedList<>();
		int[] inDegree = new int[N+1];
		ArrayList<ArrayList<Integer>> li = new ArrayList<>();
		for(int i = 0; i<=N; i++)
			li.add(new ArrayList<>());
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			li.get(start).add(end);
			inDegree[end]+=1;
		}
		//진입 차수가 0인 학생 큐 삽입
		for(int i = 1; i<=N; i++) {
			if(inDegree[i] == 0)
				que.add(i);
		}
		while(!que.isEmpty()) {
			int student = que.poll();
			result.add(student);
			
			for(Integer i : li.get(student)) {
				inDegree[i]--;
				
				if(inDegree[i] == 0) {
					que.add(i);
				}
			}
		}
		
		while(!result.isEmpty()) {
			System.out.print(result.poll() + " ");	
		}
	}

}
