package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * CCW 알고리즘으로 다각형 면적 구하기(사선공식 이용)
 */

public class Test2166 {

	static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Point> points = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		long result = 0;
		for(int i = 1; i<N-1; i++) {
			result += ccw(points.get(0), points.get(i), points.get(i+1));
		}
		/*StringBuilder sb = new StringBuilder();
		long temp = (long)Math.abs((double)Math.round(result*10));
		sb.append(temp/10);
		sb.append(".");
		sb.append(temp%10);*/
		//System.out.println(sb.toString());
		System.out.println(String.format("%.1f",Math.abs(result)/2.0));
	}
	
	static long ccw(Point p1, Point p2, Point p3) {
		long temp = (long)p1.x*p2.y + (long)p2.x*p3.y + (long)p3.x*p1.y;
		temp -= (long)p2.x*p1.y + (long)p3.x*p2.y + (long)p1.x*p3.y;
		
		return temp;
	}

}
