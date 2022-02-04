import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14503 {
	//0: ��, 1: ��, 2: ��, 3: ��
	static int[] X = {0,1,0,-1};
	static int[] Y = {-1,0,1,0};
	static int y;
	static int x;
	static int d;
	static int[][] room;
	static int N;
	static int M;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		y = Integer.parseInt(st.nextToken()); // y
		x = Integer.parseInt(st.nextToken()); // x
		d = Integer.parseInt(st.nextToken()); // ����
		
		room = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		job();
	}
	
	static int search() { // �� ���� ã�� �� ���� Ž��
		int cnt = 0;
		while(true) {
			if(cnt == 3) {
				//����
				int dBack = d - 2;
				if(dBack == -1)
					dBack = 3;
				else if(dBack == -2)
					dBack = 2;
				int searchY = y + Y[dBack];
				int searchX = x + X[dBack];
				if(!validCheck(searchY, searchX)) //������ ���� �� ���� ��,
					return 0;
				if(room[searchY][searchX] == 1) { //������ ���� ���� ��,
					return 0;
				}
				else { //������ ���� û�ҵ� ĭ�� ��,
					y = searchY;
					x = searchX;
					break;
				}
			}
			d = d - 1;
			if(d < 0)
				d = 3;
			int searchY = y + Y[d];
			int searchX = x + X[d];
			if(validCheck(searchY, searchX)) {
				if(room[searchY][searchX] == 0) {
					y = searchY;
					x = searchX;
					clear();
					break;
				}
			}
			cnt+=1;
			
		}
		return 1;
	}
	
	static void job() {
		
		while(true) {
			if(room[y][x] == 0)
				clear();
			//Ž��
			d = d-1;
			if(d < 0)
				d = 3;
			int searchY = y + Y[d];
			int searchX = x + X[d];
			
			if(validCheck(searchY, searchX)) { // ��ȿ�� �˻� ����
				if(room[searchY][searchX] == 0) { // �������� ���� ���� ĭ�� û�Ұ� �ȵǾ��ִ�.
					// ���� �� û��
					y = searchY;
					x = searchX;
					clear();
				} 
				else { //û�Ұ� �Ǿ��ִ�.
					int result = search();
					if(result == 0) { //����
						System.out.println(ans);
						return;
					}	
				}
			}
			else { // ��ȿ�� �˻� ����
				int result = search();
				if(result == 0) { //����
					System.out.println(ans);
					return;
				}
			}	
		}
		
	}
	static void clear() {
		room[y][x] = 2;
		ans+=1;
		
		return;
	}
	
	static boolean validCheck(int i, int j) {
		if(i < 0 || i >= N || j < 0 || j >= M)
			return false;
		
		return true;
	}
	

}
