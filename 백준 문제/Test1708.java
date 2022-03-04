package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 볼록 껍질 찾기 (Graham Scan)
 * Convex Hull은 반시계 방향으로 보면 좌회전 하는 규칙이 있다.
 */

public class Test1708 {

	static class Point{
		long x;
		long y;
		Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Point> points = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			points.add(new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
		}
		System.out.println(grahamScan(points));
	}
	
	static int grahamScan(ArrayList<Point> points) {
		
		// 1. y축에 대해서 가장 낮은(또는 높은)점을 찾는다.
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if(p1.y == p2.y) {
					if(p1.x > p2.x ) return 1;
					else return -1;
				}
				else {
					if(p1.y > p2.y)
						return 1;
					else return -1;
				}
			}
		});
		
		// 2. 위에서 찾은 기준점에 대해서 반시계 방향으로 정렬한다.
		// ccw 알고리즘으로 양수면 반시계방향
		Point standardPoint = points.get(0);
		Collections.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				if(ccw(standardPoint, p1, p2) > 0) {
					return -1;
				}
				else if(ccw(standardPoint, p1, p2) == 0) { // 일직선상 => 거리가 기준점에 대해서 가까운순으로 정렬
					if(distance(standardPoint, p1) < distance(standardPoint, p2))
						return -1;
					else return 1;
					
				}
				else {
					return 1;
				}
			}
		});
		
		// 3. Graham Scan
		Stack<Point> stack = new Stack<>();
		stack.push(points.get(0));
		for(int i = 1; i<N; i++) {
			// 순서대로 스택에 들어와 기존에 있었던 점들을, 새로 들어올 점에 대해서 반시계방향인지 판별
			// 거리가 기준점에 가까운 순으로 정렬한 이유
			while(stack.size() >=2 && ccw(stack.get(stack.size()-2), stack.get(stack.size()-1), points.get(i)) <= 0)
				stack.pop();
			stack.add(points.get(i));
		}
		
		return stack.size();
	}
	
	static long distance(Point p1, Point p2) {
		return (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
	}
	
	static long ccw(Point p1, Point p2, Point p3) {
		long result = (long)p1.x*p2.y + (long)p2.x*p3.y + (long)p3.x*p1.y;
		result -= (long)p2.x*p1.y + (long)p3.x*p2.y + (long)p1.x*p3.y;
		
		return result;
		
	}

}
