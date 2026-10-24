import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Solution {
    public boolean isValid(String s) {
        // создам стек для хранения открывающих скобок
        Deque<Character> stack = new ArrayDeque<>();
        // создам словарь для соответствия открывающих и закрывающих скобок
        Map<Character, Character> brackets = Map.of(')', '(', ']', '[', '}', '{');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!brackets.containsKey(c)) {
                // если текущий символ является открывающей скобкой,
                // добавляем его в стек
                stack.push(c);
            } else {
                // если текущий символ является закрывающей скобкой,
                // проверяем, соответствует ли последняя открывающая скобка в стеке
                if (!stack.isEmpty() && stack.peek().equals(brackets.get(c))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // проверки на корректность работы функции isValid
        Solution solution = new Solution();
        assert solution.isValid("([])") : "Валидно";
        assert !solution.isValid("([)]") : "Невалидно";
        assert solution.isValid("((()))") : "Валидно";
    }
}

// Вывод:
// Время - O(n): один проход по строке.
// Дополнительная память - O(n) для стека.
// Стек подходит, потому что закрывающая скобка относится к последней ещё не закрытой открывающей.
// Это принцип LIFO: кладём на вершину стека и забираем с вершины.
