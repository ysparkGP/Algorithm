package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1912 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		int[] val = new int[N];
		int[] dp = new int[N];
	
		for(int i = 0; i<N; i++) {
			val[i] = Integer.parseInt(st.nextToken());
			dp[i] = Integer.MIN_VALUE;	
		}
		
		int prev = 0;
		int max = val[0];
		for(int i = 0; i<N; i++) {
			prev += val[i];
			max = Math.max(prev, max);
			if(prev <= 0)
				prev = 0;
		}
		
		/*
		int max = Integer.MIN_VALUE;
		for(int i = 0; i<N; i++) {
			int sum = 0;
			for(int j = i; j<N; j++) {
				sum += val[j];
				dp[i] = dp[i] < sum ? sum : dp[i];
				max = max > dp[i] ? max : dp[i];
			}
		}
		*/
		
		System.out.println(max);
	}
}
