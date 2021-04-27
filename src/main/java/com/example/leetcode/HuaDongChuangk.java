package com.example.leetcode;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class HuaDongChuangk {
    public static int lengthOfLongestSubstring(String s) {
        Set hashSet = new HashSet<String>();
        int index = 0, len = 0, length = s.length();
        for (int i = 0; i < length; i++) {
            if (hashSet.size() != 0) {
                hashSet.remove(s.charAt(i - 1));
            }
            while (index < length && !hashSet.contains(s.charAt(index))) {
                hashSet.add(s.charAt(index));
                index++;
            }
            len = Math.max(len, index - i);
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}

/**
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，
 * 都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 */

/**
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 */
class HuaDongChuangk2{
    public static int findLengthlxdz(int[] nums){
        int len=0,index=0,result=0;
        for(int i=0;i<nums.length;i++){
            len=i-index+1;
            if ((i<nums.length-1&&nums[i+1]<=nums[i])){
                index=i+1;
            }
            result=Math.max(result,len);
        }
        return result;
    }

    public static void main(String[] args) {
       /* int[] nums={1,3,5,4,7,6,7,8,9,10,9,10,11,13,14,15,16,17};*/
        int[] nums={1,3,5,6,7};
        int lengthlxdz = findLengthlxdz(nums);
        System.out.println(lengthlxdz);
    }
}