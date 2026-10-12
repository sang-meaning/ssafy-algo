#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    int cursor = 0;
    int count = 0;
    int sum_weight = 0;
    queue<int> bridge;
    for (int i =0; i<bridge_length; i++){ //prefilling
        bridge.push(0);
    }
    
    while(true){
        if (bridge.front() != 0){ //선출
            count++;
        }
        sum_weight -= bridge.front();
        bridge.pop();
        if (cursor < truck_weights.size() && truck_weights[cursor] <= weight - sum_weight){ // 무게가 넘지 않으면
            sum_weight += truck_weights[cursor];
            bridge.push(truck_weights[cursor++]);
        }else{ //기존 빠진 자리 넣기
            bridge.push(0);
        }
        answer++; //턴수 증가
        if (count >= truck_weights.size()){ // 빠져나온 트럭 갯수 세기
            break;
        }
    }
    return answer;
}