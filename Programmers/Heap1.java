package Programmers;

import java.util.ArrayList;


class MinHeap{
	ArrayList<Integer> heap;
	MinHeap(){
		heap = new ArrayList<>();
		heap.add(0);
	}
	void insert(int value) {
		
		heap.add(value);
		int index = heap.size() - 1;
		while(index > 1 && heap.get(index) < heap.get(index/2)) {
			int temp = heap.get(index);
			heap.set(index, heap.get(index/2));
			heap.set(index/2, temp);
			
			index /= 2;
		}
	}
	int delete() {
		if(heap.size() <= 1)
			return -1;
		
		int deleteValue = heap.get(1);
		heap.set(1, heap.get(heap.size() - 1));
		heap.remove(heap.size()-1);
		int index = 1;
		while(2*index <= heap.size() - 1) {
			
			if(2*index + 1 <= heap.size() - 1) { // 자식노드가 2개일 때,
				if(heap.get(2*index) < heap.get(2*index + 1)) {
					if(heap.get(2*index) < heap.get(index)) {
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
					if(heap.get(2*index + 1) < heap.get(index)) {
						int temp = heap.get(index);
						heap.set(index, heap.get(2*index+1));
						heap.set(2*index+1, temp);
						index = 2*index+1;
					}
					else {
						break;
					}
				}
			}
			else { // 1개
				if(heap.get(2*index) < heap.get(index)) {
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

public class Heap1 {

	static int[] scoville = {12,9,2,3,1,10};
	static int K = 7;
	public static void main(String[] args) {
		int answer = 0;
		MinHeap mHeap = new MinHeap();
		for(int i = 0; i<scoville.length; i++)
			mHeap.insert(scoville[i]);
		
		for(int i = 1; i<=mHeap.heap.size() - 1; i++) {
			System.out.print(mHeap.heap.get(i) + " ");
		}
		System.out.println();
		
		while(mHeap.heap.get(1) < K) {
			answer++;
			int lowerScoville = mHeap.delete();
			if(lowerScoville == -1) {
				System.out.println(-1);
				return;
			}
			int lowScoville = mHeap.delete();
			if(lowScoville == -1) {
				System.out.println(-1);
				return;
			}
			int newScoville = lowerScoville + 2*lowScoville;
			mHeap.insert(newScoville);
			for(int i = 1; i<=mHeap.heap.size() - 1; i++) {
				System.out.print(mHeap.heap.get(i) + " ");
			}
			System.out.println();
		}
		
		System.out.println(answer);
		
	}

}
