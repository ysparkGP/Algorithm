import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

class Pair{
	int y;
	int x;
	Pair(int y, int x){
		this.y = y;
		this.x = x;
	}
}

public class Test3190 {
	//오른쪽, 아래, 왼쪽, 위
	static int[] y = {0,1,0,-1};
	static int[] x = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N+1][N+1];
		for(int i = 0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			board[row][col] = 1; // 사과
		}
		
		int L = Integer.parseInt(br.readLine());
		HashMap<Integer, String> map = new HashMap<>();
		for(int i = 0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int seconds = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			map.put(seconds, direction);
		}
		
		Deque<Pair> snake = new ArrayDeque<>();
		snake.add(new Pair(1,1));
		int count = 0;
		int ydirection = 0;
		int xdirection = 0;
		loop: while(true) {
			count++;
			
			
			Pair snakeLocation = snake.peek();
			int nextX = snakeLocation.x + x[xdirection];
			int nextY = snakeLocation.y + y[ydirection];
			if(nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
				break;
			}
			Iterator<Pair> iterator = snake.iterator();
			while(iterator.hasNext()) {
				Pair p = iterator.next();
				if(p.x == nextX && p.y == nextY) {
					break loop;
				}
			}
			
			if(board[nextY][nextX] == 1) { // 사과 발견
				snake.addFirst(new Pair(nextY, nextX));
				board[nextY][nextX] = 0;
			}
			else { //사과 없음
				snake.pollLast();
				snake.addFirst(new Pair(nextY, nextX));
			}
			
			String str = map.get(count);
			
			if(str == null) {
				
			}
			else if(str.equals("D")) { // 오른쪽

				ydirection++;
				xdirection++;
				if(ydirection >= 4 || xdirection >= 4) {
					ydirection = 0;
					xdirection = 0;
				}
			}
			else if(str.equals("L")) { // 왼쪽
				ydirection--;
				xdirection--;
				if(ydirection < 0 || xdirection <0) {
					ydirection = 3;
					xdirection = 3;
				}
			}
			
		}
		
		System.out.println(count);
		
	}
}
