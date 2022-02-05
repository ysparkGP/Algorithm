package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Test11047 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Integer[] coins = new Integer[N];
		for(int i = 0; i<N; i++) {
			str = bf.readLine();
			coins[i] = Integer.parseInt(str);
		}
		
		Arrays.sort(coins, new Comparator<Integer>() {
			@Override
			public int compare(Integer c1, Integer c2) {
				return c2 - c1;
			}
		});
		
		int cnt = 0;
		int value = 0;
		int i = 0;
		while(value != K) {
			if(coins[i] <= K-value) {
				value += coins[i];
				cnt++;
			}
			else {
				i++;
				if(i>=N) {
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
