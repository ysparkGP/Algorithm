package programmers;

/*
 * 2022 KAKAO BLIND RECRUITMENT
 * 자물쇠와 열쇠
 * 구현, 완전탐색
 */

public class Test_KAKAO_BLIND27 {
	
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		int N = lock.length;
		int padding = key.length-1;
		
		// lock을 고정시킨 채 key를 lock의 0,0부터 N-1,N-1까지 이동하면서 검사
		// 검사할 때는 90,180,270,360 회전시킨 key를 검사함
		// 이런 방식을 구현하기 위해서는 lock에 key를 위한 패딩을 주어야함
		
		// 1. lock padding
		lock = paddingLock(lock, key);
		
		// 2. 완전탐색
		boolean answer = false;
		loop: for(int i = 0; i<=lock.length - key.length; i++) {
			for(int j = 0; j<=lock.length - key.length; j++) {
				for(int d = 0; d<4; d++) {
					key = rotationKey(key);
					if(checkCondition(i, j, lock, key, N, padding)) {
						answer = true;
						break loop;
					}
				}
			}
		}
		System.out.println(answer);
	}
	public static int[][] paddingLock(int[][] lock, int[][] key){
		int padding = key.length-1;
		int newSize = lock.length + 2*padding;
		int[][] newLock = new int[newSize][newSize];
		
		for(int i = padding; i<newSize-padding; i++) {
			for(int j = padding; j<newSize-padding; j++) {
				newLock[i][j] = lock[i-padding][j-padding];
			}
		}
		
		return newLock;
	}
	
	public static int[][] rotationKey(int[][] key){ // 시계방향 90도 회전
		int[][] rotation = new int[key.length][key.length];
		
		for(int i = 0; i<key.length; i++) {
			for(int j = 0; j<key.length; j++) {
				rotation[i][j] = key[key.length - j - 1][i];
			}
		}
		return rotation;
	}
	public static boolean checkCondition(int i, int j, int[][] lock, int[][] key, int N, int padding) {
		
		int[][] copyLock = copyLock(lock);
		for(int y=0; y<key.length; y++) {
			for(int x=0; x<key.length; x++) {
				if(y+i>=padding && x+j>=padding && y+i<padding+N && x+j<padding+N) {
					if(copyLock[y+i][x+j] == 1 && key[y][x] == 1) return false;
					else if(copyLock[y+i][x+j] == 0 && key[y][x] == 1) copyLock[y+i][x+j] = 1;
				}
			}
		}
		
		int cnt = 0;
		for(int y = padding; y < padding+N; y++) {
			for(int x = padding; x < padding+N; x++) {
				if(copyLock[y][x] == 1) cnt++;
			}
		}
		if(cnt == N*N) return true;
		else return false;
	}
	
	public static int[][] copyLock(int[][] lock){
		int[][] copy = new int[lock.length][lock.length];
		for(int i = 0; i<copy.length; i++) {
			for(int j = 0; j<copy.length; j++) {
				copy[i][j] = lock[i][j];
			}
		}
		return copy;
	}

}
