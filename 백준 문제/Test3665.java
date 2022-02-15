package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상 정렬 문제
 * 리스트로 그래프를 표현한다면 remove와 contains의 시간 복잡도는 O(N)이므로
 * 시간 초과가 났었다.
 * 그에 반해 배열로 나타낸 그래프는 지정된 값만 변경해주는 것이므로 시간 복잡도는 O(1)으로
 * 제한시간 1초인 이 문제에서 544ms로 통과 
 */

public class Test3665 {
	
	static boolean[][] graph;
	//static ArrayList<ArrayList<Integer>> li;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			inDegree = new int[N+1];
			graph = new boolean[N+1][N+1];
			/*li = new ArrayList<>();
			for(int i = 0; i<=N; i++) {
				li.add(new ArrayList<>());
			}*/
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int i = 0; i<N; i++) {
				int start = Integer.parseInt(st.nextToken());
				inDegree[start] = i;
				for(int j = 1; j<=N; j++) {
					if(start == j) continue;
					//if(li.get(j).contains(start)) continue;
					//li.get(start).add(j);
					if(graph[j][start]) continue;
					graph[start][j] = true;
				}
			}
			
			int M = Integer.parseInt(br.readLine());
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				swap(v1,v2);
			}
			Queue<Integer> que = new LinkedList<>();
			for(int i = 1; i<=N; i++) {
				if(inDegree[i] == 0)
					que.add(i);
			}
			
			Queue<Integer> resultQue = new LinkedList<>();
			int sw = 0;
			for(int i = 0; i<N; i++) {
				if(que.isEmpty()) { // IMPOSSIBLE
					sw = 1;
					break;
				}
				else if(que.size() > 2) { // ?
					sw = 2;
					break;
				}
				int number = que.poll();
				resultQue.add(number);
				for(int j = 1; j<=N; j++) {
					
					if(graph[number][j]) {
					//if(li.get(number).contains(j)) {
						inDegree[j]--;
						if(inDegree[j] == 0) {
							que.add(j);
						}
					}
				}
				
			}
			
			if(sw == 1) {
				System.out.println("IMPOSSIBLE");
			}
			else if(sw == 2) {
				System.out.println("?");
			}
			else {
				while(!resultQue.isEmpty()) {
					System.out.print(resultQue.poll() + " ");
				}
				System.out.println();
			}
		}
	}
	
	static void swap(int start, int end) {
		if(graph[start][end]) {
			graph[start][end] = false;
			graph[end][start] = true;
			inDegree[start]++;
			inDegree[end]--;
		}
		else {
			graph[start][end] = true;
			graph[end][start] = false;
			inDegree[start]--;
			inDegree[end]++;
		}
		/*
		if(li.get(start).contains(end)) {
			li.get(start).remove(Integer.valueOf(end));
			li.get(end).add(start);
			inDegree[start]++;
			inDegree[end]--;
		}
		else {
			li.get(end).remove(Integer.valueOf(start));
			li.get(start).add(end);
			inDegree[start]--;
			inDegree[end]++;
		}*/
	}

}
