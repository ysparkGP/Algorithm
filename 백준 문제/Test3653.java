package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 창의성이 필요한 Segment Tree
 * (초기)N칸 선반과 (후기, 쌓을)M칸 선반을 따로 둔다.
 * N칸에서 M칸으로 영화DVD를 차곡차곡 쌓고 그 위의 구간합을 구하면 되겠다.
 */

public class Test3653 {
	
	static long[] tree;
	static long[] arr;
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new long[n+m+1];
			for(int i = 1; i<=n; i++) {
				arr[i] = 1;
			}
			
			tree = new long[4*(n+m)];
			init(arr,1,1,n+m);
			HashMap<Integer, Integer> map = new HashMap<>(); //영화번호, 층
			int floor = 1;
			for(int i = n; i>0; i--) {
				map.put(i, floor++);
			}
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i = n+1; i<=m+n; i++) {
				int movieNumber = Integer.parseInt(st.nextToken());
				int startFloor =  map.get(movieNumber);
				long find = add(1,1,n+m,startFloor+1,n+m);
				//System.out.println(find);
				sb.append(find + " ");
				
				update(1,1,n+m,-1,startFloor);
				
				map.put(movieNumber, i);
				update(1,1,n+m,1,i);
				
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	static long init(long[] arr, int node, int start, int end) {
		if(start != end) return tree[node] = init(arr, 2*node, start, (start+end)/2) +
				init(arr, 2*node+1, (start+end)/2+1, end);
		return tree[node] = arr[start];
	}
	
	static long add(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return  0;
		else if(left <= start && right >= end) return tree[node];
		else return add(2*node, start, (start+end)/2, left, right) +
				add(2*node+1, (start+end)/2 + 1, end, left , right);
	}
	
	static void update(int node, int start, int end, int diff, int index) {
		if(start > index || end < index) return;
		else if(start <= index && index <= end) {
			tree[node] += diff;
			if(start != end) {
				update(2*node, start, (start+end)/2, diff, index);
				update(2*node+1,  (start+end)/2+1, end, diff, index);
			}
		}
			
	}

}
