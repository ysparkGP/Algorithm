package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test5676 {

	static long[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// EOF 처리
		String str = "";
		while((str = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(str);
			StringBuilder sb = new StringBuilder();
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] arr = new int[N+1];
			st = new StringTokenizer(br.readLine()," ");
			for(int i = 1; i<=N; i++) {
				int value = Integer.parseInt(st.nextToken());
				if(value == 0) arr[i] = 0;
				else if(value > 0) arr[i] = 1;
				else arr[i] = -1;
			}
				
			tree = new long[4*N];
			init(arr, 1,1,N);
			
			for(int i = 0; i<K; i++) {
				st = new StringTokenizer(br.readLine()," ");
				char op = st.nextToken().charAt(0);
				if(op == 'C') { //update
					int index = Integer.parseInt(st.nextToken());
					long changeValue = Long.parseLong(st.nextToken());
					if(changeValue == 0) changeValue = 0;
					else if(changeValue > 0) changeValue = 1;
					else changeValue = -1;
					update(1,1,N,changeValue, index);
					/*for(int j = 1; j<4*N; j++)
						System.out.print(tree[j] + " ");
					System.out.println();*/
				}
				else { //product
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					long result = product(1,1,N,left,right);
					//System.out.println(result);
					if(result == 0) sb.append("0");
					else if(result < 0) sb.append("-");
					else sb.append("+");
				}
			}
			sb.append("\n");

			System.out.print(sb.toString());
		}
		
		
	}
	static long init(int[] arr, int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		else return tree[node] = init(arr, 2*node, start, (start+end)/2) *
				init(arr, 2*node+1, (start+end)/2+1, end);
	}
	
	static long product(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return 1;
		else if(start >= left && end <= right) return tree[node];
		else return product(2*node, start, (start+end)/2, left, right) *
				product(2*node+1, (start+end)/2+1, end, left, right);
	}
	
	static long update(int node, int start, int end, long changeValue, int index) {
		if(start > index || end < index) return tree[node];
	
		else if(start == end && start == index) return tree[node] = changeValue;
		
		else
			 return tree[node] = update(2*node, start, (start+end)/2, changeValue, index) *
						update(2*node+1, (start+end)/2+1, end, changeValue, index);
	}

}
