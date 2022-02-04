import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class cHouse{
	int y;
	int x;
	cHouse(int y, int x){
		this.y = y;
		this.x = x;
	}
}
public class Test15686 {
	static int[][] city;
	static int N,M;
	static int minDistance = 99999;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N+1][N+1];
		int index = 0;
		visit = new boolean[13];
		List<cHouse> list = new ArrayList<>();
		List<cHouse> house = new ArrayList<>();
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 1; j<=N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j] == 2) {
					list.add(new cHouse(i,j));
				}
				else if(city[i][j] == 1) {
					house.add(new cHouse(i,j));
				}
			}
		}
		List<cHouse> chickenList =  new ArrayList<>();
		dfs(0, 0, house, list, chickenList);
		System.out.println(minDistance);
	}
	static void dfs(int index, int cnt, List<cHouse> house, List<cHouse> list ,List<cHouse> chickenList) {

		if(cnt == M) {
			minDistance = Math.min(calMinCityDistance(chickenList, house), minDistance);
			return;
		}
		if(index >= list.size()) {
			return;
		}
		
		
		for(int i = index; i<list.size(); i++) {
			if(!visit[i]) {
				chickenList.add(list.get(i));
				visit[i] = true;
				dfs(i+1, cnt+1, house, list, chickenList);
				chickenList.remove(cnt);
				visit[i] = false;
			}
		}
		
	}
	
	static int calMinCityDistance(List<cHouse> chickenList, List<cHouse> house){
		int result = 0;
		if(chickenList.isEmpty()) {
			return 100;
		}
		
		for(int i = 0; i<house.size(); i++) {
			int minD = 100;
			for(int j = 0; j<chickenList.size(); j++) {
				int distance = Math.abs(house.get(i).y - chickenList.get(j).y)
						+ Math.abs(house.get(i).x - chickenList.get(j).x);
				minD = Math.min(minD, distance);		
			}
			result += minD;
		}
		
		
		return result;
	}

}
