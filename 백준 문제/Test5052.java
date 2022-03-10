package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 정렬 -> startswith
 */

public class Test5052 {

	static int[] p;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t<T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			String[] strArr = new String[N];
			for(int n = 0; n<N; n++) {
				strArr[n] = br.readLine();
			}
			Arrays.sort(strArr); // "123" -> "1230" -> "12300" -> "1231"
			
			boolean consistence = true;
			for(int i = 0; i<N-1; i++) {
				if(strArr[i+1].startsWith(strArr[i])) {
					consistence = false;
					break;
				}
			}
			
			if(consistence) sb.append("YES\n");
			else sb.append("NO\n");
		}
		
		System.out.println(sb.toString());
	}
	

}
