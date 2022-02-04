import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * ���� Tip) �ؽ��ʿ� �ִ� Ű,���� �ֵ��� �켱���� ť�� ��Ƽ� �迭�� �Ѹ��� �迭���縦 �� �ʿ䰡 ����.
 */

public class Test17140 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] firstArr = new int[100][100];
		
		for(int i = 0; i<3; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<3; j++) {
				firstArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(firstArr[r-1][c-1] == k) {
			System.out.println(0);
			return;
		}
		int[][] arr = copyArr(firstArr);
		int rIndex = 3;
		int cIndex = 3;
		
		int count = 0;
		while(true) {
			if(rIndex >= cIndex) {
				//R����	
				rIndex = 0;
				cIndex = 0;
				int[][] rArr = new int[100][100];
				for(int i = 0; i<100; i++) {				
					LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
					for(int j = 0; j<100; j++) {
						if(arr[i][j] <= 0)
							continue;
						map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
					}
					List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(map.entrySet());
					Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
						@Override
						public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
							if(e1.getValue() > e2.getValue()) {
								return 1;
							}
							else if(e1.getValue() < e2.getValue()) {
								return -1;
							}
							return e1.getKey().compareTo(e2.getKey());
						}
					});
					if(!entries.isEmpty())
						rIndex++;
					
					int index = 0;
					for(Map.Entry<Integer, Integer> entry: entries) {
						if(index >= 100)
							break;
						rArr[i][index++] = entry.getKey();
						rArr[i][index++] = entry.getValue();
					}
					cIndex = Math.max(cIndex, index); //���� ����
					
				}
				arr = copyArr(rArr);
			}
			
			else {
				//C����
				rIndex = 0;
				cIndex = 0;
				int[][] cArr = new int[100][100];
				for(int i = 0; i<100; i++) {
					LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
					for(int j = 0; j<100; j++) {
						if(arr[j][i] <= 0)
							continue;
						map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
					}
					List<Map.Entry<Integer, Integer>>entries = new LinkedList<>(map.entrySet());
					Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>(){
						@Override
						public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
							if(e1.getValue() > e2.getValue()) {
								return 1;
							}
							else if(e1.getValue() < e2.getValue()) {
								return -1;
							}
							return e1.getKey().compareTo(e2.getKey());
						}
					});
					if(!entries.isEmpty())
						cIndex++;
					int index = 0;
					for(Map.Entry<Integer, Integer> entry : entries) {
						if(index >= 100)
							break;
						cArr[index++][i] = entry.getKey();
						cArr[index++][i] = entry.getValue();
					}
					rIndex = Math.max(rIndex, index); //���� ����
					
				}	
				arr = copyArr(cArr);
			}
			count++;
			if(count > 100) {
				System.out.println(-1);
				return;
			}
			if(arr[r-1][c-1] == k) {
				break;
			}
		}
		System.out.println(count);
		/*for(int i = 0; i<20; i++) {
			for(int j = 0; j<20; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}*/
		
		
	}
	
	static int[][] copyArr(int[][] arr){
		int[][] newArr = new int[100][100];
		for(int i = 0; i<100; i++) {
			for(int j = 0; j<100; j++) {
				newArr[i][j] = arr[i][j];
			}
		}
		
		return newArr;
	}

}
