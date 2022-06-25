package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 브루트포스, 수학
 */

public class Test1007 {

	static class Pair{
		int y;
		int x;
		Pair(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static double minSum;
	static int N;
	static boolean[] visit;
	static Pair[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new Pair[N];
			for(int i = 0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine()," ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				arr[i] = new Pair(y,x);
			}
			visit = new boolean[N];
			minSum = Double.MAX_VALUE;
			
			bruteForce(0,0);
			sb.append(minSum+"\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bw.close();
	}
	static void bruteForce(int index, int count) {
		if(count == N/2) {
			minSum = Math.min(minSum, vectorSum());
			return;
		}
		else {
			for(int i = index; i<N; i++) {
				if(!visit[i]) {
					visit[i] = true;
					bruteForce(i+1, count+1);
					visit[i] = false;
				}
			}
		}
	}

	static double vectorSum() {
		int lx = 0;
		int ly = 0;
		int rx = 0;
		int ry = 0;
		for(int i = 0; i<N; i++) {
			if(visit[i]) {
				lx += arr[i].x;
				ly += arr[i].y;
			}
			else {
				rx += arr[i].x;
				ry += arr[i].y;
			}
		}
		return Math.sqrt((double)(lx - rx) * (lx - rx) + (double)(ly - ry) * (ly - ry));
	}
}
