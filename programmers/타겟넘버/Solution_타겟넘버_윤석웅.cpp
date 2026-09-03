#include <iostream>
#include <vector>
#include <string>

using namespace std;

int DFS(vector<int> numbers, vector<bool> mark, int target, int count){
  int result = 0;
  if (count != numbers.size()){
    result += DFS(numbers, mark, target, count+1); // +부호
    mark[count] = false; // 재사용 없으므로 복구는 스킵
    result += DFS(numbers, mark, target, count+1); // -부호
  } else{
    int temp = 0;
    for(int i = 0; i< numbers.size(); i++){ // 결정 부호 총합 계산
      if (mark[i] == true){
        temp += numbers[i];
      }else{
        temp -= numbers[i];
      }
    }
    if (target == temp){ // 총합이 target과 맞는 경우
      ++result;
    }
  }

  return result;
}
/* optimized
int DFS(vector<int> numbers, int sum, int target, int count){
  int result = 0;
  if (count != numbers.size()){
    result += DFS(numbers, sum + numbers[count], target, count+1); // +부호
    result += DFS(numbers, sum - numbers[count], target, count+1); // -부호
  } else{
    if (target == sum){ // 총합이 target과 맞는 경우
      ++result;
    }
  }

  return result;
}
*/

int solution(vector<int> numbers, int target) {
  vector<bool> mark(numbers.size(),true);

  int answer = 0;
  answer = DFS(numbers, 0, target, 0);
  return answer;
}