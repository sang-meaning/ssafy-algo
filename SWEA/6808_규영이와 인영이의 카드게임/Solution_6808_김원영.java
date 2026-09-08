package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	static List<Integer> other = new ArrayList<>();
	static List<Integer> other_card = new ArrayList<>();

    static int[] gave;
    static int lose_count;
    static int win_count;
    static int drawcount;
    static int win_sum;
    static int lose_sum;
    static int[] selected;

    static void backtracking() {
        if(other.size()==9) {
            for(int i=0; i<9; i++) {
                if (other.get(i) > gave[i]) {
                    win_sum += other.get(i) + gave[i];
                }else {
                    lose_sum += other.get(i) + gave[i];
                }
            }
            if(win_sum>lose_sum) {
                lose_count += 1;
            }else if(win_sum<lose_sum){
            	win_count += 1;
            }else {
            	drawcount += 1;
            }

            win_sum = 0;
            lose_sum = 0;

            return;
        }
        for(int i=0; i<9;i++) {
        	if (selected[other_card.get(i)] == 1) {
        		continue;
        	}
        	selected[other_card.get(i)] = 1;
        	other.add(other_card.get(i));

        	backtracking();

        	selected[other_card.get(i)] = 0;
        	other.remove(other.size()-1);

        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
        	win_sum = 0;
        	lose_sum = 0;

            selected = new int[19];
            selected[0] = 1;

            lose_count = 0;
            win_count = 0;
            drawcount = 0;

            gave = new int[9];

            for(int i=0; i<9; i++) {
                gave[i] = sc.nextInt();
                selected[gave[i]] = 1;
            }
            for(int i=0; i<19; i++) {
            	if(selected[i] != 1) {
            		other_card.add(i);
            	}
            }

            backtracking();

            System.out.println("#"+tc+" "+win_count+" "+lose_count);

            other.clear();
            other_card.clear();
        }
    }
}