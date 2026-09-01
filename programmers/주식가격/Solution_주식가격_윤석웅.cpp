#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> answer(prices.size(),0);
    for (int n = 0; n< answer.size(); n++){
        for (int i = n; i<prices.size() -1; i++){
            if (prices[n] > prices[i]){ // 주식 가격이 내려갔으면
                break; //바로 다음 거 탐색
            }else{
                answer[n]++;
            }
        }
    }
    //결국 내려가는 지점을 찾아야 하므로 순차탐색이 최선
    return answer;
}