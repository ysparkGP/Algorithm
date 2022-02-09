import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test10775 {
	
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parent = new int[G+1];
		for(int i = 1; i<=G; i++)
			parent[i] = i;
		
		int cnt = 0;
		for(int i = 1; i<=P; i++) {
			int p = Integer.parseInt(br.readLine());
			union(p,p-1);
			if(parent[p] < 0) {
				System.out.println(cnt);
				return;
			}
			cnt++;
			
		}
		System.out.println(cnt);

	}
	
	static int find(int a) {
		if(parent[a] == a)
			return a;
		
		else
			return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) { //b는 차선책
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA != parentB) {
			parent[a] = parentB;
		}
		else {
			parent[a] = parentB-1;
			parent[b] = parentB-1;
		}
	}

}
