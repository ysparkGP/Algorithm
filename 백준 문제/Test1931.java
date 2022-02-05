package baekjoon;


// �׸��� �˰���, ȸ�ǽ� �۾� �Ҵ� ����
// ����ð��� �������� �۾��� �� ���� ���۵ǰ� �׷� ���ؼ� �� ���� �۾��� �� �� �ֱ� ������
// ����ð��� �������� �������� ����
// �ݺ������� ����Ʈ���� ���鼭 ���� �۾� ����ð��� ���� �Ҵ��� ���� �ð��� ��
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
