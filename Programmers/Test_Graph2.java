package programmers;

import java.util.Arrays;

/*
 * 플루이드 와샬
 * 모든 정점을 기준으로 방향그래프의 연결된 간선들로 어느 정점까지 갈 수 있느냐를 체크
 */

public class Test_Graph2 {

	public static void main(String[] args) {
		int n = 5;
		int[][] results = {
				{4,3},{4,2},{3,2},{1,2},{2,5}
		};
		int[][] floyd = new int[n+1][n+1];
		for(int i = 1; i<=n; i++) Arrays.fill(floyd[i], 10000);
		for(int i = 0; i<results.length; i++) {
			int winner = results[i][0];
			int loser = results[i][1];
			floyd[winner][loser] = 1;
		}
		
		for(int k = 1; k<=n; k++) { // 경유 노드
			for(int i = 1; i<=n; i++) { // 출발 노드
				for(int j = 1; j<=n; j++) { // 도착 노드
					if(i == k || j == k || i == j) continue;
					if(floyd[i][j] > floyd[i][k] + floyd[k][j])
						floyd[i][j] = floyd[i][k] + floyd[k][j];
				}
			}
		}
		int answer = 0;
		for(int i = 1; i<=n; i++) {
			int cnt = 0;
			for(int j = 1; j<=n; j++) {
				if(floyd[i][j] < 10000 || floyd[j][i] < 10000) cnt++;
			}
			if(cnt == n-1) answer++;
		}
		System.out.println(answer);

	}

}
