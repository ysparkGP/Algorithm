import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test4195 {
	static HashMap<String, Integer> map;
	static int[] parent;
	static int[] level;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(); //출력값이 많으므로 StringBuilder를 사용
		for(int i = 0; i<N; i++) {
			int F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			parent = new int[F*2+1];
			level = new int[F*2+1];
			
			for(int j = 1; j<=2*F; j++) {
				parent[j] = j; //네트워크 부모
				level[j] = 1; //네트워크에 속해있는 친구들 수
			}
			int network = 1;
			for(int f = 1; f<=F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				if(!map.containsKey(friend1)) //기존 해시맵에 friend1 이라는 문자열이 없다면 네트워크 할당
					map.put(friend1, network++);
				if(!map.containsKey(friend2))
					map.put(friend2, network++);
				
				sb.append(union(map.get(friend1), map.get(friend2)) + "\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	static int find(int a) {
		if(parent[a] == a)  //인자로 불려온 녀석이 부모라면
			return a;
		else //인자로 불려온 녀석이 부모가 아니라면
			return parent[a] = find(parent[a]);  //재귀함수를 통해서 부모를 찾아줌
	}
	
	static int union(int a, int b) {
		
		int parentA = find(a); //a가 속해있는 네트워크의 부모 찾기
		int parentB = find(b); //b가 속해있는 네트워크의 부모 찾기
		if(parentA != parentB) {  // 서로 속해있는 네트워크의 부모가 다르다면 
			if(parentA < parentB) { // 낮은 녀석이 부모가 될 수 있게 ... 각자 속해있는 네트워크의 부모만 찾으면 되므로 이렇게 안해도됨.
				parent[parentB] = parentA;
				level[parentA] += level[parentB];  //b가 속해있는 네트워크 부모가 a로 바뀌었으니, b가 속해있는 네트워크의 개수를 a 네트워크의 부모에게 더해줌
				level[parentB] = 1;
				return level[parentA];
			}
			else {
				parent[parentA] = parentB;
				level[parentB] += level[parentA];
				level[parentA] = 1;
				return level[parentB];
			}
		}
		else {
			return level[parentA];
		}
	}

}
