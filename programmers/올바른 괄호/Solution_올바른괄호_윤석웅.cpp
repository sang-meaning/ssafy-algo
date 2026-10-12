#include <string>
#include <iostream>

using namespace std;

bool solution(string s)
{
    bool answer = true;
    int chk =0;
    cin >> s;
    for (int i =0; i<s.length(); i++){
        if (s[i] == ')'){ // close bracket over
            chk--;
        } else{
            chk++;
        }
        if (chk<0){
            answer = false;
            break;
        }
    }
    if (chk !=0){ // coupling miss
        answer = false;
    }
    return answer;
}