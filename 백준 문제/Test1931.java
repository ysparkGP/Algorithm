package baekjoon;


// 그리디 알고리즘, 회의실 작업 할당 문제
// 종료시간이 빠를수록 작업이 더 빨리 시작되고 그로 인해서 더 많은 작업을 할 수 있기 때문에
// 종료시간을 기준으로 오름차순 정렬
// 반복문으로 리스트들을 돌면서 이전 작업 종료시간과 현재 할당할 시작 시간을 비교
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class conTime implements Comparable<conTime>{
	int start;
	int end;
	conTime(int start, int end){
		super();
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(conTime p) {
		if(this.end > p.end)
			return 1;
		else if(this.end == p.end)
			if(this.start > p.start)
				return 1;
		
		return -1;
		
	}
}

public class Test1931 {
	static List<conTime> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i<N; i++) {
			String str = bf.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new conTime(start,end));
		}
		
		Collections.sort(list);
		
		int prevEnd = 0;
		int job = 0;
		for(int i = 0; i<N; i++) {
			if(prevEnd <= list.get(i).start) {
				job++;
				prevEnd = list.get(i).end;
			}
		}
		System.out.println(job);
	
	
	}
	
}
