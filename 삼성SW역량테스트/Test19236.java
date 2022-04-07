package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 삼성 SW 역량 기출문제
 * 구현, 백트래킹
 */

public class Test19236 {

	// 0~7
	static int[] cx = {0,-1,-1,-1,0,1,1,1};
	static int[] cy = {-1,-1,0,1,1,1,0,-1};
	
	static class Shark{
		int y;
		int x;
		int d;
		int score;
		public Shark() {}
		public Shark(int y, int x, int d, int score) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.score = score;
		}
	}
	static class Feed{
		int id;
		int y;
		int x;
		int d;
		boolean alive;
		public Feed(int id, int y, int x, int d, boolean alive) {
			this.id = id;
			this.y = y;
			this.x = x;
			this.d = d;
			this.alive = alive;
		}
	}
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[4][4];
		List<Feed> feedList = new ArrayList<>();
		// 1번 먹이부터 참조하기 위해서 임의의 먹이객체를 삽입
		feedList.add(new Feed(0,-1,-1,0,false));
		for(int i = 0; i<4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<4; j++) {
				int id = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[i][j] = id;
				feedList.add(new Feed(id,i,j,d-1, true));
			}
		}
		Collections.sort(feedList, new Comparator<Feed>() {
			@Override
			public int compare(Feed f1, Feed f2) {
				return Integer.compare(f1.id, f2.id);
			}
		});
		
		Shark shark = new Shark(0,0,feedList.get(map[0][0]).d, feedList.get(map[0][0]).id);
		feedList.get(map[0][0]).alive = false;
		map[0][0] = 0;
		
		dfs(shark, map, feedList);
		System.out.println(result);
	}
	static void dfs(Shark shark, int[][] map, List<Feed> feedList) {
		
		result = Math.max(result, shark.score);
		//mapPrint(map);
		
		// 먹이들 이동
		for(Feed feed: feedList) feedMove(feed, map, feedList, shark);
		
		for(int i = 1; i<=3; i++) {
			int ny = shark.y + cy[shark.d]*i;
			int nx = shark.x + cx[shark.d]*i;
			// 경계를 넘지 않고 이동할 자리에 물고기가 있다면
			if(validCheck(ny,nx) && map[ny][nx] > 0) {
				int[][] copyMap = copyArr(map);
				List<Feed> copyFeedList = copyList(feedList);
				
				Feed feed = copyFeedList.get(copyMap[ny][nx]);
				feed.alive = false;
				Shark copyShark = new Shark(ny,nx,feed.d,shark.score + feed.id);
				copyMap[ny][nx] = 0;
				dfs(copyShark, copyMap, copyFeedList);
			}
		}
	}
	// 먹이들의 이동
	static void feedMove(Feed feed, int[][] map, List<Feed> feedList, Shark shark) {
		if(!feed.alive || feed.id == 0) return;
		
		for(int i = 0; i<8; i++) {
			int nd = (feed.d + i) % 8;
			int ny = feed.y + cy[nd];
			int nx = feed.x + cx[nd];
			//System.out.println(feed.id+","+ny+","+nx+","+nd +(ny != shark.y && nx != shark.x) + "," + shark.y + ", "+shark.x);
			
			// 경계를 넘지 않고 상어와 만나지 않을 때
			if(validCheck(ny,nx) && !(ny == shark.y && nx == shark.x)) {
				// 빈 칸과 바꿔야할 때
				if(map[ny][nx] == 0) {
					map[ny][nx] = feed.id;
					map[feed.y][feed.x] = 0;
					feed.y = ny;
					feed.x = nx;
					feed.d = nd;
				}
				// 다른 물고기와 바꿔야할 때
				else {
					map[feed.y][feed.x] = map[ny][nx];
					
					Feed tempFeed = feedList.get(map[ny][nx]);
					tempFeed.y = feed.y;
					tempFeed.x = feed.x;
					
					map[ny][nx] = feed.id;
					
					feed.y = ny;
					feed.x = nx;
					feed.d = nd;
				}
				//mapPrint(map);
				return;
			}
		}
		
		
	}
	
	static int[][] copyArr(int[][] origin){
		int[][] copy = new int[4][4];
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				copy[i][j] = origin[i][j];
			}
		}
		return copy;
	}
	
	static List<Feed> copyList(List<Feed> origin){
		List<Feed> copy = new ArrayList<>();
		for(Feed feed: origin) {
			copy.add(new Feed(feed.id, feed.y, feed.x, feed.d, feed.alive));
		}
		return copy;
	}
	
	// 맵 경계 검사
	static boolean validCheck(int y, int x) {
		if(y<0 || x<0 || y>=4 || x>=4) return false;
		return true;
	}
	static void mapPrint(int[][] map) {
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
