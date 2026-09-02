#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    int cursor =0;
    while (cursor < progresses.size()){ // 배포 작업이 모두 끝나기 전까지
        for (int i = cursor; i<progresses.size(); i++){ // 배포작업이 미완료된 작업들에 대하여
            progresses[i] += speeds[i]; // 작업 진도 반영
        }
        int temp = 0;
        while(cursor < progresses.size() && progresses[cursor] >= 100){ // 오늘 완료된 작업에 대해
            temp++;
            cursor++; // 다음 작업의 진도 확인
        }
        if (temp != 0){ // 완료된 작업이 있는경우
            answer.push_back(temp);
        }
    }
    return answer;
}