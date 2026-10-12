package programmers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_소수찾기_김정원 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		System.out.println(solution(sc.nextInt()));
	}
	
    public static int solution(int n) {
        int answer = 0;
        // 먼저 소수를 찾기 위해서는 에-체 방법을 쓰면 좋다 다른것도 많지만
        // 탐색할 범위가 n 까지이므로 n+1 공간이 있는 boolean 배열을 준비하고
        // 탐색 하기 전에 소수들을 루트n 전까지 모아본다 <- 이렇게 하는 이유는 까먹음 아마 수학적인 이유
        // 소수들 만큼 더하면서 소수가 아닌것을 지워간다
        // 예 2 true -> 4 false -> 6 false -> ... n 까지
        // 예 3 true -> 6 false (이미 false) -> 9 false -> ... n 까지
        // 2 부터 n 까지 탐색을 시도할때 아래의 조건을 따른다
        // 2는 소수 이므로 위에 처럼 합성수(소수로 이루어진 수) false 로 바꾼다
        // 그리고 그것을 n 까지 진행한다
        // 다음은 3 은 false 가 아니고 true 이므로 소수이라고 판단
        // false 는 contiune; 하고 true 이면 false 바꾼다(9, 15 등)
        // 다음은 4 인데 false 라 contiune;
        // 다음은 5인데 true 라 시작
        // 그리고 남은 true 갯수를 세면 소수의 갯수가 나온다
        boolean[] numbers = new boolean[n + 1];
        Arrays.fill(numbers, true);
        numbers[0] = false;
        numbers[1] = false;
        int prime = 2;
        boolean flag = false;
        while (!flag) {
        	flag = true;
        	for (int num = prime * 2; num <= n; num += prime) {
        		if (numbers[num]) numbers[num] = false;
        	}
        	for (int num = prime + 1; num < n; num++) {
        		if (numbers[num]) {
        			prime = num;
        			flag = false;
        			break;
        		}
        	}
        }
        for (int i = 0; i <= n; i++) {
        	if(numbers[i]) answer++;
        }
        return answer;
    }
/*
문제 설명
1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.

소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
(1은 소수가 아닙니다.)

제한 조건
n은 2이상 1000000이하의 자연수입니다.
입출력 예
n	result
10	4
5	3
입출력 예 설명
입출력 예 #1
1부터 10 사이의 소수는 [2,3,5,7] 4개가 존재하므로 4를 반환

입출력 예 #2
1부터 5 사이의 소수는 [2,3,5] 3개가 존재하므로 3를 반환	
*/
}
