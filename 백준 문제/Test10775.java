import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 유니온 파인드
 */

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
			int g = Integer.parseInt(br.readLine());
			int findGate = find(g); //도킹할 수 있는 게이트 찾기
			if(findGate == 0) //도킹할 수 있는 게이트가 0이라면 댈 수 있는 곳이 없으므로 종료
				break;
			cnt++;
			union(findGate, findGate-1); //찾은 게이트는 도킹했으니 차선책을 마련
			
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
		
	}

}