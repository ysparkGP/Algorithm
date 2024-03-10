package numberOfCounselor;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class numberOfCounselor {

	static int[] mento;
	static int minScore = 999999999;
	public static void main(String[] args) {
		int k = 3;  // 상담 유형 갯수
		int n = 5;  // 멘토 인원
		int[][] reqs= {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}}; // 상담 정보
		
//		int k = 2;  // 상담 유형 갯수
//		int n = 3;  // 멘토 인원
//		int[][] reqs= {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
		
		// 멘토는 상담 유형 별 최소 한 명 이상
		mento = new int[n+1];
		for(int i = 1; i<=k; i++) {
			mento[i] += 1;
			n--;
		}
		
		combination(n,k, reqs, 0);
	}
	
	static void combination(int n, int k, int[][] reqs, int a) {
		if(n <= 0) {
			int score = calScore(n, k, reqs);
			minScore = (minScore > score)? score : minScore;
			return;
		}
		
		for(int i = a; i<k; i++) {
			mento[i+1]+=1;
			combination(n-1,k, reqs, i);
			mento[i+1]-=1;
		}
	}
	
	static int calScore(int n, int k, int[][] reqs) {
		int score = 0;
		
		for(int i = 1; i<=k; i++) {
			Queue<int[]> que = new LinkedList<>();
			for(int j = 0; j<reqs.length; j++){
				if(reqs[j][2] == i) {
					que.add(reqs[j]);
				}
			}
			
			int mentoK = mento[i];
			Queue<Integer> priQue = new PriorityQueue<>();
			
			while(!que.isEmpty()) {
				int[] req = que.poll();
				
				int completeTime = req[0] + req[1];
				if (priQue.size() >= mentoK) {
					completeTime = priQue.poll();
					if (completeTime > req[0]) {
						score += (completeTime - req[0]);
						priQue.add(completeTime + req[1]);
					}
					else {
						priQue.add(req[0] + req[1]);
					}
				}
				else {
					priQue.add(req[0] + req[1]);
				}
			}
		}
		
		return score;
	}
}
