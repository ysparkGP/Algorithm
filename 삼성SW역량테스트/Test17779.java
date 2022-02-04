import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test17779 {

	static int[][] map;
	static int N;
	static int totalArea;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
	
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalArea += map[i][j];
			}
		}
		
		brute();
		System.out.println(min);
	}
	
	static void brute() {
		
		for(int x = 1; x<=N; x++) {
			for(int y = 1; y<=N; y++) {
				for(int d2 = 1; d2+y <= N; d2++) {
					for(int d1 = 1; d1+d2+x <= N; d1++) {
						if(1 > y-d1)
							continue;
						gary(x,y,d1,d2);
					}
				}
			}
		}
	}
	
	static void gary(int x, int y, int d1, int d2) {
		int[][] newMap = new int[N+1][N+1];
		int[] area = new int[5];
		//1. 경계선 그리기
		int direction1 = 0;
		while(direction1 <= d1) { //1번 경계
			newMap[x+direction1][y-direction1] = 5;
			direction1+=1;
		}
		int direction2 = 0;
		while(direction2 <= d2) { //2번 경계
			newMap[x+direction2][y+direction2] = 5;
			direction2+=1;
		}
		direction1 = 0;
		direction2 = 0;
		while(direction2 <= d2) {
			newMap[x+d1+direction2][y-d1+direction2] = 5;
			direction2+=1;
		}
		while(direction1 <= d1) {
			newMap[x+d2+direction1][y+d2-direction1] = 5;
			direction1+=1;
		}
		
		//2. 구역 그리기
		for(int x1 = 1; x1<x+d1; x1++) {
			for(int y1 = 1; y1<=y; y1++) {
				if(newMap[x1][y1] == 5)
					break;
				newMap[x1][y1] = 1;
				area[0] += map[x1][y1];
			}
		}
		
		for(int x1 = 1; x1<=x+d2; x1++) {
			for(int y1 = N; y1>y; y1--) {
				if(newMap[x1][y1] == 5)
					break;
				newMap[x1][y1] = 2;
				area[1] += map[x1][y1];
			}
		}
		
		for(int x1 = x+d1; x1<=N; x1++) {
			for(int y1 = 1; y1 < y-d1+d2; y1++) {
				if(newMap[x1][y1] == 5)
					break;
				newMap[x1][y1] = 3;
				area[2] += map[x1][y1];
			}
		}
		
		for(int x1 = x+d2+1; x1<=N; x1++) {
			for(int y1 = N; y1 >= y-d1+d2; y1--) {
				if(newMap[x1][y1] == 5)
					break;
				newMap[x1][y1] = 4;
				area[3] += map[x1][y1];
			}
		}
		for(int x1 = x; x1<=x+d1+d2; x1++) {
			for(int y1 = y-d1; y1<=y+d2; y1++) {
				if(newMap[x1][y1] == 0)
					newMap[x1][y1] = 5;
			}
		}
		area[4] = totalArea - (area[0] + area[1] + area[2] + area[3]);
		Arrays.sort(area);
		int result = area[4] - area[0];
		min = Math.min(result, min);
		/*
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				System.out.print(newMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------" + area[4] + " - " + area[0] + " = " + result);
		*/
	}
}
