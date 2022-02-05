package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Two{
	int position;
	int time;
	
	Two(int position, int time){
		this.position = position;
		this.time = time;
	}
}

public class Test1697 {
	static boolean[] visit; // 메모리 초과 방지를 위한 방문 여부 배열

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int K = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		//걷기 : X-1 or X+1 , 순간이동 : 2*X
		Queue<Two> que = new LinkedList<>();
		que.add(new Two(N,0));
		
		visit = new boolean[100001];
		visit[N] = true;
		
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		//int time = 0;
		while(!que.isEmpty()) {
			Two value = que.poll();
			int position = value.position;
			int time = value.time;
			
			
			for(int i = 0; i<3; i++) {
				int p = position;
				int t = time;
				if(i == 0) {
					p += 1;
					t++;
				}
				else if(i == 1) {
					p -= 1;
					t++;
				}
				else if(i == 2) {
					p *= 2;
					t++;
				}
				
				if(p == K) {
					System.out.println(t);
					return;
				}
				//System.out.println(p + " " + t);
				
				if(0 <= p && p <= 100000) {   //ArrayIndexOutOfBounds 방지 if문
					if(visit[p] == false) {
						visit[p] = true;
						que.add(new Two(p, t));
					}
					
				}
			}
			
		}
		
	}
}
