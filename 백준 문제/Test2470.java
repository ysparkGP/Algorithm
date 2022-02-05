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
			
			//����Ʈ ��
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
			
			//ó�� ��
			/*
			if(solution[start] >= 0) { //��� ����� 0 ���� �꼺�� ��
				if(Math.abs(mix) <= Math.abs(min)) {
					si = start;
					ei = end;
					min = mix;
				}
				end--;
			}
			
			else if(solution[start] < 0 && solution[end] < 0) { // ��� ����� ��Į������ ��
				if(Math.abs(mix) <= Math.abs(min)) {
					si = start;
					ei = end;
					min = mix;
				}
				start++;
			}
			else if(solution[start] < 0 && solution[end] >= 0) { // �� ����� ��Į���� �꼺�� ��
				if(mix < 0) { //ȥ���ߴµ� ������� ���� �����ش�.
					if(Math.abs(mix) <= Math.abs(min)) {
						si = start;
						ei = end;
						min = mix;
					}
					start++;
					
				}
				
				else if(mix > 0) { //ȥ���ߴµ� ������ ����� �����ش�.
					
					if(Math.abs(mix) <= Math.abs(min)) {
						si = start;
						ei = end;
						min = mix;
					}
					end--;
				}
				else { // ȥ���ߴµ� 0�� ���´ٸ� ��
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
