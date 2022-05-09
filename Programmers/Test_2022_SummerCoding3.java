package programmers;

import java.util.HashMap;
import java.util.Map;

/*
 * 프로그래머스 2022 썸머 코딩 3번
 */
public class Test_2022_SummerCoding3 {

	static final char[][] keyBoard = {
			{'1','2','3','4','5','6','7','8','9','0'},
			{'Q','W','E','R','T','Y','U','I','O','P'}
	};
	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	public static void main(String[] args) {
		// 1. 맨하탄 거리기준 (수평거리+수직거리)
		// 2. 수평거리 기준
		// 3. 
		
		String line = "64E2";
		Pair left = new Pair(1,0);
		Pair right = new Pair(1,9);
		
		Map<Character, Pair> map = new HashMap<>();
		init(map);
		int[] result = new int[line.length()];
		for(int i = 0; i<line.length(); i++) {
			char order = line.charAt(i);
			Pair target = map.get(order);
			// 1. 맨하탄 거리
			int leftManDis = manDis(left, target);
			int rightManDis = manDis(right, target);
			if(leftManDis > rightManDis) {
				result[i] = 1;
				right = new Pair(target.y, target.x);
			}
			else if(leftManDis < rightManDis) {
				result[i] = 0;
				left = new Pair(target.y, target.x);
			}
			else {
				// 2. 수평 거리
				int leftHorizonDis = horizonDis(left, target);
				int rightHorizonDis = horizonDis(right, target);
				if(leftHorizonDis > rightHorizonDis) {
					result[i] = 1;
					right = new Pair(target.y, target.x);
				}
				else if(leftHorizonDis < rightHorizonDis) {
					result[i] = 0;
					left = new Pair(target.y, target.x);
				}
				else {
					if(target.x % 10 >= 5) {
						result[i] = 1;
						right = new Pair(target.y, target.x);
					}
					else {
						result[i] = 0;
						left = new Pair(target.y, target.x);
					}
				}
			}
		}
		
	}
	static void init(Map<Character, Pair> map) {
		for(int i = 0; i<keyBoard.length; i++) {
			for(int j = 0; j<keyBoard[i].length; j++) {
				map.put(keyBoard[i][j], new Pair(i,j));
			}
		}
	}
	static int manDis(Pair hand, Pair target) {
		int dis = Math.abs(hand.y - target.y) + Math.abs(hand.x - target.x);
		return dis;
	}
	static int horizonDis(Pair hand, Pair target) {
		int dis = Math.abs(hand.x - target.x);
		return dis;
	}

}
