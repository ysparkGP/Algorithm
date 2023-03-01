package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

class Node{
	boolean tf;
	int dir;
	Node(boolean tf, int dir){
		this.tf = tf;
		this.dir = dir;
	}
}

public class Test_14891 {
	static Deque<Character>[] queList;
	static Stack<Character> stack = new Stack<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		queList = new ArrayDeque[4];
		for(int i = 0; i<4; i++) {
			queList[i] = new ArrayDeque<>();
			
			String str = br.readLine();
			for(int j = 0; j<str.length(); j++) {
				queList[i].addLast(str.charAt(j));
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			startRotation(num-1, dir);
		}
		
		System.out.println(getScore());
	}
	
	public static void startRotation(int num, int dir) {
		Node[] rotationNode = new Node[4];
		for(int i = 0; i<4; i++) rotationNode[i] = new Node(false, 0);
		
		rotationNode[num] = new Node(true, dir);
		
		int prev = num;
		int left = num - 1;
		int leftDir = dir;
		while(left >= 0 && boolLeftRotation(left, prev)) {
			rotationNode[left] = new Node(true, leftDir*-1);
			left--;
			prev--;
			leftDir *= -1;
		}
		
		prev = num;
		int right = num + 1;
		int rightDir = dir;
		while(right < 4 && boolRightRotation(right, prev)) {
			rotationNode[right] = new Node(true,rightDir*-1);
			right++;
			prev++;
			rightDir *= -1;
		}
		
		executeRotation(rotationNode);
	}
	
	public static void executeRotation(Node[] rotationNode) {
		for(int i = 0; i<rotationNode.length; i++) {
			if(rotationNode[i].tf) rotation(i, rotationNode[i].dir);
		}
	}
	
	public static void rotation(int num, int dir) {
		
		if(dir == 1) {
			char value = queList[num].pollLast();
			queList[num].addFirst(value);
		}
		else {
			char value = queList[num].pollFirst();
			queList[num].addLast(value);
		}
		
		return;
	}
	
	public static boolean boolLeftRotation(int left, int prev) {
		char value1 = getLeftValue(left);
		char value2 = getRightValue(prev);
		
		if(value1 != value2) return true;
		
		return false;
	}
	
	public static boolean boolRightRotation(int right, int prev) {
		char value1 = getLeftValue(prev);
		char value2 = getRightValue(right);
		
		if(value1 != value2) return true;
		
		return false;
	}
	
	public static char getLeftValue(int num) {
		stack.clear();
		
		stack.push(queList[num].pollFirst());
		stack.push(queList[num].pollFirst());
		
		char value = queList[num].peekFirst();
		while(!stack.isEmpty()) queList[num].addFirst(stack.pop());
		
		return value;
	}
	
	public static char getRightValue(int num) {
		char temp = queList[num].pollLast();
		
		char value = queList[num].peekLast();
		queList[num].addLast(temp);
		
		return value;
		
	}
	
	public static int getScore() {
		int score = 0;
		
		if(queList[0].peekFirst() == '1') score +=1;
		if(queList[1].peekFirst() == '1') score +=2;
		if(queList[2].peekFirst() == '1') score +=4;
		if(queList[3].peekFirst() == '1') score +=8;
		
		return score;
	}

}
