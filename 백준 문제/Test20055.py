
input_list = list(map(int, input().split(' ')))
N = input_list[0]
K = input_list[1]
A = list(map(int,input().split(' ')))
robot_list = list()

def robot_down():
    if len(robot_list) > 0 and robot_list[0] == N-1:
        del robot_list[0]
    return

def rotation():
    last_to_first = A[-1]
    for i in range(2*N-1, 0, -1):
        A[i] = A[i-1]
    A[0] = last_to_first

    for i in range(len(robot_list)):
        robot_list[i] += 1

    robot_down()
    return

def move_robot():
    for i in range(len(robot_list)):
        if A[robot_list[i]+1] > 0 and robot_list[i]+1 not in robot_list:
            robot_list[i] += 1
            A[robot_list[i]] -= 1

    robot_down()
    return 


def create_robot():
    if 0 not in robot_list and A[0] > 0:
        robot_list.append(0)
        A[0] -= 1

    return 

def if_exit():
    exit = 0
    for i in range(2*N):
        if A[i] <= 0:
            exit += 1
        if exit >= K:
            return False
    return True

if __name__ == "__main__":
    step = 0

    while if_exit():
        rotation()
        move_robot()
        create_robot()
        step+=1

    print(step)