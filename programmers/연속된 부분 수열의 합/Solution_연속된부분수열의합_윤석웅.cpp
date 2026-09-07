#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> sequence, int k) {
    int len = sequence.size();
    int part_len = 10000000;
    vector<int> answer;
    int start=0, end =0;
    int temp = 0;
    while(end <= len){
        if (temp > k){
            temp -= sequence[start];
            start +=1;
        } else if(temp == k && end-start < part_len){
            part_len = end-start;
            if (!answer.empty()){
                answer.pop_back();
                answer.pop_back();
            }
            answer.push_back(start);
            answer.push_back(end-1);
        } else{
            if (end == len){
                break;
            }
            temp += sequence[end];
            end += 1;
        }
    }
    return answer;
}