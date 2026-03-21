import sys
input = sys.stdin.readline

# 각 도시에 대해 다른 도시까지의 최솟값을 갱신한다.
# ex) 1번 도시를 잡고 2 ~ n까지의 도시까지 걸리는 최소 비용을 저장
# 단, 중간에 다른 도시를 거치는 경우도 생각하기
# 1번에서 출발해 2번까지 가는 최솟값 = 1번 -> 3번 -> 2번, 1번 -> 4번 -> 2번 ... 중 최솟값
# 즉 i부터 j까지 가는 데 최솟값은 min(i -> k + k -> j, i -> j)
n = int(input())
m = int(input())

MAX_WEIGHT = 1e9 # 1 * 10^9
matrix = [[MAX_WEIGHT for _ in range(n + 1)] for _ in range(n + 1)]

for _ in range(m):
    start, end, weight = map(int, input().split())
    matrix[start][end] = min(matrix[start][end], weight)

for i in range(1, n + 1):
    matrix[i][i] = 0

# result[시작점][끝점] = weight의 최솟값
for k in range(1, n + 1): # 중간지점
    for i in range(1, n + 1): # 시작점
        for j in range(1, n + 1): # 도착 지점
            matrix[i][j] = min(matrix[i][k] + matrix[k][j], matrix[i][j])

output = []
for i in range(1, n + 1):
    row = []
    for j in range(1, n + 1):
        if matrix[i][j] == MAX_WEIGHT:
            row.append('0')
        else:
            row.append(str(matrix[i][j]))
    output.append(' '.join(row))
    
print('\n'.join(output))
