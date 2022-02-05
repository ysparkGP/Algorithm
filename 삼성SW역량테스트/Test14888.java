package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14888 {
	
	static int[] A;
	static int N;
	static int[] operator;
	static int cnt;
	static int result;
	static int max = -1000000000;
	static int min = 1000000000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		A = new int[N];
		operator = new int[4]; //+,-,*,/
		
		for(int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for(int i = 0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
			//cnt += operator[i];
		}
		
		result = A[0];
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int index) {
		if(index >= N-1) {
			max = result > max? result: max;
			min = result < min? result: min;
			result = A[0];
			return;
		}
		
		for(int i = 0; i< 4; i++) {
			
			if(operator[i] <= 0) {
				continue;
			}
			else if(operator[i] > 0 && i == 0) { // +
				int temp = result;
				result = result + A[index+1];
				operator[i]--;
				dfs(index+1);
				result = temp;
				operator[i]++;
			}
			else if(operator[i] > 0 && i == 1) { // -
				int temp = result;
				result = result - A[index+1];
				operator[i]--;
				dfs(index+1);
				result = temp;
				operator[i]++;
			}
			else if(operator[i] > 0 && i == 2) { // *
				int temp = result;
				result = result * A[index+1];
				operator[i]--;
				dfs(index+1);
				result = temp;
				operator[i]++;
			}
			else if(operator[i] > 0 && i == 3) { // /
				int temp = result;
				result = result / A[index+1];
				operator[i]--;
				dfs(index+1);
				result = temp;
				operator[i]++;
			}
		}
	}
}
