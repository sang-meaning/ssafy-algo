def solution(s):
    answer = True
    stack = []
    for word in s:
        if word == '(':
            stack.append(word)
        else:
            if len(stack) != 0:
                stack.pop()
            else :
                return False

    answer = True if len(stack) == 0 else False
    return answer