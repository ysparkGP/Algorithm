import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test14503 {
	//0: 북, 1: 동, 2: 남, 3: 서
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
		d = Integer.parseInt(st.nextToken()); // 방향
		
		room = new int[N][M];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		job();
	}
	
	static int search() { // 빈 구역 찾을 때 까지 탐색
		int cnt = 0;
		while(true) {
			if(cnt == 3) {
				//후진
				int dBack = d - 2;
				if(dBack == -1)
					dBack = 3;
				else if(dBack == -2)
					dBack = 2;
				int searchY = y + Y[dBack];
				int searchX = x + X[dBack];
				if(!validCheck(searchY, searchX)) //후진한 곳이 벾 밖일 때,
					return 0;
				if(room[searchY][searchX] == 1) { //후진한 곳이 벽일 때,
					return 0;
				}
				else { //후진한 곳이 청소된 칸일 때,
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
			//탐색
			d = d-1;
			if(d < 0)
				d = 3;
			int searchY = y + Y[d];
			int searchX = x + X[d];
			
			if(validCheck(searchY, searchX)) { // 유효성 검사 성공
				if(room[searchY][searchX] == 0) { // 왼쪽으로 고개를 꺾은 칸이 청소가 안되어있다.
					// 전진 후 청소
					y = searchY;
					x = searchX;
					clear();
				} 
				else { //청소가 되어있다.
					int result = search();
					if(result == 0) { //종료
						System.out.println(ans);
						return;
					}	
				}
			}
			else { // 유효성 검사 실패
				int result = search();
				if(result == 0) { //종료
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
