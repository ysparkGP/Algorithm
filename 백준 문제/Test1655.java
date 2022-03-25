package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Priority Queue 응용문제
 * 내림차순 정렬 우선순위 큐, minQue
 * 오름차순 정렬 우선순위 큐, maxQue
 * 사이즈가 같다면 maxQue에 추가
 * 사이즈가 다르면 minQue에 추가
 * 추가할 때마다, 각 큐의 peek()를 비교
 * maxQue.peek()가 중간값이 되게해야하므로
 * minQue.peek()가 maxQue.peek()보다 작으면 swap
 */

public class Test1655 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//List<Integer> li = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> minQue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return Integer.compare(i1, i2);
			}
		});
		PriorityQueue<Integer> maxQue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return Integer.compare(i2, i1);
			}
		});
		/*
		 * ------------------> 중간 값 <---------------------
		 * (오름차순) maxQue ->           <- minQue (내림차순)
		 */
		for(int i = 0; i<N; i++) {
			int input = Integer.parseInt(br.readLine());
			if(minQue.size() == maxQue.size()) {
				maxQue.add(input);
			}
			else {
				minQue.add(input);
			}
			if(maxQue.size() > 0 && minQue.size() > 0) {
				if(maxQue.peek() > minQue.peek()) {
					int temp = minQue.poll();
					minQue.add(maxQue.poll());
					maxQue.add(temp);
				}
			}
			
			sb.append(maxQue.peek() + "\n");
			
			/*li.add(Integer.parseInt(br.readLine()));
			Collections.sort(li, new Comparator<Integer>() {
				@Override
				public int compare(Integer i1, Integer i2) {
					return Integer.compare(i1, i2);
				}
			});
			if(i % 2 == 0) {
				sb.append(li.get(i/2) > li.get(i/2-1) ? li.get(i/2-1) : li.get(i/2));
			}
			else {
				sb.append(li.get(i/2));
			}
			sb.append("\n");*/
		}
		System.out.println(sb);
	}

}
