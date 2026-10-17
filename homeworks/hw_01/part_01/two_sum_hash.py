from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int):
        #создадим словарь, в котором ключ и индекс каждого элемента из массива nums
        db = {}    
        for i, num in enumerate(nums):
            #основная фишка - это вычитание из таргета для поиска пары
            pair = target - num
            #нельзя на самого себя
            if pair in db and db[pair] != i:
                return [i,db[pair]]
            #кладем в базу потенциальных пар
            db[num] = i
            

            
nums = [2,7,11,15]
target = 9

print(Solution().twoSum(nums,target))
#алгоритмическая сложность данного решения O(n), так как мы проходим 
# по массиву nums только один раз, 
# а операции со словарем имеют сложность O(1) в среднем

# на leetcode - это решение Runtime: 17ms, что подтверждает эффективность 
# использования хэш-таблицы для поиска пар.

# мое решение на LeetCode
# https://leetcode.com/problems/two-sum/submissions/2174382048