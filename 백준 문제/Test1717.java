import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1717 {
	
	static int[] parent;
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for(int i = 0; i<=n; i++)
			parent[i] = i;
		
		for(int i = 0; i<m; i++) {
			// 0: union, 1: find
			st = new StringTokenizer(br.readLine()," ");
		
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cal == 0) {
				union(a,b);
			}
			else if(cal == 1) {
				check(a,b);
			}
		
		}
	}
	
	static void check(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA != parentB)
			System.out.println("no");
		else
			System.out.println("yes");
	}
	
	static int find(int a) {
		if(a == parent[a])
			return a;
		
		else
			return parent[a] = find(parent[a]);
		
	}
	
	static void union(int a, int b) {
		int parentA = find(a); //a의 부모 찾기
		int parentB = find(b); //b의 부모 찾기
		
		parent[parentB] = parentA;
	}

}
