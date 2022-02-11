package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라
 */

class Node{
	int y;
	int x;
	int cost;
	Node(int y, int x, int cost){
		this.y = y;
		this.x = x;
		this.cost = cost;
	}
}

public class Test4485 {

	static int[] checkX = {0,-1,0,1};
	static int[] checkY = {-1,0,1,0};
	static int N;
	public static void main(String[] args) throws IOException{
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			cnt++;
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				return;
			int[][] map = new int[N][N];
			for(int y = 0; y<N; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				for(int x = 0; x<N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean [][] visit = new boolean[N][N];
			
			PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					return n1.cost - n2.cost;
				}
			});
			
			visit[0][0] = true;
			que.add(new Node(0,0,map[0][0]));
			while(!que.isEmpty()) {
				Node curNode = que.poll();
				
				if(curNode.y == N-1 && curNode.x == N-1) {
					System.out.println("Problem" + " " + cnt + ": " + curNode.cost);
					break;
				}
				
				for(int check = 0; check<4; check++) {
					
					int nextY = curNode.y + checkY[check];
					int nextX = curNode.x + checkX[check];
					if(!validCheck(nextY, nextX)) continue;
					if(visit[nextY][nextX]) continue;
					
					que.add(new Node(nextY, nextX, curNode.cost + map[nextY][nextX]));
					visit[nextY][nextX] = true;
				}
			}
			
		}

	}
	
	static boolean validCheck(int y, int x){
		if(y < 0 || x < 0 || y >= N || x >= N)
			return false;
		return true;
	}

}
