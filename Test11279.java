import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MaxHeap{
	ArrayList<Integer> heap;
	MaxHeap(){
		heap = new ArrayList<>();
		heap.add(0); // 1번 인덱스부터 시작
	}
	void insert(int value) {
		
		heap.add(value);
		int index = heap.size() - 1;
		while(index > 1 && heap.get(index) > heap.get(index/2)) {
			int temp = heap.get(index);
			heap.set(index, heap.get(index/2));
			heap.set(index/2, temp);
			
			index /= 2;
		}
	}
	int delete() {
		
		if(heap.size() <= 1){
			return 0;
		}
		
		int deleteValue = heap.get(1);
		heap.set(1, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		int index = 1;
		while(2*index <= heap.size() - 1) {
			
			if(2*index + 1 <= heap.size() - 1) { // 자식노드가 2개 일 때,
				if(heap.get(2*index) < heap.get(2*index + 1)) {
					if(heap.get(index) < heap.get(2*index + 1)) {
						int temp = heap.get(index);
						heap.set(index, heap.get(2*index + 1));
						heap.set(2*index + 1, temp);
						index = 2*index + 1;
					}
					else {
						break;
					}
				}
				else {
					if(heap.get(index) < heap.get(2*index)) {
						int temp = heap.get(index);
						heap.set(index, heap.get(2*index));
						heap.set(2*index, temp);
						index = 2*index;
					}
					else
						break;
				}
			}
			else { //1개
				if(heap.get(index) < heap.get(2*index)) {
					int temp = heap.get(index);
					heap.set(index, heap.get(2*index));
					heap.set(2*index, temp);
					index = 2*index;
				}
				else
					break;
			}
		}
		
		return deleteValue;
	}
}

public class Test11279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		MaxHeap mHeap = new MaxHeap();
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
