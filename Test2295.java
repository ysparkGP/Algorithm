import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test2295 {
	
	/*
	static Set<Integer> subSet;
	static List<Integer> subList;*/
	static int[] set;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		set = new int[N];
		for(int i = 0; i<N; i++) {
			set[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(set);
		/*
		subSet = new HashSet<>();
		dfs(0,0,0);
		subList = new ArrayList<>(subSet);
		Collections.sort(subList);
		
		for(int i = 0; i<subList.size(); i++)
			System.out.print(subList.get(i) + " ");
		*/
		int result = 0;
		
		for(int a = N-2; a>=0; a--) {
			
			for(int b = a; b>=0; b--) {
				
				for(int c = b; c>=0; c--) {
					
					int sum = set[a] + set[b] + set[c];
					int start = 0;
					int end = N-1;
					if(sum > set[N-1]) {
						continue;
					}
					else {
						while(start <= end) {
							int mid = (start + end) / 2;
							if(set[mid] < sum) {
								start = mid + 1;
							}
							else if(set[mid] > sum) {
								end = mid - 1;
							}
							else {
								result = Math.max(result, sum);
								break;
								
							}
						}
					}
					
				}
			}
		}
		System.out.println(result);
		
		
	}
	
	/* 메모리 초과
	static void dfs(int cnt, int i, int sum) {
		
		if(cnt == 3) {
			subSet.add(sum);
			return;
		}
		if(i >= N) {
			return;
		}
		
		dfs(cnt+1, i+1, sum+set[i]);
		dfs(cnt, i+1, sum);
	}
	*/
}

