import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Test1450 {
	
	static int N;
	static int C;
	static ArrayList<Integer> subsetA;
	static ArrayList<Integer> subsetB;
	static int[] value;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		subsetA = new ArrayList<>();
		subsetB = new ArrayList<>();
		
		value = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0; i<N; i++){
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		if(N == 1) {
			if(value[0] <= C)
				System.out.println(2);
				
			else 
				System.out.println(1);
				
			return;
		}
		
		dfsA(0,0);
		dfsB(N/2,0);
		Collections.sort(subsetA);
		
		//System.out.println("good");
		
		int count = 0;
		for(int i = 0; i<subsetB.size(); i++) {
			int B = subsetB.get(i);
			int start = 0;
			int end = subsetA.size()-1;
			
			while(start <= end) { // upper bound
				int mid = (start + end) / 2;
				
				if((subsetA.get(mid) + B) > C) {
					end = mid - 1;
				}
				else {
					start = mid + 1;
				}
			}
			
			count += start;
			
		}
		
		System.out.println(count);
	}
	
	static void dfsA(int i, int sum) {
		
		if(sum > C) {
			return;
		}
		
		if(i == N/2) {
			subsetA.add(sum);
			return;
		}
		
		dfsA(i+1,sum+value[i]);
		dfsA(i+1,sum);
		
	}
	static void dfsB(int i, int sum) {
		
		if(sum > C) {
			return;
		}
		
		if(i == N) {
			subsetB.add(sum);
			return;
		}
		
		dfsB(i+1,sum+value[i]);
		dfsB(i+1,sum);
		
	}
}