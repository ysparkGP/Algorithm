package Programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class Pair{
	int depth;
	int value;
	Pair(int depth, int value){
		this.depth = depth;
		this.value = value;
	}
}

public class Dp1 {

	public static void main(String[] args) throws IOException{
		long N = 2;
		int number = 11;
		
		HashMap<Integer, ArrayList<Long>> dp = new HashMap<>();
		ArrayList<Long> li = new ArrayList<>();
		li.add(N);
		dp.put(1, li);
		
		int answer = -1;
		for(int depth = 2; depth < 9; depth++) {
			
			ArrayList<Long> list = new ArrayList<>();
			for(int i = 1; i < depth; i++) {
				long ex = exception(N, depth);
				if(ex == number) {
					System.out.println(depth);
					return;
				}
				list.add(ex);
				
				ArrayList<Long> li1 = dp.get(i);
				ArrayList<Long> li2 = dp.get(depth-i);
				
				for(int j = 0; j<li1.size(); j++) {
					for(int k = 0; k<li2.size(); k++) {
						
						long d = div(li1.get(j), li2.get(k));
						if(d == number) {
							System.out.println(depth);
							return;
						}
						if(d != 0 &&!list.contains(d)) {
							list.add(d);
						}
						
						long p = plus(li1.get(j), li2.get(k));
						if(p == number) {
							System.out.println(depth);
							return;
						}
						if(p != 0 && !list.contains(p)) {
							list.add(p);
						}
						
						long mi = minus(li1.get(j), li2.get(k));
						if(mi == number) {
							System.out.println(depth);
							return;
						}
						if(mi != 0 && !list.contains(mi)) {
							list.add(mi);
						}
						
						long mul = multi(li1.get(j), li2.get(k));
						if(mul == number) {
							System.out.println(depth);
							return;
						}
						if(mul != 0 && !list.contains(mul)) {
							list.add(mul);
						}
					}
				}
				dp.put(depth, list);
				
			}
		}
		
		
		
	}
	static long exception(long N, int depth) {
		String temp = "";
		long result = 0;
		for(int i = 0; i<depth; i++)
			temp += Long.toString(N);
		
		result = Long.parseLong(temp);
		return result;
	}
	
	static long div(long front, long end) {
		long result = front/end;
		
		return result;
	}
	static long plus(long front, long end) {
		long result = front + end;
		
		return result;
	}
	static long minus(long front, long end) {
		long result = front - end;
		
		return result;
	}
	static long multi(long front, long end) {
		long result = front * end;
		
		return 0;
	}

}
