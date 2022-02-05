package baekjoon;

/*그리디 알고리즘 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Challenger implements Comparable<Challenger>{
	int first;
	int second;
	Challenger(int first, int second){
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int compareTo(Challenger c) {
		if(this.first == c.first)
			return Integer.compare(this.second, c.second);
		
		else
			return Integer.compare(this.first, c.first);
	}
}


public class Test1946 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] test = new int[T];
		//int[] result = new int[T];
		
		//List<List<Challenger>> totalList = new ArrayList<>();
		
		for(int i = 0; i<T; i++) {
			List<Challenger> li = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			//test[i] = N;
			for(int j = 0; j<N; j++) {
				String str = br.readLine();
				StringTokenizer st = new StringTokenizer(str, " ");
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				li.add(new Challenger(first, second));
			}
			Collections.sort(li); //오름차순으로 정렬
			
			//그리디 알고리즘
			int preSecond = li.get(0).second;
			int cnt = 1;
			for(int j = 1; j<N; j++) {
				if(li.get(j).second < preSecond) {
					cnt++;
					preSecond = li.get(j).second;
				}
			}
			System.out.println(cnt);
		}
		
	
	}
	
}
