package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_14510_김정원 {
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		// 로컬에서만 돌아갑니다!
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        int testcase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testcase; t++) {
	        	int n = Integer.parseInt(br.readLine());
	        	StringTokenizer st = new StringTokenizer(br.readLine());
	        	List<Integer> trees = new ArrayList<>();
	        	for (int i = 0; i < n; i++) {
	        		trees.add(Integer.parseInt(st.nextToken()));
	        	}

	        	// 가장 큰 나무의 높이를 구하기
	        	int maxHeight = Collections.max(trees);
	        	
	        	// 큰 나무와 높이 차를 기록
	        	trees = trees.stream().map(tree -> maxHeight-tree).toList();
	        	
	        	// 높이 차의 합 구하기
	        	int heightSum = trees.stream().mapToInt(Integer::intValue).sum();
	        	
	        	// 홀수 짝수 날에 방문할 나무들을 정해둠
	        	List<Integer> oddTrees = new ArrayList<>();
	        	List<Integer> evenTrees = new ArrayList<>();
	        	for (int i = 0; i < n; i++) {
	        		if (trees.get(i) == 0) continue;
	        		if (trees.get(i) % 2 == 0) {
	        			evenTrees.add(trees.get(i));
	        		} else {	        			
	        			oddTrees.add(trees.get(i));
	        		}
	        	}
	        	
	        	int day = 0;
	        	// 전체 너무의 높이차가 0이 되면 무한 루프 종료
	        	while (heightSum > 0) {
	        		// 높이차 리스트를 방문하면서 2 미만, 2이상 으로 나누어서 분기
	        		// 2 미만 일 경우, 홀수 번째에 방문 가능
	        		day++;
	        		if (day % 2 == 1) {
	        			if (oddTrees.isEmpty()) {
	        				// 홀수 날에서 뺄 수 있는 홀수 나무가 없을 수 있음
	        				// 하지만 evenTrees 는 2 이상이므로 따로 검사하지 않아도 됨
	        				// 특이 케이스로 전체 합 나무가 2 이하 일경우 -1 할경우 날짜가 하나 더 늘어나서 터짐
	        				if (!evenTrees.isEmpty() && heightSum > 2) {
	        					oddTrees.add(evenTrees.get(0) - 1);
	        					evenTrees.remove(0);
    							heightSum -= 1;
	        				}
	        				continue;
	        			}
	        			if (oddTrees.get(0) > 1) {
	        				evenTrees.add(oddTrees.get(0) - 1);
	        			}
	        			oddTrees.remove(0);
	        			heightSum -= 1;
	        		// 2 이상 일 경우, 짝수 번째 방문가능
	        		} else {
	        			if (evenTrees.isEmpty()) {
	        				// 짝수 날이라 짝수 나무를 뺄려고 했지만
	        				// 없어서 홀수 나무라도 뺄려고 옴
	        				// 이번에는 뺄려는 값이 크기 때문에 검사가 필요함
	        				if (!oddTrees.isEmpty()) {
	        					// 3이상인 나무들을 걸러내줌
	        					for (int i = 0; i < oddTrees.size(); i++) {
	        						if (oddTrees.get(i) > 2) {
	        							oddTrees.add(oddTrees.get(i) - 2);
	        							oddTrees.remove(i);
	        							heightSum -= 2;
	        							break;
	        						}
	        					}

	        				}
	        				continue;
	        			}
	        			if (evenTrees.get(0) > 2) {
	        				evenTrees.add(evenTrees.get(0) - 2);
	        			}
	        			
	        			evenTrees.remove(0);
	        			heightSum -= 2;
	        		}
	        	}
	        	System.out.println(String.format("#%d %d", t + 1, day));
        }
	}
	
}
/*
N개의 나무가 있다. 초기의 각 나무의 키가 주어진다. 하루에 한 나무에 물을 줄 수 있다. 
첫 날은 물을 준 나무의 키가 1 자라고, 

둘째 날은 물을 준 나무의 키가 2 자라고, 

셋째 날은 물을 준 나무의 키가 1 자라는 식으로, 

홀수 번째 날은 키가 1 자라고 

짝수 번째 날은 키가 2 자란다. 

모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜 수를 계산하라. 

어떤 날에는 물을 주는 것을 하지 않을 수도 있다.

예를 들어 나무가 2그루이고 각각의 높이가 4와 2라고 하자. 

첫째 날에 물을 주게 되면, 나무의 높이를 모두 4로 만들기 위해서는 3일째까지 물을 주어야 한다. 

둘째 날은 아무 일도 안 하게 된다. 하지만, 첫째 날을 쉬고 둘째 날에 물을 주면 2일 만에 나무의 높이가 모두 4가 된다.

케이스 수 30, N 제한 100, 나무 높이 최대 120

[제약사항]

나무의 개수 N은 2 이상 100 이하이다. (2 ≤ N ≤ 100)

주어지는 나무의 초기 높이는 1 이상 120 이하이다.

[입력]
가장 첫 줄에는 테스트 케이스의 총 수가 주어진다. 그 다음 줄부터 각 테스트 케이스가 주어지며, 
각 테스트 케이스는 2줄로 구성된다. 각 테스트 케이스의 첫째 줄에는 나무의 개수 N이 주어진다. 다음 줄에는 나무들의 높이가 N개의 자연수로 주어진다.

[출력]
출력의 각 줄은 ‘#x’로 시작하고, 공백을 한 칸 둔 다음 가능한 최소 날짜 수를 출력한다. 단, x는 테스트 케이스의 번호이다.

*/