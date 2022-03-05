package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 선형 자료구조 PriorityQueue
 */

public class Test2493 {

	static class Pair{
		int value;
		int index;
		Pair(int value, int index){
			this.value = value;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				return p1.value - p2.value;
			}
		});
		
		StringBuilder sb  = new StringBuilder();
		StringTokenizer  st= new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			int value = Integer.parseInt(st.nextToken());

			while(true) {
				if(que.size() == 0) {
					que.add(new Pair(value, i));
					sb.append(0+ " ");
					break;
				}
				
				if(que.peek().value >= value) {
					sb.append(que.peek().index + " ");
					que.add(new Pair(value, i));
					break;
				}
				else if(que.peek().value < value) {
					que.poll();
				}
			}
		}
		System.out.println(sb.toString());
	}

}
