package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * CCW 알고리즘 (외적을 이용해서 점 3개의 방향을 찾는다.)
 * 두 벡터의 외적은 두 벡터의 수직벡터이다.
 * 이 수직벡터로 두 벡터의 방향을 찾을 수 있다.
 * => 오른손법칙
 * 음수면 시계, 양수면 반시계, 일직선이면 0
 */

public class Test11758 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] point = new int[3][2];
		for(int i = 0; i<3; i++) {
			StringTokenizer st  = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<2; j++) {
				point[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] line = new int[2][2];
		line[0][0] = point[1][0] - point[0][0];
		line[0][1] = point[1][1] - point[0][1];
		line[1][0] = point[2][0] - point[0][0];
		line[1][1] = point[2][1] - point[0][1];
		
		/*
		 * AB = (x1,y1)
		 * AC = (x2,y2)
		 * => x1*y2 - y1*x2
		 */
		int result = (line[0][0] * line[1][1]) - (line[1][0] * line[0][1]);
		if(result < 0) System.out.println(-1);
		else if(result > 0) System.out.println(1);
		else System.out.println(0);
		
	}

}
