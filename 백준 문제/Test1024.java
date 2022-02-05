package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1024 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int result = 0;
		int value = 0;
		int many = 0;
		
		for(int i = 0; i<=N;i++) {
			value += i;
			many++;
			while(value > N) {
				value -= i-many+1;
				many--;
			}
			if(value == N && many >= L) {
				start = i-many+1;
				result = many;
			}
		}
		
		if(start == 0 && result-1 >= L) {
			start++;
			result--;
		}
		
		if((start == 0 && result == 0) || result > 100) {
			System.out.println("-1");
		}
		else {
			for(int i = 0; i<result; i++) {
				System.out.print(start+i + " ");
			}
		}
	}
	

}

