package programmers;

/*
 * 구현, 시뮬레이션
 */

public class Dev_Matching2021_2 {

	public static void main(String[] args) {
		int n = 2;
		int m = 5;
		int x = 0;
		int y = 1;
		int[][] queries = {{3,1},{2,2},{1,1},{2,3},{0,1},{2,1}};
		
		long x1 = x;
		long y1 = y;
		long x2 = x;
		long y2 = y;
		
		boolean check = true;
		for(int i = queries.length-1; i>=0; i--) {
			int command = queries[i][0];
			int move = queries[i][1];
			
			// 이동하는 방향에 벽이 있다면 역순 확장
			// 이동하는 방향에 벽이 없다면 역순 이동
			// 역순 이동 후범위 밖으로 나간다면 결과 없음
			if(command == 0) { // 좌
				if(y1 == 0) { // 벽, 확장
					y2 = Math.min(m-1,y2 + move);
				}
				else { // 이동
					if(y1 + move >= m) {
						check = false;
						break;
					}
					y1 = Math.min(m-1,y1 + move);
					y2 = Math.min(m-1,y2 + move);
				}
			}
			else if(command == 1) { // 우
				if(y2 == m-1) {
					y1 = Math.max(0, y1 - move);
				}
				else {
					if(y2 - move < 0) {
						check = false;
						break;
					}
					y1 = Math.max(0,y1 - move);
					y2 = Math.max(0,y2 - move);
				}
			}
			else if(command == 2) { // 상
				if(x1 == 0) {
					x2 = Math.min(n-1, x2 + move);
				}
				else {
					if(x1 + move >= n) {
						check = false;
						break;
					}
					x1 = Math.min(n-1,x1 + move);
					x2 = Math.min(n-1,x2 + move);
				}
			}
			else { // 하
				if(x2 == n-1) {
					x1 = Math.max(0, x1 - move);
				}
				else {
					if(x2 - move < 0) {
						check = false;
						break;
					}
					x1 = Math.max(0,x1 - move);
					x2 = Math.max(0,x2 - move);
				}
			}
		}
		long result = 0L;
		if(!check) {
			System.out.println(0);
		}
		else {
			result = (long) (x2 - x1 + 1) * (y2 - y1 + 1);
			System.out.println(result);
		}
	}

}
