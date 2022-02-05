package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test10815 {
	static int[] card;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		card = new int[N];
		for(int i = 0; i<N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(card);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine()," ");
		int[] find = new int[M];
		for(int i = 0; i<M; i++) {
			find[i] = Integer.parseInt(st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<M; i++) {
			sb.append(search(0,N-1,find[i]) + " ");
		}
		
		System.out.println(sb);
	}
	
	static int search(int start, int end, int find) {
		int index = -1;
		
		while(start <= end) {
			int mid = (start+end) / 2;
			
			if(card[mid] > find) {
				end = mid - 1;
			}
			else if(card[mid] < find) {
				start = mid + 1;
			}
			else {
				index = mid;
				return 1;
			}
		}
		
		
		return 0;
	}
}
