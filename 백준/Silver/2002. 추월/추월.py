import sys
input = sys.stdin.readline

n = int(input())

in_car = dict()
for i in range(n):
    in_car[input()] = i

passed = [False] * n
answer = 0

for _ in range(n):
    car = input()
    idx = in_car[car] # 지금 들어온 차가 이전에 몇 번째 들어왔는지?

    for i in range(idx):
        if not passed[i]: # 앞에 있어야 할 차가 없으면
            answer += 1 # 지금 들어온 차가 추월한거
            break
    passed[idx] = True

print(answer, end='')
