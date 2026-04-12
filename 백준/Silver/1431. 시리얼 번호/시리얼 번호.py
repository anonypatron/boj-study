import sys
input = sys.stdin.readline

n = int(input())
arr = []

for _ in range(n):
    arr.append(input())

for i in range(n - 1):
    for j in range(i + 1, n):
        left = arr[i]
        right = arr[j]

        if len(left) > len(right):
            arr[i] = right
            arr[j] = left
        elif len(left) == len(right):
            sum_a = sum_b = 0

            for x, y in zip(left, right):
                if x.isdigit():
                    sum_a += int(x)
                if y.isdigit():
                    sum_b += int(y)
            
            if sum_a > sum_b:
                arr[i] = right
                arr[j] = left
            elif sum_a == sum_b:
                if left > right:
                    arr[i] = right
                    arr[j] = left

for i in range(n):
    print(arr[i], end='')