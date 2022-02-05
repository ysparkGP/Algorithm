package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test2470 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] solution = new int[N];
		for(int i = 0; i<N; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(solution);
		int start = 0;
		int end = N-1;
		int min = 2000000001;
		int si = 0;
		int ei = 0;
		while(start < end) {
			int mix = solution[start] + solution[end];
			
			//베스트 답
			if(Math.abs(mix) <= Math.abs(min)) {
				si = start;
				ei = end;
				min = mix;
			}
			
			if(mix < 0) {
				start++;
			}
			else if(mix > 0) {
				end--;
			}
			else {	
				si = start;
				ei = end;
				break;
			}
			
			//처음 답
			/*
			if(solution[start] >= 0) { //모든 용액이 0 포함 산성일 때
				if(Math.abs(mix) <= Math.abs(min)) {
					si = start;
					ei = end;
					min = mix;
				}
				end--;
			}
			
			else if(solution[start] < 0 && solution[end] < 0) { // 모든 용액이 알칼리성일 때
				if(Math.abs(mix) <= Math.abs(min)) {
					si = start;
					ei = end;
					min = mix;
				}
				start++;
			}
			else if(solution[start] < 0 && solution[end] >= 0) { // 두 용액이 알칼리와 산성일 때
				if(mix < 0) { //혼합했는데 음수라면 음수 낮춰준다.
					if(Math.abs(mix) <= Math.abs(min)) {
						si = start;
						ei = end;
						min = mix;
					}
					start++;
					
				}
				
				else if(mix > 0) { //혼합했는데 양수라면 양수를 낮춰준다.
					
					if(Math.abs(mix) <= Math.abs(min)) {
						si = start;
						ei = end;
						min = mix;
					}
					end--;
				}
				else { // 혼합했는데 0이 나온다면 끝
					si = start;
					ei = end;
					min = mix;
					break;
				}
			}*/
			
		}
		
		System.out.println(solution[si] + " " + solution[ei]);
		
	}
}
