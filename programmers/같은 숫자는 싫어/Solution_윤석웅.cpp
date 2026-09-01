#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    cin.tie(0);
    vector<int> answer;
    int len = arr.size();
    int temp;
    for (int i = 0; i<len; i++){
        temp = arr[i];
        if (answer.size() == 0 || answer[answer.size()-1] != temp){
            answer.push_back(temp);
        }
    }
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    return answer;
}