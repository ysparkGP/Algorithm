import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class Test2841 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[][] li = new int[N][2];
		//List<List<Integer>> li = new ArrayList<>();
		List<Stack<Integer>> inst = new ArrayList<>();

		for(int i = 0; i<7; i++) {
			
			inst.add(new Stack<>());
		}
			
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int g = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			li[i][0] = g;
			li[i][1] = p;
		}
		
		int cnt = 0;
		for(int i = 0; i<N; i++) {
			
			if(inst.get(li[i][0]).empty()) {
				inst.get(li[i][0]).push(li[i][1]);
				cnt++;
			}
			
			else {
				while(true) {
					if(inst.get(li[i][0]).empty()) {
						inst.get(li[i][0]).push(li[i][1]);
						cnt++;
						break;
					}
					else if(li[i][1] > inst.get(li[i][0]).peek()) {
						inst.get(li[i][0]).push(li[i][1]);
						cnt++;
						break;
					}
					else if(li[i][1] < inst.get(li[i][0]).peek()) {
						inst.get(li[i][0]).pop();
						cnt++;
					}
					else{
						break;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}
