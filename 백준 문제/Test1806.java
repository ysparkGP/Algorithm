package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1806 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		int[] sequence = new int[N];
		int temp = 0;
		for(int i = 0; i<N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
			temp += sequence[i];
			if(S >= sequence[i]) {
				System.out.println(1);
				return;
			}
		}
		
		if(temp < S) {
			System.out.println(0);
			return;
		}
			
		
		int start = 0;
		int end = start+1;
		int min = 100000;
		int sum = sequence[start];
		while(end < N) {
			
			sum += sequence[end];
			
			//System.out.println(sequence[start] + ", " + sequence[end] + ", " + sum + ", "+ min + ", " + (end- start + 1));
			if(sum < S) {
				end++;
			}
			
			else {
				min = Math.min(min, end-start+1);
				sum -= sequence[start];
				sum -= sequence[end];
				start++;
			}
			
		}
		
		System.out.println(min);
		
	}
}
