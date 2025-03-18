
def solution(info, n, m):
    
    item_count = len(info)
    dp = [[[False]*m for _ in range(n)] for _ in range(item_count+1)]

    dp[0][0][0] = True

    for count in range(item_count):
        for i in range(n):
            for j in range(m):
                # 전이 가능 상태
                if dp[count][i][j] : 
                    if i + info[count][0] < n:
                        dp[count+1][i + info[count][0]][j] += 1
                        aPossible = True
                    if j + info[count][1] < m:
                        dp[count+1][i][j + info[count][1]] += 1
                        bPossible = True

    # print(dp)
    for i in range(n):
        for j in range(m):
            if dp[item_count][i][j] :
                return i
    
    return -1
    


# info = [[1,2], [2,3], [2,1]]
# n = 4
# m = 4
# result = 2

# info = [[1,2], [2,3], [2,1]]
# n = 1
# m = 7
# result = 0

# info = [[3,3], [3,3]]
# n = 7
# m = 1
# result = 6

# info = [[3,3], [3,3]]
# n = 6
# m = 1
# result = -1


print(solution(info,n,m))