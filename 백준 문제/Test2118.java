package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Test2118 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dist = new int[2*N];
		
		int sum = 0;
		int max = 0;
		for(int i = 0; i<N; i++) {
			int temp = Integer.parseInt(br.readLine());
			sum += temp;
			dist[i] = sum;
		}
		
		for(int i = 0; i<N; i++) {
			dist[i+N] = sum + dist[i];
		}
		
		for(int i = 0; i<N; i++) {
			
			for(int j = i+1; j<N+i; j++) {
				int value = dist[j] - dist[i];
				max = Math.max(max, Math.min(sum - value, value));
			}
			
		}
		
		System.out.println(max);

	}
}
