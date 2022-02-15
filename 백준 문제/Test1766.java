package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상 정렬
 */

public class Test1766 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		ArrayList<ArrayList<Integer>> li = new ArrayList<>();
		for(int i = 0; i<=N; i++) {
			li.add(new ArrayList<>());
		}
		int[] inDegree = new int[N+1];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			inDegree[end] += 1;
			li.get(start).add(end);
		}
		
		PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				return n1 - n2;
			}
		});
		
		for(int i = 1; i<=N; i++) {
			if(inDegree[i] == 0)
				que.add(i);
		}
		Queue<Integer> resultQue = new LinkedList<>();
		while(!que.isEmpty()) {
			int problem = que.poll();
			resultQue.add(problem);
			for(Integer i : li.get(problem)) {
				inDegree[i]--;
				if(inDegree[i] == 0)
					que.add(i);
			}
			
		}
		
		while(!resultQue.isEmpty()) {
			System.out.print(resultQue.poll() + " ");
		}
	}

}
