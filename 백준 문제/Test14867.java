package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs + eqauls와 hashcode를 재정의함으로 인해 객체들의 수월한 비교
 */

public class Test14867 {
	static class Bottle{
		int A;
		int B;
		int count;
		Bottle(int A, int B, int count){
			this.A = A;
			this.B = B;
			this.count = count;
		}
		
		@Override
		public boolean equals(Object o) {
			//if(!(o instanceof Bottle)) return false;
			Bottle b = (Bottle) o;
			
			return (b.A == this.A && b.B == this.B);
		}
		
		@Override
		public int hashCode() { // String 클래스의 경우, 동일한 문자열에 대해서는 동일한 값을 반환한다.
			return (""+A+B).hashCode();
		}
	}
	static int A,B,a,b;
	static int bottleA, bottleB;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		bfs();
	}
	
	static void bfs() {
		
		//boolean[][] visit = new boolean[1001][1001];
		HashSet<Bottle> visit = new HashSet<>();
		Queue<Bottle> que = new LinkedList<>();
		
		que.add(new Bottle(0,0,0));
		visit.add(new Bottle(0,0,0));
		//1. A에 물을 채운다.
		//2. B에 물을 채운다.
		//3. A의 물을 버린다.
		//4. B의 물을 버린다.
		//5. A의 물을 B로 이동한다.
		//6. B의 물을 A로 이동한다.
		while(!que.isEmpty()) {
			Bottle bottle = que.poll();
			//System.out.println(bottle.A + ", " + bottle.B + ", " +bottle.count);
			if(bottle.A == a && bottle.B == b) {
				System.out.println(bottle.count);
				return;
			}
			if(bottle.A > A || bottle.B > B) continue;
			
			if(bottle.A < A) {
				Bottle fillA = new Bottle(A,bottle.B, bottle.count+1);
				if(!visit.contains(fillA)) {
					que.add(fillA);
					visit.add(fillA);
				}
			}
			
			if(bottle.B < B) {
				Bottle fillB = new Bottle(bottle.A, B, bottle.count+1);
				if(!visit.contains(fillB)) {
					que.add(fillB);
					visit.add(fillB);
				}	
			}
			
			
			if(bottle.A != 0) {
				Bottle emptyA = new Bottle(0,bottle.B, bottle.count+1);
				if(!visit.contains(emptyA)) {
					que.add(emptyA);
					visit.add(emptyA);
				}
			}
			
			if(bottle.B != 0) {
				Bottle emptyB = new Bottle(bottle.A, 0, bottle.count+1);
				if(!visit.contains(emptyB)) {
					que.add(emptyB);
					visit.add(emptyB);
				}
			}
			
			if(bottle.B + bottle.A <= B) {
				Bottle aMoveB = new Bottle(0, bottle.B+bottle.A, bottle.count+1);
				if(!visit.contains(aMoveB)) {
					que.add(aMoveB);
					visit.add(aMoveB);
				}	
			}
			else {
				Bottle aMoveB = new Bottle(bottle.A-(B-bottle.B),B, bottle.count+1);
				if(!visit.contains(aMoveB)) {
					que.add(aMoveB);
					visit.add(aMoveB);
				}	
			}
			
			if(bottle.A + bottle.B <= A) {
				Bottle bMoveA = new Bottle(bottle.A+bottle.B, 0, bottle.count+1);
				if(!visit.contains(bMoveA)) {
					que.add(bMoveA);
					visit.add(bMoveA);
				}
			}
			else {
				Bottle bMoveA = new Bottle(A, bottle.B-(A-bottle.A), bottle.count+1);
				if(!visit.contains(bMoveA)) {
					que.add(bMoveA);
					visit.add(bMoveA);
				}
			}
			
		}
		System.out.println(-1);
		
	}
	/*
	static Bottle fill(Bottle originalBottle, int x) {
		Bottle bottle = new Bottle(originalBottle.A, originalBottle.B, originalBottle.count+1);
		if(x == 1) {
			bottle.A = A;
		}
		else {
			bottle.B = B;
		}
		
		return bottle;
	}
	static Bottle empty(Bottle originalBottle, int x) {
		Bottle bottle = new Bottle(originalBottle.A, originalBottle.B, originalBottle.count+1);
		
		if(x == 1) {
			bottle.A = 0;
		}
		else {
			bottle.B = 0;
		}
		
		return bottle;
		
	}
	static Bottle move(Bottle originalBottle, int x) {
		Bottle bottle = new Bottle(originalBottle.A, originalBottle.B, originalBottle.count+1);
		
		if(x == 1) { // A -> B
			if(bottle.B + bottle.A > B) { // B물통이 넘치면
				bottle.A = bottle.A - (B - bottle.B);
				bottle.B = B;
			}
			else {
				bottle.B = bottle.B + bottle.A;
				bottle.A = 0;
			}
		}
		else { // B -> A
			if(bottle.A + bottle.B > A) {
				bottle.B = bottle.B - (A - bottle.A);
				bottle.A = A;
			}
			else {
				bottle.A = bottle.A + bottle.B;
				bottle.B = 0;
			}
		}
		
		return bottle;
	}*/

}
