package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 비트마스킹, 중간에서 만나기, 이분 탐색, 투 포인터
 */

public class Test1208 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = N/2;
		int[] a = new int[1<<(N-size)];
		int[] b = new int[1<<size];
		// 비트마스킹으로 부분합 구하기
		for(int i = 0; i<(1<<(N-size)); i++) {
			for(int j = 0; j<N-size; j++) {
				if((i&(1<<j)) == (1<<j)) {
					a[i] += arr[j];
				}
			}
		}
		for(int i = 0; i<(1<<size); i++) {
			for(int j = 0; j<size; j++) {
				if((i&(1<<j)) == (1<<j)) {
					b[i] += arr[(N-size)+j];
				}
			}
		}
		Arrays.sort(a);
		Arrays.sort(b);
		
		//long cnt = tp(a,b,S);
		long answer = binarySearch(a,b,S);
		System.out.println(answer);
	}
	// 투포인터
	static long tp(int[] a, int[] b, int S) {
		long cnt = 0;
		int ap = 0;
		int bp = b.length-1;
		
		while(ap<a.length && bp >= 0) {
			int aValue = a[ap];
			int bValue = b[bp];
			if(aValue + bValue == S) {
				// 중복되는 구간 찾기
				long aCnt = 0;
				while(ap<a.length && aValue == a[ap]) {
					aCnt+=1;
					ap+=1;
				}
				long bCnt = 0;
				while(bp >= 0 && bValue == b[bp]) {
					bCnt+=1;
					bp-=1;
				}
				cnt += aCnt*bCnt;
			}
			else if(aValue + bValue < S) {
				ap++;
			}
			else {
				bp--;
			}
		}
		if(S == 0) cnt-=1;
		
		return cnt;
	}

	// 이분 탐색
	static long binarySearch(int[] a, int[] b, int S) {
		long cnt = 0;
		
		for(int i = 0; i<a.length;) {
			int aUpper = upperBound(a,a[i]);
			int aLower = lowerBound(a,a[i]);
			int bUpper = upperBound(b,S-a[i]);
			int bLower = lowerBound(b,S-a[i]);
			long aTerm = (aUpper - aLower - 1);
			long bTerm = (bUpper - bLower - 1);
			cnt += aTerm*bTerm;
			i+=(aUpper - aLower - 1);
		}
		if(S == 0) cnt--;
		return cnt;
	}
	static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length-1;
		while(left <= right) {
			int mid = (left+right)/2;
			if(arr[mid] <= target) left = mid+1;
			else right = mid-1;
		}
		return left;
	}
	static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length-1;
		while(left <= right) {
			int mid = (left+right)/2;
			if(arr[mid] >= target) right = mid-1;
			else left = mid+1;
		}
		return right;
	}
}
