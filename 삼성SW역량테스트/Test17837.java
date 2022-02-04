import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Horse{
	int y;
	int x;
	int dir;
	Horse(int y, int x, int dir){
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}

public class Test17837 {
	
	//이동방향은 1번부터
	static int[] dirX = {0,1,-1,0,0};
	static int[] dirY = {0,0,0,-1,1};
	static int[][] map;
	static int N,K;
	static ArrayList<Integer>[][] chess;
	static Horse[] horses;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			// 0: 흰색, 1: 빨간색, 2: 파란색
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		chess = new ArrayList[N+1][N+1];
		for(int i =1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				chess[i][j] = new ArrayList<>();
			}
		}
		
		horses = new Horse[K+1];
		for(int k = 1; k<=K; k++) {
			st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Horse insertHorse = new Horse(y,x,dir);
			horses[k] = insertHorse;
			chess[y][x].add(k);
		}
		
		cnt = 0;
		while(cnt < 1000) {
			cnt++;
			if(job()) {
				System.out.println(cnt);
				return;
			}
		}
		
		System.out.println(-1);
	}
	
	static boolean job() {
		//System.out.println("cnt : " + cnt);
		for(int k = 1; k<=K; k++) {
			Horse selectHorse = horses[k];
			//이동할 말들 선택
			int y = selectHorse.y;
			int x = selectHorse.x;
			int dir = selectHorse.dir;
			int sequence = chess[y][x].indexOf(k);
			//System.out.println(sequence);
			//System.out.println(k+"," + y+","+x+","+dir);
			ArrayList<Integer> moveHorses = new ArrayList<>();
			for(int i = sequence; i<chess[y][x].size(); i++) {
				moveHorses.add(chess[y][x].get(i));
			}
			
			int nextY = y + dirY[dir];
			int nextX = x + dirX[dir];
			//System.out.println(nextY + ", " + nextX);
			if(!validCheck(nextY, nextX)) {
				if(dir == 1)
					dir = 2;
				else if(dir == 2) {
					dir = 1;
				}
				else if(dir == 3) {
					dir = 4;
				}
				else if(dir == 4) {
					dir = 3;
				}
				selectHorse.dir = dir;
				int reverseY = y + dirY[dir];
				int reverseX = x + dirX[dir];
				if(validCheck(reverseY, reverseX)) {
					if(map[reverseY][reverseX] == 0) {
						move(y, x, reverseY, reverseX, moveHorses);
					}
					else if(map[reverseY][reverseX] == 1) {
						List<Integer> reverseHorses = reverseSequence(moveHorses);
						move(y, x, reverseY, reverseX, reverseHorses);
					}
					else if(map[reverseY][reverseX] == 2) {
						continue;
					}
				}
				continue;
			}
			
			if(map[nextY][nextX] == 0) { //흰
				//System.out.println("흰 : " + nextY +", " + nextX + " k");
				move(y, x, nextY, nextX, moveHorses);
				
			}
			else if(map[nextY][nextX] == 1) { //빨
				ArrayList<Integer> reverseHorses = reverseSequence(moveHorses);
				move(y, x, nextY, nextX, reverseHorses);
			}
			else if(map[nextY][nextX] == 2) { //파
				if(dir == 1)
					dir = 2;
				else if(dir == 2) {
					dir = 1;
				}
				else if(dir == 3) {
					dir = 4;
				}
				else if(dir == 4) {
					dir = 3;
				}
				selectHorse.dir = dir;
				int reverseY = y + dirY[dir];
				int reverseX = x + dirX[dir];
				if(validCheck(reverseY, reverseX)) {
					if(map[reverseY][reverseX] == 0) {
						move(y, x, reverseY, reverseX, moveHorses);
					}
					else if(map[reverseY][reverseX] == 1) {
						List<Integer> reverseHorses = reverseSequence(moveHorses);
						move(y, x, reverseY, reverseX, reverseHorses);
					}
					else if(map[reverseY][reverseX] == 2) {
						continue;
					}
				}
			}
			
			/*for(int i = 1; i<=K; i++) {
				System.out.println("순서: " + i + " Y: " + horses[i].y + " X: " + horses[i].x +
						" dir: " + horses[i].dir);
			}*/
			if(end()) {
				return true;
			}
		}
		return false;
	}
	
	static void move(int prevY, int prevX, int nextY, int nextX, List<Integer> moveHorses) {
		for(Integer moveHorse: moveHorses) {
			chess[nextY][nextX].add(moveHorse);
			chess[prevY][prevX].remove(moveHorse);
			horses[moveHorse].y = nextY;
			horses[moveHorse].x = nextX;
		}
	}
	
	static ArrayList<Integer> reverseSequence(ArrayList<Integer> moveHorses) {
		ArrayList<Integer> reverseHorses = new ArrayList<>();
		
		for(int i = moveHorses.size()-1; i >= 0; i--)
			reverseHorses.add(moveHorses.get(i));
		
		return reverseHorses;
	}
	
	static boolean end() {
		for(int y = 1; y<=N; y++) {
			for(int x = 1; x<=N; x++) {
				if(chess[y][x].size() >= 4)
					return true;
			}
		}
		return false;
	}
	
	static boolean validCheck(int y, int x) {
		if(y < 1 || x < 1 || y > N || x > N)
			return false;
		return true;
	}
}
