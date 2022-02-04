import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test17144 {
	static int R,C,T; 
	static int[] checkX = {0,-1,0,1};
	static int[] checkY = {-1,0,1,0};
	//시계
	static int[] forwardX = {1,0,-1,0};
	static int[] forwardY = {0,1,0,-1};
	//반시계
	static int[] backwardX = {1,0,-1,0};
	static int[] backwardY = {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R  = Integer.parseInt(st.nextToken());
		C  = Integer.parseInt(st.nextToken());
		T  = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		int[][] airCleaner = new int[2][2];
		int row = 0;
		int col = 0;
		for(int i = 0; i<R; i++) {
			st =  new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					airCleaner[row][col++] = i;
					airCleaner[row++][col--] = j;
				}
			}
		}
		int[][] resultMap = new int[R][C];
		for(int i = 0; i<T; i++) {
			map = diffusion(map);
			int index = 0;
			for(int j = 0; j<R; j++) {
				
			}
			map = airClear(map, airCleaner);
		}
			
		int result = 0;
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(map[i][j] > 0)
					result+=map[i][j];
			}
		}
		System.out.println(result);
	}
	
	static int[][] diffusion(int[][] map) { //step 1
		int[][] tempMap = new int[R][C];
		int[][] checkMap = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				if(map[i][j] == -1)
					continue;
				for(int check = 0; check<4; check++) {
					int y = i + checkY[check];
					int x = j + checkX[check];
					if(boundCheck(y,x)) {
						if(map[y][x] != -1 && map[i][j] > 0) {
							tempMap[y][x] += (map[i][j] / 5);
							checkMap[i][j] += 1;
						}
					}
				}
			}
		}
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				map[i][j] -= (map[i][j]/5) * checkMap[i][j];
			}
		}
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				map[i][j] += tempMap[i][j];
			}
		}
		
		return map;
	}
	
	static int[][] airClear(int[][]map, int[][] airCleaner) { //step 2
		int[][] newMap = copyMap(map);
		
		int[] airTop = airCleaner[0];			
		int airTopY = airTop[0];
		int airTopX = airTop[1];
		
		//top(반시계방향)
		int index = 0;
		while(true) {
			airTopY += backwardY[index];
			airTopX += backwardX[index];
			if(airTopX >= C) {
				airTopY -= backwardY[index];
				airTopX -= backwardX[index];
				index++;
			}
			else if(airTopY < 0) {
				airTopY -= backwardY[index];
				airTopX -= backwardX[index];
				index++;
			}
			else if(airTopX < 0) {
				airTopY -= backwardY[index];
				airTopX -= backwardX[index];
				index++;
			}
			else if(airTopY > airTop[0]) {
				newMap[airTopY-backwardY[index]][airTopX-backwardX[index]] = -1;
				break;
			}
			else {
				newMap[airTopY][airTopX] = map[airTopY-backwardY[index]][airTopX-backwardX[index]];
				if(newMap[airTopY][airTopX] < 0)
					newMap[airTopY][airTopX] = 0;
			}
			
		}
		
		//bottom(시계방향)
		index = 0;
		int[] airBottom = airCleaner[1];
		int airBottomY = airBottom[0];
		int airBottomX = airBottom[1];
		while(true) {
			airBottomY += forwardY[index];
			airBottomX += forwardX[index];
			if(airBottomX >= C) {
				airBottomY -= forwardY[index];
				airBottomX -= forwardX[index];
				index++;
			}
			else if(airBottomY >= R) {
				airBottomY -= forwardY[index];
				airBottomX -= forwardX[index];
				index++;
			}
			else if(airBottomX < 0) {
				airBottomY -= forwardY[index];
				airBottomX -= forwardX[index];
				index++;
			}
			else if(airBottomY < airBottom[0]) {
				newMap[airBottomY-forwardY[index]][airBottomX-forwardX[index]] = -1;
				break;
			}
			else {
				newMap[airBottomY][airBottomX] = map[airBottomY-forwardY[index]][airBottomX-forwardX[index]];	
				if(newMap[airBottomY][airBottomX] < 0)
					newMap[airBottomY][airBottomX] = 0;
			}
		}
		
		return newMap;
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || y >= R || x < 0 || x >= C)
			return false;
		return true;
	}
	
	static int[][] copyMap(int[][] map){
		int[][] newMap = new int[R][C];
		
		for(int i = 0; i<R; i++) {
			for(int j = 0; j<C; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}

}
