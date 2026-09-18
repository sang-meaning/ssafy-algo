#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

void BFS(const vector<vector<int>>& computers, vector<bool>& isConnected, int num){
    queue<int> worQ;
    worQ.push(num);
    isConnected[num] = true;
    while(!worQ.empty()){
        int idx = worQ.front();
        worQ.pop();
        for (int i = 0; i<computers[idx].size(); i++){
            // computers idx i --> 컴퓨터의 idx번과 i번이 연결되었는지
            if(!isConnected[i] && computers[idx][i]){ // 미지정, 연결 간선이 있는 경우
                worQ.push(i);
                isConnected[i] = true;
            }
        }
    }
}

int solution(int n, vector<vector<int>> computers){
    int answer = 0;
    vector<bool> isConnected (computers.size(), false);
    for (int i = 0; i < computers.size(); i++){
        if (!isConnected[i]){
            BFS(computers, isConnected, i);
            answer++;
        }
    }
    return answer;
}

int main(){
    int n = 3;
    vector<vector<int>> example = {{1,1,0},{1,1,0},{0,0,1}};
    cout << solution(n, example);
    return 0;
}