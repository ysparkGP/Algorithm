package numberOfCounselor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 그리디 버전
 * 각 상담원 유형 별 인원 배치로 대기 시간을 최대로 줄이는 문제
 * 상담원 유형 별 인원 배치표를 만들어서 대기 시간을 구한다(이중 배열)
 * 위에서 만든 그리디 표를 참조하여 그리디 알고리즘을 통하여 최소 대기 시간을 구한다
 */

class Time{
	int start;
	int counselTime;
	Time(int start, int runTime){
		this.start = start;
		this.counselTime = runTime;
	}
}

public class numberOfCounselor2 {

	static int[][] timeOfCounselling;
	public static void main(String[] args) {
//		int k = 3;  // 상담 유형 갯수
//		int n = 5;  // 멘토 인원
//		int[][] reqs= {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}}; // 상담 정보
		
		int k = 2;  // 상담 유형 갯수
		int n = 3;  // 멘토 인원
		int[][] reqs= {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
		
		timeOfCounselling = new int[k+1][n+1];
		List<List<Time>> typeOfCounselList = new ArrayList<>();
		for(int i = 0; i<=k; i++) {
			// 상담 시작 시간, 상담 소요 시간
			typeOfCounselList.add(new ArrayList<>());
		}
		
		// 상담 타입별로 정보 저장
		for(int i = 0; i<reqs.length; i++) {
			typeOfCounselList.get(reqs[i][2]).add(new Time(reqs[i][0], reqs[i][1]));
		}
		
		// 그리디 표 생성
		for(int type = 1; type<=k; type++) {
			// 상담할 사람이 없으면 skip
			if(typeOfCounselList.get(type).size() == 0) continue;
			for(int cnt = 1; cnt<=n; cnt++) {
				int responTime = calcScore(typeOfCounselList.get(type), cnt);
				timeOfCounselling[type][cnt] = responTime;
			}
		}
		
		int[] counselors = new int[k+1];
		for(int type = 1; type<=k; type++) {
			counselors[type]++;
			n--;
		}
		
		// 그리디 알고리즘
		while(n-- > 0) {
			
			int reduceScore = 0;
			int typeCounselor = 0;
			for(int type = 1; type <= k; type++) {
				int score = Math.abs(timeOfCounselling[type][counselors[type]] - timeOfCounselling[type][counselors[type]+1]);
				
				if(reduceScore < score) {
					reduceScore = score;
					typeCounselor = type;
				}
			}
			
			counselors[typeCounselor]++;
			
			// 상담원을 추가해도 시간이 줄지 않을 때 조기종료
			if(reduceScore == 0) break;
		}
		
		int answer = 0;
		for(int type=1; type<=k; type++) {
			if(typeOfCounselList.get(type).size() == 0) continue;
			
			answer += timeOfCounselling[type][counselors[type]];
		}
		
		System.out.println(answer);
	}
	
	public static int calcScore(List<Time> counselList, int limit) {
		int responTime = 0;
		
		PriorityQueue<Integer> que = new PriorityQueue<>();
		
		for(Time t : counselList) {
			// 상담 안하는 멘토가 있을 때
			if(que.size() < limit) {
				// 상담 끝나는 시간을 우선순위 큐에 적재
				que.add(t.start + t.counselTime);
			}
			// 상담 안하는 멘토가 없을 때
			else {
				int endTime = que.poll();
				// 대기하는 시간 계산
				if(endTime > t.start) {
					responTime += (endTime - t.start);
					que.add(endTime + t.counselTime);
				}
				else {
					que.add(t.start + t.counselTime);
				}
			}
		}
		return responTime;
	}

}
