import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MinHeap{
	
	ArrayList<Integer> heap;
	MinHeap(){
		heap = new ArrayList<Integer>();
		heap.add(0); //인덱스 1부터 시작
	}
	void insert(int value) {
		heap.add(value);
		int index = heap.size() - 1;
		
		while(index > 1 &&  heap.get(index) < heap.get(index/2)) { //삽입된 노드가 루트노드가 되거나, 부모노드가 자식노드
			int temp = heap.get(index);
			heap.set(index, heap.get(index/2));
			heap.set(index/2, temp);
			
			index /= 2;
		}
	}
	int delete() {
		
		if(heap.size() <= 1) {
			
			return 0;
		}
		
		int deleteValue = heap.get(1);
		heap.set(1, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		
		int index = 1;
		while(2*index <= heap.size() - 1) {
			
			if(2*index + 1 <= heap.size()-1) { // 자식노드 2개
				
				if(heap.get(2*index) < heap.get(2*index + 1)) {
					if(heap.get(index) > heap.get(2*index)) {
						int temp = heap.get(index);
						heap.set(index, heap.get(2*index));
						heap.set(2*index, temp);
						index = 2*index;
					}
					else {
						break;
					}
				}
				else {
					if(heap.get(index) > heap.get(2*index + 1)) {
						int temp = heap.get(index);
						heap.set(index, heap.get(2*index + 1));
						heap.set(2*index + 1, temp);
						index = 2*index + 1;
					}
					else {
						break;
					}
				}
				
			}
			else {
				if(heap.get(index) > heap.get(2*index)) {
					int temp = heap.get(index);
					heap.set(index, heap.get(2*index));
					heap.set(2*index, temp);
					index = 2*index;
				}
				else {
					break;
				}
			}
		}
		
		return deleteValue;
	}
	
}

public class Test1927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MinHeap mHeap = new MinHeap();
		for(int i = 0 ; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) {
				mHeap.insert(x);
			}
			else if(x == 0) {
				System.out.println(mHeap.delete());
			}
		}

	}

}
