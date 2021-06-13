package com.example.jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class HuaDongChuangKouMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int j = 0;
        int max = -Integer.MIN_VALUE;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            boolean flag=false;
            if (len > 0 && j < nums.length) {
                len=0;
            }
            j=i;
            while (len < k && j < nums.length) {
                max = Math.max(max, nums[j]);
                len++;
                j++;
                flag=true;
            }
            if (flag){
                list.add(max);
                max = -Integer.MIN_VALUE;
            }
            if (j == nums.length){
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
      if (k==0||nums.length==0){
          return new int[0];
      }
      Deque<Integer> deque=new LinkedList<>();
      int[] res=new int[nums.length-k+1];
      for (int i=0;i<k;i++){
          while (deque.size()>0&&deque.peekLast()<nums[i]){
                deque.removeLast();
          }
          deque.addLast(nums[i]);
      }
      res[0]=deque.peekFirst();
      for (int i=k;i<nums.length;i++){
          if(deque.peekFirst() == nums[i - k]){
              deque.removeFirst();
          }
          while (deque.size()>0&&deque.peekLast()<nums[i]){
              deque.removeLast();
          }
          deque.addLast(nums[i]);
          res[i-k+1]=deque.peekFirst();
      }
      return res;
    }
}
