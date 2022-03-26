package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 그리디 알고리즘
 * 담을 수 있는 무게가 낮은 가방 순으로부터 검사
 * 무게가 낮은 보석을 계속 주입(가방이 담을 수 있는 무게보다 무거울때까지)
 * 우선순위 큐로 하나씩 빼서 정답에 주입
 */

public class Test1202 {

	static int MAX = 100000001;
	static List<Integer> bagList;
	static class Dia implements Comparable<Dia>{
		int m;
		int v;
		public Dia(int m, int v) {
			this.m = m;
			this.v = v;
		}
		@Override
		public int compareTo(Dia o) {
			//if(this.m == o.m)  return o.v - this.v;
			return this.m - o.m;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()); // 보석의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		List<Dia> diaList = new ArrayList<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			diaList.add(new Dia(m,v));
		}
		Collections.sort(diaList);
		
		bagList = new ArrayList<>();
		for(int i = 0; i<K; i++) {
			bagList.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(bagList);
		
		long result = 0;
		PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder()); // 가치에 대해서 내림차순
		for(int i = 0, j = 0; i<K; i++) { // 가벼운 가방부터 체크
			while(j<N && bagList.get(i) >= diaList.get(j).m) {
				que.add(diaList.get(j).v);
				j++;
			}
			
			if(!que.isEmpty()) {
				result += que.poll();
			}
		}
		/*PriorityQueue<Dia> que = new PriorityQueue<>();
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			que.add(new Dia(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		bagList = new ArrayList<>();
		for(int i = 0; i<K; i++) {
			bagList.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(bagList);
		
		 remove 가 O(N)을 차지하기때문에 시간초과
		long result = 0;
		while(!que.isEmpty() && bagList.size() > 0) {
			Dia dia = que.poll();
			int find = lower_bound(0,bagList.size()-1, dia.m);
			//System.out.println(find);
			if(find > -1) {
				bagList.remove(find);
				result += dia.v;
			}
		}
		*/
		System.out.println(result);
		
	}
	/*
	static int lower_bound(int start, int end, int m) {
		
		while(start <= end) {
			int mid = (start+end)/2;
			
			if(bagList.get(mid) < m) {
				start = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		if(end+1 >= bagList.size()) return -1;
		else if(end+1 == 0) {
			if(bagList.get(end+1) >=  m) return 0;
			else return -1;
		}
		else return end+1;
	}
	*/
}
