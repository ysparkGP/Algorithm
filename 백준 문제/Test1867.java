package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 최소 버텍스 커버, 이분 매칭
 */

public class Test1867 {

	static int[] match;
	static List<Integer>[] matchingList;
	static boolean[] visit;
	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
		 int n = Integer.parseInt(st.nextToken());
		 int k = Integer.parseInt(st.nextToken());
		 
		 matchingList = new ArrayList[n+1];
		 for(int i = 0; i<=n; i++)
			 matchingList[i] = new ArrayList<>();
		 
		 for(int i = 0; i<k; i++) {
			 st = new StringTokenizer(br.readLine(), " ");
			 int selector = Integer.parseInt(st.nextToken());
			 int selection = Integer.parseInt(st.nextToken());
			 matchingList[selector].add(selection);
		 }
		
		 match = new int[n+1];
		 Arrays.fill(match, -1);
		 visit = new boolean[n+1];
		 int cnt = 0;
		 for(int i = 1; i<=n; i++) {
			 Arrays.fill(visit, false);
			 if(dfs(i)) cnt+=1;
		 }
		 System.out.println(cnt);
		 
	}
	static boolean dfs(int x) {
		
		for(Integer s: matchingList[x]) {
			if(!visit[s]) {
				visit[s] = true;
				if(match[s] == -1 || dfs(match[s])) {
					match[s] = x;
					return true;
				}
			}
		}
		
		return false;
	}

}
