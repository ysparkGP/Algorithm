package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 맵을 다 도는 것 보다는 List에 y,x,age가 있는 트리 클래스를 만든다.
 * 그리고 alive, dead, total(PriorityQueue) 큐를 만들어서 하나씩 빼어서 로직을 수행하면
 * 시간을 더 줄일수 있겠다.
 */

public class Test16235 {
	/*static class Tree{
		int age;
		Tree(int age){
			this.age = age;
		}
		
		@Override
		public boolean equals(Object o) {
			Tree t = (Tree) o;
			
			return (t.y == this.y && t.x == this.x && t.index == this.index);
		}
		
		@Override
		public int hashCode() {
			return (""+y+x+index).hashCode();
		}
		
	}*/
	static int[] cy = {-1,-1,-1,0,0,1,1,1};
	static int[] cx = {-1,0,1,-1,1,-1,0,1};
	static ArrayList<Integer>[][] treeList;
	static ArrayList<Integer>[][] deadTreeList;
	static int N;
	static int[][] map;
	static int[][] originalMap;
	static int res;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		originalMap = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=N; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		treeList = new ArrayList[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++)
				treeList[i][j] = new ArrayList<>();
					
		}
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeList[y][x].add(age);
		}
		
		
		
		for(int i = 1; i<=K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(res);
	}
	
	static void spring() { // 나무들이 양식을 먹고, 양식을 못먹은 죽은 나무들이 발생
		deadTreeList = new ArrayList[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				deadTreeList[i][j] = new ArrayList<>();
			}
		}
		
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(treeList[i][j].isEmpty()) continue;
				
				Collections.sort(treeList[i][j]);
				ArrayList<Integer> newTrees = new ArrayList<>();
				for(int k = 0; k<treeList[i][j].size(); k++) {
					if(map[i][j] >= treeList[i][j].get(k)) {
						map[i][j] -= treeList[i][j].get(k);
						int newTree = treeList[i][j].get(k) + 1;
						newTrees.add(newTree);
					}
					else {
						deadTreeList[i][j].add(treeList[i][j].get(k));
					}
				}
				treeList[i][j] = newTrees;
			}
		}
	}
	static void summer() { // 죽은 나무들이 양분이 됨
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(deadTreeList[i][j].isEmpty()) continue;
				
				for(int k = 0; k<deadTreeList[i][j].size(); k++) {
					map[i][j] += (deadTreeList[i][j].get(k) / 2);
				}
			}
		}
	}
	static void fall() { // 번식
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(treeList[i][j].isEmpty()) continue;
				
				for(int k = 0; k<treeList[i][j].size(); k++) {
					if(treeList[i][j].get(k) % 5 == 0) {
						for(int check = 0; check<8; check++) {
							int ny = i + cy[check];
							int nx = j + cx[check];
							if(boundCheck(ny,nx)) {
								treeList[ny][nx].add(1);
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		res  = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				map[i][j] += originalMap[i][j];
				res += treeList[i][j].size();
			}
		}
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 1 || x < 1 || y > N || x > N) return false;
		
		return true;
	}
	
}
