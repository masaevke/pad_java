import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // это решение через HashMap, где ключом является отсортированная строка,
        // а значением — список анаграмм
        Map<String, List<String>> db = new HashMap<>();

        for (String s : strs) {
            // сортируем строку и используем её как ключ в словаре
            char[] letters = s.toCharArray();
            Arrays.sort(letters);
            String key = new String(letters);
            db.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(db.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = new Solution().groupAnagrams(strs);
        List<List<String>> expected = List.of(
            List.of("ate", "eat", "tea"),
            List.of("bat"),
            List.of("nat", "tan")
        );

        // сортируем слова внутри групп и группы по первому слову для сравнения
        result.forEach(Collections::sort);
        result.sort(Comparator.comparing(group -> group.get(0)));

        assert result.equals(expected) : "Неверная группировка: " + result;
        System.out.println(result);
    }
}
