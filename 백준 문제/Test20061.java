package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 삼성 SW 역량 기출문제
 * 구현
 */

public class Test20061 {
    static class Mino{
        int t;
        int x;
        int y;
        public Mino(int t, int x, int y){
            this.t = t;
            this.x = x;
            this.y = y;
        }
    }
    static int gBound, bBound;
    static int gScore, bScore;
    static boolean[][] greenList; // 행
    static boolean[][] blueList; // 열
    static List<Mino> li;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        li = new ArrayList<>();
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int t = Integer.parseInt(st.nextToken()); // 1x1, 1x2, 2x1
            int x = Integer.parseInt(st.nextToken()); // 행
            int y = Integer.parseInt(st.nextToken()); // 열
            li.add(new Mino(t,x,y));
        }
        greenList = new boolean[6][4];
        blueList = new boolean[6][4];

        job();
        resultPrint();
    }
    public static void job(){
    	
        for(Mino m : li){
            greenJob(m);
            blueJob(m);
            removeAndCheck();
            boundCheck();
        }
    }

    public static void greenJob(Mino m){
        if(m.t == 1){ // 1x1
            int i = 0;
            while(i<=5 && !greenList[i][m.y]) {
            	i++;
            }
            greenList[i-1][m.y]  =  true;
        }
        else if(m.t == 2){ // 1x2
        	int i = 0;
        	while(i<=5 && !greenList[i][m.y] && !greenList[i][m.y+1]) {
        		i++;
        	}
        	greenList[i-1][m.y] = true;
        	greenList[i-1][m.y+1] = true;
        	
        }
        else{ // 2x1
        	int i = 0;
        	while(i<5 && !greenList[i][m.y] && !greenList[i+1][m.y]) {
        		i++;
        	}
        	greenList[i-1][m.y] = true;
        	greenList[i][m.y] = true;
        }
    }
    public static void blueJob(Mino m){
        if(m.t == 1){ // 1x1
        	int i = 0;
        	while(i<=5 && !blueList[i][m.x]) {
        		i++;
        		
        	}
        	blueList[i-1][m.x] = true;
        }
        else if(m.t == 2){ // 2x1
            int i = 0;
            while(i<5 && !blueList[i][m.x] && !blueList[i+1][m.x]) {
            	i++;
            }
            blueList[i-1][m.x] = true;
            blueList[i][m.x] = true;
        	
        }
        else{ // 1x2
        	int i = 0;
        	while(i<=5 && !blueList[i][m.x] && !blueList[i][m.x+1]) {
        		i++;
        	}
        	blueList[i-1][m.x] = true;
            blueList[i-1][m.x+1] = true;
        }
        
        
    }
    public static void removeAndCheck(){
        for(int i = 5; i>=0; i--){

            boolean green = true;
            boolean blue = true;
            for(int j = 0; j<4; j++){
                if(!greenList[i][j]) green = false;
                if(!blueList[i][j]) blue = false;
                
            }
            if(green) {
            	gScore++;
            	remove(i,1);
            }
            if(blue) {
            	bScore++;
            	remove(i,2);
            }
            if(green || blue) i++;
        }
    }
    public static void remove(int row, int gb){
        if(gb == 1){ // green
            for(int i = row; i>=1; i--){
                for(int j = 0; j<4; j++){
                    greenList[i][j] = greenList[i-1][j];
                }
            }
        }
        else{ // blue
            for(int i = row; i>=1; i--){
                for(int j = 0; j<4; j++){
                    blueList[i][j] = blueList[i-1][j];
                }
            }
        }
    }
    public static void boundCheck(){
        gBound = 0;
        bBound = 0;
        for(int i = 0; i<2; i++){

            for(int j = 0; j<4; j++){
                if(blueList[i][j]){
                    bBound++;
                    break;
                }
            }
            for(int j = 0; j<4; j++){
                if(greenList[i][j]){
                    gBound++;
                    break;
                }
            }
        }
        while(gBound-- > 0){
            remove(5,1);
        }
        while(bBound-- > 0){
            remove(5,2);
        }
        for(int i = 0; i<2; i++) {
        	for(int j = 0;  j<4;  j++) {
        		blueList[i][j] = false;
        		greenList[i][j] = false;
        	}
        }
    }
    public static void resultPrint(){
        int score = 0;
        for(int i = 5; i>=2; i--){
            for(int j = 0; j<4; j++){
                if(blueList[i][j]) score++;
                if(greenList[i][j]) score++;
            }
        }
        System.out.println(gScore+bScore);
        System.out.println(score);
    }
}