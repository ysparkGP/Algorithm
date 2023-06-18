from sys import stdin

def permutation(li, N):
    li = sorted(li)
    used = [False] * len(li)
    
    def generate(g, used):
        if len(g) == N:
            print(g)
            return
        
        for i in range(len(li)):
            # 중복을 허용한 순열
            # i == 0 : 0번째 인덱스 비교하지 않고 바로 시작
            # li[i] != li[i-1] : 정렬된 원소에서 i번째와 i-1번째 원소는 달라야 함
            # used[i-1] : 대신 1,1 같은 요소를 뽑으려면 i-1번째 원소가 사용되었어야 함
            # if not used[i] and (i == 0 or li[i] != li[i-1] or used[i-1]):
            if not used[i]:
                g.append(li[i])
                used[i] = True
                generate(g,used)
                used[i] = False
                g.pop()

    generate([], used)

if __name__ == "__main__":
    li = list(map(int, stdin.readline().split(' ')))
    N = int(stdin.readline())
    permutation(li, N)
