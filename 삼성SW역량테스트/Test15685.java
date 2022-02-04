import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class DragonCurve{
	int x;
	int y;
	int d;
	int g;
	DragonCurve(int x, int y, int d, int g){
		this.x = x;
		this.y = y;
		this.d = d;
		this.g = g;
	}
}

class Pairs{
	int x;
	int y;
	Pairs(int x, int y){
		this.x = x;
		this.y = y;
	}
}
public class Test15685 {

	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	static List<DragonCurve> dragonCurves;
	static boolean[][] map = new boolean[101][101];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		dragonCurves = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x=0,y=0,d=0,g=0;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
		
			dragonCurves.add(new DragonCurve(x,y,d,g));
		}
		for(int i = 0; i<dragonCurves.size(); i++) {
			List<Pairs> newList = new ArrayList<>();
			int x = dragonCurves.get(i).x;
			int y = dragonCurves.get(i).y;
			int d = dragonCurves.get(i).d;
			newList.add(new Pairs(x,y));
			map[y][x] = true;
			int nx = x + dx[d];
			int ny = y + dy[d];
			newList.add(new Pairs(nx,ny));
			map[ny][nx] = true;
			
			for(int j = 1; j<=dragonCurves.get(i).g; j++) {
				for(int k = newList.size()-2; k >=0; k--) {
					
					int directionX = newList.get(k+1).x - newList.get(k).x;
					int directionY = newList.get(k+1).y - newList.get(k).y;
					int nextX = 0;
					int nextY = 0;
					if(directionX == dx[0] && directionY == dy[0]) {
						nextX = newList.get(newList.size()-1).x + dx[1];
						nextY = newList.get(newList.size()-1).y + dy[1];
					}
					else if(directionX == dx[1] && directionY == dy[1]) {
						nextX = newList.get(newList.size()-1).x + dx[2];
						nextY = newList.get(newList.size()-1).y + dy[2];
					}
					else if(directionX == dx[2] && directionY == dy[2]) {
						nextX = newList.get(newList.size()-1).x + dx[3];
						nextY = newList.get(newList.size()-1).y + dy[3];
					}
					else if(directionX == dx[3] && directionY == dy[3]) {
						nextX = newList.get(newList.size()-1).x + dx[0];
						nextY = newList.get(newList.size()-1).y + dy[0];
					}
					newList.add(new Pairs(nextX,nextY));
					map[nextY][nextX] = true;
				}
			}
			
		}
		int answer = 0;
		for(int y = 0; y<100; y++) {
			for(int x = 0; x<100; x++) {
				if(map[y][x] && map[y][x+1] && map[y+1][x] && map[y+1][x+1]) {
					answer += 1;
				}
			}
		}
		System.out.println(answer);
	}	

}
