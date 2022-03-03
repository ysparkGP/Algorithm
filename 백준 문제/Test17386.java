package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * CCW 알고리즘을 응용한 선분 교차 판별
 */

public class Test17386 {
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point p) {
			if(this.x == p.x)
				return this.y - p.y;
			
			return this.x - p.x;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Point> points =  new ArrayList<>();
		for(int i = 0; i<2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			points.add(new Point(x1,y1));
			points.add(new Point(x2,y2));
		}
		System.out.println(lineCross(points.get(0), points.get(1), points.get(2), points.get(3)));
		
	}

	static int ccw(Point p1, Point p2, Point p3) {
		long crossProduct = (long)p1.x*p2.y + (long)p2.x*p3.y + (long)p3.x*p1.y;
		crossProduct -= (long)p2.x*p1.y + (long)p3.x*p2.y + (long)p1.x*p3.y;
		
		if(crossProduct > 0)  return 1;
		else if(crossProduct == 0) return 0;
		else return -1;
	}
	
	static int lineCross(Point p1, Point p2, Point p3, Point p4) {
		int p123 = ccw(p1,p2,p3);
		int p124 = ccw(p1,p2,p4);
		int p341 = ccw(p3,p4,p1);
		int p342 = ccw(p3,p4,p2);
	
		if(p123 * p124 < 0 && p341 * p342 < 0) return 1; // 두 라인 교차
		else if(p123*p124 == 0 && p341*p342 == 0) { // 두 라인 평행
			Point A,B,C,D;
			if(p1.compareTo(p2) > 0) {
				A = p2;
				B = p1;
			}
			else {
				A = p1;
				B = p2;
			}
			
			if(p3.compareTo(p4) > 0) {
				C = p4;
				D = p3;
			}
			else {
				C = p3;
				D = p4;
			}
			
			if(D.compareTo(A) >= 0 && B.compareTo(C) >= 0 ) return 1;
			else return 0;
		}
		else if(p123*p124 == 0 || p341*p342 == 0) { // 세 점에 대해서 평행일 때, 한 직선에 한 점이 포함되느냐 판별
			if(p123*p124 == 0) {
				if(p341 == p342) return 0;
				else return 1;
			}
			else {
				if(p123 == p124) return 0;
				else return 1;
			}
		}
		else return 0;
	}
	
}
