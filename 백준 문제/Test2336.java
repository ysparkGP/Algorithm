package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 최소 값 세그먼트 트리(순위 찾기 응용 문제)
 * 굉장하다라는 뜻은 자기보다 대단한 사람이 없어야함
 * 즉, 내가 다른 사람보다 시험을 하나라도 더 잘 봤다면 그건 굉장하다는 가능성이 있다
 * 첫 시험에 대해서 정렬(아무 시험이나 상관 없음) => 그 뒤에 나오는 학생보다 앞에 나오는 학생은
 * 대단하다라는 전제를 깔고 들어감
 * 두 번째 시험과 세 번째 시험에 대해선 세그먼트 트리를 적용
 * 세그먼트 트리의 인덱스는 두 번째 시험의 학생순위이고, 그 인덱스의 값은 세 번째 시험의 학생순위
 * 처음에는 Integer.MAX 값으로 모두 초기화 되어있음.
 * 첫 번째 시험에 대해 정렬된 학생순으로 쿼리 삽입
 * 이 쿼리가 뱉는 값은 [1-두번째 시험 순위] 구간의 최솟값.
 * 즉, 나중에 들어온 번호가 굉장항 학생일 될 가능성이 있는 가를 확인하는 작업.
 * 이해가 안가지만 머릿속으로 시뮬레이션을 돌려보면 어느 정도 이해는 가긴함.
 * 
 */

public class Test2336 {
	
	static class Student{
		int first;
		int second;
		int third;
		Student(int first, int second, int third){
			
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}
	
	static int[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Student[] students = new Student[N+1];
		for(int i = 0; i<=N; i++) {
			students[i] = new Student(0,0,0);
		}
			
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			students[Integer.parseInt(st.nextToken())].first = i;
		}
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			students[Integer.parseInt(st.nextToken())].second = i;
		}
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 1; i<=N; i++) {
			students[Integer.parseInt(st.nextToken())].third = i;
		}
		
		
		
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student st1, Student st2) {
				return st1.first - st2.first;
			}
		});
		/*for(Student student: students) {
			System.out.println(student.first + ", " +  student.second + ", " + student.third);
		}*/
		tree = new int[4*N];
		init(1,1,N);
		
		int result = 0;
		for(int i = 1; i<=N; i++) {
			if(students[i].third < query(1,1,N,1,students[i].second)) result++;
			update(1,1,N,students[i].third, students[i].second);
		}
		System.out.println(result);
		
	}
	
	static int init(int node, int start, int end) {
		if(start == end) return tree[node] = Integer.MAX_VALUE;
		else
			return tree[node] = Math.min(init(2*node, start, (start+end)/2),
					init(2*node+1, (start+end)/2+1, end));
	}
	
	static int update(int node, int start, int end, int changeValue, int index) {
		if(start == end && start == index) return tree[node] = changeValue;
		else if(start > index || end < index) return tree[node];
		else return tree[node] = Math.min(update(2*node, start, (start+end)/2, changeValue, index),
				update(2*node+1, (start+end)/2+1, end, changeValue, index));
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if(start > right || end < left) return Integer.MAX_VALUE;
		else if(left <= start && end <= right) return tree[node];
		else return Math.min(query(2*node, start, (start+end)/2, left, right),
				query(2*node+1, (start+end)/2+1, end, left, right));
	}
}
