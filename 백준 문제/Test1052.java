package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1052 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " "); 
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int n = N;
		
		/*int[] arr = new int[N+1];
		for(int i = 1; i<=N;i++) {
			arr[i] = 1;
		}
		
		int iter = 0;
		while(n != 0) {
			iter++;
			n/=2;
		}
		
		n = N;
		
		while(iter != 0) {
			for(int i = 1; i<=n/2;i++) {
				if(arr[i] == arr[i + n/2]) {
					arr[i] += arr[i+n/2];
					arr[i + n/2] = 0; 
				}
			}
			n/=2;
			iter--;
		}
		
		for(int i = 0; i<=N;i++) {
			System.out.print(arr[i] + " ");
		}*/
		
		
		
	}

}
