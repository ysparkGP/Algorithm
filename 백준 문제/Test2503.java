import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Test2503 {

	static boolean visit[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] baseball = new int[N][3];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<3; j++) {
				baseball[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[1000];
		Arrays.fill(visit, true);
		for(int i = 0; i<N; i++) {
			search(baseball[i]);
		}
		int result = 0;
		for(int i = 100; i<1000; i++)
			if(visit[i]) {
				String value = Integer.toString(i);
				boolean check = true;
				loop: for(int k = 0; k<2; k++) {
					for(int j = k+1; j<3; j++) {
						if(value.charAt(k) == value.charAt(j)) {
							check = false;
							break loop;
						}
						if(value.charAt(j) == '0' || value.charAt(k) == '0') {
							check = false;
							break loop;
						}
					}
				}
				if(check) {
					result++;
					//System.out.println(i);
				}
					
			}
		
		System.out.println(result);
	}
	
	static void search(int baseball[]) {
		
		String minh = Integer.toString(baseball[0]);
		
		
		for(int i = 100; i<1000; i++) {
			int strike = baseball[1];
			int ball = baseball[2];
			String expect = Integer.toString(i);
			
			boolean[] check = new boolean[3];
			//strike 계산
			if(expect.charAt(0) == minh.charAt(0)) {
				strike--;
				check[0] = true;
			}
				
			if(expect.charAt(1) == minh.charAt(1)) {
				strike--;
				check[1] = true;
			}
				
			if(expect.charAt(2) == minh.charAt(2)) {
				strike--;
				check[2] = true;
			}
				
			//ball 계산
			for(int j = 0; j<3; j++) {
				for(int k = 0; k<3; k++) {
					if(k != j) {
						
						if(expect.charAt(j) == minh.charAt(k))
							ball--;
					}
				}
			}
			
			if(visit[i]) {
				if(strike == 0 && ball == 0) 
					visit[i] = true;
				else
					visit[i] = false;
			}
			else
				visit[i] = false;
		
		}
	}
	
}
