import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {

    // O(n) по времени, O(n) по памяти
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // значение -> индекс
        for (int i = 0; i < nums.length; i++) {
            long complement = (long) target - nums[i];
            if (complement >= Integer.MIN_VALUE && complement <= Integer.MAX_VALUE
                    && seen.containsKey((int) complement)) {
                return new int[] { seen.get((int) complement), i };
            }
            seen.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 9))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 2, 4 }, 6))); // [1, 2]
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 3 }, 6))); // [0, 1]
    }
}
