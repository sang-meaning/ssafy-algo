def solution(numbers, target):
    
    results = [[0]]
    
    for i in range(len(numbers)):
        
        row = []
        
        num = numbers[i]
        
        for j in results[i]:

            plus_j = j + num
            minus_j = j - num

            row.append(plus_j)
            row.append(minus_j)
        
        results.append(row)
    
    print(results)
    
    answer = 0
    
    for i in results[len(numbers)]:
        
        if i == target:
            answer += 1
        
    return answer