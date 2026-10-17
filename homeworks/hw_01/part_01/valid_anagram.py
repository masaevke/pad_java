from collections import Counter

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        s_cnt = Counter(s)
        t_cnt = Counter(t)
        for c, cnt in s_cnt.items():
            if t_cnt[c] != cnt:
                return False
        for c, cnt in t_cnt.items():
            if s_cnt[c] != cnt:
                return False
        return True

            
s = "anagram"
t = "nagaram"

print(Solution().isAnagram(s, t))