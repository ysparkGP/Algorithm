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
		
		StringBuilder sb = new StringBuilder(); //��°��� �����Ƿ� StringBuilder�� ���
		for(int i = 0; i<N; i++) {
			int F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			parent = new int[F*2+1];
			level = new int[F*2+1];
			
			for(int j = 1; j<=2*F; j++) {
				parent[j] = j; //��Ʈ��ũ �θ�
				level[j] = 1; //��Ʈ��ũ�� �����ִ� ģ���� ��
			}
			int network = 1;
			for(int f = 1; f<=F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				String friend1 = st.nextToken();
				String friend2 = st.nextToken();
				if(!map.containsKey(friend1)) //���� �ؽøʿ� friend1 �̶�� ���ڿ��� ���ٸ� ��Ʈ��ũ �Ҵ�
					map.put(friend1, network++);
				if(!map.containsKey(friend2))
					map.put(friend2, network++);
				
				sb.append(union(map.get(friend1), map.get(friend2)) + "\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	static int find(int a) {
		if(parent[a] == a)  //���ڷ� �ҷ��� �༮�� �θ���
			return a;
		else //���ڷ� �ҷ��� �༮�� �θ� �ƴ϶��
			return parent[a] = find(parent[a]);  //����Լ��� ���ؼ� �θ� ã����
	}
	
	static int union(int a, int b) {
		
		int parentA = find(a); //a�� �����ִ� ��Ʈ��ũ�� �θ� ã��
		int parentB = find(b); //b�� �����ִ� ��Ʈ��ũ�� �θ� ã��
		if(parentA != parentB) {  // ���� �����ִ� ��Ʈ��ũ�� �θ� �ٸ��ٸ� 
			if(parentA < parentB) { // ���� �༮�� �θ� �� �� �ְ� ... ���� �����ִ� ��Ʈ��ũ�� �θ� ã���� �ǹǷ� �̷��� ���ص���.
				parent[parentB] = parentA;
				level[parentA] += level[parentB];  //b�� �����ִ� ��Ʈ��ũ �θ� a�� �ٲ������, b�� �����ִ� ��Ʈ��ũ�� ������ a ��Ʈ��ũ�� �θ𿡰� ������
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
