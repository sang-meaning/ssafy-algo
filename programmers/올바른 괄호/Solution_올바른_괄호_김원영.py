def solution(s):
    s = list(s)
    map = {"(":")",")":"("}
    temp = []
    while(s):
        if not temp:
            temp.append(map[s[-1]])
            s.pop()
        elif temp[-1] == s[-1]:
            temp.pop()
            s.pop()
        else:
            temp.append(map[s[-1]])
            s.pop()
        if temp and temp[0] == ')':
            return False
    if temp:
        return False
    else:
        return True
