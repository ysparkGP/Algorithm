package Programmers;

import java.util.Arrays;

public class Binary_Search {

	static int[] rocks = {3,6,9,10,14,17};
	static int totalDistance;
	public static void main(String[] args) {
		totalDistance = 23;
		int n = 2;
		int answer = 0;
		Arrays.sort(rocks);
		
		int start = 0;
		int end = totalDistance;
		
		
		// best
		while(start <= end) {
			int mid = (start + end) / 2;
			int hits = 0;
			int cur = 0;
			for(int i = 0; i<rocks.length; i++) {
				
				if(rocks[i] - cur < mid) {
					hits++;
				}
				else {
					cur = rocks[i];
				}
			}
			
			if(n < hits) { // 깬 돌이 n개보다 많을 때,
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		System.out.println(end);
		
		/* my solution
		while(start <= end) {
			int mid = (start+end)/2;
			System.out.println(start + ", " + end);
			
			if(delete(mid,n)) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		
		System.out.println(end);*/
	}
	
	// my solution
	static boolean delete(int distance, int n) {
		
		int[] cloneRocks = new int[rocks.length+2];

		
		cloneRocks[0] = 0;
		cloneRocks[rocks.length+1] = totalDistance;
		for(int i = 1; i< rocks.length+1; i++)
			cloneRocks[i] = rocks[i-1];
		
		for(int i = 1; i<cloneRocks.length-1; i++) {
			int frontDistance = cloneRocks[i] - cloneRocks[i-1];
			int afterDistance = cloneRocks[i+1] - cloneRocks[i];
			
			if(frontDistance < distance && afterDistance >= distance) {
				/*if(i == 1) {
					n--;
					cloneRocks[i] = cloneRocks[i-1];
				}
				else {
					if(cloneRocks[i-2] - cloneRocks[])
				}*/
				n--;
				cloneRocks[i] = cloneRocks[i-1];
			}
			
			else if(afterDistance < distance && frontDistance >= distance) {
				if(i == cloneRocks.length-2 ) {
					n--;
					cloneRocks[i] = cloneRocks[i-1];
				}
				else {
					if(cloneRocks[i+2] - cloneRocks[i+1] < distance) {
						continue;
					}
				}
			}
			
			else if(afterDistance < distance && frontDistance < distance) {
				n--;
				cloneRocks[i] = cloneRocks[i-1];
			}
			
			if(n < 0) {
				return false;
			}
		}
		
		
		return true;
		
	}
}
