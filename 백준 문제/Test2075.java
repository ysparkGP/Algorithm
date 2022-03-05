package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Sliding Window
 */

public class Test2075 {

	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for(int i = 0; i<N; i++) {
			que.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i<N; i++) {
			st =  new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(value > que.peek()) {
					que.poll();
					que.add(value);
				}	
			}
			
		}
		System.out.println(que.poll());
	}

}
