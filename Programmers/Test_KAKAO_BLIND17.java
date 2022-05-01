package programmers;

import java.util.Arrays;

/*
 * 2018 KAKAO BLIND RECRUITMENT
 * [1차] 프렌즈블록
 * 구현
 */

public class Test_KAKAO_BLIND17 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	// 오른쪽, 아래, 오른쪽-아래
	static int[] dy = {0,1,1,0};
	static int[] dx = {1,0,1,0};
	public static void main(String[] args) {
		int m = 4; // 높이
		int n = 5; // 폭
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		char[][] map = new char[m][n];
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}
		int answer = 0;
		while(true) {
			int value = remove(map, m, n);
			if(value == 0) break;
			answer += value;
		}
		System.out.println(answer);
	}
	
	// 삭제 작업
	static int remove(char[][] map, int m, int n) {
		int answer = 0;
		
		boolean[][] willRemove = new boolean[m][n];
		
		for(int y = 0; y<m-1; y++) {
			for(int x = 0; x<n-1; x++) {
				if(map[y][x] != 'X') {
					boolean equal = true;
					for(int d = 0; d<3; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						if(map[y][x] != map[ny][nx]) {
							equal = false;
							break;
						}
					}
					if(equal) {
						for(int d = 0; d<4; d++) {
							int ny = y + dy[d];
							int nx = x + dx[d];
							if(!willRemove[ny][nx]) {
								answer+=1;
								willRemove[ny][nx] = true;
							}
						}
					}
				}
			}
		}
		
		map = blocksMove(map, m, n, willRemove);
		return answer;
	}
	// 블록 이동 작업
	static char[][] blocksMove(char[][] map, int m, int n, boolean[][] willRemove) {
		char[][] newMap = copyMap(map, m, n);
		
		for(int i = 0; i<m; i++) Arrays.fill(map[i], 'X');
		
		for(int x = 0; x<n; x++) {
			int originalIndex = m-1;
			for(int y = m-1; y>=0; y--) {
				if(map[y][x] == 'X') {
					while(originalIndex >= 0 && willRemove[originalIndex][x]) {
						originalIndex--;
					}
					if(originalIndex == -1) {
						newMap[y][x] = 'X';
						continue;
					}
					map[y][x] = newMap[originalIndex][x];
					willRemove[originalIndex][x] = true;
				}
			}
		}
		
		return newMap;
	}
	
	static char[][] copyMap(char[][] map, int m, int n){
		char[][] copyMap = new char[m][n];
		for(int i = 0; i<m; i++) {
			for(int j = 0; j<n; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		return copyMap;
	}

}
