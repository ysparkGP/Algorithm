package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test21608 {
	                    //��,�Ʒ�,������,����
	static int[] xCheck = {0,0,1,-1};
	static int[] yCheck = {1,-1,0,0};
	static int N;
	static int size;
	static List<ArrayList<Integer>> info = new ArrayList<>();  //ArrayList.contains �޼��带 �̿��� �����ϴ� �л� ��ȣ ã��
	static int[] order;
	static int[][] classroom;
	static int[][] totalScore;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		size = N*N;
		//board = new boolean[size][size];
		order = new int[size];       //�л� ��ġ ������ ���� 2���� �迭
		classroom = new int[N][N];   //�л����� ��ġ�ϱ� ���� 2���� �迭
		totalScore = new int[N][N];  //���� ������ ���� 2���� �迭
		
		for(int i = 0; i<=size; i++) {
			info.add(new ArrayList<Integer>());
		}
		
		
		for(int i = 0; i<size; i++) {
			String str;
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int id = Integer.parseInt(st.nextToken());
			order[i] = id;
			
			for(int j = 0; j < 4; j++) {
				info.get(id).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		
		int rx = 0, ry = 0;
		
		for(int i = 0; i<size;i ++) {
			int id = order[i];
			int max = -1;
			
			for(int y = 0; y<N; y++) {
				for(int x = 0; x<N; x++) {
					
					if(classroom[y][x] > 0) {
						continue;
					}
					
					int score = 0;
					for(int check = 0; check < 4; check++) {  //id�л��� (y,x)��ǥ �ڸ��� �����ұ�? ���� �ű��
						
						int dx = x + xCheck[check];
						int dy = y + yCheck[check];
						
						if(valCheck(dy, dx) && info.get(id).contains(classroom[dy][dx]))
							score += 10;
						else if(valCheck(dy,dx) && classroom[dy][dx] == 0) {
							score += 1;
						}
					}
					
					if(score > max) {
						rx = x;
						ry = y;
						max = score;
					}
				}
			}
			
			classroom[ry][rx] = id;
		}
		
		int result = 0;
		for(int y = 0; y<N; y++) {  //���� �ű��
			for(int x = 0; x<N; x++) {
				for(int check = 0; check < 4; check++) {
					int dy = y + yCheck[check];
					int dx = x + xCheck[check];
					
					if(valCheck(dy,dx) && info.get(classroom[y][x]).contains(classroom[dy][dx])) {
						totalScore[y][x]++;
					}
				}
				
				switch(totalScore[y][x]) {
				case 0:
					break;
				case 1:
					result += 1;
					break;
				case 2:
					result += 10;
					break;
				case 3:
					result += 100;
					break;
				case 4:
					result += 1000;
					break;
				}
					
			}
		}
		
		System.out.println(result);
	}
	
	static boolean valCheck(int y, int x) {  //��ȿ�� �˻�
		
		if(y<0 || x<0 || y>=N ||x>=N)
			return false;
		
		
		return true;
	}
		
}
