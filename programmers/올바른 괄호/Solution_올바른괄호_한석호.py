from collections import deque 

def solution(s):
    answer = True
    
    queue = deque()
    
    left = 0
    right = 0
    
    for i in s:
        queue.append(i)
        if i == '(':
            left += 1
        else:
            right += 1
        
        if left < right:
            return False
    
    
    if queue[0] == ')' or left != right:
        return False
    
#     while queue:
#         check = queue.popleft()
        
#         if check == '(':
#             left -= 1
        
#         elif check == ')':
#             right -= 1
        
#         # print("left right", left, right)
        
#         if left > right:
#             return False
    

    return True
