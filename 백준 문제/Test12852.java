package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * dp
 */

public class Test12852 {

	static class Pair{
		int cnt;
		int preValue;
		
		Pair(int cnt, int preValue){
			this.cnt = cnt;
			this.preValue = preValue;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Pair[] dp = new Pair[N+1];
		for(int i = 0; i<=N; i++) {
			dp[i] = new Pair(Integer.MAX_VALUE, 0);
		}
		dp[N].cnt = 0;
		dp[N].preValue = Integer.MAX_VALUE;
		for(int i = N; i>=1; i--) {
			if(i%2 == 0) {
				if(dp[i].cnt + 1 < dp[i/2].cnt) {
					dp[i/2] = new Pair(dp[i].cnt + 1, i);
				}
			}
			if(i%3 == 0) {
				if(dp[i].cnt + 1 < dp[i/3].cnt) {
					dp[i/3] = new Pair(dp[i].cnt + 1, i);
				}
			}
			
			if(dp[i].cnt + 1 < dp[i-1].cnt) {
				dp[i-1] = new Pair(dp[i].cnt+1, i);
			}
		}
		System.out.println(dp[1].cnt);
		List<Integer> list = new ArrayList<>();
		int index = 1;
		while(index <= N) {
			list.add(index);
			index = dp[index].preValue;
		}
		for(int i = list.size()-1; i>=0; i--) {
			System.out.print(list.get(i) + " ");
		}
	}

}
