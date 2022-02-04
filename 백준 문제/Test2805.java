import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test2805 {
	static int[] tree;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tree = new int[N+1];
		int min = 0;
		int max = 0;
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, tree[i]);
		}
		
		long prev = -1;
		while(true) {
			int value = (min + max) / 2;
			long result = cal(value);
			
			System.out.println(max + ", " + min + ", " + value + ", "+ result);
			
			if(result == M) {
				System.out.println(value);
				return ;
			}
			
			if(prev == result) {
				System.out.println(value);
				return;
			}
			
			else {
				if(result < M) {
					max = value;
				}
				
				else if(result >M) {
					min = value;
				}
			}
			
			prev  =  result;
		}
		
		
	}
	
	static public long cal(int value) {
		long sum = 0;
		for(int i = 1; i<=N; i++) {
			if(tree[i] - value <= 0)
				continue;
			else {
				sum = sum + tree[i] - value;
			}
		}
		
		return sum;
	}

}
