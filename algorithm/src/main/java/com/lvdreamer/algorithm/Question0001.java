package com.lvdreamer.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0001 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Question0001().twoSum(new int[]{11, 2, 15, 7}, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int tmpNum = target - nums[i];
            if (numMap.containsKey(tmpNum)) {
                return new int[]{numMap.get(tmpNum), i};
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No result");
    }
}
