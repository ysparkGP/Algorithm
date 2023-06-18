from sys import stdin

def combination(li, N):
    li = sorted(li)
    used = [False] * len(li)

    def generate(g, used):
        if len(g) == N:
            print(g)
            return
        
        # 순열과 다른 특징 : 처음부터 계산하지않고 누적 개념으로
        start = li.index(g[-1]) + 1 if g else 0
        for i in range(start, len(li)):
            # 중복을 허용한 조합
            # i == 0 : 0번째 인덱스 비교하지 않고 바로 시작
            # li[i] != li[i-1] : 정렬된 원소에서 i번째와 i-1번째 원소는 달라야 함
            # used[i-1] : 대신 1,1 같은 요소를 뽑으려면 i-1번째 원소가 사용되었어야 함
            if not used[i] and (i ==0 or li[i] != li[i-1] or used[i-1]):
                used[i] = True
                g.append(li[i])
                generate(g,used)
                used[i] = False
                g.pop()

    generate([], used)
    
if __name__ == "__main__":
    li = list(map(int, stdin.readline().split(' ')))
    N = int(stdin.readline())
    combination(li, N)