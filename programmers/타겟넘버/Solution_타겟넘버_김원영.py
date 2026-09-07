def solution(numbers, target):
    n = len(numbers)
    answers = []
    combination = []
    count = 0
    def backtracking():
        if len(combination) == n:
            copy_numbers = numbers.copy()
            for i in range(len(combination)):
                if combination[i] == '+':
                    copy_numbers[i] *= 1
                else:
                    copy_numbers[i] *= (-1)
                    
            if sum(copy_numbers) == target:
                nonlocal count
                count += 1
            return
        
        combination.append('+')
        backtracking()
        combination.pop()
        
        combination.append('-')
        backtracking()
        combination.pop()
    
    backtracking()

    return count