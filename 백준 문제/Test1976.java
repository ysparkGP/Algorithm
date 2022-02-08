import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test1976 {

	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		parent = new int[N+1];
		for(int i = 1; i<=N; i++) {
			parent[i] = i;
		}
		
		int[][] map = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					union(i,j);
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] plan = new int[M];
		for(int i = 0 ; i<M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i<=N; i++)
			System.out.print(parent[i] + " ");
		
		int planParent = find(plan[0]);
		for(int i = 1; i<M; i++) {
			int iParent = find(plan[i]);
			if(planParent != iParent) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static int find(int a) {
		
		if(a == parent[a])
			return a;
		
		else
			return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		
		int parentA = find(a);
		int parentB = find(b);
		
		
		if(parentA != parentB) {
			parent[parentB] = parentA;
		}
	}

}
