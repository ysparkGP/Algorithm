package Programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap2 {
	static int[][] jobs = {{1,3},{0,8},{0,9}};
	
	public static void main(String[] args) {
		
		int answer = 0;
		Arrays.sort(jobs, new Comparator<int[]>() {
			@Override
			public int compare(int[] j1, int[] j2) {
				return j1[0] - j2[0];
			}
		});
		
		
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() { //실행시간을 기준으로 정렬
			@Override
			public int compare(int[] j1, int[] j2) {
				return j1[1] - j2[1];
			}
		});
		
		int endTime = 0;
		int jobIndex = 0;
		int count = 0;
		while(count < jobs.length) {
			
			while(jobIndex < jobs.length && jobs[jobIndex][0] <= endTime) { //작업 큐에 들어갈 작업들은 방금 작업이 끝난 시점 안에 있는 작업들
				pQueue.add(jobs[jobIndex]);
				jobIndex++;
			}
			if(jobIndex < jobs.length && pQueue.isEmpty()) { //작업 큐가 비었을 때(작업 사이에 공백 시간이 존재한다면)
				endTime = jobs[jobIndex][0];
			}
			else { //작업 큐에 작업들이 쌓였을 때
				int[] nowJob = pQueue.poll();
				count++;
				answer += (endTime - nowJob[0]) + nowJob[1];
				endTime += nowJob[1];
			}	
		}
		answer /= jobs.length;
		System.out.println(answer);
	}
	

}
