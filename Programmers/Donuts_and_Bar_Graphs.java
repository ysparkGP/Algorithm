package programmers_problems;

import java.util.HashMap;
import java.util.Map;

/*
* 도형을 그려보면 패턴이 보임
* 간선 -> 출발번호에서 도착번호로 이어진 간선
* 브릿지정점 : 출발번호가 2개 이상이고 도착번호가 없음
* 막대 모양 : 출발번호가 없고 도착번호가 1개 이상 
* 8자 모양 : 출발번호가 2개, 도착번호가 2개 이상
* 도넛 모양 : 나머지
*/

class Pair{
	int inCnt;
	int outCnt;
	Pair(int inCnt, int outCnt){
		this.inCnt = inCnt;
		this.outCnt = outCnt;
	}
}

public class Donuts_and_Bar_Graphs {
	
	public static void main(String[] args) {
		
//		int[][] edge = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
		int[][] edge = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
		int[] answer = {0,0,0,0};
		
		Map<Integer, Pair> map = new HashMap<>();
		for(int i = 0; i<edge.length; i++) {
			if (!map.containsKey(edge[i][0])) {
				map.put(edge[i][0], new Pair(0,0));
			}
			if (!map.containsKey(edge[i][1])) {
				map.put(edge[i][1], new Pair(0,0));
			}
			
			map.get(edge[i][0]).outCnt++;
			map.get(edge[i][1]).inCnt++;
		}
		
		map.forEach((k,v) -> {
			if(v.outCnt >= 2 && v.inCnt == 0) answer[0] = k;
			if(v.outCnt == 0 && v.inCnt >= 1) answer[2] += 1;
			if(v.outCnt >= 2 && v.inCnt >= 2) answer[3] += 1;
		});
		answer[1] = map.get(answer[0]).outCnt - (answer[2] + answer[3]);
		
		for(int i = 0; i<answer.length; i++)
			System.out.print(answer[i] + " ");
	}
	
}
