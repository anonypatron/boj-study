import sys
input = sys.stdin.readline

n = int(input())
text = input().strip()
text_len = len(text)

max_len = left = right = 0
alphabet_cnt = dict()

while right < text_len:
    char = text[right]
    if char not in alphabet_cnt:
        alphabet_cnt[char] = 1
    else:
        alphabet_cnt[char] += 1
    
    while len(alphabet_cnt) > n:
        alphabet_cnt[text[left]] -= 1
        if alphabet_cnt[text[left]] == 0:
            del alphabet_cnt[text[left]]
        left += 1

    max_len = max(max_len, right - left + 1)
    right += 1

print(max_len, end='')
