package programmers;

/*
 * 2020 KAKAO BLIND RECRUITMENT
 * 외벽 점검
 * 순열, 구현, 완전 탐색
 */

public class Test_KAKAO_BLIND32 {

	static int[] lineWeak;
	static int answer;
	static int originalSize;
	public static void main(String[] args) {
		int n = 12;
		int[] weak = {1,3,4,9,10};
		int[] dist = {3, 5, 7};
		
		// 1. 원 형태의 취약 지점들을 직선으로 매핑
		weakMapping(weak, n);
		// 2. 순열로 친구들을 선발(1명부터 n명까지)
		// 3. 뽑힌 친구들을 취약 지점들을 매꿀 수 있는지 검사
		answer = -1;
		originalSize = weak.length;
		for(int depth = 1; depth<=originalSize; depth++) {
			int[] list = new int[depth];
			boolean[] visit = new boolean[dist.length];
			if(answer == -1) permutation(lineWeak, dist, visit, 0, depth, list);
		}
		
		System.out.println(answer);
	}
	
	// 1
	static void weakMapping(int[] weak, int n) {
		lineWeak = new int[2*weak.length - 1];
		for(int i = 0; i<weak.length; i++) {
			lineWeak[i] = weak[i];
		}
		for(int i = 0; i<weak.length-1; i++) {
			lineWeak[i+weak.length] = weak[i] + n;
		}
	}
	
	// 2
	static void permutation(int[] weak, int[] dist, boolean[] visit, int index, int depth, int[] list) {
		if(answer != -1) return;
		if(index >= depth) {
			if(check(weak, dist, list)) {
				answer = depth;
			}
			return;
		}
		
		for(int i = 0; i<dist.length; i++) {
			if(!visit[i]) {
				visit[i] = true;
				list[index] = i;
				permutation(weak, dist, visit, index+1, depth, list);
				visit[i] = false;
			}
		}
	}
	
	// 3
	static boolean check(int[] weak, int[] dist, int[] list) {
		for(int i = 0; i<originalSize; i++) {
			int start = i;
			int cnt = 0;
			for(int j = 0; j<list.length; j++) {
				for(int next = start; next < start+originalSize; next++) {
					if(weak[next] - weak[start] <= dist[list[j]]) {
						cnt+=1;
					}
					else {
						start = next;
						break;
					}
					if(cnt == originalSize) return true;
				}
			}
		}
		return false;
	}

}
