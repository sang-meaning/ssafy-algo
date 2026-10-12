function solution(number) {
    let answer = 0;
    let set = 0;
    for(let i =0; i<number.length-2;i++)
    {
        for(let j=i+1;j<number.length-1;j++)
        {
            set =number[i];
            set +=number[j];
            for(let k = j+1;k<number.length;k++)
            {
                if(set + number[k] == 0)
                    answer++;
            }
        }
    }
    return answer;
}