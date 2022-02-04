import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test10816 {
	static int N;
	static int[] li;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		li = new int[N];
		for(int i = 0; i<N; i++) {
			li[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(li);
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		//int[] find = new int[M];
		int find;
		StringBuilder result = new StringBuilder();
		for(int i = 0; i<M; i++) {
			find = Integer.parseInt(st.nextToken());
			int low = lower_bound(find);
			int up = upper_bound(find);
			if(low == -1) {
				result.append("0" + " ");
			}
			else {
				result.append(up - low + 1 + " ");
			}
		}
		
		System.out.println(result);
		
		

	}
	
	//Upper Bound: 찾는 숫자와 같은 수의 인덱스 중 가장 큰 인덱스 반환
	static int upper_bound(int find) {
		int index = -1;
		int start = 0;
		int end = N-1;
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(li[mid] == find) {
				index = mid;
				start = mid + 1;
			}
			else if(li[mid] > find) {
				end = mid - 1;
			}
			else if(li[mid] < find) {
				start = mid + 1;
			}
		}
		
		return index;
	}
	
	//Lower Bound: 찾는 숫자와 같은 수의 인덱스 중 가장 작은 인덱스 반환
	static int lower_bound(int find) {
		int index = -1;
		int start = 0;
		int end = N-1;
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(li[mid] == find) {
				index = mid;
				end = mid - 1;
			}
			else if(li[mid] > find) {
				end = mid - 1;
			}
			else if(li[mid] < find) {
				start = mid + 1;
			}
		}
		
		return index;
	}
}
