package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair>{
	int y;
	int x;
	
	Pair(int y, int x){
		this.y = y;
		this.x = x;
	}
	
	@Override
	public int compareTo(Pair p) {
		if(this.x > p.x) //양수면 자리바꿈, this가 클 경우 자리를 바꾸니 오름차순
			return 1;
		else if(this.x == p.x) {
			if(this.y > p.y) {
				return 1;
			}
		}
		return -1;
	}
}

public class Test2641 {
	static int nowX;
	static int nowY;
	public static void main(String[] args) throws IOException{	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		List<Pair> standard = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			int direction = Integer.parseInt(st.nextToken());
			Pair np = cal(direction);
			standard.add(np);
		}
		
		Collections.sort(standard); //x,y 좌표 오름차순 정렬
		
		if(standard.get(0).x != 0 || standard.get(0).y != 0) { //시작점을 0,0로 변경
			int tempX = standard.get(0).x;
			int tempY = standard.get(0).y;
			for(int i = 0; i<standard.size(); i++) {
				standard.get(i).x -= tempX;
				standard.get(i).y -= tempY;
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		boolean[] take = new boolean[M]; //샘플들 중 진짜를 찾기 위한 배열
		Arrays.fill(take, true);
		int[][] samples = new int[M][N];
		int index = M;
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			nowY = 0;
			nowX = 0;
			List<Pair> sample = new ArrayList<>();
			for(int j = 0; j<N; j++) {
				samples[i][j] = Integer.parseInt(st.nextToken());
				Pair sp = cal(samples[i][j]);
				sample.add(sp);
			}
			
			Collections.sort(sample);
			
			if(sample.get(0).x != 0 || sample.get(0).y != 0) {
				int tempX = sample.get(0).x;
				int tempY = sample.get(0).y;
				for(int k = 0; k<sample.size(); k++) {
					sample.get(k).x -= tempX;
					sample.get(k).y -= tempY;
				}
			}
			
			for(int k = 0; k<sample.size(); k++) {
				if(sample.get(k).x != standard.get(k).x && sample.get(k).y != standard.get(k).y) {
					take[i] = false;
					index--;
					break;
				}
			}
			
		}
		System.out.println(index);
		for(int i = 0; i<M; i++) {
			if(take[i] == true) {
				for(int j = 0; j<N; j++) {
					System.out.print(samples[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	
	static Pair cal(int direction) {

		if(direction == 1) {
			nowX++;
		}
		else if(direction == 2) {
			nowY++;
		}
		else if(direction == 3) {
			nowX--;
		}
		else if(direction == 4) {
			nowY--;
		}
		
		Pair p = new Pair(nowY,nowX);
		
		return p;
	}
}
