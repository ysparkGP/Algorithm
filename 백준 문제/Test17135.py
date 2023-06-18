from sys import stdin

N, M, D = map(int, stdin.readline().split(' '))

world = []
for i in range(N):
    line = list(map(int, stdin.readline().split(' ')))
    world.append(line)
world.append([0]*M)
visit = [False]*M

max_remove = 0

class Point:
    def __init__(self, y, x, d):
        self.y = y
        self.x = x
        self.d = d

def custom_sort(point):
    return (point.d, point.x)

def possible_attack(matrix, i, j, archer_x):
    if abs(i-N) + abs(j-archer_x) <= D : return abs(i-N) + abs(j-archer_x)
    return -1

def attack(matrix, c):
    # D 거리 이하면 적들을 공격, 공격해야할 적들이 많을 경우 왼쪽부터
    attack_list = list()
    for archer_x in c:
        point_list = list()
        for i in range(N-1, -1, -1):
            for j in range(M):
                if matrix[i][j] == 1 :
                    distance = possible_attack(matrix, i, j, archer_x)
                    if distance > 0 :
                        point_list.append(Point(i,j,distance))

        if len(point_list) >= 1:
            sorted_points = sorted(point_list, key=custom_sort)
            if [sorted_points[0].y, sorted_points[0].x] not in attack_list :
                attack_list.append([sorted_points[0].y, sorted_points[0].x])
    
    cnt = 0
    for i in range(len(attack_list)):
        matrix[attack_list[i][0]][attack_list[i][1]] = 0
        cnt+=1

    return cnt 

def enemy_move(matrix):
    matrix.insert(0, [0]*M)
    matrix.pop()
    return

def game_over(matrix):
    for row in matrix:
        if 1 in row: return False
    return True

def simulation(matrix, c):
    remove_cnt = 0
    while not game_over(matrix):
        remove_cnt += attack(matrix, c)
        enemy_move(matrix)

    return remove_cnt

# N가지 조합 생성
def combination(A):

    def generate(c):
        global max_remove
        if len(c) == A:
            copy = [arr[:] for arr in world]
            max_remove = max(max_remove, simulation(copy, c))
            return

        start = c[-1] + 1 if c else 0
        for i in range(start, M):
            c.append(i)
            generate(c)
            c.pop()
                
    generate([])

if __name__ == '__main__':

    combination(3)
    print(max_remove)