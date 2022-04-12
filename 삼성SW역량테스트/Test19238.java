package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 삼성 SW 역량 기출문제
 * bfs, 구현
 *
 */

public class Test19238 {
	static int[][] map;

	static class Pair{
		int y;
		int x;
		int energy;
		Pair(int y, int x, int energy){
			this.y = y;
			this.x = x;
			this.energy = energy;
		}
	}
	static class Customer{
		int y;
		int x;
		int ty;
		int tx;
		Customer(int y, int x, int ty, int tx){
			this.y = y;
			this.x = x;
			this.ty = ty;
			this.tx = tx;
		}
	}
	static int[] dy = {0,-1,0,1};
	static int[] dx = {-1,0,1,0};
	static int N;
	static Pair start;
	static int consumeEnergy;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int energy = Integer.parseInt(st.nextToken());

		map = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine()," ");
		start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),energy);
		
		List<Customer> list = new ArrayList<>();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int ty = Integer.parseInt(st.nextToken());
			int tx = Integer.parseInt(st.nextToken());
			list.add(new Customer(y,x,ty,tx));
		}
		while(list.size() > 0) {
			Customer customer = find(list, start);
			if(customer == null) {
				System.out.println(-1);
				return;
			}
			if(!arrive(list, customer)) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(start.energy);
		
	}
	
	// 승객을 찾는다.
	static Customer find(List<Customer> list, Pair start) {
		Customer selectCustomer = null;
		consumeEnergy = 0;
		
		boolean[][] visit = new boolean[N+1][N+1];
		PriorityQueue<Pair> que = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair p1, Pair p2) {
				if(p1.energy==p2.energy) {
					if(p1.y == p2.y) return Integer.compare(p1.x, p2.x);
					return Integer.compare(p1.y, p2.y);
				}
				return Integer.compare(p2.energy, p1.energy);
			}
		});
		que.add(start);
		visit[start.y][start.x] = true;
		loop: while(!que.isEmpty()) {
			Pair p = que.poll();
			if(p.energy <= 0) continue;
			
			for(Customer customer : list) {
				if(customer.y == p.y && customer.x == p.x) {
					selectCustomer = customer;
					start.y = p.y;
					start.x = p.x;
					start.energy = p.energy;
					break loop;
				}
			}
			
			for(int i = 0; i<4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(rangeCheck(ny,nx) && !visit[ny][nx] && map[ny][nx] != 1) {
					que.add(new Pair(ny,nx,p.energy-1));
					visit[ny][nx] = true;
				}
			}
		}
		return selectCustomer;
	}
	
	// 승객을 목적지에 내려준다.
	static boolean arrive(List<Customer> list, Customer customer) {

		boolean success = false;
		// 리스트에서 승객 제거
		list.remove(customer);
		Queue<Pair> que = new LinkedList<>();
		boolean[][] visit = new boolean[N+1][N+1];
		que.add(start);
		visit[start.y][start.x] = true;
		// 승객 목적지 도달
		while(!que.isEmpty()) {
			Pair p = que.poll();
			if(p.energy < 0) continue;
			if(p.energy >= 0) {
				if(p.y == customer.ty && p.x == customer.tx) {
					int lastEnergy = (start.energy - p.energy) * 2;
					start.y = p.y;
					start.x = p.x;
					start.energy = p.energy + lastEnergy;
					success = true;
					break;
				}
			}
			
			for(int i = 0; i<4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(rangeCheck(ny,nx) && map[ny][nx] != 1 && !visit[ny][nx]) {
					que.add(new Pair(ny,nx,p.energy-1));
					visit[ny][nx] = true;
				}
			}
		}
		
		// 택시 연료 충전
		return success;
	}

	// 범위 체크
	static boolean rangeCheck(int y, int x) {
		if(y<1 || x<1 || y>N || x>N) return false;
		return true;
	}
}
