package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 이분 매칭
 */

public class Test9576 {
	static class Range{
		int start;
		int end;
		Range(int start, int end){
			this.start = start;
			this.end  = end;
		}
	}

	static Range[] range;
	static boolean[] visit;
	static int[] match;
	static int N,M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());  // 책 수
			M = Integer.parseInt(st.nextToken()); // 학생 수
			
			range = new Range[M+1];
			visit = new boolean[N+1];
			match = new int[N+1];
			Arrays.fill(match, -1);
			for(int i = 1; i<=M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				range[i] = new Range(start,end);
			}
			
			int cnt = 0;
			for(int i = 1; i<=M; i++) {
				//System.out.println(i +", "+ cnt);
				Arrays.fill(visit, false);
				if(dfs(i)) cnt++;
			}
			System.out.println(cnt);
		}
	}
	
	static boolean dfs(int x) {
		
		for(int next = range[x].start; next<=range[x].end; next++) {
			if(visit[next]) continue;
			visit[next] = true;
			if(match[next] == -1 || dfs(match[next])) { // 매칭관계가 비어있거나, 다시 매칭관계를 조정할 수 있다면
				match[next] = x;
				return true;
			}
		}
		
		return false;
	}

}
