package Programmers;

public class Kakao2021_Blind_Recruitmment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String new_id =  "abcdefghijklmn.p";
		String answer = "";
		//1
		new_id = new_id.toLowerCase();
		//2
		new_id = new_id.replaceAll("[^0-9a-z-_.]", "");
		//3
		new_id = new_id.replaceAll("\\.{2,}",".");
		//4
		new_id = new_id.replaceAll("^[.]|[.]$", "");
		//5
		if(new_id.isEmpty()) {
			new_id += "a";
		}
		//6
		if(new_id.length() >= 16) {
			new_id = new_id.substring(0,15);
			new_id = new_id.replaceAll("[.]$", "");
		}

		//7
		else if(new_id.length() <= 2) {
			while(new_id.length() < 3) {
				new_id += new_id.charAt(new_id.length()-1);
			}
		}
		
		
		System.out.println(new_id);
		
	}

}
