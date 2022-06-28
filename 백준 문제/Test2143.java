package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 부분합, 투 포인터
 */

public class Test2143 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N+1];
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<M; i++) B[i] = Integer.parseInt(st.nextToken());
		
		List<Integer> aList = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			int sum = 0;
			for(int j = i; j<N; j++) {
				sum += A[j];
				aList.add(sum);
			}
		}
		List<Integer> bList = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			int sum = 0;
			for(int j = i; j<M; j++) {
				sum += B[j];
				bList.add(sum);
			}
		}
		Collections.sort(aList);
		Collections.sort(bList);
		System.out.println(search(aList, bList, T));
	}
	public static long search(List<Integer> aList, List<Integer> bList, int target) {
		long result = 0;
		
		int left = 0;
		int right = bList.size()-1;
		while(left < aList.size() && right >= 0) {
			int sum = aList.get(left) + bList.get(right);
			if(sum > target) {
				right--;
			}
			else if(sum < target) {
				left++;
			}
			else {
				long aCnt = 0;
				long bCnt = 0;
				int aValue = aList.get(left);
				int bValue = bList.get(right);
				
				while(left < aList.size() && aValue == aList.get(left) ) {
					aCnt+=1;
					left+=1;
				}
				while(right >= 0 && bValue == bList.get(right)) {
					bCnt+=1;
					right-=1;
				}
				result += aCnt*bCnt;
			}
			
		}
		
		return result;
	}
}
