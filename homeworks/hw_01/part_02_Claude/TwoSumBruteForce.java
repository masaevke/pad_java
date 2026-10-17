import java.util.Arrays;

public class TwoSumBruteForce {

    // O(n^2) по времени, O(1) по памяти
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 9))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 2, 4 }, 6))); // [1, 2]
        System.out.println(Arrays.toString(twoSum(new int[] { 3, 3 }, 6))); // [0, 1]
    }
}
