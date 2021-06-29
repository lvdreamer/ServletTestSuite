package com.lvdreamer.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * <p>
 * <p>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Question0046 {

    public static void main(String[] args) {
        List<List<Integer>> res = new Question0046().permute(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println(res);

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int visit[] = new int[nums.length];
        permute(nums, result, new ArrayList<Integer>(), visit);
        return result;
    }

    public void permute(int[] nums, List<List<Integer>> result, ArrayList<Integer> tmpArray, int visit[]) {
        if (tmpArray.size() == nums.length) {
            result.add(new ArrayList<>(tmpArray));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] == 1) continue;
            visit[i] = 1;
            tmpArray.add(nums[i]);
            permute(nums, result, tmpArray, visit);
            visit[i] = 0;
            tmpArray.remove(tmpArray.size() - 1);
        }
    }

}
