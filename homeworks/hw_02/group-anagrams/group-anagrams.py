from typing import List
from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        #это решение через HashMap, где ключом является отсортированная строка, 
        # а значением - список анаграмм
        db = defaultdict(list)

        for s in strs:
            #сортируем строку и используем её как ключ в словаре
            key = tuple(sorted(s)) 
            #основная фишка в сортировке строки, 
            #так как все анаграммы будут иметь одинаковый отсортированный вид
            db[key].append(s)

        return list(db.values())

strs = ["eat","tea","tan","ate","nat","bat"]   

result = Solution().groupAnagrams(strs)
expected = [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]

assert sorted(sorted(group) for group in result) == sorted(sorted(group) for group in expected)
