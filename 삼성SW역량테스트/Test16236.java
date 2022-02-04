import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark{
	int y;
	int x;
	int size;
	int count;
	Shark(int y, int x, int size){
		this.y = y;
		this.x = x;
		this.size = size;
		this.count = 0;
	}
}
class Node{
	int y;
	int x;
	int size;
	int distance;
	Node(int y, int x, int size, int distance){
		this.y = y;
		this.x = x;
		this.size = size;
		this.distance = distance;
	}
}
public class Test16236 {
	static int answer;
	static int[] checkY = {-1,0,1,0};
	static int[] checkX = {0,-1,0,1};
	static int N;
	static int[][] map;
	static Shark shark;
	static List<Node> feedList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		List<Node> feeds = new ArrayList<>();

		map = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Shark(i,j,2);
					map[i][j] = 0;
				}
				
			}
		}
		
		while(true) {
			search(shark); //모든 먹이 찾기
			
			if(feedList.size() == 0) {
				break;
			}
			
			// 모든 먹이들을 거리순으로 정렬
			PriorityQueue<Node> distancePriorityQueue = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node n1 ,Node n2) {	
					
					return n1.distance - n2.distance;
				}
			});
			for(Node feed: feedList) {
				distancePriorityQueue.add(feed);
			}
			
			Node feed;
			if(distancePriorityQueue.size() >= 2) {
				//거리순으로 정렬된 먹이를 차례대로 봅는다
				List<Node> tempList = new ArrayList<>();
				Iterator<Node> iter = distancePriorityQueue.iterator();
				int preDistance = distancePriorityQueue.peek().distance ;
				int same = 0;
				while(iter.hasNext()) {
					if(preDistance == iter.next().distance)
						same++;
					else
						break;
				}
				//거리가 같을 때는, 가장 위의 물고기, 그래도 여러마리면, 가장 왼쪽에 있게끔 뽑는 과정
				for(int i = 0; i<same; i++) {
					tempList.add(distancePriorityQueue.poll());
				}
				
				Collections.sort(tempList, new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					if(n1.y == n2.y)
						return n1.x - n2.x;
					return n1.y - n2.y;
				}});
				feed = tempList.get(0);
			}
			else {
				feed = distancePriorityQueue.poll();
			}
			
			int y = feed.y;
			int x = feed.x;
			int size = feed.size;
			int distance = feed.distance;
			shark.count+=1;
			if(shark.count >= shark.size) {
				shark.count = 0;
				shark.size+=1;
			}
			map[y][x] = 0;
			shark.y = y;
			shark.x = x;
			answer+=distance;
			
		}
		
		System.out.println(answer);
	}
	
	//bfs search
	static void search(Shark shark) {
		feedList = new ArrayList<>();
		boolean[][] visit = new boolean[N][N];
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(new Node(shark.y,shark.x,shark.size,0));
		visit[shark.y][shark.x] = true;
		while(!nodes.isEmpty()) {
			Node node = nodes.poll();
			int ny = node.y;
			int nx = node.x;
			int nsize = node.size;
			int ndistance = node.distance;
			if(shark.size > nsize && nsize != 0) {
				Node feed = new Node(ny,nx,nsize,ndistance);
				feedList.add(feed);
			}
			for(int check = 0; check<4; check++) {
				int cy = ny + checkY[check];
				int cx = nx + checkX[check];
				if(boundCheck(cy,cx)) {
					if(!visit[cy][cx] && shark.size >= map[cy][cx]) {
						visit[cy][cx] = true;
						nodes.add(new Node(cy,cx,map[cy][cx],ndistance+1));
					}
				}
			}
		}	
		
	}
	
	static boolean boundCheck(int y, int x) {
		if(y < 0 || y >= N || x < 0 || x >= N)
			return false;
		
		return true;
	}

}
